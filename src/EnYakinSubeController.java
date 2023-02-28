import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EnYakinSubeController implements Initializable {

    @FXML
    private AnchorPane sayfa;

    @FXML
    private ComboBox<Il> cb;

    @FXML
    private Label label;


    @FXML
    public void islem(ActionEvent event) throws IOException {
        //SubeData.getInstance().dosyaOku("sube.xlsx");
        //IlData.getInstance().dosyaOku("il.xlsx");
        //System.out.println(IlData.getInstance().getIlListesi());
        List<Il> ilListesi = IlData.getInstance().getIlListesi();
        double sonuc=0;
        double sonuc2=0;
        String enYakin="";
        for (Il il: ilListesi) {
            //System.out.println(il.getIlKoordinati());
            double ilKoordinat= il.getIlKoordinati();
            int secilenilKoordinat= (int) cb.getSelectionModel().getSelectedItem().getIlKoordinati();
            sonuc=Math.abs(secilenilKoordinat-ilKoordinat);
            //System.out.println(sonuc);
            if(sonuc<=sonuc2){
                enYakin=il.getIlAdi();
            }
            sonuc2=sonuc;
        }
        label.setText("En yakın şube: " + enYakin);
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*try {
            SubeData.getInstance().dosyaOku("sube.xlsx");
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        cb.setItems(IlData.getInstance().getIlListesi());

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
