import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FiyatHesaplamaSayfasiController implements Initializable {

    @FXML
    private AnchorPane sayfa;

    @FXML
    private ListView<Il> lv;

    @FXML
    private ComboBox<Il> cb1;

    @FXML
    private ComboBox<Il> cb2;

    @FXML
    private CheckBox express_check;

    @FXML
    private JFXTextField agirlik_tf;

    @FXML
    private RadioButton koli_rb;

    @FXML
    private RadioButton evrak_rb;

    @FXML
    private JFXButton Button;

    double sonuc=0;
    Alert alert=null;
    @FXML
    public void hesapla(ActionEvent event) {
        double koordinat1 = cb1.getSelectionModel().getSelectedItem().getIlKoordinati();
        double koordinat2 = cb2.getSelectionModel().getSelectedItem().getIlKoordinati();
        double expressKatsayisi=1.5;
        int mesafe;
        mesafe=mesafeBelirle(koordinat1,koordinat2);
        Boolean isExpress=express_check.isSelected();
        Boolean isEvrak=evrak_rb.isSelected();
        Boolean isKoli=koli_rb.isSelected();
        int agirlik=Integer.parseInt(agirlik_tf.getText());

        if (agirlik>=5 && isEvrak){
            alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hata");
            alert.setHeaderText("Hatalı Giriş");
            alert.setContentText("Ağırlığı 5kg fazla olan kargolar evrak sayılmaz");
            alert.showAndWait();
        }
        else {
            if(agirlik<5 && isEvrak &&isExpress){
                sonuc=mesafe*expressKatsayisi;
            }
            else if(agirlik<5 && isEvrak){
                sonuc=mesafe;
            }
            if(isKoli &&isExpress){
                sonuc=agirlik*mesafe*expressKatsayisi;
            }
            else if(isKoli){
                sonuc=agirlik*mesafe;
            }
            alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Sonuç");
            alert.setHeaderText(null);
            alert.setContentText("Ödemeniz gereken tutar: " + sonuc);
            alert.showAndWait();
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            IlData.getInstance().dosyaOku("il.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        ToggleGroup toggleGroup = new ToggleGroup();
        evrak_rb.setToggleGroup(toggleGroup);
        koli_rb.setToggleGroup(toggleGroup);
        cb1.setItems(IlData.getInstance().getIlListesi());
        cb2.setItems(IlData.getInstance().getIlListesi());
    }

    public int mesafeBelirle(double koordinat1,double koordinat2) {
        return (int) Math.abs(koordinat1-koordinat2);
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
