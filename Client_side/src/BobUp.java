import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.scene.*;

import java.io.IOException;

public class BobUp {

    public static void display(String title, String filename, int hide_buttons,String icon_path) throws IOException {
        Parent root = FXMLLoader.load(BobUp.class.getResource(filename));
        Scene scene = new Scene(root);

        Stage window = new Stage();
        window.setResizable(false);
        window.setTitle(title);
        window.getIcons().add(new Image(icon_path));
        //Block events to other windows
        window.initModality(Modality.APPLICATION_MODAL);
        //remove close,max.min
        if(hide_buttons==1){
            window.initStyle(StageStyle.UNDECORATED);
        }
        //
        //window.initStyle(StageStyle.TRANSPARENT);
        //scene.setFill(Color.TRANSPARENT);

        //
        window.setScene(scene);
        window.showAndWait();
    }

}
