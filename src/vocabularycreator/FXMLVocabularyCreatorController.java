package vocabularycreator;
// @author Ľudovít "Luigi" Kováč

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FXMLVocabularyCreatorController implements Initializable {

    @FXML
    private Label label;

    FileInputStream input;
    FileWriter output = null;
    StringBuilder subtitles = new StringBuilder();
    String subtitlesString, subtitlesWithoutWhitespaces;
    String[] subtitlesWordsArray;
    ArrayList subtitlesUniqueValues = new ArrayList<>();
    int charCode;

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {

        Stage stage = new Stage(StageStyle.UNIFIED);

        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Subtitle file (*.srt)", "*.srt");
        fileChooser.getExtensionFilters().add(filter);
        File inputFile = fileChooser.showOpenDialog(stage);
        String inputFileName = inputFile.getName();
        String fileName = inputFileName.replaceAll(".srt", "");
        File outputFile = new File(System.getProperty("user.home") + "/desktop/WordList_" + fileName + ".txt");

        try {

            input = new FileInputStream(inputFile);
            output = new FileWriter(outputFile);

            while ((charCode = input.read()) != -1) {

                if (charCode == 10 /* NEW LINE */
                        || charCode == 32 /* SPACE */
                        || charCode == 33 /* ! */
                        || charCode == 34 /* " */
                        || charCode == 39 /* ' */
                        || charCode == 96 /* ` */
                        || charCode == 44 /* , */
                        || charCode == 46 /* . */
                        || charCode == 63 /* ? */
                        /* 65...90 = A...Z */
                        || charCode == 65 || charCode == 66 || charCode == 67 || charCode == 68 || charCode == 69
                        || charCode == 70 || charCode == 71 || charCode == 72 || charCode == 73 || charCode == 74
                        || charCode == 75 || charCode == 76 || charCode == 77 || charCode == 78 || charCode == 79
                        || charCode == 80 || charCode == 81 || charCode == 82 || charCode == 83 || charCode == 84
                        || charCode == 85 || charCode == 86 || charCode == 87 || charCode == 88 || charCode == 89
                        || charCode == 90
                        /* 97...122 = a...z */
                        || charCode == 97 || charCode == 98 || charCode == 99 || charCode == 100 || charCode == 101
                        || charCode == 102 || charCode == 103 || charCode == 104 || charCode == 105 || charCode == 106
                        || charCode == 107 || charCode == 108 || charCode == 109 || charCode == 110 || charCode == 111
                        || charCode == 112 || charCode == 113 || charCode == 114 || charCode == 115 || charCode == 116
                        || charCode == 117 || charCode == 118 || charCode == 119 || charCode == 120 || charCode == 121
                        || charCode == 122) {

                    if (charCode == 10 /* NEW LINE */ || charCode == 33 /* ! */ || charCode == 34 /* " */
                            || charCode == 44 /* , */ || charCode == 46 /* . */ || charCode == 63 /* ? */) {

                        subtitles.append((char) 32 /* SPACE */);

                    } else {

                        subtitles.append((char) charCode);

                    }

                }

            }

        } catch (FileNotFoundException exc) {

        } catch (IOException exc) {

        }

        subtitlesString = subtitles.toString();

        // remove more whitespaces and all letter to low
        subtitlesWithoutWhitespaces = subtitlesString.replaceAll("\\s+", " ").toLowerCase();

        // subtitlesWithoutWhitespaces to array
        subtitlesWordsArray = subtitlesWithoutWhitespaces.split(" ");

        for (int i = 0; i < subtitlesWordsArray.length; i++) {

            if (!subtitlesUniqueValues.contains(subtitlesWordsArray[i])) {
                subtitlesUniqueValues.add(subtitlesWordsArray[i]);
            }

        }

        // write unique words to .txt file
        for (Iterator iterator = subtitlesUniqueValues.iterator(); iterator.hasNext();) {
            Object next = iterator.next();

            String word = next.toString();

            output.write(word + System.getProperty("line.separator"));

        }

        label.setText("File was created on the Desktop");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
