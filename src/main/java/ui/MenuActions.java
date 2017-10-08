package ui;

import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.ChannelInfo;
import utils.FileUtils;
import utils.TableViewUtils;

import java.util.List;

public class MenuActions extends InfoWindow {
    public MenuActions(BorderPane root, Stage stage) {
        super(root, stage);
        setActions();
    }

    protected void setActions(){
        channel_Info.setOnMouseClicked(event ->{
            channel_Info.setDisable(true);
            channel_info_action();
        });
        compare_channels.setOnMouseClicked(event -> {
            compare_channels.setDisable(true);
            compare_channels_action();
        });
        sort_channels.setOnMouseClicked(event -> {
            sort_channels.setDisable(true);
            sort_channels_action();
        });
        media_info.setOnMouseClicked(event -> {
            media_info.setDisable(true);
            media_info_action();
        });
        compare_media.setOnMouseClicked(event -> {
            compare_media.setDisable(true);
            compare_media_action();
        });
        sort_media.setOnMouseClicked(event -> {
            sort_media.setDisable(true);
            sort_media_action();
        });
    }

    protected void channel_info_action(){
        center.getChildren().clear();
        new Thread(() -> {
            actionName.setText("Channel Info");
            setDefault();

            infoBox.getChildren().clear();
            infoBox.getChildren().addAll(channel_box_1, separator, channel_description_1);

            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, show, id_input_1, infoBox));
            channel_Info.setDisable(false);
        }).start();
    }

    protected void compare_channels_action(){
        center.getChildren().clear();
        new Thread(() -> {
            setDefault();
            actionName.setText("Compare channels ");

            infoBox.getChildren().clear();
            infoBox.getChildren().addAll(channel_box_1, channel_box_2);
            infoBox.getChildren().add(1,separator);

            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, compare, id_input_1,
                    id_input_2, infoBox));
            compare_channels.setDisable(false);
        }).start();
    }

    protected void sort_channels_action(){
        center.getChildren().clear();

        new Thread(()->{
            actionName.setText("Sort channels");
            TableViewUtils.removeColumn(tableView);
            setDefault();
            tableView.getItems().clear();
            tableView.setItems(allChannels);
            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, id_input_1,
                    load, add, tableView));
            sort_channels.setDisable(false);
        }).start();

        add.setOnMouseClicked(event -> {
            if (id_input_1.getText().length() == 24)
                try {
                    sortByTitle(new ChannelInfo(id_input_1.getText(), apiKey),true);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
        });

        load.setOnMouseClicked(event -> {
            allChannels.clear();
            List<String> list = FileUtils.load(cache_path);
            for (int i = 0; i < list.size(); i++) {
                try {
                    ChannelInfo channelInfo = new ChannelInfo(list.get(i), apiKey);
                    sortByTitle(channelInfo, false);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void media_info_action() {
        channel_info_action();
        actionName.setText("Media resonance");
        channel_box_1.getChildren().addAll(commentsCount_1);
        media_info.setDisable(false);
    }

    protected void compare_media_action() {
        compare_channels_action();
        actionName.setText("Compare media resonance");
        channel_box_1.getChildren().add(commentsCount_1);
        channel_box_2.getChildren().add(commentsCount_2);
        compare_media.setDisable(false);
    }

    protected void sort_media_action(){
        center.getChildren().clear();
        add.setOnMouseClicked(event -> {
            if (id_input_1.getText().length() == 24)
                try {
                    sortByComments(new ChannelInfo(id_input_1.getText(), apiKey), true);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
        });

        load.setOnMouseClicked(event -> {
            allChannels.clear();
            List<String> list = FileUtils.load(cache_path);
            for (int i = 0; i < list.size(); i++) {
                try {
                    ChannelInfo channelInfo = new ChannelInfo(list.get(i), apiKey);
                    sortByComments(channelInfo, false);
                } catch (UnirestException e) {
                    e.printStackTrace();
                }
            }
        });

        new Thread(() -> {
            actionName.setText("Sort channels by media");
            tableView.getItems().clear();
            setDefault();
            TableViewUtils.addColumn(tableView);
            tableView.setItems(allChannels);

            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, id_input_1,
                    add, load, tableView));
            sort_media.setDisable(false);
        }).start();
    }
}