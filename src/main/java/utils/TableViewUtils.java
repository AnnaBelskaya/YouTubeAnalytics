package utils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewUtils {
    private static TableColumn<ChannelInfo,Long> commentsColumn = new TableColumn<>("Comments");

    private TableViewUtils(){}

    public static TableView getTable(){
        TableView tableView = new TableView();
        tableView.setStyle("-fx-background-color: white;" +
                "-fx-font-size: 11pt;");

        TableColumn<ChannelInfo,String> titleColumn = new TableColumn<>("Title");
        titleColumn.setMinWidth(200);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("channelTitle"));

        TableColumn<ChannelInfo,String> dateColumn = new TableColumn<ChannelInfo,String>("Date");
        dateColumn.setMinWidth(70);
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<ChannelInfo,Long> videosColumn = new TableColumn<>("Videos");
        videosColumn.setMinWidth(60);
        videosColumn.setCellValueFactory(new PropertyValueFactory<>("videos"));

        TableColumn<ChannelInfo,Long> subsColumn = new TableColumn<>("Subs");
        subsColumn.setMinWidth(60);
        subsColumn.setCellValueFactory(new PropertyValueFactory<>("subscribers"));

        TableColumn<ChannelInfo,Long> viewsColumn = new TableColumn<>("Views");
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
        if (tableView.getColumns().size() == 5)
            tableView.getColumns().add(commentsColumn);
    }

    public static void removeColumn(TableView tableView){
        if (tableView.getColumns().size() == 6)
            tableView.getColumns().remove(commentsColumn);
    }
}
