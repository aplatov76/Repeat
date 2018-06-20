package GUI;

import Collection.ListCustProduct;
import Collection.Person;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import fxuidemo.Repeat;
import java.awt.Desktop;
import java.io.PrintStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class ListCustomerProduct
{
  public ListCustomerProduct() {}
  
  public static final ObservableList<ListCustProduct> list = FXCollections.observableArrayList();
  Desktop desktop = null;
  public static int indexnewcontract = -1;
  public static int typecontract = 0;
  
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    TableView table = createTable();
    root.setLeft(table);
    VBox box = createBut(stage, table);
    root.setId("bp");
    box.setSpacing(10.0D);
    root.setRight(box);
    
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    String title = "Cписок продуктов , только под заказ";
    stage.setTitle(title);
    stage.setWidth(1000.0D);
    stage.setHeight(600.0D);
    stage.show();
  }
  

  private VBox createBut(final Stage stage, final TableView table)
  {
    VBox node = new VBox();
    

    final ConnectDB db = new ConnectDB();
    
    final Button add = new Button("Добавить");
    add.setDisable(!Repeat.user.getRules(13));
    Button correct = new Button("Изменить");
    correct.setDisable(!Repeat.user.getRules(14));
    Button delet = new Button("Удалить");
    delet.setDisable(!Repeat.user.getRules(15));
    Button search = new Button("Поиск");
    Button group = new Button("Группы");
    Button close = new Button("Закрыть");
    Button print = new Button("Печать");
    
    correct.setId("dark-blue");
    delet.setId("dark-blue");
    add.setId("dark-blue");
    group.setId("dark-blue");
    print.setId("dark-blue");
    
    add.setMinSize(100.0D, 40.0D);
    print.setMinSize(100.0D, 40.0D);
    group.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    correct.setMinSize(100.0D, 40.0D);
    delet.setMinSize(100.0D, 40.0D);
    search.setMinSize(100.0D, 40.0D);
    

    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        AddCustProduct adb = new AddCustProduct();
        adb.start(new Stage());
        System.out.println(add.getScene().getX());
        System.out.println(add.getScene().getWindow().getX());
        System.out.println(add.getScene().getWindow().getScene().getX());
      }
    });
    print.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event) {}

    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
    });
    correct.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int n = table.getSelectionModel().getSelectedIndex();
        if (n != -1) {
          CorrectCustProduct abc = new CorrectCustProduct(n);
          abc.start(new Stage());
        } else {
          Dialogs.showErrorDialog(stage, "Выберите продукт из списка");
        }
      } });
    delet.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = table.getSelectionModel().getSelectedIndex();
        
        if (b != -1)
          if (Dialogs.showConfirmDialog(stage, "Удалить: " + ((ListCustProduct)ListCustomerProduct.list.get(b)).getName() + " ?") == Dialogs.DialogResponse.YES) {
            ConnectDB db = new ConnectDB();
            db.deletCustProduct(((ListCustProduct)ListCustomerProduct.list.get(b)).getId_product());
            db.setLog((ListCustProduct)ListCustomerProduct.list.get(b), 2, Repeat.user.getName());
            ListCustomerProduct.list.remove(b);
            Dialogs.showInformationDialog(stage, "Категория удалена.");
          } else {
            Dialogs.showErrorDialog(stage, "Выберите продукт из списка");
          }
      }
    });
    search.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event) {}


    });
    ObservableList data = FXCollections.observableArrayList();
    ObservableList<Integer> datacode = FXCollections.observableArrayList();
    ObservableList groups = FXCollections.observableArrayList();
    
    db.loadGroupList(groups);
    db.loadProductCustomerOrder(data);
    db.loadCustProductId(datacode);
    

    final AutoFillTextBox box = new AutoFillTextBox(data);
    final AutoFillTextBox boxcode = new AutoFillTextBox(datacode);
    box.getListview().setMinSize(100.0D, 100.0D);
    
    box.setListLimit(100);
    box.setMinWidth(570.0D);
    box.setId("box");
    boxcode.getListview().setMinSize(100.0D, 100.0D);
    boxcode.setListLimit(100);
    boxcode.setMinWidth(570.0D);
    boxcode.setId("box");
    
    final Popup popup = new Popup();
    final Popup popupgroup = new Popup();
    HBox button3 = new HBox();
    button3.setSpacing(8.0D);
    Button popupok = new Button("Поиск");
    Button popupclose = new Button("Закрыть");
    Button search_group = new Button("Показать");
    Button pop_group_go = new Button("Группы");
    
    search.setId("dark-blue");
    search_group.setId("dark-blue");
    close.setId("dark-blue");
    popupclose.setId("dark-blue");
    popupok.setId("dark-blue");
    pop_group_go.setId("dark-blue");
    
    button3.getChildren().addAll(new Node[] { search, pop_group_go });
    


    final ChoiceBox<String> chois = new ChoiceBox(groups);
    

    chois.setMinWidth(290.0D);
    





    VBox popupsearch = new VBox();
    HBox popupbut1 = new HBox();
    popupsearch.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    popupsearch.setSpacing(8.0D);
    popupbut1.setSpacing(8.0D);
    
    popupbut1.getChildren().addAll(new Node[] { popupok, popupclose });
    
    popupsearch.getChildren().addAll(new Node[] { box, boxcode, popupbut1 });
    popupsearch.setId("dark-popup");
    popup.getContent().addAll(new Node[] { popupsearch });
    
    VBox popupgroups = new VBox();
    popupgroups.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    popupgroups.setSpacing(8.0D);
    
    popupgroups.getChildren().addAll(new Node[] { chois, search_group });
    popupgroups.setId("dark-popup");
    popupgroup.getContent().addAll(new Node[] { popupgroups });
    
    popupclose.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        popup.hide();
      }
      
    });
    popupok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        int d = ListCustomerProduct.list.size();
        if (!box.getText().equals("")) {
          int sid = db.setID(box.getText());
          for (int i = 0; i < d; i++) if (sid == ((ListCustProduct)ListCustomerProduct.list.get(i)).getId_product()) {
              table.scrollTo(i);
              table.getSelectionModel().select(i);
              break;
            }
        }
        else if (!boxcode.getText().equals("")) {
          try {
            int sid = Integer.parseInt(boxcode.getText());
            for (int i = 0; i < d; i++) if (sid == ((ListCustProduct)ListCustomerProduct.list.get(i)).getId_product()) {
                table.scrollTo(i);
                table.getSelectionModel().select(i);
                break;
              }
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Неправильный формат кода товара");
          }
        }
        box.getTextbox().setText("");
        boxcode.getTextbox().setText("");
        popup.hide();
      }
      

    });
    search.setOnMouseClicked(new EventHandler<MouseEvent>()
    {




      public void handle(MouseEvent event)
      {




        if (!popup.isShowing()) {
          popup.setX(event.getScreenX() - 620.0D);
          popup.setY(event.getScreenY() - 20.0D);
          popup.show(stage);
        } else {
          popup.hide();
        }
      }
    });
    group.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        if (!popupgroup.isShowing()) {
          popupgroup.setX(event.getScreenX() - 360.0D);
          popupgroup.setY(event.getScreenY() - 10.0D);
          popupgroup.show(stage);
        } else {
          popupgroup.hide();
        }
      }
    });
    search_group.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (chois.getSelectionModel().getSelectedIndex() != -1) {
          String p = (String)chois.getValue();
          int to = p.indexOf(" ");
          p = p.substring(0, to);
          db.getCustomerProductGroup(ListCustomerProduct.list, p);
        }
        
        popupgroup.hide();
      }
      

    });
    node.getChildren().addAll(new Node[] { add, correct, delet, search, group, print, close });
    return node;
  }
  
  private TableView createTable() {
    TableView table = new TableView();
    table.setStyle("-fx-font: normal 11 Arial;");
    table.columnResizePolicyProperty();
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn id = new TableColumn("№");
    id.setMinWidth(40.0D);
    
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("Код");
    name.setMinWidth(50.0D);
    name.setCellValueFactory(new PropertyValueFactory("id_product"));
    

    TableColumn dstart = new TableColumn("Наименование");
    dstart.setMinWidth(440.0D);
    dstart.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn dend = new TableColumn("Группа");
    dend.setMinWidth(50.0D);
    dend.setCellValueFactory(new PropertyValueFactory("group"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(70.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    


    table.setMinWidth(768.0D);
    table.getColumns().addAll(new Object[] { id, name, dstart, dend, price });
    
    getContract();
    table.setItems(list);
    return table;
  }
  
  public void getContract() { ConnectDB mysql = new ConnectDB();
    mysql.loadProductCustomer(list);
  }
}
