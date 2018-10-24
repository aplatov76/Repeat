package GUI;

import Collection.MoveProduct;
import Connect.ConnectDB;
import fxuidemo.Repeat;
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


public class Move_product_history
{
  public Move_product_history() {}
  
  Desktop desktop = null;
  private static ObservableList<MoveProduct> return_product = FXCollections.observableArrayList();
  
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    TableView<MoveProduct> table = createTable();
    root.setLeft(table);
    VBox box = createBut(stage);
    root.setId("bp");
    box.setSpacing(10.0);
    root.setRight(box);
    
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    String title = "Перемещения товаров";
    stage.setTitle(title);
    stage.setWidth(1340.0D);
    stage.setHeight(600.0D);
    stage.show();
  }  

  private VBox createBut(final Stage stage)
  {
    VBox node = new VBox();
    

    Button add = new Button("Добавить");
    //Button isnow = new Button("История");
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
    //isnow.setMinSize(100.0D, 40.0D);
    

    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        GUI.MoveProduct add = new GUI.MoveProduct();
        try {
          add.start(new Stage());
        } catch (Exception ex) {
          java.util.logging.Logger.getLogger(Repeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
      }
      
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
    });

 
    node.getChildren().addAll(new Node[] { add, close });
    return node;
  }
  
  private TableView<MoveProduct> createTable() {
      
    TableView<MoveProduct> table = new TableView();
    table.setStyle("-fx-font: normal 11 Arial;");
    table.setMinWidth(1100);
    
     
    //table.columnResizePolicyProperty();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn time_r = new TableColumn("Дата");
    time_r.setMinWidth(110.0D);
    time_r.setCellValueFactory(new PropertyValueFactory("data_return"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(470.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    
    TableColumn id = new TableColumn("Код");
    id.setMinWidth(60.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(60.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    
    TableColumn stock = new TableColumn("Cклад отг.");
    stock.setMinWidth(60.0D);
    stock.setCellValueFactory(new PropertyValueFactory("stock"));
    
    TableColumn user_name = new TableColumn("User");
    user_name.setMinWidth(90.0D);
    user_name.setCellValueFactory(new PropertyValueFactory("user"));
    
    table.setId("tablefont");
    
    getReturn();
    table.setItems(return_product);
    
    table.getColumns().addAll(time_r, name, id, size, stock, user_name);
    return table;
  }
  
  public void getReturn() { 
      ConnectDB mysql = new ConnectDB();
      //mysql.getReturnProduct(return_product, 0 ,"2018-08-05 23:59");
      return_product.removeAll(return_product);
      mysql.getMoveProduct(return_product);
  }
}
