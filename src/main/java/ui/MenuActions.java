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
        channel_Info.setOnMouseClicked(event -> channel_info_action());
        compare_channels.setOnMouseClicked(event -> compare_channels_action());
        sort_channels.setOnMouseClicked(event -> sort_channels_action());
        media_info.setOnMouseClicked(event -> media_info_action());
        compare_media.setOnMouseClicked(event -> compare_media_action());
        sort_media.setOnMouseClicked(event -> sort_media_action());
    }

    protected void channel_info_action(){
        center.getChildren().clear();
        actionName.setText("Channel Info");
        setDefault();

        new Thread(() -> {
            channel_Info.setDisable(true);

            infoBox.getChildren().clear();
            infoBox.getChildren().addAll(channel_box_1, separator, channel_description_1);

            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, show, id_input_1, infoBox));
            channel_Info.setDisable(false);
        }).start();
    }

    protected void compare_channels_action(){
        center.getChildren().clear();
        setDefault();
        actionName.setText("Compare channels ");

        new Thread(() -> {
            compare_channels.setDisable(true);

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
        actionName.setText("Sort channels");
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
        setDefault();
        tableView.getItems().clear();
        new Thread(() -> {
            tableView.setItems(allChannels);
            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, id_input_1,
                    load, add, tableView));
        }).start();
    }

    protected void media_info_action() {
        media_info.setDisable(true);
        channel_info_action();
        actionName.setText("Media resonance");
        channel_box_1.getChildren().addAll(commentsCount_1);
        media_info.setDisable(false);
    }

    protected void compare_media_action() {
        compare_media.setDisable(true);
        compare_channels_action();
        actionName.setText("Compare media resonance");
        channel_box_1.getChildren().add(commentsCount_1);
        channel_box_2.getChildren().add(commentsCount_2);
        compare_media.setDisable(false);
    }

    protected void sort_media_action(){
        center.getChildren().clear();
        actionName.setText("Sort channels by media");
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
        tableView.getItems().clear();
        setDefault();

        new Thread(() -> {
            TableViewUtils.addColumn(tableView);
            tableView.setItems(allChannels);

            Platform.runLater(() -> center.getChildren().addAll(actionName, infoLabel, id_input_1,
                    add, load, tableView));
        }).start();

        setDefault();
    }
}