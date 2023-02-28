import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.*;

public class GonderiKaydiSayfasiController implements Initializable {
    @FXML
    private ListView<Kargo> lv;


    @FXML
    private JFXTextField arama_tf;

    @FXML
    private AnchorPane sayfa;

    @FXML
    public void ara(ActionEvent event) {
        String aranan=arama_tf.getText();
        lv.setItems(filterList(KargoData.getInstance().getKargoListesi(),aranan));
    }

    @FXML
    public void sil(ActionEvent event) {
        Kargo secilenKargo=lv.getSelectionModel().getSelectedItem();
        KargoData.getInstance().kargoKaydiSil(secilenKargo);
    }
    @FXML
    public void ekle(ActionEvent event) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sayfa.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Gonderi.fxml"));

        dialog.setTitle(null);
        dialog.getDialogPane().setContent(fxmlLoader.load());

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        GonderiController gonderi=fxmlLoader.getController();

        Optional<ButtonType> sonuc=dialog.showAndWait();
        if (sonuc.get()==ButtonType.APPLY){
            Kargo kargo=gonderi.yeniGonderi();
            KargoData.getInstance().getKargoListesi().add(kargo);
        }
        lv.setItems(KargoData.getInstance().getKargoListesi());

    }
    @FXML
    public void guncelle(ActionEvent event) throws IOException {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sayfa.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("Gonderi.fxml"));

        dialog.setTitle(null);
        dialog.getDialogPane().setContent(fxmlLoader.load());

        GonderiController gonderi=fxmlLoader.getController();
        gonderi.visibility("adSoyad",false);

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> sonuc=dialog.showAndWait();
        if (sonuc.get()==ButtonType.APPLY){

            String adSoyad=lv.getSelectionModel().getSelectedItem().getAdSoyadi();
            String kargoNo=lv.getSelectionModel().getSelectedItem().getTakipNo();

            Kargo secilenKargo=lv.getSelectionModel().getSelectedItem();
            KargoData.getInstance().kargoKaydiSil(secilenKargo);

            Kargo kargo=gonderi.gonderiGuncelle(adSoyad,kargoNo);
            KargoData.getInstance().getKargoListesi().add(kargo);
        }
        lv.setItems(KargoData.getInstance().getKargoListesi());

    }



    private boolean searcFindOrders(Kargo kargo,String searchText){
        return (kargo.getTakipNo().contains(searchText.toLowerCase())) ||
                (kargo.getAdres().contains(searchText.toLowerCase())) ||
                (kargo.getAdSoyadi().contains(searchText.toLowerCase()));
    }

    private ObservableList<Kargo> filterList(List<Kargo> list,String searchText){
        List<Kargo> filteredList= new ArrayList<>();
        for (Kargo kargo : list){
            if(searcFindOrders(kargo,searchText)){
                filteredList.add(kargo);
            }
        }
        return FXCollections.observableList(filteredList);
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lv.setItems(KargoData.getInstance().getKargoListesi());
        if(!lv.getItems().isEmpty()){
            lv.getSelectionModel().select(0);
        }
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
}
