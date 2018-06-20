package GUI;

import Collection.Person;
import Collection.contract;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;






public class Contracts
{
  public Contracts() {}
  
  public static final ObservableList<contract> contract = FXCollections.observableArrayList();
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
    String title = typecontract == 0 ? "Договоры рассрочки платежа" : "Customer orders";
    stage.setTitle(title);
    stage.setTitle(title);
    stage.setWidth(1100.0D);
    stage.setHeight(600.0D);
    stage.show();
  }
  

  private VBox createBut(final Stage stage, final TableView table)
  {
    VBox node = new VBox();
    

    Button add = new Button("Добавить");
    Button isnow = new Button("Active");
    Button endcontract = new Button("Closed");
    Button allcontract = new Button("All contr.");
    Button print = new Button("Печать");
    Button close = new Button("Закрыть");
    Button paidadd = new Button("Paid");
    
    if (typecontract == 0) add.setDisable(!Repeat.user.getRules(11));
    if (typecontract == 1) { add.setDisable(!Repeat.user.getRules(12));
    }
    final Popup popup = new Popup();
    final VBox boxpopup = new VBox();
    
    boxpopup.setSpacing(8.0D);
    boxpopup.setId("dark-popup");
    boxpopup.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    popup.getContent().add(boxpopup);
    popup.setWidth(100.0D);
    popup.setHeight(50.0D);
    
    add.setMinSize(100.0D, 40.0D);
    paidadd.setMinSize(100.0D, 40.0D);
    print.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    isnow.setMinSize(100.0D, 40.0D);
    endcontract.setMinSize(100.0D, 40.0D);
    allcontract.setMinSize(100.0D, 40.0D);
    

    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        if (Contracts.typecontract == 0) {
          AddContract co = new AddContract();
          co.start(new Stage());
        }
        else {
          AddOrder co = new AddOrder();
          co.start(new Stage());
        }
        
      }
    });
    paidadd.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int t = table.getSelectionModel().getSelectedIndex();
        System.out.print("t: " + t);
        ContractPaid ca; if (t != -1)
          ca = new ContractPaid(t); else {
          Dialogs.showErrorDialog(stage, "Ошибка номер 405, выделите договор. ", "Error Dialog", "");
        }
        
      }
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
    });
    isnow.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        getContract(1, Contracts.typecontract);
      }
    });
    endcontract.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        getContract(0, Contracts.typecontract);
      }
    });
    allcontract.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        getContract(2, Contracts.typecontract);
      }
    });
    table.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (event.getClickCount() == 2) {
          int n = table.getSelectionModel().getSelectedIndex();
          if (n != -1) {
            if (!popup.isShowing()) {
              boxpopup.getChildren().clear();
              boxpopup.getChildren().add(new Text("ФИО: " + ((contract)Contracts.contract.get(n)).getName()));
              boxpopup.getChildren().add(new Text("Phone: " + ((contract)Contracts.contract.get(n)).getPhone()));
              boxpopup.getChildren().add(new Text("Адрес :" + ((contract)Contracts.contract.get(n)).getAdress()));
              


              popup.setX(event.getScreenX());
              popup.setY(event.getScreenY());
              popup.show(stage);
            }
            else {
              popup.hide();
            }
          }
        }
      }
    });
    print.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try
        {
          if ((Contracts.indexnewcontract != -1) && (Contracts.typecontract == 0)) {
            URI line = new URI("http://localhost/java/fun.php?num=" + Contracts.indexnewcontract);
            desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported()) {
              desktop.browse(line);
              Contracts.indexnewcontract = -1;
            } else {
              Dialogs.showErrorDialog(stage, "Ошибка номер 405. ", "Error Dialog", "");
            }
          }
          else if (Contracts.typecontract == 0) {
            int t = table.getSelectionModel().getSelectedIndex();
            if (t != -1) {
              URI line = new URI("http://localhost/java/fun.php?num=" + ((contract)Contracts.contract.get(t)).getId());
              desktop = Desktop.getDesktop();
              if (Desktop.isDesktopSupported()) desktop.browse(line); else {
                Dialogs.showErrorDialog(stage, "Ошибка номер 405, выделите договор. ", "Error Dialog", "");
              }
            }
          }
          if ((Contracts.indexnewcontract != -1) && (Contracts.typecontract == 1)) {
            URI line = new URI("http://localhost/java/order.php?num=" + Contracts.indexnewcontract);
            desktop = Desktop.getDesktop();
            if (Desktop.isDesktopSupported()) {
              desktop.browse(line);
              Contracts.indexnewcontract = -1;
            } else {
              Dialogs.showErrorDialog(stage, "Ошибка номер 405. ", "Error Dialog", "");
            }
          }
          else if (Contracts.typecontract == 1) {
            int t = table.getSelectionModel().getSelectedIndex();
            if (t != -1) {
              URI line = new URI("http://localhost/java/order.php?num=" + ((contract)Contracts.contract.get(t)).getId());
              desktop = Desktop.getDesktop();
              if (Desktop.isDesktopSupported()) desktop.browse(line); else {
                Dialogs.showErrorDialog(stage, "Ошибка номер 405, выделите договор. ", "Error Dialog", "");
              }
              
            }
            
          }
        }
        catch (URISyntaxException|IOException ex) {}
      }
    });
    node.getChildren().addAll(new Node[] { add, isnow, endcontract, allcontract, paidadd, print, close });
    return node;
  }
  
  private TableView createTable() {
    TableView table = new TableView();
    table.setStyle("-fx-font: normal 11 Arial;");
    table.columnResizePolicyProperty();
    
    TableColumn id = new TableColumn("Id");
    id.setMinWidth(40.0D);
    id.setMaxWidth(40.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("ФИО");
    name.setMinWidth(340.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn dstart = new TableColumn("Дата закл.");
    dstart.setMinWidth(90.0D);
    dstart.setCellValueFactory(new PropertyValueFactory("dstart"));
    
    TableColumn dend = new TableColumn("Дата окон.");
    dend.setMinWidth(90.0D);
    dend.setCellValueFactory(new PropertyValueFactory("dend"));
    

    TableColumn price = new TableColumn("Total");
    price.setMinWidth(90.0D);
    price.setCellValueFactory(new PropertyValueFactory("total_amount"));
    

    TableColumn sum = new TableColumn("Paid");
    sum.setMinWidth(90.0D);
    sum.setCellValueFactory(new PropertyValueFactory("amount_paid"));
    
    TableColumn remain = new TableColumn("Remain");
    remain.setMinWidth(90.0D);
    remain.setCellValueFactory(new PropertyValueFactory("remaining_sum"));
    
    TableColumn user = new TableColumn("User");
    user.setMinWidth(90.0D);
    user.setCellValueFactory(new PropertyValueFactory("user"));
    




    table.setMaxWidth(922.0D);
    table.getColumns().addAll(new Object[] { id, name, dstart, dend, price, sum, remain, user });
    

    table.setItems(contract);
    return table;
  }
  
  public void getContract(int i, int j) { ConnectDB mysql = new ConnectDB();
    mysql.getContractsList(i, j, contract);
  }
}
