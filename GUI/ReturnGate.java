/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Collection.contract;
import Connect.ConnectDB;
import Popup.ReturnGateway;
import calendar.SimpleCalendar;
import fxuidemo.Repeat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author andrew
 */
public class ReturnGate {
    
private static ObservableList<Collection.History> hist = FXCollections.observableArrayList();
public static int gateEvent = 0;
    
  public void start(Stage stage) {
    BorderPane root = new BorderPane();
    TableView<Collection.History> table = createTableHistory();
    root.setLeft(table);
    VBox box = createVBox(stage, table);
    root.setId("bp");
    box.setSpacing(10.0);
    root.setRight(box);
    
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    String title = "Продажи";
    stage.setTitle(title);
    stage.setWidth(1340.0D);
    stage.setHeight(700.0D);
    stage.show();
  }     
    

  private VBox createVBox(final Stage stage, final TableView tb)
  {
    GridPane node = new GridPane();
    node.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    node.setId("but");
    node.setVgap(8.0D);
    node.setHgap(8.0D);
    node.setPadding(new Insets(0.0D, 20.0D, 0.0D, 20.0D));
    SimpleCalendar simpleCalender = new SimpleCalendar();
    SimpleCalendar simpleCalendar = new SimpleCalendar();
    final TextField dateField = new TextField("Select date");
    final TextField dataField = new TextField("Select date");
    simpleCalender.dateProperty().addListener(new ChangeListener<Date>()
    {

      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dateField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    simpleCalendar.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dataField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    Button ok = new Button("Показать");
    Button add = new Button("Возврат");
    Button ext = new Button("Hазад");
    
    ok.setId("dark-blue");
    add.setId("dark-blue");
    ext.setId("dark-blue");
    
    ok.setMinSize(20.0D, 30.0D);
    add.setMinSize(20.0D, 30.0D);
    ext.setMinSize(20.0D, 30.0D);

    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
       // root.getChildren().clear();
      //  root.getChildren().add(but);
          stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher n = p.matcher(dataField.getText());
        if ((m.matches()) && (n.matches())) {
          //Repeat.connectordb.getHistory(dateField.getText(), dataField.getText(), hist);
          getReturn(dateField.getText(), dataField.getText());
        } else {
          Dialogs.showErrorDialog(stage, "Неверный формат даты");
        }
      }
    });
    
    tb.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (event.getClickCount() == 2) {
          int n = tb.getSelectionModel().getSelectedIndex();
          if (n != -1) {
            if (gateEvent == 0) {
                gateEvent = 1;
                ReturnGateway popupClass = new ReturnGateway(hist.get(n));
                
                Popup popup = popupClass.getMain();
                
                int width = Repeat.sSize.width;
                int height = Repeat.sSize.height;

                popup.setX(width/2 - width*0.33);
                popup.setY(height/2 - height*0.25);
                popup.show(stage);
            }
            else {
             // popup.hide();
            }
          }
        }
      }
    });
    
    node.add(simpleCalender, 0, 1);
    node.add(dateField, 1, 1);
    node.add(simpleCalendar, 0, 2);
    node.add(dataField, 1, 2);
    HBox but = new HBox();
    but.setSpacing(5);
    but.getChildren().addAll(ok,add,ext);
    
    VBox returnbutton = new VBox();
    returnbutton.getChildren().addAll(node,but);

    return returnbutton;
  }  
    

private TableView createTableHistory() {
    final TableView<Collection.History> table = new TableView();
    

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    TableColumn idop = new TableColumn("id опер.");
    idop.setMinWidth(50.0D);
    idop.setCellValueFactory(new PropertyValueFactory("idop"));
    

    TableColumn time = new TableColumn("Дата");
    time.setMinWidth(80.0D);
    time.setCellValueFactory(new PropertyValueFactory("data"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(430.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    
    TableColumn id = new TableColumn("Код");
    id.setMinWidth(60.0D);
    id.setCellValueFactory(new PropertyValueFactory("idpr"));
    

    TableColumn balance = new TableColumn("Остаток");
    balance.setMinWidth(60.0D);
    balance.setCellValueFactory(new PropertyValueFactory("balance"));
    

    TableColumn size = new TableColumn("Продано");
    size.setMinWidth(60.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(80.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(100.0D);
    sum.setCellValueFactory(new PropertyValueFactory("sum"));
    
    TableColumn user = new TableColumn("User");
    user.setMinWidth(80.0D);
    user.setCellValueFactory(new PropertyValueFactory("user"));
    
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setId("tablefont");
    table.setItems(hist);
   // table.setMaxWidth(1076.0D);
    
    
    table.getColumns().addAll(new TableColumn[] { idop, time, name, id,/* balance,*/ size, price, sum, user });
    return table;
  }    

  public void getReturn(String dateField,String dataField) { 
      ConnectDB mysql = new ConnectDB();
      System.out.println(dateField + " "+ dataField);
      mysql.getHistory(dateField, dataField, hist,Repeat.user.getGroup_user());
  }
    
}
