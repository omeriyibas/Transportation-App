import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GonderiSuresiController implements Initializable {

    @FXML
    private AnchorPane sayfa;

    @FXML
    private ComboBox<Il> cb1;

    @FXML
    private ComboBox<Il> cb2;

    @FXML
    private CheckBox express_check;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            IlData.getInstance().dosyaOku("il.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        ToggleGroup toggleGroup = new ToggleGroup();
        cb1.setItems(IlData.getInstance().getIlListesi());
        cb2.setItems(IlData.getInstance().getIlListesi());

    }

    @FXML
    public void hesapla(ActionEvent event) {
        Alert alert=null;
        double koordinat1 = cb1.getSelectionModel().getSelectedItem().getIlKoordinati();
        double koordinat2 = cb2.getSelectionModel().getSelectedItem().getIlKoordinati();
        int sure=0;
        if (express_check.isSelected()){
            sure= (int) (Math.abs(koordinat1-koordinat2))/2;
        }
        else {
            sure=(int)(Math.abs(koordinat1-koordinat2));
        }
        alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hesaplanan");
        alert.setHeaderText(null);
        alert.setContentText("Tahmini Gün Sayisi: " + sure);
        alert.showAndWait();
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
