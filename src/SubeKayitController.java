import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

import java.util.Random;

public class SubeKayitController {

    @FXML
    private JFXTextField il_tf;

    @FXML
    private JFXTextField kapasite_tf;

    @FXML
    private JFXTextField koordinat_tf;

    public Sube yeniSube(){
        String il=il_tf.getText();
        /*double kapasite=Double.valueOf(kapasite_tf.getText());
        double koordinat=Double.valueOf(koordinat_tf.getText());*/

        double kapasite=Double.parseDouble(kapasite_tf.getText());
        double koordinat=Double.parseDouble(koordinat_tf.getText());


        Sube sube =new Sube(il,koordinat,kapasite);
        return sube;
    }

}
