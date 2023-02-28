import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SubeKayitSayfasiController implements Initializable {


    @FXML
    private AnchorPane sayfa;

    @FXML
    private ListView<Sube> lv;

    @FXML
    private JFXTextField arama_tf;

    public void ara(ActionEvent event) {
        String aranan=arama_tf.getText();
        lv.setItems(filterList(SubeData.getInstance().getsubeListesi(),aranan));
    }

    public void sil(ActionEvent event) {
        Sube secilenSube=lv.getSelectionModel().getSelectedItem();
        SubeData.getInstance().subeKaydiSil(secilenSube);
    }

    public void ekle(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sayfa.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("SubeKayit.fxml"));

        dialog.setTitle(null);
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        SubeKayitController subeKaydi=fxmlLoader.getController();

        Optional<ButtonType> sonuc=dialog.showAndWait();
        if (sonuc.get()==ButtonType.APPLY){
            Sube sube=subeKaydi.yeniSube();
            SubeData.getInstance().getsubeListesi().add(sube);
        }
        lv.setItems(SubeData.getInstance().getsubeListesi());

    }

    public void bilgi(ActionEvent event) {
        Sube secilenSube=lv.getSelectionModel().getSelectedItem();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText("Şube kapasitesi: " + secilenSube.getSubeKapasitesi());
        alert.showAndWait();
    }

    public void geriDon(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminSayfasi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) sayfa.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean searcFindOrders(Sube sube,String searchText){
        String subeKapasitesi=String.valueOf(sube.getSubeKapasitesi());
        String ilKoordinati=String.valueOf(sube.getSubeKapasitesi());
        return (sube.getIlAdi().contains(searchText.toLowerCase())) ||
                (subeKapasitesi.contains(searchText.toLowerCase())) ||
                (ilKoordinati.contains(searchText.toLowerCase()));
    }

    private ObservableList<Sube> filterList(List<Sube> list, String searchText){
        List<Sube> filteredList= new ArrayList<>();
        for (Sube sube : list){
            if(searcFindOrders(sube,searchText)){
                filteredList.add(sube);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lv.setItems(SubeData.getInstance().getsubeListesi());
        if(!lv.getItems().isEmpty()){
            lv.getSelectionModel().select(0);
        }
    }

}
