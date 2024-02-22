import java.util.Arrays;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

/**
 * Class that makes up my Jordle game.
 * @author Davis Guest
 * @version 1.0
 */
public class Jordle extends Application {
    private Scene gameScene;
    private WordPane[] wordPanelArray = new WordPane[6];
    private UserPanel userPanel;
    private String jordleWord;
    private int count = 0;
    private int numCorrect = 0;
    Label jordle;

    Random r = new Random();

    /**
     * Constructor for the Jordle class.
     */
    public Jordle() {
        //Generating random word
        int rItem = r.nextInt(Words.list.size());
        jordleWord = Words.list.get(rItem);
        System.out.println("Word of the day is: " + jordleWord);

        //Setting up BorderPane
        BorderPane bPane = new BorderPane();

        // -Border Pane CENTER
        VBox vBox = new VBox(5);
        vBox.setAlignment(Pos.CENTER);
        jordle = new Label("Jordle");
        jordle.setFont(new Font("Arial", 32));
        jordle.setAlignment(Pos.CENTER);

        vBox.getChildren().add(jordle);

        for (int i = 0; i < 6; i++) {
            wordPanelArray[i] = new WordPane();
            wordPanelArray[i].setAlignment(Pos.CENTER);
            vBox.getChildren().add(wordPanelArray[i].row);
        }
        bPane.setCenter(vBox);

        // -Border Pane BOTTOM
        userPanel = new UserPanel();
        bPane.setBottom(userPanel.bottomPanel);

        gameScene = new Scene(bPane);
    }

    /**
     * Inner class used to setup the the row of letters representing
     * an input word.
     */
    class WordPane extends HBox {
        private HBox row = new HBox(5);
        private Label[] wordColumns = new Label[5];

