import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class IlData {
    private static IlData instace=new IlData();

    private ObservableList<Il> ilListesi;

    public ObservableList<Il> getIlListesi() {

        return ilListesi;
    }
    private IlData(){}

    public static IlData getInstance(){return instace;}

    public void dosyaYaz(String dosyaAdi) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("sheet");
        Iterator<Il> iterator = ilListesi.iterator();
        int rowIndex=0;
        while (iterator.hasNext()){
            Il il = iterator.next();
            Row row= sheet.createRow(rowIndex++);
            Cell cell0=row.createCell(0);
            cell0.setCellValue(il.getIlAdi());
            Cell cell1=row.createCell(1);
            cell1.setCellValue(il.getIlKoordinati());
        }

        FileOutputStream fos = new FileOutputStream(dosyaAdi);
        workbook.write(fos);
        fos.close();
        System.out.println(dosyaAdi + "dosyasi oluşturuldu");
    }

    public void dosyaOku(String dosyaAdi) throws IOException {
        ilListesi = FXCollections.observableArrayList();

        FileInputStream fis = new FileInputStream(dosyaAdi);
        DataFormatter formatter=new DataFormatter();
        Workbook workbook=new XSSFWorkbook(fis);
        Sheet sheet= workbook.getSheetAt(0);
        Iterator<Row> rowIterator=sheet.iterator();
        while (rowIterator.hasNext()){
            String ilAdi="";
            Double ilKoordinat=0.0;
            Row row=rowIterator.next();
            Iterator<Cell> cellIterator=row.cellIterator();
            while (cellIterator.hasNext()){
                cellIterator.next();
                ilAdi=formatter.formatCellValue(row.getCell(0));
                ilKoordinat=row.getCell(1).getNumericCellValue();
            }
            Il il = new Il(ilAdi,ilKoordinat);
            ilListesi.add(il);
        }
        fis.close();
    }

    public void ilEkle(Il il){
        ilListesi.add(il);
    }

}
