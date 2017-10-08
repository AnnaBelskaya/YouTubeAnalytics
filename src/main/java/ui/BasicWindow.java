package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.io.IOException;

public class BasicWindow extends Settings {
    private JFXButton toggle_button = new JFXButton("Settings");
    private JFXButton close = new JFXButton("X");
    protected HBox infoBox;
    protected JFXButton channel_Info = new JFXButton("Channel info");
    protected JFXButton compare_channels = new JFXButton("Compare channels");
    protected JFXButton sort_channels = new JFXButton("Sort by data");
    protected JFXButton media_info = new JFXButton("Media info");
    protected JFXButton compare_media = new JFXButton("Compare media");
    protected JFXButton sort_media = new JFXButton("Sort by media");
    protected JFXButton add = new JFXButton("Add");
    protected JFXButton load = new JFXButton("Load from cache");
    protected Label timer, actionName, infoLabel;
    protected JFXTextField channelId_1 = new JFXTextField();
    protected JFXTextField channelId_2 = new JFXTextField();
    protected boolean isSettingsMode = false;
    protected HBox top = new HBox();
    protected VBox left;
    protected Pane center = new Pane();
    protected BorderPane root;
    protected Stage stage;

    public BasicWindow(BorderPane root, Stage stage) {
        super();
        this.root = root;
        this.stage = stage;
        center.setStyle("-fx-background-color: #e0e3e8;" +
                "-fx-border-color: #6f6f6f;");
        setElements();
        setTop();
        setLeft();
        root.setCenter(center);
    }

    protected void setElements(){
        actionName = new Label();
        actionName.setStyle("-fx-font-size: 14pt; -fx-font-weight: bold;");
        actionName.setTranslateX(25);
        actionName.setTranslateY(15);

        infoLabel = new Label("Enter channel ID into the text field.");
        infoLabel.setTranslateX(25);
        infoLabel.setTranslateY(70);
        infoLabel.setStyle("-fx-font-style: italic; -fx-text-fill: dimgray; -fx-font-size: 10pt;");

        channelId_1.setPromptText("Channel ID#1");
        channelId_1.setLabelFloat(true);
        channelId_1.setId("tf1");

        channelId_2.setPromptText("Channel ID#2");
        channelId_2.setLabelFloat(true);
        channelId_2.setId("tf1");

        add = new JFXButton("Add");
        add.setId("mainButton");
        add.setTranslateX(330);
        add.setTranslateY(100);

        load = new JFXButton("Load from cache");
        load.setId("mainButton");
        load.setTranslateX(480);
        load.setTranslateY(100);

        infoBox = new HBox();
        infoBox.setTranslateX(25);
        infoBox.setTranslateY(155);
        infoBox.setId("box");
    }

    private void setTop(){
        new Thread(() -> {
            final Label projectName = new Label("YouTube Analytics 1.0");
            projectName.setId("projectName");

            toggle_button.setId("rightButton");
            toggle_button.setPrefWidth(100);
            toggle_button.setOnMouseClicked(event -> {
                if (isSettingsMode){
                    try {
                        saveSettings();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    toggle_button.setText("Settings");
                    root.setLeft(left);
                    root.setCenter(center);
                } else {
                    toggle_button.setText("<- back");
                    root.setLeft(null);
                    root.setCenter(settings_pane);
                }
                isSettingsMode = !isSettingsMode;
            });

            close.setId("rightButton");
            close.setOnMouseClicked(event -> stage.close());

            top.setSpacing(250);
            top.setPrefSize(root.getWidth(), 70);
            top.setTranslateY(20);
            top.setTranslateX(10);

            Platform.runLater(() -> {
                top.getChildren().addAll(toggle_button, projectName, close);
                root.setTop(top);
            });
        }).start();
    }

    private void setLeft(){
        new Thread(() -> {
            channel_Info.setGraphic(new Circle(5, Color.CORAL));
            channel_Info.setId("leftButton");
            channel_Info.setTranslateX(10);

            compare_channels.setGraphic(new Circle(5, Color.GOLD));
            compare_channels.setId("leftButton");
            compare_channels.setTranslateX(10);

            sort_channels.setGraphic(new Circle(5, Color.BLUEVIOLET));
            sort_channels.setId("leftButton");
            sort_channels.setTranslateX(10);

            media_info.setGraphic(new Circle(5, Color.CRIMSON));
            media_info.setId("leftButton");
            media_info.setTranslateX(10);

            compare_media.setGraphic(new Circle(5, Color.FLORALWHITE));
            compare_media.setId("leftButton");
            compare_media.setTranslateX(10);

            sort_media.setGraphic(new Circle(5, Color.FORESTGREEN));
            sort_media.setId("leftButton");
            sort_media.setTranslateX(10);

            ImageView timer_image = new ImageView("http://cistrome.org/TIMER/img/timer.png");
            timer_image.setFitWidth(40);
            timer_image.setPreserveRatio(true);
            timer = new Label("Timer: 0ms.");
            timer.setGraphic(timer_image);
            timer.setTranslateX(15);
            timer.setId("timer");

            VBox buttonsBox = new VBox(channel_Info, compare_channels,
                    sort_channels, media_info, compare_media, sort_media);
            buttonsBox.setSpacing(20);
            buttonsBox.setTranslateY(20);

            left = new VBox(buttonsBox, timer);
            left.setSpacing(200);
            left.setPrefSize(250,580);
            left.setStyle("-fx-background-color: #3c4151;");

            Platform.runLater(() -> {
                root.setLeft(left);
            });
        }).start();
    }
}