        /**
         * Constructor for the WordPane class.
         */
        WordPane() {
            for (int i = 0; i < 5; i++) {
                wordColumns[i] = new Label();
                wordColumns[i].setAlignment(Pos.CENTER);
                wordColumns[i].setMaxWidth(50);
                wordColumns[i].setMaxHeight(50);
                wordColumns[i].setMinWidth(50);
                wordColumns[i].setMinHeight(50);
                wordColumns[i].setFont(new Font("Arial", 25));
                wordColumns[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
                    new Insets(0))));
                wordColumns[i].setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, new BorderWidths(1))));
                row.getChildren().add(wordColumns[i]);
            }
            row.setAlignment(Pos.CENTER);
        }

        /**
         * Method that sets the text and respective color for each label.
         * @param charValue String representing the input character
         * @param pos int representing the index/position for the respective HBox
         * @param color Color representing the color of the label
         */
        public void setLabelText(String charValue, int pos, Color color) {
            this.wordColumns[pos].setText(charValue);
            this.wordColumns[pos].setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY,
                new Insets(0))));
        }

        /**
         * Method that clears the HBox when resetting.
         */
        public void clearWordHBox() {
            for (int i = 0; i < 5; i++) {
                wordColumns[i].setText("");
                wordColumns[i].setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY,
                    new Insets(0))));
            }
        }
    }

    /**
     * Inner class that is used to setup the user panel/ all
     * buttons, textfields, and labels used for the user to input or recieve information.
     */
    class UserPanel extends HBox {
        private HBox bottomPanel = new HBox(10);
        private Label text;
        private Button reset;
        private Button instructions;
        private TextField input;


        /**
         * Constructor for the UserPanel class.
         */
        UserPanel() {
            bottomPanel.setAlignment(Pos.CENTER);
            bottomPanel.setPrefWidth(400);
            bottomPanel.setPrefHeight(40);

            // TextField Setup
            input = new TextField();
            input.setPromptText("Enter a word...");
            input.requestFocus();
            input.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    numCorrect = 0;
                    String userInput = input.getText();
                    String[] userInputArray = userInput.split("");
                    ArrayList<String> jordleWordArray = new ArrayList<>(Arrays.asList(jordleWord.split("")));
                    ArrayList<Boolean> wordMatchList = new ArrayList<Boolean>();

                    boolean containsDigit = false;
                        if (userInput != null && !userInput.isEmpty()) {
                            for (char c : userInput.toCharArray()) {
                                if (Character.isDigit(c)) {
                                    containsDigit = Character.isDigit(c);
                                    break;
                                } else if (!Character.isLetter(c)) {
                                    containsDigit = !Character.isLetter(c);
                                    break;
                                }
                            }
                        }
                    if (!containsDigit) {
                        if (userInput.length() == 5) {
                            for (int i = 0; i < 5; i++) {
                                if (jordleWordArray.contains(userInputArray[i])) {
                                    if (jordleWordArray.get(i).equals(userInputArray[i])) {
                                        getActivePanel().setLabelText(userInputArray[i], i, Color.GREEN);
                                        wordMatchList.add(true);
                                        input.setText("");
                                        numCorrect++;
                                    } else {
                                        getActivePanel().setLabelText(userInputArray[i], i, Color.YELLOW);
                                        wordMatchList.add(false);
                                        input.setText("");
                                    }
                                } else {
                                    getActivePanel().setLabelText(userInputArray[i], i, Color.GRAY);
                                    wordMatchList.add(false);
                                    input.setText("");
                                }
                            }
                            input.setText("");
                            count++;
                            if (count == 6) {
                                text.setText("Game over. The word was \"" + jordleWord + "\"");
                                jordle.setTextFill(Color.RED);
                                input.setDisable(true);
                            } else if (numCorrect == 5) {
                                text.setText("Congratulations! You've guessed the word!");
                                jordle.setTextFill(Color.GREEN);
                                input.setDisable(true);
                            }
                        } else {
                            input.setText("");
                            Alert length = new Alert(Alert.AlertType.ERROR, "ERROR. Your"
                                + " input word is not 5 letters.");
                            length.showAndWait();
                        }

                    } else {
                        input.setText("");
                        Alert notLetter = new Alert(Alert.AlertType.ERROR, "ERROR. Your input word"
                            + " contains numbers or special characters.");
                        notLetter.showAndWait();
                    }
                }
            });

            // Text Setup
            text = new Label("Try guessing a word!");
            text.setFont(new Font("Arial", 15));

            // Instructions Button Setup
            instructions = new Button("Instructions");
            instructions.setOnAction((ActionEvent e) -> {
                Stage iStage = new Stage();
                iStage.setMinHeight(250.0);
                iStage.setMinWidth(402.0);
                iStage.setTitle("Instructions");
                TextArea iText = new TextArea(
                    "Welcome to Jordle!\n"
                    + "\nTry guessing some 5 letter words and press enter to lock in your guess!\n"
                    + "---------------------------------------------------------------------\n"
                    + "Rules:\n"
                    + "*Guess the word in six tries or less\n"
                    + "*A correct letter turns green\n"
                    + "*A correct letter in the wrong place turns yellow\n"
                    + "*An incorrect letter turns gray\n"
                    + "*Letters can be used more than once\n"
                    + "---------------------------------------------------------------------\n"
                    + "Have fun!"
                    );
                iText.setEditable(false);
                Scene iScene = new Scene(iText);
                iStage.setScene(iScene);
                iStage.show();
            });

            // Reset Button Setup
            reset = new Button("Restart");
            reset.setOnAction((ActionEvent e) -> {
                input.setDisable(false);
                clearAllPanels();
                count = 0;
                text.setText("Try guessing a word!");
                jordle.setTextFill(Color.BLACK);
                int rItem = r.nextInt(Words.list.size());
                jordleWord = Words.list.get(rItem);
                System.out.println("Word of the day is: " + jordleWord);
            });
            bottomPanel.getChildren().addAll(text, reset, instructions, input);
        }
    }
    /**
     * Method that gets the current selected HBox in VBox.
     * @return HBox representing the currently selected section of the VBox
     */
    public WordPane getActivePanel() {
        return this.wordPanelArray[count];
    }
    /**
     * Method that clears all of the squares/ each HBox of squares.
     */
    private void clearAllPanels() {
        for (int i = 0; i < count; i++) {
            wordPanelArray[i].clearWordHBox();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setMinHeight(500.0);
        primaryStage.setMinWidth(620.0);
        primaryStage.setTitle("Jordle");
        primaryStage.setScene(gameScene);
        primaryStage.show();
    }

    /**
     * Main method for the Jordle class.
     * @param args Command line arguements
     */
    public static void main(String[] args) {
        launch(args);
    }
}