import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminSayfasi extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        IlData.getInstance().dosyaOku("il.xlsx");
        KargoData.getInstance().dosyaOku("kargoKaydi.xlsx");
        KargoDurumuData.getInstance().dosyaOku("kargoDurum.xlsx");
        SubeData.getInstance().dosyaOku("sube.xlsx");

        Parent root = FXMLLoader.load(getClass().getResource("AdminSayfasi.fxml"));
        stage.setTitle("Admin Sayfasi");
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();
    }
}
