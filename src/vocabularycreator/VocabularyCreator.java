package vocabularycreator;
// @author Ľudovít "Luigi" Kováč
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class VocabularyCreator extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLVocabularyCreator.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("VocabularyCreator 1.0 (for Windows)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
