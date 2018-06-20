package GUI;

import Collection.GateReg;
import Collection.MetallList;
import Connect.ConnectDB;
import java.io.PrintStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
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

public class AddMetall
  extends Application
{
  static ObservableList<MetallList> ml = FXCollections.observableArrayList();
  
  public String USER_NAME;
  

  public AddMetall() {}
  
  public void start(Stage stage)
  {
    ConnectDB db = new ConnectDB();
    
    db.getBecapMetall(ml);
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    VBox but = createBut(stage);
    
    stage.setTitle("Регистрация приема лома");
    root.setId("bp");
    but.setId("but");
    
    root.setRight(but);
    
    root.setCenter(createVBox(db, stage, but));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
    
    stage.setScene(scene);
    
    stage.setWidth(1050.0D);
    stage.setHeight(460.0D);
    stage.show();
  }
  

  private VBox createBut(final Stage stage)
  {
    VBox node = new VBox();
    node.setSpacing(8.0D);
    node.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    Button add = new Button("Добавить");
    Button otchet = new Button("Отчет");
    Button close = new Button("Закрыть");
    
    add.setMinSize(130.0D, 50.0D);
    otchet.setMinSize(130.0D, 50.0D);
    close.setMinSize(130.0D, 50.0D);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        System.out.print("esa");
        stage.close();
      }
      
    });
    node.getChildren().addAll(new Node[] { add, otchet, close });
    return node;
  }
  
  private VBox createVBox(ConnectDB db, Stage stage, VBox but)
  {
    VBox grid = new VBox();
    grid.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    grid.setSpacing(8.0D);
    
    TableView tl = createTableReg();
    
    grid.getChildren().add(tl);
    



    ((Node)but.getChildren().get(0)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        AddMetallNow m = new AddMetallNow();
      }
      
    });
    ((Node)but.getChildren().get(1)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        MetallOtchetToday m = new MetallOtchetToday();
        m.start(new Stage());
        m.getMetallOtchet();

      }
      

    });
    return grid;
  }
  
  private TableView createTableReg() { TableView<MetallList> table = new TableView();
    
    table.columnResizePolicyProperty();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn n = new TableColumn("№");
    n.setMinWidth(30.0D);
    n.setMaxWidth(40.0D);
    n.setCellValueFactory(new PropertyValueFactory("index"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(450.0D);
    name.setMaxWidth(480.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn id = new TableColumn("Код");
    id.setMinWidth(58.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(50.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(90.0D);
    
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(90.0D);
    
    sum.setCellValueFactory(new PropertyValueFactory("summa"));
    



    table.setStyle("-fx-font: normal 11 Arial;");
    


    table.setItems(ml);
    
    table.getColumns().addAll(new TableColumn[] { n, id, name, size, price, sum });
    

    return table;
  }
  
  private String Time_m() { SimpleDateFormat s = new SimpleDateFormat("HH:mm");
    return s.format(new Date());
  }
  

  private int validateSizeProduct(ObservableList<GateReg> g)
  {
    ConnectDB db = new ConnectDB();
    
    return 0;
  }
}
