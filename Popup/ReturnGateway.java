package Popup;

import Collection.Cassa;
import Collection.History;
import Collection.Person;
import Collection.Prices;
import Connect.ConnectDB;
import GUI.ReturnGate;
import fxuidemo.Repeat;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;


public class ReturnGateway
{
  private Popup main;
  
  public Popup getMain()
  {
    return main;
  }
  

  public ReturnGateway(Collection.History prod)
  {
    main = new Popup();    

    GridPane vboxfiltr = createFiltr(prod, main);    
    main.getContent().add(vboxfiltr);
  }
  
  private GridPane createFiltr(final Collection.History prod, final Popup popupfiltr) {
      
    GridPane grid = new GridPane();
    grid.setId("pfilter");
    grid.setPadding(new Insets(50.0, 50.0, 50.0, 50.0));
    grid.setHgap(15.0D);
    grid.setVgap(5.0D);  
    grid.setMinWidth(650);
    grid.setMinHeight(270);
      
  //  VBox menu = new VBox();
    
  //  menu.setSpacing(8.0D);
  //  menu.setId("pfilter");
 //   menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D
    
    final RadioButton c_stock_0 = new RadioButton();
    final RadioButton c_stock_1 = new RadioButton();
    
    ToggleGroup tggroup = new ToggleGroup();
    c_stock_0.setToggleGroup(tggroup);
    c_stock_1.setToggleGroup(tggroup);
    
    HBox c_stock = new HBox();
    c_stock.getChildren().addAll(new Label("Воронцово: "),c_stock_0,new Label("Ильинск: "),c_stock_1);
    
    Label stockLbl = new Label("Склад возврата");
    stockLbl.setTextFill(Color.RED);
    
    TextField name = new TextField();
    name.setText(prod.getName());
    name.setDisable(true);
    name.setMinWidth(500);
    TextField stock = new TextField();
    stock.setDisable(true);
    stock.setText(prod.getStock().toString());
    final TextField size = new TextField();
    
    grid.add(new Label("Наименование: "),0,0);
    grid.add(name,1,0);
    grid.add(new Label("Склад отгрузки: "),0,1);
    grid.add(stock, 1, 1);
    grid.add(stockLbl,0,2); // склад Возврата
    grid.add(c_stock, 1, 2);
    grid.add(new Label("Количество: "), 0, 3);
    grid.add(size, 1, 3);

    final Button ok = new Button("Готово");
    Button ext = new Button("Закрыть");
    
    HBox button = new HBox();
    button.setSpacing(8.0);
    button.getChildren().addAll(ok,ext);
    grid.add(button, 0, 4, 1, 2);
    
    final Label info = new Label();
    grid.add(info,0,6,2,1);
    
    if(!Repeat.user.getRules(4))ok.setDisable(true);
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    
   // HBox button = new HBox();
   // button.setSpacing(8.0D);
   // button.getChildren().addAll(new Node[] { ok, ext });
    
    size.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
     //   if (t.getCode() == KeyCode.ENTER) { 
          //System.out.println(t.getCode() +", ");
        try {
           
          int full_bd =  getFullSize(prod.getIdop());
          int event_size = Integer.parseInt(size.getText());
          System.out.println(full_bd + " " + event_size);
          
          if(event_size > prod.getSize() - full_bd){
              ok.setDisable(true);
              info.setText("* Превышен размер продажи или ранее были возвраты");
              info.setTextFill(Color.RED);
          }
          else {
              ok.setDisable(false);
              info.setText("");
          }

        } catch (NumberFormatException ex) {
            
            System.out.println("RG error");
        }    
     //   }
        
      }
      
    });
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        popupfiltr.hide();
        ReturnGate.gateEvent = 0;
      }
      
    });

    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
          ReturnGate.gateEvent = 0;
          int full_bd =  getFullSize(prod.getIdop());
          System.out.println(full_bd);
          int event_size = Integer.parseInt(size.getText());
          System.out.println(event_size);
          
          int stock_gate = -1;
          if(c_stock_0.isSelected())stock_gate = 0;  
            else if(c_stock_1.isSelected())stock_gate = 1;

          if(event_size > prod.getSize() - full_bd){
              info.setText("* Превышен размер продажи или ранее были возвраты");
              info.setTextFill(Color.RED);
          }
          else {
              ok.setDisable(false);
              info.setText("");
              setReturnProduct(prod,event_size,stock_gate);
              
              main.hide();
              
          }
      }
      
    });   
    
    return grid;
  }
  
  public int getFullSize(int idop)
  {
        ConnectDB db = new ConnectDB();
        int r = db.getReturnProductIdOperation(idop);
    return r;
  }
  
  public void setReturnProduct(History prod, int size, int event_stock){
      
      ConnectDB db = new ConnectDB();
      
      int bds_full[] = db.getSize(prod.getIdpr());
      //int stock_gate = prod.getStock();
      int sid = -9999;      
      
            if(event_stock == 0){
                sid = bds_full[1];
                //stock_gate = 0;
            }
            else if(event_stock == 1){
                sid = bds_full[2];
                //stock_gate = 1;
            }
     int balance = bds_full[0] + size;
     int balance_event_stock = sid + size;
     db.setReturnProduct(prod, size, event_stock, balance, balance_event_stock, Repeat.user.getId_user());
     Cassa.rasxod = Cassa.rasxod + size*prod.getPrice();
     Cassa.cassa = Cassa.cassa - size*prod.getPrice();
     Cassa.setCassa();
     
      
  }
  
}
