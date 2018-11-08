package Popup;

import Collection.Person;
import Collection.Prices;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class PFilterCorrectStatus
{
  private Popup main;
  
  public Popup getMain()
  {
    return main;
  }
  
  public PFilterCorrectStatus(ObservableList<Prices> prod, Label info, TableView table,final ConnectDB db)
  {
    main = new Popup();

    VBox vboxfiltr = createFiltr(prod, main, info, table, db);
    
    main.getContent().add(vboxfiltr);
  }
  
  private VBox createFiltr(final ObservableList<Prices> prod, final Popup popupfiltr, final Label info, final TableView table, final ConnectDB db) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("pfilter");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    
    ToggleGroup tggroup = new ToggleGroup();
    
    final RadioButton st0 = new RadioButton();
    final RadioButton st1 = new RadioButton();
    final RadioButton st2 = new RadioButton();

    st0.setToggleGroup(tggroup);
    st1.setToggleGroup(tggroup);
    st2.setToggleGroup(tggroup);
    
    final CheckBox abc = new CheckBox();
    HBox sort1 = new HBox();
    sort1.setSpacing(8.0D);
    HBox sort2 = new HBox();
    sort2.setSpacing(8.0D);
    HBox sort3 = new HBox();
    sort3.setSpacing(8.0D);
    HBox sort4 = new HBox();
    sort4.setSpacing(8.0D);
    HBox sort5 = new HBox();
    sort5.setSpacing(8.0D);
    

    Label lbl1 = new Label(" - используемые категории, статус 0 ;");
    lbl1.setTextFill(Color.DARKORANGE);
    Label lbl2 = new Label(" - малоиспользуемые категории, статус 1;");
    lbl2.setTextFill(Color.DARKORANGE);
    Label lbl3 = new Label(" - неиспользуемые категории, статус 2;");
    lbl3.setTextFill(Color.DARKORANGE);
    Label lbl4 = new Label("- изменить статус всех категорий в списке;");
    lbl4.setTextFill(Color.DARKORANGE);
    


    sort1.getChildren().addAll(new Node[] { st0, lbl1 });
    sort2.getChildren().addAll(new Node[] { st1, lbl2 });
    sort3.getChildren().addAll(new Node[] { st2, lbl3 });
    sort4.getChildren().addAll(new Node[] { abc, lbl4 });
    


    Button ok = new Button("Готово");
    Button ext = new Button("Закрыть");
    
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    

    if (!Repeat.user.getRules(4)) { ok.setDisable(true);
    }
    HBox button = new HBox();
    button.setSpacing(8.0D);
    button.getChildren().addAll(new Node[] { ok, ext });
    
    final Label lbl = new Label();
    

    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        popupfiltr.hide();
      }
      
    });
    abc.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        if (abc.isSelected()) {
          lbl.setText("*Статус будет изменен у всех категорий в\n  таблице");
          lbl.setTextFill(Color.YELLOW);
        }
        else {
          lbl.setText("");

        }
        
      }
      

    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        int status = -1;
        if (st0.isSelected()) status = 0;
        if (st1.isSelected()) status = 1;
        if (st2.isSelected()) { status = 2;
        }
        int tindex = table.getSelectionModel().getSelectedIndex();
        
        if ((abc.isSelected()) && (status >= 0)) {
          setGroupStatus(prod, status, db);
          getProduct(prod, status, db);
          PFilterCorrectStatus.this.setLabelInfo(info, status);
          
          popupfiltr.hide();
        }
        
        if ((!abc.isSelected()) && (status >= 0) && (tindex != -1)) {
          setProductIdStatus(((Prices)prod.get(tindex)).getId(), status, db);
          
          Prices a = (Prices)prod.get(tindex);
          
          prod.set(tindex, new Prices(a.getId(), a.getCode(), a.getName(), a.getGroup(), a.getSize(), a.getPrice(), status));
          popupfiltr.hide();
        }
        
      }
      
    });
    Label lbl6 = new Label("Изменение статуса : ");
    lbl6.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:lightgrey;");
    Label lbl7 = new Label("Групповые операции : ");
    lbl7.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:lightgrey;");
    Label lbl10 = new Label("");
    lbl10.setTextFill(Color.DARKORANGE);
    

    menu.getChildren().addAll(new Node[] { lbl6, sort1, sort2, sort3, lbl7, sort4, sort5, lbl10, button, lbl });
    
    return menu;
  }
  
  public void setGroupStatus(ObservableList<Prices> prod, int status, ConnectDB db)
  {
    //ConnectDB db = new ConnectDB();
    db.setGroupStatus(prod, status);
  }
  

  public void setProductIdStatus(int id, int status, ConnectDB db)
  {
    //ConnectDB mysql = new ConnectDB();
    
    db.setProductStatus(id, status);
  }
  
  public void getProduct(ObservableList<Prices> prod, int ostatus, ConnectDB db)
  {
    db.getProductForStatus(prod, ostatus);
  }
  


  private void setLabelInfo(Label info, int status)
  {
    if (status == 0)
    {
      info.setText("* Cейчас отображаются категории доступные к реализации.");
      info.setTextFill(Color.GREEN);
    }
    if (status == 1)
    {
      info.setText("* Cейчас отображаются категории доступные к реализации, но маловостребованные.\n  Баланс данных категорий положителен, но продажи не регистрировались более 3-x месяцев.");
      info.setTextFill(Color.YELLOW);
    }
    if (status == 2)
    {
      info.setText("* Cейчас отображаются категории недоступные к реализации.\n  Баланс данных категорий нулевой и продажи не регистрировались более 3-x месяцев.");
      info.setTextFill(Color.RED);
    }
  }
}
