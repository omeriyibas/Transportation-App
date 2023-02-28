import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class KargoData {
    private static KargoData instance = new KargoData();

    private ObservableList<Kargo> kargoListesi;

    public ObservableList<Kargo> getKargoListesi(){
        return kargoListesi;
    }

    private KargoData(){}

    public static KargoData getInstance() {
        return instance;
    }

    public void dosyaYaz(String dosyaAdi) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("sheet");
        Iterator<Kargo> iterator = kargoListesi.iterator();
        int rowIndex=0;
        while (iterator.hasNext()){
            Kargo kargo = iterator.next();
            Row row= sheet.createRow(rowIndex++);
            Cell cell0=row.createCell(0);
            cell0.setCellValue(kargo.getAdSoyadi());
            Cell cell1=row.createCell(1);
            cell1.setCellValue(kargo.getAdres());
            Cell cell3=row.createCell(2);
            cell3.setCellValue(kargo.getTakipNo());
        }

        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        workbook.write(fos);
        fos.close();
        System.out.println(dosyaAdi + "dosyasi oluşturuldu");
    }

    public void dosyaOku(String dosyaAdi) throws IOException {
        kargoListesi = FXCollections.observableArrayList();

        FileInputStream fis = new FileInputStream(dosyaAdi);
        DataFormatter formatter=new DataFormatter();
        Workbook workbook=new XSSFWorkbook(fis);
        Sheet sheet= workbook.getSheetAt(0);
        Iterator<Row> rowIterator=sheet.iterator();
        while (rowIterator.hasNext()){
            String adSoyad="";
            String adres="";
            String kargoNo="";
            Row row=rowIterator.next();
            Iterator<Cell> cellIterator=row.cellIterator();
            while (cellIterator.hasNext()){
                cellIterator.next();
                adSoyad=formatter.formatCellValue(row.getCell(0));
                adres=formatter.formatCellValue(row.getCell(1));
                kargoNo=formatter.formatCellValue(row.getCell(2));
            }
            Kargo kargo = new Kargo(adSoyad,adres,kargoNo);
            kargoListesi.add(kargo);
        }
        fis.close();
    }

    public void kargoKaydiEkle(Kargo kargo){
        kargoListesi.add(kargo);
    }

    public void kargoKaydiSil(Kargo kargo){
        kargoListesi.remove(kargo);
    }

}
