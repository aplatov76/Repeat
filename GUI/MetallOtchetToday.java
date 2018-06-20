package GUI;

import Collection.MetallOtchet;
import Connect.ConnectDB;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MetallOtchetToday
{
  public MetallOtchetToday() {}
  
  private ObservableList<MetallOtchet> collect_otchet = FXCollections.observableArrayList();
  
  public void start(Stage stage) { BorderPane root = new BorderPane();
    root.setLeft(createTable());
    VBox box = createBut(stage);
    root.setId("bp");
    box.setSpacing(10.0D);
    root.setRight(box);
    
    stage.setTitle("Отчет по лому");
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
    stage.setScene(scene);
    
    stage.setWidth(1000.0D);
    stage.setHeight(600.0D);
    stage.show();
  }
  
  private VBox createBut(final Stage stage)
  {
    VBox node = new VBox();
    
    Button print = new Button("Печать");
    Button close = new Button("Закрыть");
    
    print.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        stage.close();
      }
    });
    print.setOnMouseClicked(new EventHandler<MouseEvent>()
    {


      public void handle(MouseEvent event) {}


    });
    node.getChildren().addAll(new Node[] { print, close });
    return node;
  }
  
  private TableView createTable() {
    TableView table = new TableView();
    

    ConnectDB mysql = new ConnectDB();
    
    table.columnResizePolicyProperty();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn index = new TableColumn("№");
    index.setMinWidth(10.0D);
    index.setMinWidth(20.0D);
    index.setCellValueFactory(new PropertyValueFactory("index"));
    

    TableColumn id = new TableColumn("Код");
    id.setMinWidth(40.0D);
    id.setMaxWidth(50.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(290.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    


    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(40.0D);
    size.setMaxWidth(70.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(80.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(90.0D);
    sum.setCellValueFactory(new PropertyValueFactory("summa"));
    



    table.setMaxWidth(762.0D);
    table.setStyle("-fx-font: normal 11 Arial;");
    table.getColumns().addAll(new Object[] { index, name, id, size, price, sum });
    
    table.setItems(collect_otchet);
    return table;
  }
  
  public void getMetallOtchet() {
    ConnectDB mysql = new ConnectDB();
    
    mysql.getMetallOtchet(collect_otchet);
  }
}
