import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class KargoIstekController {

    @FXML
    private AnchorPane sayfa;

    @FXML
    private JFXTextArea adres_tf;

    @FXML
    private JFXTextField adSoyad_tf;

    @FXML
    private ListView<Kargo> lv;


    public void bilgleriKaydet(ActionEvent event) throws IOException {
        String adres = adres_tf.getText();
        String adSoyad=adSoyad_tf.getText();
        Random random = new Random();
        String kargoNo= String.format("%04d",random.nextInt(10000));

        //KargoData.getInstance().dosyaOku("kargoKaydi.xlsx");
        Kargo kargo=new Kargo(adSoyad,adres,kargoNo);
        KargoData.getInstance().kargoKaydiEkle(kargo);
        //KargoData.getInstance().dosyaYaz("kargoKaydi.xlsx");
    }

    public void geriDon(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Anasayfa.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) sayfa.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
