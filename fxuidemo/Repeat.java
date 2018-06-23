package fxuidemo;

import Collection.AdminPane;
import Collection.Cassa;
import Collection.Log_view;
import Collection.Person;
import Collection.Prices;
import Connect.ConnectDB;
import GUI.ActualStatusProduct;
import GUI.AddMetall;
import GUI.AddProduct;
import GUI.AddProductGate;
import GUI.Contracts;
import GUI.HistoryMetall;
import GUI.HistoryProcurement;
import GUI.ListCustomerProduct;
import GUI.Otchet;
import Popup.PFilterPriceView;
import autofilltextbox.AutoFillTextBox;
import calendar.SimpleCalendar;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.animation.ScaleTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Repeat extends javafx.application.Application
{
  private static final ConnectDB connectordb = new ConnectDB();
  private static final MD5 md5 = new MD5();
  
  public static Person user;
  public static String USER_NAME;
  public static ObservableList<Collection.Registration> prod = FXCollections.observableArrayList();
  public static ObservableList<Prices> prices = FXCollections.observableArrayList();
  public static ObservableList<AdminPane> admprod = FXCollections.observableArrayList();
  public static ObservableList<Collection.History> historyprod = FXCollections.observableArrayList();
  private Stage st;
  private VBox but;
  
  public Repeat() {}
  
  public static void main(String[] args) { javafx.application.Application.launch(args); }
  

  public void start(Stage stage)
  {
    stage.setTitle("FX Layer Cake");
    st = stage;
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    but = createBut(root);
    but.setSpacing(5.0D);
    but.setPadding(new Insets(0.0D, 20.0D, 0.0D, 20.0D));
    but.setAlignment(Pos.TOP_CENTER);
    
    root.setLeft(createGridPaneAutorize(root, but, stage));
    
    root.setRight(but);
    
    Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    root.setId("bp");
    but.setId("but");
    
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    
    stage.setWidth(sSize.width);
    stage.setHeight(sSize.height);
    
    stage.show();
  }
  

  private Button createButton(Runnable action, String name)
  {
    final Button node = new Button(name);
    node.setId("btnLogin");
    double SCALE = 1.3D;
    double DURATION = 300.0D;
    
    final ScaleTransition animationGrow = new ScaleTransition(javafx.util.Duration.millis(DURATION), node);
    animationGrow.setToX(SCALE);
    animationGrow.setToY(SCALE);
    

    final ScaleTransition animationShrink = new ScaleTransition(javafx.util.Duration.millis(DURATION), node);
    animationShrink.setToX(1.0D);
    animationShrink.setToY(1.0D);
    

    javafx.scene.effect.Reflection effect = new javafx.scene.effect.Reflection();
    node.setEffect(effect);
    
    node.setOnMouseEntered(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        node.toFront();
        animationShrink.stop();
        animationGrow.playFromStart();
      }
      
    });
    node.setOnMouseExited(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        animationGrow.stop();
        animationShrink.playFromStart();
      }
      
    });
    return node;
  }
  
  private VBox createBut(final BorderPane root) { VBox node = new VBox();
    Label lbl = new Label();
    
    Button one = new Button("Учет материалов");
    Button two = new Button("Прайс лист/доп.информ.");
    Button three = new Button("Администрирование");
    Button fout = new Button("История/Статистика");
    Button six = new Button("Журнал операций");
    Button seven = new Button("Управление пользов.");
    Button close = new Button("Выйти");
    one.setMinSize(200.0D, 60.0D);
    
    one.setDisable(true);
    
    two.setMinSize(200.0D, 60.0D);
    two.setDisable(true);
    three.setMinSize(200.0D, 60.0D);
    three.setDisable(true);
    fout.setMinSize(200.0D, 60.0D);
    fout.setDisable(true);
    six.setMinSize(200.0D, 60.0D);
    six.setDisable(true);
    seven.setMinSize(200.0D, 60.0D);
    seven.setDisable(true);
    close.setMinSize(200.0D, 60.0D);
    close.setDisable(false);
    
    three.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        TableView tb = Repeat.this.createTableAdmin();
        root.setRight(Repeat.this.createVBoxAdmin(root, tb));
        root.setLeft(tb);
      }
      
    });
    fout.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        TableView tb = Repeat.this.createTableHistory();
        root.setRight(Repeat.this.createVBoxHistory(root, tb));
        root.setLeft(tb);
      }
      

    });
    node.getChildren().addAll(new Node[] { lbl, one, two, three, fout, six, seven, close });
    return node;
  }
  
  private void controlEnable(VBox but) {
    ((Label)but.getChildren().get(0)).setText("");
    ((Node)but.getChildren().get(1)).setDisable(true);
    ((Node)but.getChildren().get(2)).setDisable(true);
    ((Node)but.getChildren().get(3)).setDisable(true);
    ((Node)but.getChildren().get(4)).setDisable(true);
    ((Node)but.getChildren().get(5)).setDisable(true);
    ((Node)but.getChildren().get(6)).setDisable(true);
  }
  
  private VBox createVBoxRegistration(final BorderPane root, final VBox but, TableView tb)
  {
    VBox node = new VBox();
    
    node.setId("but");
    node.setSpacing(5.0D);
    node.setPadding(new Insets(0.0D, 20.0D, 0.0D, 20.0D));
    Button addproduct = new Button("Добавить запись");
    Button metall = new Button("Учет металл");
    

    SplitMenuButton otchet = new SplitMenuButton();
    otchet.setText("Отчет");
    otchet.setPopupSide(javafx.geometry.Side.LEFT);
    otchet.setAlignment(Pos.CENTER);
    

    MenuItem menuItemcut = new MenuItem("Текущего пользователя");
    MenuItem menuItempop = new MenuItem("Полный отчет ");
    menuItempop.setDisable(!user.getRules(9));
    
    otchet.getItems().addAll(new MenuItem[] { menuItemcut, menuItempop });
    

    Button print = new Button("Печать");
    
    Button prev = new Button("Назад");
    SplitMenuButton contract = new SplitMenuButton();
    
    contract.setText("Договоры");
    
    contract.setPopupSide(javafx.geometry.Side.RIGHT);
    contract.setAlignment(Pos.CENTER);
    
    MenuItem customer1 = new MenuItem(" Рассрочка ");
    customer1.setDisable(!user.getRules(11));
    MenuItem customer2 = new MenuItem(" Сustomer order ");
    
    contract.getItems().addAll(new MenuItem[] { customer1, customer2 });
    Cassa cas = new Cassa();
    
    TextField cassa = cas.getCassa(0);
    TextField prixod = cas.getCassa(1);
    TextField rasxod = cas.getRasxod();
    cassa.setId("cassa");
    prixod.setId("cassa");
    rasxod.setId("cassa");
    

    contract.setMinSize(200.0D, 60.0D);
    addproduct.setMinSize(200.0D, 60.0D);
    otchet.setMinSize(200.0D, 60.0D);
    prev.setMinSize(200.0D, 60.0D);
    print.setMinSize(200.0D, 60.0D);
    metall.setMinSize(200.0D, 60.0D);
    
    prev.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        root.setRight(but);
      }
      
    });
    contract.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Contracts abc = new Contracts();
        Contracts.typecontract = 1;
        abc.getContract(1, 1);
        abc.start(new Stage());
      }
      

    });
    addproduct.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
