package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.mashape.unirest.http.exceptions.UnirestException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.FileUtils;
import utils.ImageUtils;
import utils.ChannelInfo;
import utils.TableViewUtils;
import java.util.Collections;
import java.util.Comparator;

public class InfoWindow extends BasicWindow {
    protected JFXTextField id_input_1, id_input_2;
    protected JFXButton show, compare;
    protected ObservableList<ChannelInfo> allChannels;
    protected TableView tableView;
    protected VBox channel_box_1, channel_box_2;
    protected JFXTextArea channel_title_1, channel_title_2,
            channel_description_1;
    protected Label videosCount_1, videosCount_2,
            viewsCount_1, viewsCount_2,
            commentsCount_1, commentsCount_2,
            creationDate_1, creationDate_2,
            subsCount_1, subsCount_2;
    protected ImageView channel_picture_1, channel_picture_2;
    protected Separator separator;

    public InfoWindow(BorderPane root, Stage stage) {
        super(root, stage);
        setBasicElements();
        setChannelInfoGraphics();
    }

    protected void setDefault(){
        channel_title_1.setText(" Channel title ");
        channel_title_2.setText(" Channel title ");
        channel_description_1.setText("Description");
        videosCount_1.setText("Videos\t");
        videosCount_2.setText("Videos\t");
        commentsCount_1.setText("Comments\t");
        commentsCount_2.setText("Comments\t");
        subsCount_1.setText("Subscribers\t");
        subsCount_2.setText("Subscribers\t");
        creationDate_1.setText("Date\t\t");
        creationDate_2.setText("Date\t\t");
        viewsCount_1.setText("Views\t");
        viewsCount_2.setText("Views\t");

        Platform.runLater(()->timer.setText("Timer: 0ms."));

        channel_picture_1 = ImageUtils.crop(ImageUtils.default_image);
        channel_picture_2 = ImageUtils.crop(ImageUtils.default_image);

        channel_box_1.getChildren().clear();
        channel_box_1.getChildren().addAll(channel_picture_1,
                channel_title_1,
                creationDate_1,
                subsCount_1,
                videosCount_1,
                viewsCount_1);

        channel_box_2.getChildren().clear();
        channel_box_2.getChildren().addAll(channel_picture_2,
                channel_title_2,
                creationDate_2,
                subsCount_2,
                videosCount_2,
                viewsCount_2);

        id_input_1.clear();
        id_input_2.clear();
    }

    protected void setChannelInfoGraphics(){
        tableView = TableViewUtils.getTable();

        channel_title_1 = new JFXTextArea(" Channel title ");
        channel_title_1.setEditable(false);
        channel_title_1.setId("channelTitle");

        channel_title_2 = new JFXTextArea();
        channel_title_2.setEditable(false);
        channel_title_2.setId("channelTitle");

        channel_description_1 = new JFXTextArea("Description");
        channel_description_1.setEditable(false);
        channel_description_1.setId("description");

        videosCount_1 = new Label("Videos\t");
        videosCount_1.setId("customLabel");
        videosCount_2 = new Label("Videos\t");
        videosCount_2.setId("customLabel");

        viewsCount_1 = new Label("Views\t");
        viewsCount_1.setId("customLabel");
        viewsCount_2 = new Label("Views\t");
        viewsCount_2.setId("customLabel");

        creationDate_1 = new Label("Created at\t");
        creationDate_1.setId("customLabel");
        creationDate_2 = new Label("Created at\t");
        creationDate_2.setId("customLabel");

        commentsCount_1 = new Label("Comments\t");
        commentsCount_1.setId("customLabel");
        commentsCount_2 = new Label("Comments\t");
        commentsCount_2.setId("customLabel");

        subsCount_1 = new Label("Subscribers\t");
        subsCount_1.setId("customLabel");
        subsCount_2 = new Label("Subscribers\t");
        subsCount_2.setId("customLabel");

        separator = new Separator(Orientation.VERTICAL);
        separator.setPrefHeight(390);
        separator.setTranslateY(5);

        channel_picture_1 = new ImageView();
        channel_picture_2 = new ImageView();

        channel_box_1 = new VBox(channel_picture_1,
                channel_title_1,
                creationDate_1,
                subsCount_1,
                videosCount_1,
                viewsCount_1);
        channel_box_1.setSpacing(10);
        channel_box_1.setTranslateX(10);
        channel_box_1.setTranslateY(10);

        channel_box_2 = new VBox(channel_picture_2,
                channel_title_2,
                creationDate_2,
                subsCount_2,
                videosCount_2,
                viewsCount_2);
        channel_box_2.setSpacing(10);
        channel_box_2.setTranslateX(10);
        channel_box_2.setTranslateY(10);
    }

