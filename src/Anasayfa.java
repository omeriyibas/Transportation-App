import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class Anasayfa extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        IlData.getInstance().dosyaOku("il.xlsx");
        KargoData.getInstance().dosyaOku("kargoKaydi.xlsx");
        KargoDurumuData.getInstance().dosyaOku("kargoDurum.xlsx");
        SubeData.getInstance().dosyaOku("sube.xlsx");

        Parent root = FXMLLoader.load(getClass().getResource("Anasayfa.fxml"));
        stage.setTitle("Anasayfa");
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(null);
        alert.setTitle(null);
        alert.setContentText("Kaydetmek istermisiniz=");
        Optional<ButtonType> result=alert.showAndWait();
        if (result.get()==ButtonType.OK){
            IlData.getInstance().dosyaYaz("il.xlsx");
            KargoData.getInstance().dosyaYaz("kargoKaydi.xlsx");
            KargoDurumuData.getInstance().dosyaYaz("kargoDurum.xlsx");
            SubeData.getInstance().dosyaYaz("sube.xlsx");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
