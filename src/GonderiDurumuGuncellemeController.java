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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class GonderiDurumuGuncellemeController implements Initializable {
    @FXML
    private AnchorPane sayfa;

    @FXML
    private ListView<KargoDurumu> lv;

    @FXML
    private JFXTextField arama_tf;

    @FXML
    private TextArea kargoBilgisi_txtarea;

    @FXML
    void ara(ActionEvent event) {
        String aranan=arama_tf.getText();
        lv.setItems(filterList(KargoDurumuData.getInstance().getKargoDurumuListesi(),aranan));
    }

    @FXML
    void guncelle(ActionEvent event) throws IOException {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(sayfa.getScene().getWindow());

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("GonderiDurumu.fxml"));

        dialog.setTitle(null);
        dialog.getDialogPane().setContent(fxmlLoader.load());

        GonderiDurumuController gonderiDurumu=fxmlLoader.getController();

        dialog.getDialogPane().getButtonTypes().add(ButtonType.APPLY);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> sonuc=dialog.showAndWait();

        if (sonuc.get()==ButtonType.APPLY){

            String adSoyad=lv.getSelectionModel().getSelectedItem().getKargoGorevlisi();
            String kargoNo=lv.getSelectionModel().getSelectedItem().getKargoNo();

            KargoDurumu secilenKargo=lv.getSelectionModel().getSelectedItem();
            KargoDurumuData.getInstance().kargoDurumuSil(secilenKargo);

            KargoDurumu kargoDurumu=gonderiDurumu.gonderiDurumuGuncelle(adSoyad,kargoNo);
            KargoDurumuData.getInstance().getKargoDurumuListesi().add(kargoDurumu);
            kargoBilgisi_txtarea.setText(lv.getSelectionModel().getSelectedItem().getKargoDurumBilgisi());
        }
        lv.setItems(KargoDurumuData.getInstance().getKargoDurumuListesi());


    }

    void DialogEkraniGoster(String dilogEkraniAdresi){

    }

    @FXML
    void sil(ActionEvent event) {
        KargoDurumu secilenKargoDurumu=lv.getSelectionModel().getSelectedItem();
        KargoDurumuData.getInstance().kargoDurumuSil(secilenKargoDurumu);
    }


    @FXML
    void geriDon(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminSayfasi.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) sayfa.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    private boolean searcFindOrders(KargoDurumu kargoDurumu,String searchText){
        return (kargoDurumu.getKargoNo().contains(searchText.toLowerCase())) ||
                (kargoDurumu.getKargoDurumBilgisi().contains(searchText.toLowerCase())) ||
                (kargoDurumu.getKargoGorevlisi().contains(searchText.toLowerCase()));
    }

    private ObservableList<KargoDurumu> filterList(List<KargoDurumu> list, String searchText){
        List<KargoDurumu> filteredList= new ArrayList<>();
        for (KargoDurumu kargoDurumu : list){
            if(searcFindOrders(kargoDurumu,searchText)){
                filteredList.add(kargoDurumu);
            }
        }
        return FXCollections.observableList(filteredList);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lv.setItems(KargoDurumuData.getInstance().getKargoDurumuListesi());
        kargoBilgisi_txtarea.setEditable(false);
        if(!lv.getItems().isEmpty()){
            lv.getSelectionModel().select(0);
        }
        kargoBilgisi_txtarea.setText(lv.getSelectionModel().getSelectedItem().getKargoDurumBilgisi());
    }


    public void kargoBilgisiGoster(MouseEvent mouseEvent) {
        kargoBilgisi_txtarea.setText(lv.getSelectionModel().getSelectedItem().getKargoDurumBilgisi());
        System.out.println("asd");
    }
}
