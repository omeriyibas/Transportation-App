import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GonderiController {
    @FXML
    private JFXTextField adSoyad_tf;

    @FXML
    private JFXTextArea adres_tf;

    public Kargo yeniGonderi(){
        String adSoyad=adSoyad_tf.getText();
        String adres=adres_tf.getText();
        Random random=new Random();
        String kargoNo= String.format("%04d",random.nextInt(10000));
        Kargo kargo =new Kargo(adSoyad,adres,kargoNo);
        return kargo;
    }

    public void visibility(String obje,Boolean visible){
        if (visible==false &&obje.equals("adSoyad")){
            adSoyad_tf.setVisible(false);
        }
    }


    public Kargo gonderiGuncelle(String adSoyad,String kargoNo){
        String adres=adres_tf.getText();
        Kargo kargo = new Kargo(adSoyad,adres,kargoNo);
        return kargo;
    }

}
