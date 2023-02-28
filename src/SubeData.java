import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class SubeData {

    private static SubeData instance = new SubeData();

    private ObservableList<Sube> subeListesi;

    public ObservableList<Sube> getsubeListesi(){
        return subeListesi;
    }


    private SubeData(){}

    public static SubeData getInstance() {
        return instance;
    }

    public void dosyaYaz(String dosyaAdi) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("sheet");
        Iterator<Sube> iterator = subeListesi.iterator();
        int rowIndex=0;
        while (iterator.hasNext()){
            Sube sube = iterator.next();
            Row row= sheet.createRow(rowIndex++);
            Cell cell0=row.createCell(0);
            cell0.setCellValue(sube.getIlAdi());
            Cell cell1=row.createCell(1);
            cell1.setCellValue(sube.getIlKoordinat());
            Cell cell3=row.createCell(2);
            cell3.setCellValue(sube.getSubeKapasitesi());
        }

        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        workbook.write(fos);
        fos.close();
        System.out.println(dosyaAdi + "dosyasi oluşturuldu");
    }

    public void dosyaOku(String dosyaAdi) throws IOException {
        subeListesi = FXCollections.observableArrayList();

        FileInputStream fis = new FileInputStream(dosyaAdi);
        DataFormatter formatter=new DataFormatter();
        Workbook workbook=new XSSFWorkbook(fis);
        Sheet sheet= workbook.getSheetAt(0);
        Iterator<Row> rowIterator=sheet.iterator();
        while (rowIterator.hasNext()){
            String ilAdi="";
            double ilKoordinati=0;
            double subeKapasitesi=0;
            Row row=rowIterator.next();
            Iterator<Cell> cellIterator=row.cellIterator();
            while (cellIterator.hasNext()){
                cellIterator.next();
                ilAdi=formatter.formatCellValue(row.getCell(0));
                ilKoordinati=row.getCell(1).getNumericCellValue();
                subeKapasitesi=row.getCell(2).getNumericCellValue();
            }
            Sube sube = new Sube(ilAdi,ilKoordinati,subeKapasitesi);
            subeListesi.add(sube);
        }
        fis.close();
    }

    public void subeKaydiEkle(Sube sube){
        subeListesi.add(sube);
    }

    public void subeKaydiSil(Sube sube){
        subeListesi.remove(sube);
    }

}

