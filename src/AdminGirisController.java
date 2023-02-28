import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class AdminGirisController {

    @FXML
    private AnchorPane AdminGiris;

    @FXML
    private JFXTextField kullnaiciAdi_tf;

    @FXML
    private JFXTextField sifre_tf;

    public void sayfayaGit(String sayfaAdresi) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sayfaAdresi));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) AdminGiris.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    int sayac=3;
    public void GirisYap(ActionEvent event) throws IOException {
        String kullaniciAdi=kullnaiciAdi_tf.getText();
        String sifre= sifre_tf.getText();
        Alert alert =null;
        if (kullaniciAdi.equals("admin")&&sifre.equals("admin")){
            alert = new Alert(Alert.AlertType.INFORMATION);
            System.out.println("Giriş Başarılı");
            alert.setTitle(null);
            alert.setHeaderText(null);
            alert.setContentText("Giriş Başarılı!");
            Optional<ButtonType> sonuc=alert.showAndWait();
            if (sonuc.get()==ButtonType.OK){
                sayfayaGit("AdminSayfasi.fxml");
            }
        }
        else {
            alert= new Alert(Alert.AlertType.ERROR);
            if(sayac>0){
                alert.setTitle("Giriş Başarısız");
                alert.setHeaderText(null);
                alert.setContentText(sayac+" Deneme Hakkınız Kaldı!");
                Optional<ButtonType> sonuc=alert.showAndWait();
                if (sonuc.get()==ButtonType.OK){
                    sayac--;
                }
            }
            else{
                alert.setTitle("Giriş Başarısız");
                alert.setHeaderText("Hakkınız Doldu");
                alert.setContentText("Programdan Çıkılıyor");
                Optional<ButtonType> sonuc=alert.showAndWait();
                if (sonuc.get()==ButtonType.OK){
                    System.exit(0);
                }
            }
        }
    }

}
