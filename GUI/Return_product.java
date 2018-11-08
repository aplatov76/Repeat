package GUI;

import Collection.Registration_return;
import Connect.ConnectDB;
import java.awt.Desktop;
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
import javafx.stage.Popup;
import javafx.stage.Stage;


public class Return_product
{
  public Return_product() {}
  
  Desktop desktop = null;
  private static ObservableList<Collection.Registration_return> return_product = FXCollections.observableArrayList();
  private final ConnectDB db = new ConnectDB();
  
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    TableView<Registration_return> table = createTable();
    root.setLeft(table);
    VBox box = createBut(stage, table);
    root.setId("bp");
    box.setSpacing(10.0);
    root.setRight(box);
    
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    String title = "Возврат";
    stage.setTitle(title);
    stage.setWidth(1340.0D);
    stage.setHeight(600.0D);
    stage.show();
  }  

  private VBox createBut(final Stage stage, final TableView table)
  {
    VBox node = new VBox();
    

    Button add = new Button("Добавить");
    Button isnow = new Button("История");
    Button close = new Button("Закрыть");
    
    final Popup popup = new Popup();
    final VBox boxpopup = new VBox();
    
    boxpopup.setSpacing(8.0D);
    boxpopup.setId("dark-popup");
    boxpopup.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    popup.getContent().add(boxpopup);
    popup.setWidth(100.0D);
    popup.setHeight(50.0D);
    
    add.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    isnow.setMinSize(100.0D, 40.0D);
    

    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
          
          ReturnGate ot = new ReturnGate();
          ot.start(new Stage());
      }
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        db.closeConnect();
        stage.close();
      }
    });
    isnow.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
       // getReturn(1, Contracts.typecontract);
      }
    });


    node.getChildren().addAll(new Node[] { add, isnow, close });
    return node;
  }
  
  private TableView<Registration_return> createTable() {
      
    TableView<Registration_return> table = new TableView();
    table.setStyle("-fx-font: normal 11 Arial;");

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn time_r = new TableColumn("Возврат");
    time_r.setMinWidth(110.0D);
    time_r.setCellValueFactory(new PropertyValueFactory("data_return"));
    
    TableColumn time_p = new TableColumn("Продажа");
    time_p.setMinWidth(120.0D);
    time_p.setCellValueFactory(new PropertyValueFactory("data_prodag"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(470.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    
    TableColumn id = new TableColumn("Код");
    id.setMinWidth(60.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(60.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(80.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(90.0D);
    sum.setCellValueFactory(new PropertyValueFactory("sum"));
    
    TableColumn user_name = new TableColumn("User");
    user_name.setMinWidth(90.0D);
    user_name.setCellValueFactory(new PropertyValueFactory("user"));
    
    table.setId("tablefont");
    
    getReturn();
    table.setItems(return_product);
    
    table.getColumns().addAll(time_r, time_p, name, id, size, price, sum, user_name);
    return table;
  }
  
  public void getReturn() { 
      db.getReturnProduct(return_product, 0 ,"2018-08-05 23:59");
  }
}