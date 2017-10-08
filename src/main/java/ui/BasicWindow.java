package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    protected JFXButton channel_Info = new JFXButton("Channel info");
    protected JFXButton compare_channels = new JFXButton("Compare channels");
    protected JFXButton sort_channels = new JFXButton("Sort by data");
    protected JFXButton media_info = new JFXButton("Media info");
    protected JFXButton compare_media = new JFXButton("Compare media");
    protected JFXButton sort_media = new JFXButton("Sort by media");
    protected Label timer;

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
        root.setStyle("-fx-background-color: #57657b;");
        center.setStyle("-fx-background-color: #e0e3e8;" +
                "-fx-border-color: #6f6f6f;");
        setElements();
        setTop();
        setLeft();
        root.setCenter(center);
    }

    protected void setElements(){
        String tfStyle = "-fx-background-color: white;" +
                "-fx-border-color: #c1cad7;" +
                "-fx-font-size: 10pt;";

        channelId_1.setPrefWidth(250);
        channelId_1.setPromptText("Channel ID#1");
        channelId_1.setLabelFloat(true);
        channelId_1.setStyle(tfStyle);
        channelId_1.setUnFocusColor(Color.TRANSPARENT);
        channelId_1.setFocusColor(Color.TRANSPARENT);
        channelId_1.setTranslateX(10);
        channelId_1.setTranslateY(20);

        channelId_2.setPrefWidth(250);
        channelId_2.setPromptText("Channel ID#2");
        channelId_2.setLabelFloat(true);
        channelId_2.setStyle(tfStyle);
        channelId_2.setUnFocusColor(Color.TRANSPARENT);
        channelId_2.setFocusColor(Color.TRANSPARENT);
        channelId_2.setTranslateX(10);
        channelId_2.setTranslateY(20);
    }

    private void setTop(){
        new Thread(() -> {
            String style = "-fx-text-fill: #d5d5dc;" +
                    "-fx-font-size: 14;" +
                    "-fx-font-weight: bold;";

            final Label projectName = new Label("YouTube Analytics 1.0");
            projectName.setStyle("-fx-text-fill: #cec2ff;" +
                    "-fx-font-size: 16pt;");

            toggle_button.setStyle(style);
            toggle_button.setPrefWidth(100);
            toggle_button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
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
                }
            });

            close.setStyle(style);
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
            String style =
                    "-fx-font-size: 12pt; -fx-text-fill: #fffbfb; -fx-font-weight: bold;";
            channel_Info.setGraphic(new Circle(5, Color.CORAL));
            channel_Info.setStyle(style);
            channel_Info.setTranslateX(10);

            compare_channels.setGraphic(new Circle(5, Color.GOLD));
            compare_channels.setStyle(style);
            compare_channels.setTranslateX(10);

            sort_channels.setGraphic(new Circle(5, Color.BLUEVIOLET));
            sort_channels.setStyle(style);
            sort_channels.setTranslateX(10);

            media_info.setGraphic(new Circle(5, Color.CRIMSON));
            media_info.setStyle(style);
            media_info.setTranslateX(10);

            compare_media.setGraphic(new Circle(5, Color.FLORALWHITE));
            compare_media.setStyle(style);
            compare_media.setTranslateX(10);

            sort_media.setGraphic(new Circle(5, Color.FORESTGREEN));
            sort_media.setStyle(style);
            sort_media.setTranslateX(10);

            ImageView timer_image = new ImageView("http://cistrome.org/TIMER/img/timer.png");
            timer_image.setFitWidth(40);
            timer_image.setPreserveRatio(true);
            timer = new Label("Timer: 0ms.");
            timer.setGraphic(timer_image);
            timer.setTranslateX(15);
            timer.setStyle("-fx-font-size: 12pt;" +
                    "-fx-text-fill: lightgray;");

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