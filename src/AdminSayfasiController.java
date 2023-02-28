import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminSayfasiController {

    @FXML
    private AnchorPane adminSayfasi;

    @FXML
    public void gonderiKayiGuncellemeSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("GonderiKaydiSayfasi.fxml");
    }

    @FXML
    public void gonderiDrumuGuncellemeSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("GonderiDurumuGuncelleme.fxml");
    }

    @FXML
    public void SubeKayitSayfasinaGit(ActionEvent event) throws IOException {
        sayfayaGit("SubeKayitSayfasi.fxml");

    }




    public void sayfayaGit(String sayfaAdresi) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(sayfaAdresi));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) adminSayfasi.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
