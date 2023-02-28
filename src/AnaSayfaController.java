import com.jfoenix.controls.JFXButton;
import com.sun.xml.bind.XmlAccessorFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;

public class AnaSayfaController {

    @FXML
    private AnchorPane anasayfa;

    @FXML
    private JFXButton adminGiris_Button;

    @FXML
    public void fiyatHesaplamaSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("FiyatHesaplamaSayfasi.fxml");
    }

    @FXML
    public void AdminGirisSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("AdminGiris.fxml");
    }

    @FXML
    public void gonderiSuresiSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("GonderiSuresi.fxml");
    }

    @FXML
    public void kargoIstekSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("KargoIstek.fxml");
    }

    @FXML
    public void enyakinSubeSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("EnYakinSube.fxml");
    }
    @FXML
    public void KargoTakipSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("KargoTakip.fxml");
    }

    public void sayfayaGit(String sayfaAdresi) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sayfaAdresi));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) anasayfa.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
