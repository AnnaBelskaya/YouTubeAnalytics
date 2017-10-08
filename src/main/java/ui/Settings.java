package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Settings {
    protected Pane settings_pane;
    protected final String api_path = "files/api.txt";
    protected String cache_path = "files/cache.txt";

    protected boolean cache_is_on;
    protected boolean timing_is_on;
    protected String apiKey;

    private JFXButton clear_cache;
    private JFXCheckBox cache_on;
    private JFXCheckBox timing_on;
    private JFXTextField cache_input;
    private JFXTextField api_input;

    public Settings() {
        settings_pane = new Pane();
        setElements();
        setActions();
    }

    private void setElements(){
        apiKey = FileUtils.readLine(api_path);

        ImageView background = new ImageView("https://image.ibb.co/bZSgOw/settings.jpg");
        background.setFitWidth(900);
        background.setPreserveRatio(true);

        cache_on = new JFXCheckBox();
        cache_input = new JFXTextField();
        cache_input.setMinWidth(300);
        cache_input.setFocusColor(Color.BLACK);
        cache_input.setText(cache_path);

        VBox cacheBox = new VBox(cache_on, cache_input);
        cacheBox.setSpacing(50);
        cacheBox.setTranslateY(55);
        cacheBox.setTranslateX(280);

        clear_cache = new JFXButton("Clear cache");
        clear_cache.setTranslateY(50);
        clear_cache.setTranslateX(370);
        clear_cache.setId("clearButton");
        clear_cache.setOnMouseClicked(event -> FileUtils.write("",cache_path, false));

        timing_on = new JFXCheckBox();
        timing_on.setTranslateX(280);
        timing_on.setTranslateY(240);

        api_input = new JFXTextField();
        api_input.setMinWidth(300);
        api_input.setFocusColor(Color.BLACK);
        api_input.setTranslateX(280);
        api_input.setTranslateY(420);
        api_input.setText(apiKey);

        settings_pane.getChildren().addAll(background, cacheBox,
                timing_on, api_input, clear_cache);
    }

    private void setActions(){
        cache_on.selectedProperty().addListener((observable, oldValue, newValue) -> cache_is_on = newValue);
        timing_on.selectedProperty().addListener((observable, oldValue, newValue) -> timing_is_on = newValue);
    }

    protected void saveSettings() throws IOException {
        if (!api_input.getText().equals(apiKey)){
            apiKey = api_input.getText();
            FileUtils.write(apiKey, api_path, false);
        }

        if (!cache_input.getText().equals(cache_path)){
            cache_path = cache_input.getText();
        }
    }
}
