import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GonderiDurumuGuncelleme extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        KargoDurumuData.getInstance().dosyaOku("kargoDurum.xlsx");

        Parent root = FXMLLoader.load(getClass().getResource("GonderiDurumuGuncelleme.fxml"));
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();
    }
}
