import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class KargoDurumuData {
    private static KargoDurumuData instance = new KargoDurumuData();

    private ObservableList<KargoDurumu> kargoDurumuListesi;

    public ObservableList<KargoDurumu> getKargoDurumuListesi(){
        return kargoDurumuListesi;
    }

    private KargoDurumuData(){}

    public static KargoDurumuData getInstance() {
        return instance;
    }

    public void dosyaYaz(String dosyaAdi) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("sheet");
        Iterator<KargoDurumu> iterator = kargoDurumuListesi.iterator();
        int rowIndex=0;
        while (iterator.hasNext()){
            KargoDurumu kargoDurumu = iterator.next();
            Row row= sheet.createRow(rowIndex++);
            Cell cell0=row.createCell(0);
            cell0.setCellValue(kargoDurumu.getKargoGorevlisi());
            Cell cell1=row.createCell(1);
            cell1.setCellValue(kargoDurumu.getKargoDurumBilgisi());
            Cell cell3=row.createCell(2);
            cell3.setCellValue(kargoDurumu.getKargoNo());
        }

        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        workbook.write(fos);
        fos.close();
        System.out.println(dosyaAdi + "dosyasi oluşturuldu");
    }

    public void dosyaOku(String dosyaAdi) throws IOException {
        kargoDurumuListesi = FXCollections.observableArrayList();

        FileInputStream fis = new FileInputStream(dosyaAdi);
        DataFormatter formatter=new DataFormatter();
        Workbook workbook=new XSSFWorkbook(fis);
        Sheet sheet= workbook.getSheetAt(0);
        Iterator<Row> rowIterator=sheet.iterator();
        while (rowIterator.hasNext()){
            String kargoGorevlisi="";
            String kargoDurumBilgisi="";
            String kargoNo="";
            Row row=rowIterator.next();
            Iterator<Cell> cellIterator=row.cellIterator();
            while (cellIterator.hasNext()){
                cellIterator.next();
                kargoGorevlisi=formatter.formatCellValue(row.getCell(0));
                kargoDurumBilgisi=formatter.formatCellValue(row.getCell(1));
                kargoNo=formatter.formatCellValue(row.getCell(2));
            }
            KargoDurumu kargoDurumu = new KargoDurumu(kargoGorevlisi,kargoDurumBilgisi,kargoNo);
            kargoDurumuListesi.add(kargoDurumu);
        }
        fis.close();
    }

    public void kargoDurumuEkle(KargoDurumu kargoDurumu){
        kargoDurumuListesi.add(kargoDurumu);
    }

    public void kargoDurumuSil(KargoDurumu kargoDurumu){
        kargoDurumuListesi.remove(kargoDurumu);
    }

}
