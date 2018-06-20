package Popup;

import Collection.Prices;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

public class PFilterPriceView
{
  private Popup main;
  
  public Popup getMain()
  {
    return main;
  }
  

  public PFilterPriceView(ObservableList<Prices> prod, TableView table)
  {
    main = new Popup();
    


    VBox vboxfiltr = createFiltr(prod, main, table);
    
    main.getContent().add(vboxfiltr);
  }
  
  private VBox createFiltr(final ObservableList<Prices> prod, final Popup popupfiltr, final TableView table) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("pfilter");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));
    
    HBox sort1 = new HBox();
    sort1.setSpacing(8.0D);
    
    Label lbl1 = new Label("Наименование продукта");
    lbl1.setTextFill(Color.DARKORANGE);
    Label lbl2 = new Label("Код продукта");
    lbl2.setTextFill(Color.DARKORANGE);
    Label lbl3 = new Label("Группа продуктов");
    lbl3.setTextFill(Color.DARKORANGE);
    
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<Integer> datacode = FXCollections.observableArrayList();
    ObservableList<String> group = FXCollections.observableArrayList();
    
    getProduct(data, datacode, group);
    
    final AutoFillTextBox box = new AutoFillTextBox(data);
    final AutoFillTextBox boxcode = new AutoFillTextBox(datacode);
    final ChoiceBox<String> chois = new ChoiceBox(group);
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
    
    Label lbl = new Label();
    

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
        if (!box.getText().equals("")) {
          int d = prod.size();
          int sid = getProductId(box.getText());
          
          for (int i = 0; i < d; i++)
            if (sid == ((Prices)prod.get(i)).getId()) {
              table.scrollTo(i);
              table.getSelectionModel().select(i);
              break;
            }
          box.getTextbox().setText("");
        }
        else if (!boxcode.getText().equals(""))
        {
          try {
            int sid = Integer.parseInt(boxcode.getText());
            table.scrollTo(sid);
            boxcode.getTextbox().setText("");
          }
          catch (NumberFormatException ex) {}
        }
        else if (chois.getSelectionModel().getSelectedIndex() != -1) {
          String p = (String)chois.getValue();
          
          int to = p.indexOf(" ");
          p = p.substring(0, to);
          int group_id = Integer.parseInt(p);
          getProductFromGroup(prod, group_id);
        }
        

        popupfiltr.hide();
      }
      

    });
    sort1.getChildren().addAll(new Node[] { ok, ext });
    menu.getChildren().addAll(new Node[] { lbl1, box, lbl2, boxcode, lbl3, chois, sort1 });
    
    return menu;
  }
  
  public void getProduct(ObservableList<String> data, ObservableList<Integer> code, ObservableList<String> group)
  {
    ConnectDB connectordb = new ConnectDB();
    
    connectordb.loadProduct(data);
    connectordb.loadGroupList(group);
    connectordb.loadProductId(code);
  }
  
  public int getProductId(String name) {
    ConnectDB connectordb = new ConnectDB();
    
    return connectordb.setID(name);
  }
  
  public void getProductFromGroup(ObservableList<Prices> prod, int group) {
    ConnectDB connectordb = new ConnectDB();
    
    connectordb.getGroupPrices(prod, group, 0);
  }
}
