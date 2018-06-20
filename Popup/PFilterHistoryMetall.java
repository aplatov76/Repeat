package Popup;

import Connect.ConnectDB;
import GUI.HistoryMetall;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class PFilterHistoryMetall
{
  private String data1;
  private String data2;
  private Popup main;
  
  public Popup getMain()
  {
    return main;
  }
  
  public void setData1(String data1) {
    this.data1 = data1;
  }
  
  public void setData2(String data2) {
    this.data2 = data2;
  }
  
  public PFilterHistoryMetall(ObservableList<String> data) {
    main = new Popup();
    


    VBox vboxfiltr = createFiltr(data, main);
    
    main.getContent().add(vboxfiltr);
  }
  
  private VBox createFiltr(ObservableList<String> data, final Popup popupfiltr) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("but");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    
    ToggleGroup tggroup = new ToggleGroup();
    


    final RadioButton rbdata = new RadioButton();
    final RadioButton rbcode = new RadioButton();
    final RadioButton rball = new RadioButton();
    final RadioButton rbno = new RadioButton();
    
    rbdata.setToggleGroup(tggroup);
    rbcode.setToggleGroup(tggroup);
    rball.setToggleGroup(tggroup);
    rbno.setToggleGroup(tggroup);
    
    HBox sort1 = new HBox();
    sort1.setSpacing(8.0D);
    HBox sort2 = new HBox();
    sort2.setSpacing(8.0D);
    HBox sort3 = new HBox();
    sort3.setSpacing(8.0D);
    HBox sort4 = new HBox();
    sort4.setSpacing(8.0D);
    
    sort1.getChildren().addAll(new Node[] { rbdata, new Label(" - дате;") });
    sort2.getChildren().addAll(new Node[] { rbcode, new Label(" - коду;") });
    sort3.getChildren().addAll(new Node[] { rball, new Label(" - дате и коду;") });
    sort4.getChildren().addAll(new Node[] { rbno, new Label(" - без сортировки;") });
    
    final ChoiceBox box = new ChoiceBox(data);
    
    box.setMinWidth(270.0D);
    

    Button ok = new Button("Показать");
    Button ext = new Button("Закрыть");
    
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

        int rbtemp = -1;
        if (rbno.isSelected()) rbtemp = 0;
        if (rbdata.isSelected()) rbtemp = 1;
        if (rbcode.isSelected()) rbtemp = 2;
        if (rball.isSelected()) { rbtemp = 3;
        }
        int select = -1;
        int index = box.getSelectionModel().getSelectedIndex();
        
        select = index;
        
        if (rbtemp != -1)
        {
          if ((rbtemp == 1) && (select == -1)) {
            lbl.setText("Проверьте параметры.");
            lbl.setTextFill(Color.RED);
          }
          else {
            if (select != -1) select++;
            getMetallHistory(select, rbtemp);
            lbl.setText("");
          }
        }
        else
        {
          lbl.setText("Проверьте параметры.");
          lbl.setTextFill(Color.RED);

        }
        

      }
      


    });
    menu.getChildren().addAll(new Node[] { new Label("Cортировать по : "), sort1, sort2, sort3, sort4, box, button, lbl });
    
    return menu;
  }
  
  public void getMetallHistory(int rypegroup, int typefilter) {
    ConnectDB mysql = new ConnectDB();
    
    mysql.getHistoryMetall(HistoryMetall.contract, data1, data2, rypegroup, typefilter);
  }
}
