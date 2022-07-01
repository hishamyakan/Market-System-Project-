import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class serverFallControler implements Initializable {

    @FXML
    private Label LABEL;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        PauseTransition pt=new PauseTransition();
        pt.setDuration(Duration.seconds(5));
        pt.setOnFinished(e->{
            LABEL.getScene().getWindow().hide();
        });
        pt.play();
    }

}