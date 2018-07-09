package GUI;

import Collection.Cassa;
import Collection.GateReg;
import Collection.Registration;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import fxuidemo.Repeat;
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
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.FocusModel;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewFocusModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProduct extends Application
{
  ObservableList<GateReg> prod = FXCollections.observableArrayList();
  
  public String USER_NAME;
  private int ni = 1;
  

  public AddProduct(ObservableList<Registration> p){
      
  }
  

  public void start(Stage stage)
  {
    ObservableList<String> data = FXCollections.observableArrayList();
    

    ObservableList<Integer> cdata = FXCollections.observableArrayList();
    ConnectDB db = new ConnectDB();
    db.loadProduct(data);
    db.loadProductId(cdata);
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20.0D, 20.0, 20.0, 20.0));
    VBox but = createBut(stage);
    
    stage.setTitle("Регистрация продаж");
    root.setId("bp");
    but.setId("but");
    
    root.setRight(but);
    
    root.setCenter(createGrid(data, cdata, db, stage, but));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    stage.setWidth(1080.0);
    stage.setHeight(680.0);
    stage.show();
  }
  

  private VBox createBut(final Stage stage)
  {
    VBox node = new VBox();
    Label lbl = new Label();
    node.setSpacing(8.0);
    Button add = new Button("Добавить");
    Button clear = new Button("Удалить");
    Button good = new Button("Готово");
    
    Button close = new Button("Закрыть");
    
    add.setMinSize(130.0, 50.0);
    clear.setMinSize(130.0, 50.0);
    good.setMinSize(130.0, 50.0);
    
    close.setMinSize(130.0, 50.0);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (prod.size() > 1) {
          Dialogs.DialogResponse response = Dialogs.showConfirmDialog(stage, "Список товаров не пуст, закрыть окно?", "Confirm Dialog With Options", "Предупреждение", Dialogs.DialogOptions.OK_CANCEL);
          if (response == Dialogs.DialogResponse.OK) stage.close();
        } else {
          stage.close();
        }
      }
    });
    node.getChildren().addAll(new Node[] { add, clear, good, close });
    return node;
  }
  
  private GridPane createGrid(ObservableList data1, ObservableList cdata, final ConnectDB db, final Stage stage, VBox but)
  {
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
    grid.setHgap(5.0);
    grid.setVgap(5.0);
    
    final AutoFillTextBox box = new AutoFillTextBox(data1);
    final AutoFillTextBox codebox = new AutoFillTextBox(cdata);
    
    final RadioButton st0 = new RadioButton();
          st0.setSelected(true);
    final RadioButton st1 = new RadioButton();
    
    ToggleGroup tggroup = new ToggleGroup();
    st0.setToggleGroup(tggroup);
    st1.setToggleGroup(tggroup);
    
    HBox rb = new HBox();
    
    rb.getChildren().addAll(new Label("Воронцово: "),st0,new Label("Ильинск: "),st1);
    
    box.setListLimit(100);
    codebox.setListLimit(100);
    box.getListview().setMinHeight(100.0);
    box.setMinWidth(704.0);
    codebox.setMinWidth(704.0);
    box.setId("box");
    codebox.setId("box");
    
    final TextField size = new TextField();
    size.setText("1");
    
    final TextField stock_event = new TextField();
    stock_event.setDisable(true);
    
    int gy = 1;
    grid.add(new Label("Наименование: "), 0, 0);
    grid.add(box, 1, 0, 1, 1);
    
    grid.add(new Label("По коду: "), 0, 1);
    grid.add(codebox, 1, 1, 1, 1);
    
    grid.add(new Label("Количество: "), 0, 2);
    grid.add(size, 1, 2, gy, 1);
    
    grid.add(new Label("Склад: "), 0, 3);
    grid.add(rb, 1, 3, gy, 1);
    
    final TextField price = new TextField();
    price.setDisable(true);
    
    grid.add(new Label("Цена: "), 0, 4);
    grid.add(price, 1, 4, gy, 1);
    
    final TextField summa = new TextField();
    summa.setDisable(true);
    grid.add(new Label("Cумма: "), 0, 5);
    grid.add(summa, 1, 5, gy, 1);
    
    grid.add(new Label("Склад: "), 0, 6);
    grid.add(stock_event, 1, 6, gy, 1);
    
    final TextField control = new TextField();
    control.setDisable(true);
    grid.add(new Label("Общее: "), 0, 7);
    grid.add(control, 1, 7, gy, 1);
    
    final TableView<GateReg> tl = createTableReg();
    
    prod.add(new GateReg());
    grid.add(tl, 0, 9, 2, 1);
    
    
    st0.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
          String sn = box.getText();
          
          if(!sn.isEmpty()){
            int id_product = db.setID(sn);
            //System.out.println(st1.isSelected());
            int stock_full[] = db.getSize(id_product);            
            stock_event.setText(""+stock_full[1]);
          }
          
      }
      

    });
    
    st1.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
          String sn = box.getText();
          
          if(!sn.isEmpty()){
            int id_product = db.setID(sn);
            //System.out.println(st1.isSelected());
            int stock_full[] = db.getSize(id_product);            
            stock_event.setText(""+stock_full[2]);
          }
          
      }
      

    });

    box.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        if (t.getCode() == KeyCode.ENTER) {
          /*String sn = box.getText();
          try {
            int ssize = Integer.parseInt(size.getText());
            price.setText(Double.toString(db.price(sn)));
            control.setText(String.valueOf(db.getSize(sn)));
            if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
            size.setText("1");
          }
          */
            
        String sn = box.getText();
        try {
          int id_product = db.setID(sn);
          int ssize = Integer.parseInt(size.getText());

          price.setText(Double.toString(db.price(id_product)));
          
          int[] stock_full = db.getSize(id_product);
          System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
          
          control.setText(String.valueOf(stock_full[0]));
          
          int bds = -1;
          int stock_gate = -1;
          if(st0.isSelected()){
              bds = stock_full[1];
              stock_gate = 0;
          }
            else if(st1.isSelected()){
                bds = stock_full[2];
                stock_gate = 1;
            }
                else bds = -1;
          
          stock_event.setText(String.valueOf(bds));
          if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
          size.setText("1");
        }    
        }
        
      }
      
    });
    box.getListview().setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        String sn = box.getText();
        try {
          int id_product = db.setID(sn);
          int ssize = Integer.parseInt(size.getText());

          price.setText(Double.toString(db.price(id_product)));
          
          int[] stock_full = db.getSize(id_product);
          System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
          
          control.setText(String.valueOf(stock_full[0]));
          
          int bds = -1;
          int stock_gate = -1;
          if(st0.isSelected()){
              bds = stock_full[1];
              stock_gate = 0;
          }
            else if(st1.isSelected()){
                bds = stock_full[2];
                stock_gate = 1;
            }
                else bds = -1;
          
          stock_event.setText(String.valueOf(bds));
          if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
          size.setText("1");
        }
        
      }
      
    });
    codebox.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        if (t.getCode() == KeyCode.ENTER) {
          try
          {
            int id_product = Integer.parseInt(codebox.getText());
            String name_product = db.getName(id_product);
            int ssize = Integer.parseInt(size.getText());
            box.getTextbox().setText(name_product);
            
            box.getListview().getFocusModel().isFocused(1);
            

            price.setText(Double.toString(db.price(id_product)));
            
            int[] stock_full = db.getSize(id_product);
            System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
            
            control.setText(String.valueOf(stock_full[0]));
            
            if(st0.isSelected())stock_event.setText(String.valueOf(stock_full[1]));// = stock_full[1];
                else if(st1.isSelected())stock_event.setText(String.valueOf(stock_full[2]));
            
            if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
            size.setText("1");
          }
          
        }
        
      }
      
    });
    size.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String sn = box.getText();
        try {
          int sc = Integer.parseInt(size.getText());
          summa.setText(Double.toString(sc * Double.parseDouble(price.getText())));
        }
        catch (NumberFormatException ex) {
          size.setText("");
          summa.setText("0.0");
        }
        
      }
    });
    codebox.getListview().setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        try
        {
            int id_product = Integer.parseInt(codebox.getText());
            String name_product = db.getName(id_product);
            int ssize = Integer.parseInt(size.getText());
            box.getTextbox().setText(name_product);
            
            box.getListview().getFocusModel().isFocused(1);
            

            price.setText(Double.toString(db.price(id_product)));
            
            int[] stock_full = db.getSize(id_product);
            System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
            control.setText(String.valueOf(stock_full[0]));
            if(st0.isSelected())stock_event.setText(String.valueOf(stock_full[1]));// = stock_full[1];
            else if(st1.isSelected())stock_event.setText(String.valueOf(stock_full[2]));
            
            if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
          size.setText("1");
        }
        
      }
      
    });
    ((Node)but.getChildren().get(0)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        String sn = box.getText();
        try {
          int sc = Integer.parseInt(size.getText());
          int bds_full[] = db.getSize(sn);
          
          int bds = -1;
          int stock_gate = -1;
          if(st0.isSelected()){
              bds = bds_full[1];
              stock_gate = 0;
          }
            else if(st1.isSelected()){
                bds = bds_full[2];
                stock_gate = 1;
            }
                else bds = -1;
          
          
          
          double p = Double.parseDouble(price.getText());
          double sum = Double.parseDouble(summa.getText());
          
          if ((sc > 0) && (p > 0.0D) && (sum > 0.0D) && (bds >= sc))
          {
            // неизвестен первый аргумент  AddProduct.access$008(AddProduct.this)
            prod.add(0, new GateReg(AddProduct.this.ni, sn, Integer.toString(db.setID(sn)), size.getText(), price.getText(), Double.parseDouble(summa.getText()), stock_gate));
            int index = prod.size() - 1;
            double sumbreak = ((GateReg)prod.get(index)).getSum();
            sumbreak += sum;
            ((GateReg)prod.get(index)).setSum(sumbreak);
            
            box.getTextbox().setText("");
            codebox.getTextbox().setText("");
            size.setText("1");
            price.setText("0.0");
            summa.setText("0.0");
            control.setText("0");
          }
          else {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Сколько там ?");
          }
        }
        catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Сколько там ?");
        }
      }
    });
    ((Node)but.getChildren().get(1)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        int n = tl.getFocusModel().getFocusedIndex();
        int lim = prod.size() - 1;
        if ((n != lim) && (n != -1)) {
          double sum = ((GateReg)prod.get(n)).getSum();
          double sumbreak = ((GateReg)prod.get(lim)).getSum();
          sumbreak -= sum;
          ((GateReg)prod.get(lim)).setSum(sumbreak);
          prod.remove(n);
        } else {
          Dialogs.showErrorDialog(stage, "Ничего не выбрано.");
        }
      }
    });
    ((Node)but.getChildren().get(2)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        int lim = prod.size() - 1;
        int validatesize = AddProduct.this.validateSizeProduct(prod);
        
        if (validatesize == -1) {
          for (int i = 0; i < lim; i++) {
            String name = ((GateReg)prod.get(i)).getName();
            int a = Integer.parseInt(((GateReg)prod.get(i)).getSize());

            int pid = Integer.parseInt(((GateReg)prod.get(i)).getId());
            
            int bds_full[] = db.getSize(pid);
            int sid = -1;
            int stock_gate = prod.get(i).getStock();
            
            if(stock_gate == 0){
                sid = bds_full[1];
                //stock_gate = 0;
            }
            else if(stock_gate == 1){
                sid = bds_full[2];
                //stock_gate = 1;
            }
            
            if ((sid <= 0) || (sid < a))
            {
              Dialogs.showErrorDialog(stage, "Фатальная ошибка , пожалуйста сообщите администратору.\nОшибка номер 400, \nID ошибочная строка:" + pid + "\n" + "Снимок экрана, кнопка  prt sc.", "Error Dialog", "");

            }
            else
            {
            /*
            *
            * запись в БД
            *
            */

              double c = Double.parseDouble(((GateReg)prod.get(i)).getPrice());
              double d = ((GateReg)prod.get(i)).getSum();
              
              if ((a > 0) && (d > 0.0D) && (c > 0.0D)) {
                int balance = bds_full[0] - a;
                int balance_event_stock = sid - a;
                db.reliz_day(pid, name, balance, a, stock_gate, c, d, Repeat.USER_NAME);
                Repeat.prod.add(new Registration(AddProduct.this.Time_m(), name, Integer.valueOf(pid), Integer.valueOf(a),stock_gate, c, d, Repeat.USER_NAME));
                
                db.setSize(pid, balance, stock_gate, balance_event_stock);
                Cassa.cassa += d;
                Cassa.setCassa();
              }
            }
          }
          
          prod.remove(0, prod.size());
          prod.add(new GateReg());
        } else {
          Dialogs.showErrorDialog(stage, "Ошибка номер 402.\nНет необходимого количества товара, номер строки: " + validatesize, "Error Dialog", "");
        }
        
      }
    });
    return grid;
  }
  
  private TableView<GateReg> createTableReg() { 
    TableView<GateReg> table = new TableView<GateReg>();

    TableColumn n = new TableColumn("№");
    n.setMinWidth(30.0D);
    n.setMaxWidth(40.0D);
    n.setCellValueFactory(new PropertyValueFactory("n"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(450.0);
    name.setMaxWidth(480.0);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    TableColumn id = new TableColumn("Код");
    id.setMinWidth(58.0);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(50.0);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    
    TableColumn stock = new TableColumn("Склад");
    stock.setMinWidth(50.0);
    stock.setCellValueFactory(new PropertyValueFactory("stock"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(90.0);
    
    price.setCellValueFactory(new PropertyValueFactory("price"));
    

    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(90.0);
    
    sum.setCellValueFactory(new PropertyValueFactory("sum"));
 
    table.setStyle("-fx-font: normal 11 Arial;");
    table.setMaxWidth(820.0);
    table.setMaxHeight(350.0);
    
    table.setItems(prod);
    
    table.getColumns().addAll(n, name, id, size,stock, price, sum );
    

    return table;
  }
  
  private String Time_m() { SimpleDateFormat s = new SimpleDateFormat("HH:mm");
    return s.format(new Date());
  }
  

  private int validateSizeProduct(ObservableList<GateReg> g)
  {
    ConnectDB db = new ConnectDB();
    
    int n = g.size() - 1;
    int errorrow = -1;
    if (n == 0) { 
            errorrow = 0;
    }
    
    int stock_event = -1;
    
    for (int i = 0; i < n; i++)
    {
      stock_event  = ((GateReg)prod.get(i)).getStock();
      int a = Integer.parseInt(((GateReg)prod.get(i)).getSize());
      int b_full[] = db.getSize(Integer.parseInt(((GateReg)g.get(i)).getId()));
      int b = -1;
      if(stock_event == 0)b = b_full[1];
      else if(stock_event == 1)b = b_full[2];
      if (a > b) errorrow = i;
    }
    return errorrow;
  }
}