//        AddProduct add = new AddProduct(Repeat.prod);
  //      add.start(new Stage());
        USER_NAME = Repeat.user.getName();
      }
    });
    metall.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        AddMetall add = new AddMetall();
        add.start(new Stage());
        USER_NAME = Repeat.user.getName();
      }
    });
    print.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Print a = new Print(Repeat.prod);
      }
      
    });
    otchet.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (!Repeat.user.getRules(9)) {
          Otchet ot = new Otchet();
          ot.start(new Stage());
          ot.setShortOtchet();
        }
        else {
          Otchet ot = new Otchet();
          ot.start(new Stage());
          ot.setFullOtchet();
        }
        
      }
      
    });
    menuItemcut.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Otchet ot = new Otchet();
        ot.start(new Stage());
        ot.setShortOtchet();
      }
      
    });
    menuItempop.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Otchet ot = new Otchet();
        ot.start(new Stage());
        ot.setFullOtchet();
      }
      
    });
    customer1.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Contracts abc = new Contracts();
        Contracts.typecontract = 0;
        abc.start(new Stage());
        abc.getContract(1, 0);
      }
      
    });
    customer2.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Contracts abc = new Contracts();
        Contracts.typecontract = 1;
        abc.start(new Stage());
        abc.getContract(1, 1);
      }
      

    });
    node.getChildren().addAll(new Node[] { addproduct, otchet, print, contract, metall, prev, cassa, prixod, rasxod });
    return node;
  }
  
  private void clearPane(BorderPane root) {
    root.getChildren().remove(0, root.getChildren().size());
  }
  
  private TableView createTableReg()
  {
    TableView table = new TableView();
    
    table.columnResizePolicyProperty();
    
    TableColumn time = new TableColumn("Время");
    time.setMinWidth(80.0D);
    time.setCellValueFactory(new PropertyValueFactory("data"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(500.0D);
    name.setMaxWidth(501.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    
    TableColumn id = new TableColumn("Код");
    id.setMinWidth(80.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(80.0D);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(80.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(120.0D);
    sum.setCellValueFactory(new PropertyValueFactory("sum"));
    
    TableColumn user_name = new TableColumn("User");
    user_name.setMinWidth(100.0D);
    user_name.setCellValueFactory(new PropertyValueFactory("user"));
    
    table.setId("tablefont");
    table.setItems(prod);
    
    if (!user.getRules(10))
      connectordb.getSessionBecap(prod, user.getName()); else {
      connectordb.getSessionBecap(prod);
    }
    table.getColumns().addAll(new Object[] { time, name, id, size, price, sum, user_name });
    
    return table;
  }
  
  private TableView createTableHistory() {
    TableView<Collection.History> table = new TableView();
    

    table.columnResizePolicyProperty();
    
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
    table.setItems(historyprod);
    table.setMaxWidth(1076.0D);
    table.getColumns().addAll(new TableColumn[] { idop, time, name, id, balance, size, price, sum, user });
    return table;
  }
  
  private TableView createTableAdmin()
  {
    TableView<AdminPane> table = new TableView();
    

    table.columnResizePolicyProperty();
    
    TableColumn code = new TableColumn("№");
    code.setMinWidth(35.0D);
    code.setMaxWidth(45.0D);
    code.setCellValueFactory(new PropertyValueFactory("number"));
    

    TableColumn id = new TableColumn("Код");
    id.setMinWidth(30.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(405.0D);
    name.setMaxWidth(416.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn sname = new TableColumn("Наименование");
    sname.setMinWidth(380.0D);
    sname.setCellValueFactory(new PropertyValueFactory("shortname"));
    

    TableColumn group = new TableColumn("Группа");
    group.setMinWidth(30.0D);
    group.setCellValueFactory(new PropertyValueFactory("group"));
    

    TableColumn helf = new TableColumn("Вес");
    helf.setMinWidth(30.0D);
    helf.setCellValueFactory(new PropertyValueFactory("helf"));
    
    TableColumn status = new TableColumn("Статус");
    status.setMinWidth(20.0D);
    status.setCellValueFactory(new PropertyValueFactory("actual_status"));
    

    TableColumn col = new TableColumn("Остаток");
    col.setMinWidth(60.0D);
    col.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(60.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    
    connectordb.getPaneAdmin(admprod);
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setItems(admprod);
    table.setId("tablefont");
    
    table.getColumns().addAll(new TableColumn[] { code, id, sname, group, helf, status, col, price });
    table.setMinWidth(1038.0D);
    return table;
  }
  
  private TableView createTableLog() {
    TableView<Log_view> table = new TableView();
    

    table.columnResizePolicyProperty();
    
    TableColumn code = new TableColumn("№");
    code.setMinWidth(35.0D);
    
    code.setCellValueFactory(new PropertyValueFactory("index"));
    

    TableColumn data = new TableColumn("Дата");
    data.setMinWidth(140.0D);
    data.setCellValueFactory(new PropertyValueFactory("data"));
    

    TableColumn id = new TableColumn("Код oпер.");
    id.setMinWidth(60.0D);
    id.setCellValueFactory(new PropertyValueFactory("idop"));
    

    TableColumn id_product = new TableColumn("Код прод.");
    id_product.setMinWidth(40.0D);
    
    id_product.setCellValueFactory(new PropertyValueFactory("idproduct"));
    

    TableColumn sname = new TableColumn("Наименование");
    sname.setMinWidth(450.0D);
    sname.setCellValueFactory(new PropertyValueFactory("name_product"));
    

    TableColumn group = new TableColumn("Группа");
    group.setMinWidth(30.0D);
    group.setCellValueFactory(new PropertyValueFactory("group_product"));
    

    TableColumn helf = new TableColumn("Тип");
    helf.setMinWidth(30.0D);
    helf.setCellValueFactory(new PropertyValueFactory("type"));
    

    TableColumn col = new TableColumn("Остаток");
    col.setMinWidth(40.0D);
    
    col.setCellValueFactory(new PropertyValueFactory("ostatok"));
    


    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(60.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn user = new TableColumn("User");
    user.setMinWidth(60.0D);
    user.setCellValueFactory(new PropertyValueFactory("user"));
    


    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    table.setId("tablefont");
    
    table.getColumns().addAll(new TableColumn[] { code, data, id, id_product, sname, group, col, price, helf, user });
    table.setMaxWidth(1470.0D);
    return table;
  }
  



  private GridPane createGridPaneAutorize(final BorderPane root, final VBox but, final Stage stage)
  {
    final GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    gridPane.setHgap(5.0D);
    gridPane.setVgap(5.0D);
    
    gridPane.add(new Label("Login : "), 0, 0);
    final TextField login = new TextField();
    login.setText("godvin");
    gridPane.add(login, 1, 0, 2, 1);
    gridPane.add(new Label("Password : "), 0, 1);
    final PasswordField pass = new PasswordField();
    pass.setText("767690");
    gridPane.add(pass, 1, 1, 2, 1);
    
    Button log = createButton(new Runnable() { public void run() {} }, "Войти");
    

    log.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.user = Repeat.connectordb.autorizeuser(login.getText(), Repeat.md5.getHash(pass.getText()));
        boolean keyread = true;
        if ((Repeat.user != null) && 
          (Repeat.user.getUse_key() == 1)) {
          security.ReadKey key = new security.ReadKey();
          String keys = key.read("/media/key/key");
          
          String dbkey = Repeat.connectordb.getKeyUser(Repeat.user.getId_user());
          
          if (keys == null) { keyread = false;
          } else if (!dbkey.equals(keys)) { keyread = false;
          }
        }
        if ((Repeat.user != null) && (keyread))
        {
          ((Label)but.getChildren().get(0)).setText("Welcome " + Repeat.user.getName());
          ((Label)but.getChildren().get(0)).setTextFill(Color.GREEN);
          if (Repeat.user.getRules(0)) ((Node)but.getChildren().get(1)).setDisable(false);
          if (Repeat.user.getRules(1)) { ((Node)but.getChildren().get(2)).setDisable(false);
          }
          if ((Repeat.user.getRules(2)) || (Repeat.user.getRules(3)) || (Repeat.user.getRules(4)) || (Repeat.user.getRules(8)) || (Repeat.user.getRules(13)) || (Repeat.user.getRules(14)) || (Repeat.user.getRules(15)) || (Repeat.user.getRules(15)) || (Repeat.user.getRules(16)) || (Repeat.user.getRules(17)) || (Repeat.user.getRules(18)) || (Repeat.user.getRules(19)))
          {
            ((Node)but.getChildren().get(3)).setDisable(false);
          }
          if ((Repeat.user.getRules(5)) || (Repeat.user.getRules(20))) ((Node)but.getChildren().get(4)).setDisable(false);
          if (Repeat.user.getRules(6)) ((Node)but.getChildren().get(5)).setDisable(false);
          if (Repeat.user.getRules(7)) ((Node)but.getChildren().get(6)).setDisable(false);
          root.getChildren().remove(0);

        }
        else if (!keyread) {
          ((Label)but.getChildren().get(0)).setText("Нет ключа");
          ((Label)but.getChildren().get(0)).setTextFill(Color.ORANGERED);
          
          Repeat.user = null;
        }
        else {
          ((Label)but.getChildren().get(0)).setText("Неверный пароль");
          ((Label)but.getChildren().get(0)).setTextFill(Color.RED);
        }
        
      }
    });
    ((Button)but.getChildren().get(7)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        if (Repeat.user == null) { stage.close();
        } else {
          Cassa.cassa = 0.0D;
          Cassa.start = 0.0D;
          Repeat.user = null;
          login.setText("");
          pass.setText("");
          Repeat.this.controlEnable(but);
          root.getChildren().add(0, gridPane);
        }
        
      }
    });
    ((Button)but.getChildren().get(6)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        GUI.CorrectUser user = new GUI.CorrectUser();
        user.start(new Stage());
      }
      
    });
    ((Button)but.getChildren().get(1)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        TableView tb = Repeat.this.createTableReg();
        root.setRight(Repeat.this.createVBoxRegistration(root, but, tb));
        root.setLeft(tb);
        


        double p = Repeat.connectordb.getStartSum(Repeat.user.getName(), Repeat.user.getRules(10));
        if (p == 0.0D)
        {
          try
          {
            Cassa.start = Double.parseDouble(Dialogs.showInputDialog(st, "amount in cash now :", "Введите начальную сумму", "Fx Layer "));
          }
          catch (NumberFormatException|NullPointerException ed)
          {
            System.out.println("A");
            Cassa.start = 0.0D;
          }
          finally {
            if (Cassa.start > 0.0D) {
              System.out.println("B");
              Cassa.cassa += Cassa.start;
              Repeat.connectordb.setStartSum(Cassa.start, "", Repeat.user.getName());
              Cassa.setCassa();
            }
          }
        }
        else if (Cassa.start == 0.0D) {
          System.out.println("C");
          Cassa.start = p;
          Cassa.cassa += p;
          Cassa.setCassa();
        }
        else {
          System.out.print("\nD\n");
          Cassa.cassa += p;
          Cassa.setCassa();

        }
        
      }
      

    });
    ((Button)but.getChildren().get(2)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        TableView<Prices> tb = Repeat.this.createTablePrice();
        root.setLeft(tb);
        root.setRight(Repeat.this.createVBoxSeePrice(root, but, tb));
        

        Repeat.connectordb.getProductForStatus(Repeat.prices, -1);
      }
      

    });
    ((Button)but.getChildren().get(5)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        TableView a = Repeat.this.createTableLog();
        
        root.setLeft(a);
        
        root.setRight(Repeat.this.getLogViewPopap(root, a));
      }
      
    });
    gridPane.add(log, 1, 2);
    gridPane.add(createButton(new Runnable() { public void run() {} }, "Отмена"), 2, 2);
    



    gridPane.setId("root");
    return gridPane;
  }
  
  private VBox createVBoxAdmin(final BorderPane root, final TableView tb) {
    VBox node = new VBox();
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<Integer> datacode = FXCollections.observableArrayList();
    
    ObservableList<String> group = FXCollections.observableArrayList();
    
    node.setPadding(new Insets(20.0D, 10.0D, 20.0D, 10.0D));
    node.setSpacing(8.0D);
    node.setId("but");
    connectordb.loadProduct(data);
    connectordb.loadGroupList(group);
    connectordb.loadProductId(datacode);
    
    Label texoper = new Label("Работа с номенклатурой");
    Label texcontract = new Label("Договоры и заказы покупателей");
    Label searcsort = new Label("Поиск и сортировка продукции");
    Label order_product = new Label("Тех.операции \"Под заказ\"");
    Label product_add_now = new Label("Управление закупками");
    Label use_status_product = new Label("Управление статусами продуктов");
    
    product_add_now.setId("labeladmin");
    texoper.setId("labeladmin");
    searcsort.setId("labeladmin");
    texcontract.setId("labeladmin");
    order_product.setId("labeladmin");
    use_status_product.setId("labeladmin");
    
    Button add = new Button("Добавить");
    Button cor = new Button("Изменить");
    Button del = new Button("Удалить");
    
    Button cgroup = new Button(" Группы ");
    Button print = new Button("Печать ");
    
    Button cproduct = new Button(" Список ");
    Button cproductadd = new Button(" Добавить ");
    cproductadd.setDisable(!user.getRules(13));
    
    Button otchetzakup = new Button("История закупок");
    Button productaddnow = new Button(" Добавить ");
    productaddnow.setDisable(!user.getRules(16));
    Button productaddlist = new Button();
    productaddlist.setText("Ожидают(" + connectordb.getProcurementActivCount() + ")");
    
    otchetzakup.setId("dark-blue");
    cproduct.setId("dark-blue");
    cproductadd.setId("dark-blue");
    productaddnow.setId("dark-blue");
    productaddlist.setId("dark-blue");
    
    Button contract = new Button("Заказы");
    Button zakazi = new Button("Рассрочка");
    









    contract.setId("dark-blue");
    zakazi.setId("dark-blue");
    add.setId("dark-blue");
    cor.setId("dark-blue");
    del.setId("dark-blue");
    cgroup.setId("dark-blue");
    print.setId("dark-blue");
    


    if (!user.getRules(2)) add.setDisable(true);
    if (!user.getRules(4)) cor.setDisable(true);
    if (!user.getRules(3)) del.setDisable(true);
    if (!user.getRules(8)) { cgroup.setDisable(true);
    }
    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        GUI.AdminAdd add = new GUI.AdminAdd();
        try {
          add.start(new Stage());
        } catch (Exception ex) {
          java.util.logging.Logger.getLogger(Repeat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
      }
      
    });
    otchetzakup.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        HistoryProcurement abc = new HistoryProcurement();
        abc.getContract(0);
        abc.start(new Stage());
      }
      
    });
    cgroup.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        GUI.CorrectGroup gdb = new GUI.CorrectGroup();
        gdb.start(new Stage());
      }
      
    });
    productaddlist.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        AddProductGate ddt = new AddProductGate();
        ddt.start(new Stage());
        ddt.getContract();
      }
      
    });
    productaddnow.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        GUI.AddProductSizeNow gdb = new GUI.AddProductSizeNow();
      }
      
    });
    contract.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        Contracts gdb = new Contracts();
        Contracts.typecontract = 1;
        gdb.start(new Stage());
        gdb.getContract(1, 1);
      }
    });
    zakazi.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        Contracts gdb = new Contracts();
        Contracts.typecontract = 0;
        gdb.start(new Stage());
        gdb.getContract(1, 0);



      }
      




    });
    cproduct.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        ListCustomerProduct list = new ListCustomerProduct();
        list.start(new Stage());
      }
      

    });
    cproductadd.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        GUI.AddCustProduct adb = new GUI.AddCustProduct();
        adb.start(new Stage());
      }
      

    });
    print.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try
        {
          java.net.URI line = new java.net.URI("http://localhost/java/modlistprint.php");
          Desktop desktop = Desktop.getDesktop();
          if (Desktop.isDesktopSupported()) {
            desktop.browse(line);
          }
          else {
            Dialogs.showErrorDialog(st, "Ошибка номер 405. ", "Error Dialog", "");
          }
          

        }
        catch (java.io.IOException|java.net.URISyntaxException ex) {}
      }
      

    });
    cor.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = tb.getSelectionModel().getSelectedIndex();
        if (b != -1) {
          AdminPane cor = (AdminPane)Repeat.admprod.get(b);
          GUI.AdminCorrect next = new GUI.AdminCorrect(b);
          System.out.print(cor.getId() + "<<\n");
          next.start(new Stage());
        } else {
          Dialogs.showErrorDialog(st, "Ничего не выбрано");
        }
      }
    });
    del.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = tb.getSelectionModel().getSelectedIndex();
        
        if (b != -1) {
          if (Dialogs.showConfirmDialog(st, "Удалить " + ((AdminPane)Repeat.admprod.get(b)).getName()) == javafx.scene.control.Dialogs.DialogResponse.YES) {
            Repeat.connectordb.deletProduct(((AdminPane)Repeat.admprod.get(b)).getId());
            Repeat.connectordb.setLog((AdminPane)Repeat.admprod.get(b), (AdminPane)Repeat.admprod.get(b), 2, Repeat.user.getName());
            Repeat.admprod.remove(b);
            Dialogs.showInformationDialog(st, "Категория удалена.");


          }
        }
        else
        {

          Dialogs.showErrorDialog(st, "Ничего не выбрано");
        }
      }
    });
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
    
    HBox button = new HBox();
    HBox button1 = new HBox();
    HBox button2 = new HBox();
    HBox button3 = new HBox();
    HBox button4 = new HBox();
    HBox button5 = new HBox();
    HBox button6 = new HBox();
    

    button.setMinWidth(250.0D);
    button.setSpacing(5.0D);
    button1.setSpacing(5.0D);
    button2.setSpacing(5.0D);
    button3.setSpacing(8.0D);
    button4.setSpacing(8.0D);
    button5.setSpacing(8.0D);
    button6.setSpacing(8.0D);
    
    button.getChildren().addAll(new Node[] { add, cor, del });
    button1.getChildren().addAll(new Node[] { contract, zakazi });
    button2.getChildren().addAll(new Node[] { cgroup, print });
    button4.getChildren().addAll(new Node[] { cproduct, cproductadd });
    button5.getChildren().addAll(new Node[] { productaddnow, productaddlist });
    


    Button search = new Button("Поиск");
    final Popup popup = new Popup();
    final Popup popupgroup = new Popup();
    
    Button popupok = new Button("Поиск");
    Button popupclose = new Button("Закрыть");
    Button search_group = new Button("Показать");
    Button pop_group_go = new Button("Группы");
    Button status = new Button("Упр. статус.");
    
    SplitMenuButton show_status = new SplitMenuButton();
    
    show_status.setText("Показать");
    
    show_status.setPopupSide(javafx.geometry.Side.LEFT);
    show_status.setAlignment(Pos.CENTER);
    
    MenuItem ss1 = new MenuItem(" 0 - активные ");
    MenuItem ss2 = new MenuItem(" 1 - малоактивные. ");
    MenuItem ss3 = new MenuItem(" 2 - неактивные. ");
    
    ss1.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Repeat.connectordb.getProductForStatusAdmin(Repeat.admprod, 0);
      }
      
    });
    ss2.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Repeat.connectordb.getProductForStatusAdmin(Repeat.admprod, 1);
      }
      
    });
    ss3.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent t)
      {
        Repeat.connectordb.getProductForStatusAdmin(Repeat.admprod, 2);
      }
      

    });
    show_status.getItems().addAll(new MenuItem[] { ss1, ss2, ss3 });
    
    Button close = new Button("Назад");
    
    search.setId("dark-blue");
    search_group.setId("dark-blue");
    close.setId("dark-blue");
    popupclose.setId("dark-blue");
    popupok.setId("dark-blue");
    search_group.setId("dark-blue");
    pop_group_go.setId("dark-blue");
    status.setId("dark-blue");
    
    button3.getChildren().addAll(new Node[] { search, pop_group_go, status });
    button6.getChildren().addAll(new Node[] { status, show_status });
    

    final ChoiceBox<String> chois = new ChoiceBox(group);
    

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
        int d = Repeat.admprod.size();
        if (!box.getText().equals("")) {
          int sid = Repeat.connectordb.setID(box.getText());
          for (int i = 0; i < d; i++) if (sid == ((AdminPane)Repeat.admprod.get(i)).getId()) {
              tb.scrollTo(i);
              tb.getSelectionModel().select(i);
              break;
            }
        }
        else if (!boxcode.getText().equals("")) {
          try {
            int sid = Integer.parseInt(boxcode.getText());
            for (int i = 0; i < d; i++) if (sid == ((AdminPane)Repeat.admprod.get(i)).getId()) {
                tb.scrollTo(i);
                tb.getSelectionModel().select(i);
                break;
              }
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(st, "Неправильный формат кода товара");
          }
        }
        box.getTextbox().setText("");
        boxcode.getTextbox().setText("");
        popup.hide();
      }
      
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Repeat.this.clearPane(root);
        if (popup.isShowing()) popup.hide();
        if (popupgroup.isShowing()) popupgroup.hide();
        root.setRight(but);

      }
      

    });
    status.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        ActualStatusProduct abc = new ActualStatusProduct();
        abc.setProductStatus(2);
        abc.start(new Stage());

      }
      


    });
    search.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (!popup.isShowing()) {
          popup.setX(event.getScreenX() - 620.0D);
          popup.setY(event.getScreenY() - 20.0D);
          popup.show(st);
        } else {
          popup.hide();
        }
      } });
    pop_group_go.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        if (!popupgroup.isShowing()) {
          popupgroup.setX(event.getScreenX() - 420.0D);
          popupgroup.setY(event.getScreenY() - 10.0D);
          popupgroup.show(st);
          
          chois.show();
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
          int group_send = Integer.parseInt(p);
          Repeat.connectordb.getGroupProductAdmin(Repeat.admprod, group_send, 0);
        }
        
        popupgroup.hide();
      }
      

    });
    node.getChildren().addAll(new Node[] { texoper, button, button2, texcontract, button1, searcsort, button3, order_product, button4, product_add_now, button5, otchetzakup, use_status_product, button6, close });
    return node;
  }
  
  private GridPane createVBoxHistory(final BorderPane root, TableView tb)
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
    if (!user.getRules(5)) ok.setDisable(true);
    Button otchet = new Button("Отчет");
    if (!user.getRules(5)) otchet.setDisable(true);
    Button grap = new Button("График продаж");
    if (!user.getRules(5)) grap.setDisable(true);
    Button grapmetall = new Button("График приема");
    if (!user.getRules(20)) grapmetall.setDisable(true);
    Button otchetzakup = new Button("История закупок");
    
    Button histmetall = new Button("История металл");
    if (!user.getRules(20)) { histmetall.setDisable(true);
    }
    Button statistic_product = new Button("Управление статусами");
    
    Button ext = new Button("Hазад");
    
    otchetzakup.setId("dark-blue");
    ok.setId("dark-blue");
    otchet.setId("dark-blue");
    ext.setId("dark-blue");
    grap.setId("dark-blue");
    histmetall.setId("dark-blue");
    grapmetall.setId("dark-blue");
    statistic_product.setId("dark-blue");
    
    grap.setMinSize(20.0D, 30.0D);
    
    ok.setMinSize(20.0D, 30.0D);
    otchet.setMinSize(20.0D, 30.0D);
    histmetall.setMinSize(20.0D, 30.0D);
    statistic_product.setMinSize(20.0D, 30.0D);
    grapmetall.setMinSize(20.0D, 30.0D);
    ext.setMinSize(20.0D, 30.0D);
    otchetzakup.setMinSize(20.0D, 30.0D);
    

    otchetzakup.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        HistoryProcurement abc = new HistoryProcurement();
        abc.getContract(0);
        abc.start(new Stage());
      }
      
    });
    histmetall.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        HistoryMetall abc = new HistoryMetall();
        abc.getContract(0);
        abc.start(new Stage());
      }
      

    });
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        root.getChildren().clear();
        root.getChildren().add(but);
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
          Repeat.connectordb.getHistory(dateField.getText(), dataField.getText(), Repeat.historyprod);
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
      }
    });
    statistic_product.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        ActualStatusProduct abc = new ActualStatusProduct();
        abc.setProductStatus(2);
        abc.start(new Stage());
      }
    });
    grap.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher n = p.matcher(dataField.getText());
        GUI.GrapficsHist a; if ((m.matches()) && (n.matches())) {
          a = new GUI.GrapficsHist(dateField.getText(), dataField.getText());
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
        
      }
    });
    grapmetall.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher n = p.matcher(dataField.getText());
        GUI.GraphicsHistMetall a; if ((m.matches()) && (n.matches())) {
          a = new GUI.GraphicsHistMetall(dateField.getText(), dataField.getText());
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
      }
    });
    otchet.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher n = p.matcher(dataField.getText());
        if ((m.matches()) && (n.matches())) {
          Otchet ot = new Otchet();
          ot.start(new Stage());
          ot.setProdHistory(dateField.getText(), dataField.getText());
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
      }
    });
    Label l1 = new Label("История продаж");
    Label l2 = new Label("История закупок");
    Label l3 = new Label("История приема лома.");
    
    l1.setId("labeladmin");
    l2.setId("labeladmin");
    l3.setId("labeladmin");
    
    node.add(l1, 0, 0, 2, 1);
    node.add(simpleCalender, 0, 1);
    node.add(dateField, 1, 1);
    node.add(simpleCalendar, 0, 2);
    node.add(dataField, 1, 2);
    HBox buttongroup1 = new HBox();
    buttongroup1.setSpacing(5.0D);
    buttongroup1.getChildren().addAll(new Node[] { ok, otchet });
    


    node.add(buttongroup1, 1, 3, 2, 1);
    node.add(grap, 1, 4, 2, 1);
    node.add(l2, 0, 5, 2, 1);
    node.add(otchetzakup, 1, 6, 2, 1);
    node.add(l3, 0, 7, 2, 1);
    node.add(histmetall, 1, 8, 2, 1);
    node.add(grapmetall, 1, 9, 2, 1);
    node.add(ext, 1, 11, 1, 1);
    
    return node;
  }
  
  private VBox createVBoxSeePrice(final BorderPane root, final VBox but, TableView tb) {
    VBox main = new VBox();
    main.setSpacing(2.0D);
    main.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    Button menu = new Button(" Меню");
    Button ext = new Button("Назад");
    menu.setMinHeight(60.0D);
    ext.setMinHeight(60.0D);
    PFilterPriceView abc = new PFilterPriceView(prices, tb);
    final Popup popupmenu = abc.getMain();
    
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (popupmenu.isShowing()) popupmenu.hide();
        Repeat.this.clearPane(root);
        root.setRight(but);
      }
      
    });
    ext.setId("dark-blue");
    menu.setId("dark-blue");
    
    main.setAlignment(Pos.CENTER_RIGHT);
    main.getChildren().addAll(new Node[] { menu, ext });
    
    final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    menu.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (!popupmenu.isShowing()) {
          popupmenu.setX(sSize.getWidth() / 2.0D - 300.0D);
          popupmenu.setY(sSize.getHeight() / 2.0D - 150.0D);
          popupmenu.show(st);
        } else {
          popupmenu.hide();
        }
        
      }
      

    });
    return main;
  }
  
  private TableView<Prices> createTablePrice() {
    TableView<Prices> table = new TableView();
    
    table.columnResizePolicyProperty();
    TableColumn code = new TableColumn("№");
    code.setMinWidth(60.0D);
    code.setCellValueFactory(new PropertyValueFactory("code"));
    
    TableColumn id = new TableColumn("Код");
    id.setMinWidth(70.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("Наименование продукта");
    name.setMinWidth(610.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn helf = new TableColumn("Группа");
    helf.setMinWidth(60.0D);
    helf.setCellValueFactory(new PropertyValueFactory("group"));
    
    TableColumn actual_status = new TableColumn("Статус");
    actual_status.setMinWidth(60.0D);
    actual_status.setCellValueFactory(new PropertyValueFactory("actual_status"));
    

    TableColumn col = new TableColumn("Остаток");
    col.setMinWidth(70.0D);
    col.setCellValueFactory(new PropertyValueFactory("size"));
    

    TableColumn price = new TableColumn("Стоимость");
    price.setMinWidth(100.0D);
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    table.setStyle("-fx-font: normal 11 Arial;-fx-font-header: normal 11 Arial;");
    
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    table.setMaxWidth(1186.0D);
    table.getColumns().addAll(new TableColumn[] { code, id, name, helf, actual_status, col, price });
    table.setItems(prices);
    
    return table;
  }
  

  private VBox getLogViewPopap(final BorderPane root, TableView<Log_view> t)
  {
    VBox a = new VBox();
    a.setSpacing(2.0D);
    

    Button menu = new Button(" Меню");
    Button ext = new Button("Назад");
    menu.setMinHeight(60.0D);
    ext.setMinHeight(60.0D);
    final Popup popupmenu = new Popup();
    
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (popupmenu.isShowing()) popupmenu.hide();
        Repeat.this.clearPane(root);
        root.setRight(but);

      }
      

    });
    ext.setId("dark-blue");
    menu.setId("dark-blue");
    a.setAlignment(Pos.CENTER_RIGHT);
    a.getChildren().addAll(new Node[] { menu, ext });
    


    GridPane gridmenu = getGridJournal(root, t, popupmenu);
    final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    popupmenu.getContent().addAll(new Node[] { gridmenu });
    
    menu.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (!popupmenu.isShowing()) {
          popupmenu.setX(sSize.getWidth() - 240.0D);
          popupmenu.setY(65.0D);
          popupmenu.show(st);
        }
        else
        {
          popupmenu.hide();

        }
        
      }
      

    });
    return a;
  }
  
  private GridPane getGridJournal(BorderPane root, final TableView<Log_view> a, final Popup popupmenu)
  {
    GridPane node = new GridPane();
    final ConnectDB db = new ConnectDB();
    
    node.setId("but");
    node.setVgap(8.0D);
    node.setHgap(8.0D);
    
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
    final Popup popupfiltr = new Popup();
    


    VBox vboxfiltr = createFiltrHistory(a, popupfiltr);
    
    popupfiltr.getContent().add(vboxfiltr);
    

    Button ok = new Button("Показать");
    Button f = new Button("Фильтр");
    Button ext = new Button("Закрыть");
    
    f.setId("dark-blue");
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    
    HBox box = new HBox();
    
    box.setSpacing(8.0D);
    box.getChildren().addAll(new Node[] { ok, f });
    
    final CheckBox add = new CheckBox();
    final CheckBox cor = new CheckBox();
    final CheckBox del = new CheckBox();
    node.add(simpleCalender, 0, 0);
    node.add(dateField, 1, 0);
    node.add(simpleCalendar, 0, 1);
    node.add(dataField, 1, 1);
    node.add(add, 0, 2);
    node.add(new Label(" - Добавленные"), 1, 2);
    node.add(del, 0, 3);
    node.add(new Label(" - Удаленные"), 1, 3);
    node.add(cor, 0, 4);
    node.add(new Label(" - Измененные"), 1, 4);
    node.add(box, 1, 5);
    node.add(ext, 1, 6);
    
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {

        if (popupfiltr.isShowing()) popupfiltr.hide();
        if (popupmenu.isShowing()) { popupmenu.hide();
        }
        
      }
    });
    final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    f.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        if (popupfiltr.isShowing()) { popupfiltr.hide();
        } else {
          popupfiltr.setX(sSize.getWidth() - 832.0D);
          popupfiltr.setY(115.0D);
          popupfiltr.show(st);
        }
        
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
          a.setItems(db.getJournal(dateField.getText(), dataField.getText() + " 23:59", add.selectedProperty().get(), del.selectedProperty().get(), cor.selectedProperty().get()));
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
      }
    });
    return node;
  }
  
  private VBox createFiltrHistory(final TableView<Log_view> a, final Popup popupfiltr) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("but");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<Integer> datacode = FXCollections.observableArrayList();
    
    connectordb.loadProduct(data);
    connectordb.loadProductId(datacode);
    
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
    
    Button ok = new Button("Показать");
    Button ext = new Button("Закрыть");
    
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    
    HBox button = new HBox();
    button.setSpacing(8.0D);
    button.getChildren().addAll(new Node[] { ok, ext });
    
    HBox getdate = new HBox();
    getdate.setSpacing(8.0D);
    
    SimpleCalendar s1 = new SimpleCalendar();
    SimpleCalendar s2 = new SimpleCalendar();
    final TextField d1 = new TextField("Select date");
    final TextField d2 = new TextField("Select date");
    s1.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        d1.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    s2.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        d2.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        String name = box.getText();
        String id_product = boxcode.getText();
        if (name.length() > 0)
        {
          int id = Repeat.connectordb.setID(name);
          Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
          
          Matcher m = p.matcher(d1.getText());
          Matcher n = p.matcher(d2.getText());
          if ((m.matches()) && (n.matches())) {
            a.setItems(Repeat.connectordb.getJournalFiltr(d1.getText(), d2.getText() + " 23:59", id));
            popupfiltr.hide();
          } else {
            Dialogs.showErrorDialog(st, "Неверный формат даты");
          }
        }
        else if (id_product.length() > 0) {
          try {
            int id = Integer.parseInt(id_product);
            Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
            
            Matcher m = p.matcher(d1.getText());
            Matcher n = p.matcher(d2.getText());
            if ((m.matches()) && (n.matches())) {
              a.setItems(Repeat.connectordb.getJournalFiltr(d1.getText(), d2.getText() + " 23:59", id));
              popupfiltr.hide();
            } else {
              Dialogs.showErrorDialog(st, "Неверный формат даты");
            }
            

          }
          catch (NumberFormatException ex) {}
        }
      }
    });
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        popupfiltr.hide();
      }
      
    });
    getdate.getChildren().addAll(new Node[] { s1, d1, s2, d2 });
    

    menu.getChildren().addAll(new Node[] { box, boxcode, getdate, button });
    return menu;
  }
}
