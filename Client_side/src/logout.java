import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class logout {
        //Create variable
        static boolean answer;
        public static boolean display(String title,String message) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle(title);
            window.setMinWidth(250);
            Label label = new Label();
            label.setText(message);

            window.getIcons().add(new Image("assets/exit.png"));
            //Create two buttons
            Button yesButton = new Button("Yes");
            yesButton.setStyle("-fx-background-color:#41a646; -fx-text-fill:white; ");
            yesButton.setPrefWidth(70);
            Button noButton = new Button("No");
            noButton.setStyle("-fx-background-color:gray; -fx-text-fill:white; -fx-width:30px");
            noButton.setPrefWidth(70);

            //Clicking will set answer and close window
            yesButton.setOnAction(e -> {
                answer = true;
                window.close();
            });
            noButton.setOnAction(e -> {
                answer = false;
                window.close();
            });
            VBox la=new VBox(30);

            HBox layout = new HBox(30);
            Label empty=new Label("");
            Label empty2=new Label("");
            //Add buttons
            layout.getChildren().addAll(label, yesButton, noButton);
            layout.setAlignment(Pos.CENTER);
            la.setAlignment(Pos.CENTER);
            window.setResizable(false);
            la.getChildren().addAll(empty2,label,layout,empty);
            Scene scene = new Scene(la);
            scene.getStylesheets().add("style.css");
            window.setScene(scene);
            window.showAndWait();

            //Make sure to return answer
            return answer;
        }
}
