package utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewUtils {
    private static TableColumn<ChannelInfo,Long> commentsColumn = new TableColumn<>("Comments");

    private TableViewUtils(){}

    public static TableView getTable(){
        TableView tableView = tableView = new TableView();
        tableView.setStyle("-fx-background-color: white;" +
                "-fx-font-size: 11pt;");

        TableColumn<ChannelInfo,String> titleColumn = new TableColumn<ChannelInfo,String>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<ChannelInfo,String>("channelTitle"));

        TableColumn<ChannelInfo,String> dateColumn = new TableColumn<ChannelInfo,String>("Date");
        dateColumn.setMinWidth(70);
        dateColumn.setCellValueFactory(new PropertyValueFactory<ChannelInfo,String>("date"));

        TableColumn<ChannelInfo,String> videosColumn = new TableColumn<ChannelInfo,String>("Videos");
        videosColumn.setMinWidth(60);
        videosColumn.setCellValueFactory(new PropertyValueFactory<>("videos"));

        TableColumn<ChannelInfo,String> subsColumn = new TableColumn<ChannelInfo, String>("Subs");
        subsColumn.setMinWidth(60);
        subsColumn.setCellValueFactory(new PropertyValueFactory<>("subscribers"));

        TableColumn<ChannelInfo,String> viewsColumn = new TableColumn<ChannelInfo,String>("Views");
        viewsColumn.setMinWidth(70);
        viewsColumn.setCellValueFactory(new PropertyValueFactory<>("views"));

        tableView.setTranslateX(15);
        tableView.setTranslateY(155);
        tableView.setPrefSize(620,410);

        tableView.getColumns().addAll(titleColumn, dateColumn, videosColumn, subsColumn,
                viewsColumn);

        return tableView;
    }

    public static void addColumn(TableView tableView){
        commentsColumn.setMinWidth(60);
        commentsColumn.setCellValueFactory(new PropertyValueFactory<>("comments"));

        tableView.getColumns().addAll(commentsColumn);
    }

    public static void removeColumn(TableView tableView){
        if (tableView.getColumns().contains(commentsColumn))
            tableView.getColumns().remove(commentsColumn);
    }
}
