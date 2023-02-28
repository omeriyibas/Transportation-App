import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.Random;

public class GonderiDurumuController {

    @FXML
    private JFXTextArea durum_tf;

    public KargoDurumu gonderiDurumuGuncelle(String adSoyad,String kargoNo){
        String durum=durum_tf.getText();
        KargoDurumu kargoDurumu = new KargoDurumu(adSoyad,durum,kargoNo);
        return kargoDurumu;
    }
}
