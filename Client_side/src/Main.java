import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {

    private static Stage stg;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stg=primaryStage;
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load(getClass().getResource("loginForm.fxml"));
        primaryStage.setTitle("Market Place System");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        stg.setOnCloseRequest(e->{
            e.consume();
            boolean result=logout.display("Exit","Sure, you want to exit ?");
            if(result==true){
                stg.hide();
            }
        });
        stg.getIcons().add(new Image("assets/market.png"));
        boolean f=new File("src/betangena").mkdirs();
        boolean b=new File("src/betangena/Video Games").mkdirs();
        boolean a=new File("src/betangena/Book").mkdirs();
        boolean cc=new File("src/betangena/Furniture").mkdirs();
        boolean d=new File("src/betangena/Electronics").mkdirs();
        boolean e=new File("src/betangena/Fashion").mkdirs();
        File ff=new File("src/betangena/ayhaga.txt");
        publicData.path=ff.getAbsolutePath().replaceAll("\\\\ayhaga.txt","").replaceAll("\\\\","//");
    }

    public void changeScene(String fxml) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
