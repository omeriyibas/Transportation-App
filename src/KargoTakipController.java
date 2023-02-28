import com.jfoenix.controls.JFXTextField;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class KargoTakipController implements Initializable {

    @FXML
    private AnchorPane sayfa;

    @FXML
    private JFXTextField kargo_tf;

    @FXML
    private Label gorevliAdi_lb;

    @FXML
    private Label gonderiDurumu_lb;


    public void ara(ActionEvent event) {

        List<KargoDurumu> kargoDurumuList= KargoDurumuData.getInstance().getKargoDurumuListesi();

        String arananKargoNo=kargo_tf.getText().trim();
        String kargoGorevlisi="";
        String kargoDurumBilgisi="";
        Alert alert=null;

        for (KargoDurumu kargoDurumu:kargoDurumuList) {
            if(kargoDurumu.getKargoNo().equals(arananKargoNo)){
                kargoGorevlisi=kargoDurumu.getKargoGorevlisi();
                kargoDurumBilgisi=kargoDurumu.getKargoDurumBilgisi();
            }
            else {
                alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Hata");
                alert.setHeaderText("Hatalı Giriş");
                alert.setContentText("Kargo Takip Numarası Eksik Yazılmış Yada Bulunamadı");
                alert.showAndWait();
            }
        }

        gorevliAdi_lb.setText(kargoGorevlisi);
        gonderiDurumu_lb.setText(kargoDurumBilgisi);

        System.out.println(kargoGorevlisi);
        System.out.println(kargoDurumBilgisi);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            KargoDurumuData.getInstance().dosyaOku("kargoDurum.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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
