package GUI;

import Collection.Cassa;
import Collection.GateReg;
import Collection.Person;
import Collection.contract;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import calendar.SimpleCalendar;
import fxuidemo.Repeat;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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

public class AddContract extends javafx.application.Application
{
  ObservableList<GateReg> gatereg = FXCollections.observableArrayList();
  private int index = 0;
  

  public AddContract() {}
  

  public void start(Stage stage)
  {
    ObservableList<String> data = FXCollections.observableArrayList();
    ConnectDB db = new ConnectDB();
    db.loadProduct(data);
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(20.0, 20.0, 20.0, 20.0));
    VBox but = createBut(stage);
    stage.setTitle("Договор с рассрочкой платежа");
    root.setId("bp");
    but.setId("but");
    
    root.setRight(but);
    
    root.setCenter(createGrid(data, db, stage, but));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    stage.setWidth(1100.0D);
    stage.setHeight(750.0D);
    stage.show();
  }
  
  private VBox createBut(final Stage stage)
  {
    VBox node = new VBox();
    node.setSpacing(40.0D);
    Button add = new Button("Добавить");
    Button clear = new Button("Удалить");
    Button good = new Button("Готово");
    Button close = new Button("Закрыть");
    

    add.setMinSize(130.0D, 50.0D);
    clear.setMinSize(130.0D, 50.0D);
    good.setMinSize(130.0D, 50.0D);
    close.setMinSize(130.0D, 50.0D);
    VBox one = new VBox();
    VBox two = new VBox();
    one.setSpacing(8.0D);
    two.setSpacing(8.0D);
    one.getChildren().addAll(new Node[] { good, close });
    two.getChildren().addAll(new Node[] { add, clear });
    close.setOnMouseClicked(new EventHandler<MouseEvent>(){
      public void handle(MouseEvent event)
      {
        stage.close();
      }
      
    });
    node.getChildren().addAll(new Node[] { one, two });
    return node;
  }
  
  private GridPane createGrid(ObservableList data1, final ConnectDB db, final Stage stage, VBox but) {
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10.0D, 20.0D, 10.0D, 20.0D));
    grid.setHgap(5.0D);
    grid.setVgap(5.0D);
    
    //ObservableList<String> years = FXCollections.observableArrayList();
    //for (int i = 1940; i < 1999; i++)years.add(Integer.toString(i));
    
    final AutoFillTextBox box = new AutoFillTextBox(data1);
    final TextField year = new TextField();
    box.setListLimit(100);
    box.setMinWidth(704.0D);
    box.setId("box");
    
    final TextField size = new TextField();
    final TextField name = new TextField();
    final TextField doc = new TextField();
    final TextField docinfo = new TextField();
    final TextField firstsum = new TextField();
    final TextField customer_doc_cout = new TextField();
    final TextField customer_phone = new TextField();
    firstsum.setMaxWidth(430.0D);
    customer_phone.setMaxWidth(420.0D);
    HBox year_date = new HBox();
    year_date.setSpacing(8.0D);
    year_date.setAlignment(Pos.CENTER_LEFT);
    
    final RadioButton st0 = new RadioButton();
          st0.setSelected(true);
    final RadioButton st1 = new RadioButton();
    
    ToggleGroup tggroup = new ToggleGroup();
    st0.setToggleGroup(tggroup);
    st1.setToggleGroup(tggroup);
    
    HBox rb = new HBox();
    
    rb.getChildren().addAll(new Label("Воронцово: "),st0,new Label("Ильинск: "),st1);
    
    final TextField stock_event = new TextField();
    stock_event.setDisable(true);
    
    
    
    SimpleCalendar simpleCalendar1 = new SimpleCalendar();
    SimpleCalendar simpleCalendar2 = new SimpleCalendar();
    final TextField dateField = new TextField("");
    dateField.setDisable(true);
    simpleCalendar1.dateProperty().addListener(new ChangeListener<Date>()
    {

      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dateField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    simpleCalendar2.dateProperty().addListener(new ChangeListener<Date>()
    {

      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        year.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    year_date.getChildren().addAll(new Node[] { simpleCalendar2, year, new Label("Договор сроком до: "), simpleCalendar1, dateField });
    
    int gy = 1;
    doc.setText("/");
    grid.add(new Label("ФИО: "), 0, 0);
    grid.add(name, 1, 0, gy, 1);
    grid.add(new Label("Cерия/номер: "), 0, 1);
    grid.add(doc, 1, 1, gy, 1);
    grid.add(new Label("Адрес регистр.: "), 0, 2);
    grid.add(docinfo, 1, 2, gy, 1);
    grid.add(new Label("Выдан(кем): "), 0, 3);
    grid.add(customer_doc_cout, 1, 3, gy, 1);
    

    grid.add(new Label("Выдан(когда) : "), 0, 4);
    grid.add(year_date, 1, 4, gy, 1);
    grid.add(new Label("Телефон: "), 0, 5);
    grid.add(customer_phone, 1, 5, gy, 1);
    grid.add(new Label("Первый взнос: "), 0, 6);
    grid.add(firstsum, 1, 6, gy, 1);
    grid.add(new Label("______________________________________________________________________________________________________________________"), 0, 7, 4, 1);
    size.setText("1");
    grid.add(new Label("Наименование: "), 0, 8);
    grid.add(box, 1, 8, 1, 1);
    

    grid.add(new Label("Количество: "), 0, 9);
    grid.add(size, 1, 9, gy, 1);
    
    grid.add(new Label("Склад: "), 0, 10);
    grid.add(rb, 1, 10, gy, 1);
    
    final TextField price = new TextField();
    price.setDisable(true);
    
    grid.add(new Label("Цена: "), 0, 11);
    grid.add(price, 1, 11, gy, 1);
    
    final TextField summa = new TextField();
    summa.setDisable(true);
    grid.add(new Label("Cумма: "), 0, 12);
    grid.add(summa, 1, 12, gy, 1);
    
    grid.add(new Label("Склад: "), 0, 13);
    grid.add(stock_event, 1, 13, gy, 1);
    
    final TextField control = new TextField();
    control.setDisable(true);    
    grid.add(new Label("Общее: "), 0, 14);
    grid.add(control, 1, 14, gy, 1);
    
    final TableView<GateReg> tl = createTableReg();
    

    grid.add(tl, 0, 15, 2, 1);
    
    
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
          }*/
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
    box.getListview().setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        /*String sn = box.getText();
        try {
          int ssize = Integer.parseInt(size.getText());
          price.setText(Double.toString(db.price(sn)));
          control.setText(String.valueOf(db.getSize(sn)));
          if (ssize > 0) summa.setText(Double.toString(ssize * Double.parseDouble(price.getText())));
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
          size.setText("1");
        }*/
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
    ((Node)((VBox)but.getChildren().get(1)).getChildren().get(0)).setOnMouseClicked(new EventHandler<MouseEvent>()
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
          
          int type_cash = 0;// нет выбора оплаты
          
          if ((sc > 0) && (p > 0.0) && (sum > 0.0) && (bds >= sc))
          {
              // первый аргумент неизвестен - AddContract.this.index
            gatereg.add(0, new GateReg(AddContract.this.index++, sn, Integer.toString(db.setID(sn)), size.getText(), price.getText(), Double.parseDouble(summa.getText()),stock_gate,type_cash));
            int index = gatereg.size() - 1;
            double sumbreak = ((GateReg)gatereg.get(index)).getSum();
            sumbreak += sum;
            ((GateReg)gatereg.get(index)).setSum(sumbreak);
            
            box.getTextbox().setText("");
            size.setText("1");
            price.setText("0.0");
            summa.setText("0.0");
            control.setText("0");
          }
          else {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "# 99b");
          }
        }
        catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "");
        }
      }
    });
    ((Node)((VBox)but.getChildren().get(1)).getChildren().get(1)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try {
          int n = tl.getFocusModel().getFocusedIndex();
          int lim = gatereg.size() - 1;
          if ((n != lim) && (n != -1)) {
            double sum = ((GateReg)gatereg.get(n)).getSum();
            double sumbreak = ((GateReg)gatereg.get(lim)).getSum();
            sumbreak -= sum;
            ((GateReg)gatereg.get(lim)).setSum(sumbreak);
            gatereg.remove(n);
          }
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ничего не выбрано", "Error Dialog", "");
        }
        
      }
    });
    ((Node)((VBox)but.getChildren().get(0)).getChildren().get(0)).setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try
        {
          int validatesize = AddContract.this.validateSizeProduct(gatereg, db);
          
          if (validatesize == -1)
          {
            int lim = gatereg.size() - 1;
            double pok_firstpaid = Double.parseDouble(firstsum.getText());
            String pok_fio = name.getText();
            String pok_adress = docinfo.getText();
            String pok_num = doc.getText();
            
            String pok_dend = dateField.getText();
            double pok_total = ((GateReg)gatereg.get(lim)).getSum();
            String pok_phone = customer_phone.getText();
            String pok_customer_doc_cout = customer_doc_cout.getText();
            
            int validateinfo = AddContract.this.validateInfo(pok_firstpaid, pok_fio, pok_adress, pok_num, year.getText(), pok_customer_doc_cout, pok_phone, pok_dend, pok_total);
            
            if(db.connectCheck()){
            if (validateinfo == -1)
            {
              int idc = db.setNewContract(pok_fio, pok_num, pok_adress, year.getText(), pok_customer_doc_cout, pok_phone, pok_dend, pok_total, pok_firstpaid, pok_total - pok_firstpaid, true, Repeat.user.getName(), 0);
              
              Contracts.contract.add(new contract(idc, pok_fio, pok_num, pok_adress, year.getText(), pok_customer_doc_cout, pok_phone, AddContract.this.Time(), pok_dend, pok_total, pok_firstpaid, pok_total - pok_firstpaid, true, Repeat.user.getName()));
              String[] arrayRefUpd = new String[lim];
              
              for (int i = 0; i < lim; i++)
              {
                String name = ((GateReg)gatereg.get(i)).getName();
                int pid = Integer.parseInt(((GateReg)gatereg.get(i)).getId());
                
                int a = Integer.parseInt(((GateReg)gatereg.get(i)).getSize());
                int bds_full[] = db.getSize(pid);
                int sid = -1;
                int stock_gate = gatereg.get(i).getStock();

                if (stock_gate == 0) {
                    sid = bds_full[1];
                    //stock_gate = 0;
                } else if (stock_gate == 1) {
                    sid = bds_full[2];
                    //stock_gate = 1;
                  }                
                
                
                if ((sid <= 0) || (sid < a)) {
                  Dialogs.showErrorDialog(stage, "Фатальная ошибка , пожалуйста сообщите администратору.\nОшибка номер 400\nID ошибочного договора:" + idc + "\n" + "Снимок экрана, кнопка  prt sc.", "Error Dialog", "");
                  
                  break;
                }
                
                double c = Double.parseDouble(((GateReg)gatereg.get(i)).getPrice());
                double d = ((GateReg)gatereg.get(i)).getSum();
                int id = db.setID(name);
                
                if ((a > 0) && (d > 0.0D) && (c > 0.0D))
                {
                  int balance = bds_full[0] - a;
                  int balance_event_stock = sid - a;
//int id, int idproduct, String nameproduct, int size, double price, double sum,int stock_ship                    
                  db.setListContract(idc, id, name, a, c, d,stock_gate);
                  //db.setSize(id, b - a);
                  arrayRefUpd[i] = (stock_gate == 0) ? "UPDATE `prais` SET `sell` = '" + balance + "', `stock_0` =  '"+balance_event_stock+"' WHERE `prais`.`id` = '" + pid + "'" : "UPDATE `prais` SET `sell` = '" + balance + "', `stock_1` =  '"+balance_event_stock+"' WHERE `prais`.`id` = '" + pid + "'";
  
                  //db.setSize(pid, balance, stock_gate, balance_event_stock);
                } else {
                  Dialogs.showErrorDialog(stage, "Ошибка номер 401.\n1. Ошибка в строке #: " + i + "" + "4. Снимок экрана, кнопка  prt sc.", "Error Dialog", "");
                }
              }
              db.setSize(/*pid, balance, stock_gate, balance_event_stock*/arrayRefUpd);
              if(db.connectCheck()){
                    db.setContractPaid(idc, pok_firstpaid, pok_total - pok_firstpaid, Repeat.user.getName());
                    Cassa.cassa += pok_firstpaid;
                    Cassa.setCassa();
                    gatereg.clear();
                    gatereg.add(new GateReg());
              
                    Dialogs.showInformationDialog(stage, "Договор создан");
                    Contracts.indexnewcontract = idc;
                    db.closeConnect();
                    stage.close();
              } else Dialogs.showErrorDialog(stage, "Ошибка номер 403. Нет соединения", "Error Dialog", "");
            }
            else if (validateinfo == 0) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, первоначальный взнос отрицателен. ", "Error Dialog", "");
            } else if (validateinfo == 1) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, ФИО слишком короткое. ", "Error Dialog", "");
            } else if (validateinfo == 2) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, адрес слишком короткий. ", "Error Dialog", "");
            } else if (validateinfo == 3) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, формат даты выдачи неверен. ", "Error Dialog", "");
            } else if (validateinfo == 4) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, первый взнос превышает полную сумму заказа. ", "Error Dialog", "");
            } else if (validateinfo == 5) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, cерия/номер введены неправильно. ", "Error Dialog", "");
            } else if (validateinfo == 6) { Dialogs.showErrorDialog(stage, "Ошибка номер 403, дата окончания договора не выбрана. ", "Error Dialog", "");
            }
          }
          else
          {
            Dialogs.showErrorDialog(stage, "Ошибка номер 402.\nНет необходимого количества товара, номер строки: " + validatesize, "Error Dialog", "");
          }
          } else Dialogs.showErrorDialog(stage, "Ошибка номер 403. Нет соединения", "Error Dialog", "");
        } catch (NumberFormatException ex) {
          System.out.println("exception");
          Dialogs.showErrorDialog(stage, "Ошибка номер 404, некоторые данные введены некорректно.\nПроверьте правильность вводимых данных и повторите попытку.", "Error Dialog", "");
        }
        
      }
      
    });
    return grid;
  }
  
  private TableView<GateReg> createTableReg()
  {
    TableView<GateReg> table = new TableView<GateReg>();
    
    TableColumn n = new TableColumn("№");
    n.setMinWidth(30.0D);
    n.setMaxWidth(40.0D);
    n.setCellValueFactory(new PropertyValueFactory("n"));
    

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
    
    TableColumn stock = new TableColumn("Склад");
    stock.setMinWidth(50.0);
    stock.setCellValueFactory(new PropertyValueFactory("stock"));
    

    TableColumn price = new TableColumn("Цена");
    price.setMinWidth(90.0D);
    
    price.setCellValueFactory(new PropertyValueFactory("price"));
    
    TableColumn sum = new TableColumn("Сумма");
    sum.setMinWidth(90.0D);
    
    sum.setCellValueFactory(new PropertyValueFactory("sum"));

    table.setStyle("-fx-font: normal 11 Arial;");
    table.setMaxWidth(820.0D);
    table.setMaxHeight(320.0D);
    gatereg.add(new GateReg());
    table.setItems(gatereg);
    
    table.getColumns().addAll(n, name, id, size, stock, price, sum);
    

    return table;
  }
  
  private String Time() {
    SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
    return s.format(new Date());
  }
  
  private int validateSizeProduct(ObservableList<GateReg> g,ConnectDB db)
  {
    //ConnectDB db = new ConnectDB();
    /*
    int n = g.size() - 1;
    int errorrow = -1;
    if (n == 0) { errorrow = 0;
    }
    for (int i = 0; i < n; i++)
    {
      int a = Integer.parseInt(((GateReg)gatereg.get(i)).getSize());
      int b = db.getSize(Integer.parseInt(((GateReg)g.get(i)).getId()));
      if (a > b) errorrow = i;
    }
    return errorrow;
    */
    int n = g.size() - 1;
    int errorrow = -1;
    if (n == 0) { 
            errorrow = 0;
    }
    
    int stock_event = -1;
    
    for (int i = 0; i < n; i++)
    {
      stock_event  = ((GateReg)gatereg.get(i)).getStock();
      int a = Integer.parseInt(((GateReg)gatereg.get(i)).getSize());
      int b_full[] = db.getSize(Integer.parseInt(((GateReg)g.get(i)).getId()));
      int b = -1;
      if(stock_event == 0)b = b_full[1];
        else if(stock_event == 1)b = b_full[2];
      if (a > b) errorrow = i;
    }
    return errorrow;
    
  }
  
  private int validateInfo(double pok_firstpaid, String pok_fio, String pok_adress, String pok_num, String pok_year, String coutpas, String phone, String pok_dend, double pok_total) {
   //System.out.println("firstpaid: " + pok_firstpaid + " fio: " + pok_fio + " adress: " + pok_adress + "\nyear: " + pok_year + " total: " + pok_total + " pok_num: " + pok_num + " pok_year: " + pok_year + " coutpas: " + coutpas + "\nphone: " + phone + " pok_end: " + pok_dend + " pok_total: " + pok_total);
    


    int valid = -1;
    
    if (pok_firstpaid < 0.0D) valid = 0;
    if (pok_fio.length() < 10) { valid = 1;
    }
    Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
    Pattern w = Pattern.compile("([2][0]\\d{2}|[1][9][6-9]\\d{1})\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
    
    if (pok_adress.length() < 10) { valid = 2;
    }
    Matcher e = w.matcher(pok_year);
    if (!e.matches()) { valid = 3;
    }
    if (pok_total < pok_firstpaid) valid = 4;
    if (pok_num.length() < 9) valid = 5;
    Matcher m = p.matcher(pok_dend);
    
    if (!m.matches()) { valid = 6;
    }
    if (phone.length() < 6) valid = 7;
    if (coutpas.length() < 10) valid = 8;
    
    return valid;
  }
}
