package GUI;

import Collection.HistoryMetallOtchet;
import Connect.ConnectDB;
import Popup.PFilterHistoryMetall;
import calendar.SimpleCalendar;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;






public class HistoryMetall
{
  public static final ObservableList<HistoryMetallOtchet> contract = ;
  
  public HistoryMetall() {}
  
  public void start(Stage stage) { BorderPane root = new BorderPane();
    TableView table = createTable();
    VBox pane = new VBox();
    
    HBox box = createBut(stage, table);
    root.setId("bp");
    box.setId("but");
    box.setSpacing(8.0D);
    pane.setSpacing(8.0D);
    pane.setAlignment(Pos.TOP_CENTER);
    pane.getChildren().addAll(new Node[] { box, table });
    
    root.setCenter(pane);
    
    Scene scene = new Scene(root);
    
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setTitle("История регистрации металл");
    stage.setWidth(1200.0D);
    stage.setHeight(670.0D);
    stage.show();
  }
  
  public static void main(String[] args)
  {
    Application.launch(args);
  }
  
  private HBox createBut(final Stage stage, TableView table)
  {
    HBox node = new HBox();
    
    ObservableList<String> namemetall = FXCollections.observableArrayList();
    
    getListMetall(namemetall);
    
    final PFilterHistoryMetall PFilter = new PFilterHistoryMetall(namemetall);
    final Popup mainfilter = PFilter.getMain();
    

    node.setAlignment(Pos.TOP_CENTER);
    Button ok = new Button("Сегодня");
    Button add = new Button("Неделя");
    Button correct = new Button("Месяц");
    Button show_me = new Button("Показать");
    Button filter = new Button("Фильтр");
    Button close = new Button("Закрыть");
    

    ok.setId("dark-blue");
    add.setId("dark-blue");
    show_me.setId("dark-blue");
    close.setId("dark-blue");
    correct.setId("dark-blue");
    filter.setId("dark-blue");
    
    SimpleCalendar simpleCalender = new SimpleCalendar();
    SimpleCalendar simpleCalendar = new SimpleCalendar();
    final TextField dateField = new TextField("Select date");
    final TextField dataField = new TextField("Select date");
    
    simpleCalender.dateProperty().addListener(new ChangeListener()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dateField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    simpleCalendar.dateProperty().addListener(new ChangeListener()
    {

      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dataField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      

    });
    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        getContract(7);
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        getContract(0);
      }
      
    });
    show_me.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher e = p.matcher(dataField.getText());
        if ((m.matches()) && (e.matches())) { HistoryMetall.this.dateShowMe(dateField.getText(), dataField.getText());
        } else {
          Dialogs.showInformationDialog(stage, "Неверный формат дат");
        }
      }
    });
    filter.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher e = p.matcher(dataField.getText());
        if ((m.matches()) && (e.matches())) {
          PFilter.setData1(dateField.getText());
          PFilter.setData2(dataField.getText());
          if (!mainfilter.isShowing()) mainfilter.show(stage, 1040.0D, 70.0D); else {
            mainfilter.hide();
          }
        } else {
          Dialogs.showInformationDialog(stage, "Неверный формат дат");
        }
        
      }
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
        getContract(30);
      }
      

    });
    table.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event) {}


    });
    node.getChildren().addAll(new Node[] { ok, add, correct, simpleCalender, dateField, simpleCalendar, dataField, show_me, filter, close });
    return node;
  }
  
  private TableView createTable() {
    TableView table = new TableView();
    table.setStyle("-fx-font: normal 11 Arial;");
    

    TableColumn id = new TableColumn("№");
    id.setMinWidth(70.0D);
    
    id.setCellValueFactory(new PropertyValueFactory("index"));
    

    TableColumn data = new TableColumn("Дата");
    data.setMinWidth(70.0D);
    data.setCellValueFactory(new PropertyValueFactory("data"));
    

    TableColumn name = new TableColumn("Наименование ");
    name.setMinWidth(370.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn code = new TableColumn("Код");
    code.setMinWidth(70.0D);
    code.setCellValueFactory(new PropertyValueFactory("code"));
    


    TableColumn size = new TableColumn("Принято");
    size.setMinWidth(80.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn remain = new TableColumn("Ср. цена");
    remain.setMinWidth(58.0D);
    remain.setCellValueFactory(new PropertyValueFactory("remain"));
    


    TableColumn sum = new TableColumn("Cумма");
    sum.setMinWidth(50.0D);
    sum.setCellValueFactory(new PropertyValueFactory("sum"));
    

    TableColumn user = new TableColumn("User");
    user.setMinWidth(50.0D);
    user.setCellValueFactory(new PropertyValueFactory("user"));
    

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    table.setMinWidth(700.0D);
    table.setMinHeight(500.0D);
    table.getColumns().addAll(new Object[] { id, data, name, code, size, remain, sum, user });
    table.setItems(contract);
    return table;
  }
  
  public void getContract(int day) { ConnectDB mysql = new ConnectDB();
    mysql.getHistoryMetall(contract, day);
  }
  
  private void getListMetall(ObservableList<String> namemetall) {
    ConnectDB mysql = new ConnectDB();
    mysql.loadMetallName(namemetall);
  }
  
  private void dateShowMe(String d1, String d2) {
    ConnectDB mysql = new ConnectDB();
    


    mysql.getHistoryMetall(contract, d1, d2, -1, 0);
  }
}
