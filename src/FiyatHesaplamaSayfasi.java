import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FiyatHesaplamaSayfasi extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FiyatHesaplamaSayfasi.fxml"));
        stage.setTitle("Fiyat Hesaplama Sayfasi");
        stage.setScene(new Scene(root, 500, 500));
        stage.setResizable(false);
        stage.show();

    }
}
