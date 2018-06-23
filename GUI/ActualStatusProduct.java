package GUI;

import Collection.Person;
import Collection.Prices;
import Connect.ConnectDB;
import Popup.PFilterActualInfoProduct;
import Popup.PFilterCorrectStatus;
import fxuidemo.Repeat;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class ActualStatusProduct
{
  public ActualStatusProduct() {}
  
  private static ObservableList<Prices> no_actual = FXCollections.observableArrayList();
  
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    
    VBox main = new VBox();
    main.setSpacing(8.0D);
    Label info = new Label();
    info.setText("* Cейчас отображаются категории, исключенныe из списка продуктов доступных к реализации.");
    info.setTextFill(Color.RED);
    
    TableView<Prices> table = createTable();
    
    //main.getChildren().addAll(new Node[] { info, table });
    main.getChildren().addAll(info, table);
    root.setLeft(main);
    VBox box = createBut(stage, info, table);
    root.setId("bp");
    box.setSpacing(10.0);
    root.setRight(box);
    
    stage.setTitle("Информация об неиспользуемых/малоиспользуемых продуктах");
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    
    stage.setWidth(1150.0);
    stage.setHeight(700.0);
    stage.show();
  }
  
  private VBox createBut(final Stage stage, Label info, TableView table)
  {
    VBox node = new VBox();
    
    PFilterActualInfoProduct PFilter = new PFilterActualInfoProduct(no_actual, info);
    final Popup mainfilter = PFilter.getMain();
    
    PFilterCorrectStatus PFilterCorrect = new PFilterCorrectStatus(no_actual, info, table);
    final Popup correctfilter = PFilterCorrect.getMain();
    

    Button filter = new Button("Фильтр");
    Button operations = new Button("Операции");
    Button close = new Button("Закрыть");
    
    filter.setMinSize(100.0D, 40.0D);
    operations.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    
    if (!Repeat.user.getRules(4)) { operations.setDisable(true);
    }
    close.setOnMouseClicked(new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event)
      {
        stage.close();
      }
    });
    operations.setOnMouseClicked(new EventHandler<MouseEvent>(){

      public void handle(MouseEvent event)
      {
        if (!correctfilter.isShowing()) correctfilter.show(stage, 490.0D, 200.0D); else {
          correctfilter.hide();
        }
      }
    });
    filter.setOnMouseClicked(new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event)
      {
        if (!mainfilter.isShowing()) mainfilter.show(stage, 490.0D, 200.0D); else {
          mainfilter.hide();
        }
        
      }
      
    });
    node.getChildren().addAll(new Node[] { filter, operations, close });
    return node;
  }
  
  private TableView<Prices> createTable() {
    TableView<Prices> table = new TableView<Prices>();
    
    TableColumn<Prices,Integer> index = new TableColumn<Prices,Integer>("№");
    index.setMinWidth(20.0);
    index.setMaxWidth(40.0);
    index.setCellValueFactory(new PropertyValueFactory("code"));
    //index.setCellFactory(value);
    

    TableColumn<Prices, String> name = new TableColumn<Prices, String>("Наименование");
    name.setMinWidth(400.0);
    
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn<Prices,Integer> id = new TableColumn<Prices,Integer>("Код");
    id.setMinWidth(20.0);
    id.setMaxWidth(50.0);
    id.setCellValueFactory(new PropertyValueFactory("id"));    

    TableColumn<Prices,Integer> size = new TableColumn<Prices,Integer>("Кол.eд.");
    size.setMinWidth(40.0);
    size.setMaxWidth(60.0);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn<Prices,Double> price = new TableColumn<Prices,Double>("Цена");
    price.setMinWidth(50.0);
    
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn<Prices,Integer> group = new TableColumn<Prices,Integer>("Группа");
    group.setMinWidth(20.0);
    
    group.setCellValueFactory(new PropertyValueFactory("group"));
    

    TableColumn<Prices,Integer> status = new TableColumn<Prices,Integer>("Статус");
    status.setMinWidth(20.0);
    
    status.setCellValueFactory(new PropertyValueFactory("actual_status"));

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setMinWidth(970.0);
    table.setMinHeight(610.0);
    

    table.setStyle("-fx-font: normal 11 Arial;");
    table.setItems(no_actual);
    table.getColumns().addAll(index, id, name, group, size, price, status);  
    

  //  table.getColumns().addAll(elements)
    //                                    (int id, int co, String name, int group, int size, double price, int as)
    return table;
  }
  
  public void setProductStatus(int status) { no_actual.clear();
    ConnectDB db = new ConnectDB();
    db.getProductForStatus(no_actual, status);
  }
  
  public void setShortOtchet() {}
}
