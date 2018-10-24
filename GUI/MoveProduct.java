/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Collection.MoveToStock;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import fxuidemo.Repeat;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;

import javafx.scene.control.Label;
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

public class MoveProduct extends Application
{
  ObservableList<MoveToStock> prod = FXCollections.observableArrayList();
  
  public String USER_NAME;
  private int ni = 1;
  

  public MoveProduct(){
      
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
    
    stage.setTitle("Перемещение товаров");
    root.setId("bp");
    but.setId("but");
    
    root.setRight(but);
    
    root.setCenter(createGrid(data, cdata, db, stage, but));
    Scene scene = new Scene(root);
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    stage.setWidth(1080.0);
    stage.setHeight(630.0);
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
          Dialogs.DialogResponse response = Dialogs.showConfirmDialog(stage, "Список не пуст, закрыть окно?", "Confirm Dialog With Options", "Предупреждение", Dialogs.DialogOptions.OK_CANCEL);
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
    
    rb.getChildren().addAll(new Label("Воронцово: "),st0,new Label("/ Ильинск: "),st1,new Label("(0 - Воронцово, 1 - Ильинск)"));        
    
    
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
    
    grid.add(new Label("Откуда: "), 0, 3);
    grid.add(rb, 1, 3, gy, 1);
    
    grid.add(new Label("Наличие: "), 0, 4);
    grid.add(stock_event, 1, 4, gy, 1);
    
    final TextField control = new TextField();
    control.setDisable(true);
    grid.add(new Label("Общее: "), 0, 5);
    grid.add(control, 1, 5, gy, 1);  
    
    final TableView<MoveToStock> tl = createTableReg();
    
    //prod.add(new MoveToStock());
    grid.add(tl, 0, 6, 2, 1);
    
    
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
          
          int[] stock_full = db.getSize(id_product);
          //System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
          
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
            
            int[] stock_full = db.getSize(id_product);
            System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
            
            control.setText(String.valueOf(stock_full[0]));
            
            if(st0.isSelected())stock_event.setText(String.valueOf(stock_full[1]));// = stock_full[1];
                else if(st1.isSelected())stock_event.setText(String.valueOf(stock_full[2]));
            
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Категория не найденна");
            size.setText("1");
          }
          
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
            
            int[] stock_full = db.getSize(id_product);
            System.out.println(stock_full[0] + " "+stock_full[1]+ " "+stock_full[2]);
            control.setText(String.valueOf(stock_full[0]));
            if(st0.isSelected())stock_event.setText(String.valueOf(stock_full[1]));// = stock_full[1];
            else if(st1.isSelected())stock_event.setText(String.valueOf(stock_full[2]));
            
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
          int index = prod.size();
          
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
                    
          if ((sc > 0) && (bds >= sc))
          {
            // неизвестен первый аргумент  AddProduct.access$008(AddProduct.this)
            // String name, Integer id, Integer size, Integer stock, String user
            prod.add(0, new MoveToStock(index++,sn,db.setID(sn), sc, stock_gate, Repeat.USER_NAME));
            
            
            box.getTextbox().setText("");
            codebox.getTextbox().setText("");
            size.setText("1");
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
        int lim = prod.size();
      //  int validatesize = validateSizeProduct(prod);
      //  if (validatesize == -1) {
           System.out.println("lim"+lim);
          //int id_check = db.getMaxCheck() +1;
            
          for (int i = 0; i < lim; i++) {
            int a = ((MoveToStock)prod.get(i)).getSize();
            int pid = ((MoveToStock)prod.get(i)).getId();
            
            int stock = prod.get(i).getStock();
            int stock_gate = -1;
            /*
            *
            * запись в БД
            *
            */              
              if (a > 0) {               
                //db.reliz_day(pid, name, balance, a, stock_gate, c, d, Repeat.USER_NAME,id_check);
                //Repeat.prod.add(new Registration(AddProduct.this.Time_m(), name, Integer.valueOf(pid), Integer.valueOf(a),stock_gate, c, d, Repeat.USER_NAME));
                // stock_gate id склада из которого перемещают.
                db.setMove(pid, stock, a,Repeat.user.getName());
                //Cassa.cassa += d;
                //Cassa.setCassa();
            }
          }
          
          prod.remove(0, prod.size());
          System.out.println("completed");
    //    } else {
     //     Dialogs.showErrorDialog(stage, "Ошибка номер 402.\nНет необходимого количества товара, номер строки: " + validatesize, "Error Dialog", "");
     //   }
        
      }
    });
    return grid;
  }
  
  private TableView<MoveToStock> createTableReg() { 
    TableView<MoveToStock> table = new TableView<MoveToStock>();
    
    table.columnResizePolicyProperty();
    
    TableColumn index = new TableColumn("№");
    index.setMinWidth(30.0D);
    //n.setMaxWidth(40.0D);
    index.setCellValueFactory(new PropertyValueFactory("index"));  

    TableColumn n = new TableColumn("id");
    n.setMinWidth(30.0D);
    //n.setMaxWidth(40.0D);
    n.setCellValueFactory(new PropertyValueFactory("id"));    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(450.0);
    //name.setMaxWidth(480.0);
    name.setCellValueFactory(new PropertyValueFactory("name"));    

    TableColumn id = new TableColumn("Код");
    id.setMinWidth(58.0);
    id.setCellValueFactory(new PropertyValueFactory("id"));    

    TableColumn size = new TableColumn("Кол.eд.");
    size.setMinWidth(50.0);
    size.setCellValueFactory(new PropertyValueFactory("size"));
    
    TableColumn stock = new TableColumn("Склад отгр");
    stock.setMinWidth(50.0);
    stock.setCellValueFactory(new PropertyValueFactory("stock"));    
 
    table.setStyle("-fx-font: normal 11 Arial;");
    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    //table.setMaxWidth(820.0);
    table.setMaxHeight(350.0);
    System.out.println(prod);
    table.setItems(prod);
    
    table.getColumns().addAll(index,n, name, size,stock);   

    return table;
  }
}