    private void setBasicElements(){
        id_input_1 = new JFXTextField();
        id_input_1.setTranslateX(25);
        id_input_1.setTranslateY(100);
        id_input_1.setId("tf2");

        id_input_2 = new JFXTextField();
        id_input_2.setTranslateX(330);
        id_input_2.setTranslateY(100);
        id_input_2.setId("tf2");

        show = new JFXButton("Show info");
        show.setId("mainButton");
        show.setTranslateX(330);
        show.setTranslateY(100);
        show.setOnMouseClicked(event -> {
            try {
                long start = System.currentTimeMillis();
                if (!id_input_1.equals(""))
                    showInfo_1();
                long end = System.currentTimeMillis();
                if (timing_is_on)
                    timer.setText("Timer: " + (end - start) + "ms.");
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });

        compare = new JFXButton("Compare");
        compare.setId("mainButton");
        compare.setTranslateX(330);
        compare.setTranslateY(50);
        compare.setOnMouseClicked(event -> {
            try {
                long start = System.currentTimeMillis();
                compareAction();
                long end = System.currentTimeMillis();
                if (timing_is_on)
                    timer.setText("Timer: " + (end - start) + "ms.");
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        });
        allChannels = FXCollections.observableArrayList();
    }

    protected void sortByTitle(ChannelInfo channelInfo, boolean addToCache) {
        long start = System.currentTimeMillis();
        if (cache_is_on && addToCache)
            FileUtils.write(id_input_1.getText(), cache_path, true);

        allChannels.add(channelInfo);
        Collections.sort(allChannels, Comparator.comparing(ChannelInfo::getChannelTitle));
        tableView.setItems(allChannels);

        long end = System.currentTimeMillis();
        if (timing_is_on)
            timer.setText("Timer: " + (end - start) + "ms.");
    }

    protected void sortByComments(ChannelInfo channelInfo, boolean addToCache) {
        long start = System.currentTimeMillis();
        allChannels.add(channelInfo);

        if (cache_is_on && addToCache) {
            if (!FileUtils.readLine(cache_path).equals(""))
                FileUtils.write("\n", cache_path, true);
            FileUtils.write(id_input_1.getText(), cache_path, true);
        }

        Collections.sort(allChannels, Comparator.comparing(ChannelInfo::getComments));
        Collections.reverse(allChannels);
        tableView.setItems(allChannels);

        long end = System.currentTimeMillis();
        if (timing_is_on)
            timer.setText("Timer: " + (end - start) + "ms.");
    }


    protected void showInfo_1() throws UnirestException {
        ChannelInfo loader = new ChannelInfo(id_input_1.getText(), apiKey);
        channel_title_1.setText(" Channel title: " + loader.getChannelTitle());
        creationDate_1.setText("Date\t\t\t" + loader.getDate());
        subsCount_1.setText("Subscribers\t" + loader.getSubscribers());
        videosCount_1.setText("Videos\t\t" + loader.getVideos());
        viewsCount_1.setText("Views\t\t" + loader.getViews());
        commentsCount_1.setText("Comments\t" + loader.getComments());
        channel_description_1.setText("Description\n\n" + loader.getDescription());
        channel_box_1.getChildren().remove(0);
        channel_box_1.getChildren().add(0,loader.getImage());
    }

    protected void compareAction() throws UnirestException {
        showInfo_1();
        ChannelInfo loader = new ChannelInfo(id_input_2.getText(), apiKey);
        channel_title_2.setText(" Channel title: " + loader.getChannelTitle());
        creationDate_2.setText("Date:\t\t" + loader.getDate());
        subsCount_2.setText("Subscribers\t" + loader.getSubscribers());
        videosCount_2.setText("Videos\t\t" + loader.getVideos());
        viewsCount_2.setText("Views\t\t" + loader.getViews());
        commentsCount_2.setText("Comments\t" + loader.getComments());
        channel_box_2.getChildren().remove(0);
        channel_box_2.getChildren().add(0,loader.getImage());
    }
}