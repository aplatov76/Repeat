package Popup;

import Collection.Prices;
import Connect.ConnectDB;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;







public class PFilterActualInfoProduct
{
  private String data1;
  private String data2;
  private Popup main;
  
  public Popup getMain()
  {
    return main;
  }
  
  private void setData1(String data1) {
    this.data1 = data1;
  }
  
  private void setData2(String data2) {
    this.data2 = data2;
  }
  
  public PFilterActualInfoProduct(ObservableList<Prices> prod, Label info) {
    main = new Popup();
    


    VBox vboxfiltr = createFiltr(prod, main, info);
    
    main.getContent().add(vboxfiltr);
  }
  
  private VBox createFiltr(final ObservableList<Prices> prod, final Popup popupfiltr, final Label info) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("pfilter");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    
    ToggleGroup tggroup = new ToggleGroup();
    ToggleGroup szgroup = new ToggleGroup();
    

    final RadioButton st0 = new RadioButton();
    final RadioButton st1 = new RadioButton();
    final RadioButton st2 = new RadioButton();
    

    final RadioButton size_zero = new RadioButton();
    final RadioButton size_no_zero = new RadioButton();
    
    st0.setToggleGroup(tggroup);
    st1.setToggleGroup(tggroup);
    st2.setToggleGroup(tggroup);
    size_zero.setToggleGroup(szgroup);
    size_no_zero.setToggleGroup(szgroup);
    
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
    Label lbl4 = new Label("- нулевой остаток;");
    lbl4.setTextFill(Color.DARKORANGE);
    
    Label lbl5 = new Label("- положительный остаток;");
    lbl5.setTextFill(Color.DARKORANGE);
    

    sort1.getChildren().addAll(new Node[] { st0, lbl1 });
    sort2.getChildren().addAll(new Node[] { st1, lbl2 });
    sort3.getChildren().addAll(new Node[] { st2, lbl3 });
    sort4.getChildren().addAll(new Node[] { size_zero, lbl4, size_no_zero, lbl5 });
    

    final Slider slider = new Slider();
    
    slider.setBlockIncrement(1.0D);
    slider.setMajorTickUnit(1.0D);
    slider.setMax(360.0D);
    slider.setMin(1.0D);
    slider.setMinorTickCount(1);
    slider.setOrientation(Orientation.HORIZONTAL);
    slider.setShowTickLabels(false);
    slider.setSnapToTicks(false);
    



    ObservableList<String> group_product = FXCollections.observableArrayList();
    getGroup(group_product);
    final ChoiceBox<String> box = new ChoiceBox(group_product);
    box.getSelectionModel().select(0);
    
    box.setMinWidth(270.0D);
    

    Button ok = new Button("Показать");
    Button ok0 = new Button("Показать");
    Button ext = new Button("Закрыть");
    
    ok0.setId("dark-blue");
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    
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
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        int offonsize = -1;
        int day = (int)slider.getValue();
        if (size_zero.isSelected()) offonsize = 0;
        if (size_no_zero.isSelected()) { offonsize = 1;
        }
        int group = box.getSelectionModel().getSelectedIndex();
        
        if (group > 0) {
          String p = (String)box.getSelectionModel().getSelectedItem();
          
          int to = p.indexOf(" ");
          p = p.substring(0, to);
          group = Integer.parseInt(p);
        }
        
        if ((day > 0) && (offonsize != -1) && (group != -1)) {
          getProduct(prod, offonsize, day, group);
          lbl.setText("Запрос выполнен");
          lbl.setTextFill(Color.GREEN);
          
          String infogroup = "";
          if (group > 0) { infogroup = " Группa \"" + (String)box.getSelectionModel().getSelectedItem() + "\".";
          }
          if (offonsize == 0) {
            info.setText("* Cейчас отображаются категории с нулевым балансом и периодом простоя более " + day + " дней." + infogroup);
            info.setTextFill(Color.RED);
          }
          else {
            info.setText("* Cейчас отображаются категории с положительным балансом и периодом простоя более " + day + " дней." + infogroup);
            info.setTextFill(Color.YELLOW);
          }
          main.hide();
        }
        else
        {
          lbl.setText("Проверьте параметры");
          lbl.setTextFill(Color.RED);
        }
        
      }
    });
    ok0.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        int status = 0;
        if (st0.isSelected()) {
          status = 0;
          info.setText("* Cейчас отображаются категории доступные к реализации.");
          info.setTextFill(Color.GREEN);
        }
        if (st1.isSelected()) {
          status = 1;
          info.setText("* Cейчас отображаются категории доступные к реализации, но маловостребованные.\n  Баланс данных категорий положителен, но продажи не регистрировались более 3-x месяцев.");
          info.setTextFill(Color.YELLOW);
        }
        if (st2.isSelected()) {
          status = 2;
          info.setText("* Cейчас отображаются категории недоступные к реализации.\n  Баланс данных категорий нулевой и продажи не регистрировались более 3-x месяцев.");
          info.setTextFill(Color.RED);
        }
        getProduct(prod, status);
        main.hide();
      }
      

    });
    Label lbl6 = new Label("Cтатус : ");
    lbl6.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:lightgrey;");
    Label lbl7 = new Label("Статистика : ");
    lbl7.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:lightgrey;");
    Label lbl8 = new Label("Временной интервал : ");
    lbl8.setTextFill(Color.DARKORANGE);
    Label lbl9 = new Label("Группа товаров : ");
    lbl9.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:lightgrey;");
    final Label lbl10 = new Label("");
    lbl10.setTextFill(Color.DARKORANGE);
    
    slider.valueProperty().addListener(new ChangeListener()
    {
      public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
        int d = t1.intValue();
        lbl10.setText(String.valueOf(d) + " - дней");

      }
      

    });
    menu.getChildren().addAll(new Node[] { lbl6, sort1, sort2, sort3, ok0, lbl7, sort4, sort5, lbl8, slider, lbl10, lbl9, box, button, lbl });
    
    return menu;
  }
  
  public void getGroup(ObservableList<String> group_product) {
    ConnectDB mysql = new ConnectDB();
    
    mysql.loadGroupList(group_product);
  }
  
  public void getProduct(ObservableList<Prices> prod, int status) {
    ConnectDB mysql = new ConnectDB();
    
    mysql.getProductForStatus(prod, status);
  }
  
  public void getProduct(ObservableList<Prices> prod, int offnosize, int day, int group)
  {
    ConnectDB mysql = new ConnectDB();
    

    mysql.getProductForFilter1(prod, offnosize, day, group);
  }
}
