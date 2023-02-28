import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GonderiKaydiSayfasi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        KargoData.getInstance().dosyaOku("kargoKaydi.xlsx");
        Parent root = FXMLLoader.load(getClass().getResource("GonderiKaydiSayfasi.fxml"));
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();
    }

}
