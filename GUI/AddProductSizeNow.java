package GUI;

import Collection.Person;
import Collection.Procurement_product_hist;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public final class AddProductSizeNow
  extends Application
{
  public AddProductSizeNow()
  {
    start(new Stage());
  }
  
  public void start(final Stage stage)
  {
    final ConnectDB db = new ConnectDB();
    ObservableList<String> data = FXCollections.observableArrayList();
    ObservableList<Integer> datacode = FXCollections.observableArrayList();
    

    db.loadProduct(data);
    db.loadProductId(datacode);
    
    VBox pane = new VBox();
    pane.setId("bp");
    Button ok = new Button("Добавить");
    
    Button close = new Button("Закрыть");
    final Label lbl = new Label();
    ok.setId("dark-blue");
    close.setId("dark-blue");
    pane.setSpacing(8.0D);
    
    final AutoFillTextBox<String> box = new AutoFillTextBox(data);
    final AutoFillTextBox<Integer> boxcode = new AutoFillTextBox(datacode);
    box.getListview().setMinSize(100.0, 100.0);
    
    box.setListLimit(100);
    box.setMinWidth(570.0);
    box.setId("box");
    boxcode.getListview().setMinSize(100.0D, 100.0D);
    boxcode.setListLimit(100);
    boxcode.setMinWidth(570.0D);
    boxcode.setId("box");
    

    final TextField size = new TextField();
    
    HBox button = new HBox();
    button.setSpacing(8.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    
    pane.getChildren().addAll(new Node[] { lbl, new Label("Наименование:"), box, new Label("Код:"), boxcode, new Label("Количество:"), size, button });
    

    pane.setPadding(new Insets(5.0D, 20.0D, 20.0D, 20.0D));
    Scene scene = new Scene(pane);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        db.closeConnect();
        stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try {
          String sn = box.getText();
          int id_product = db.setID(sn);
          int size_product = Integer.parseInt(size.getText());
          
          if ((size_product > 0) && (id_product != -1)) {
              /*
              *
              *
              * ERROR
              *
              */
            //int sid = db.getSize(id_product);            
            //int indexend = db.setAddProductSizeIsnow(sn, id_product, size_product, Repeat.user.getName(), true);

            int searchindex = 0;//AddProductSizeNow.this.searchIndexTable(indexend);
            if (searchindex != -1) {
              Procurement_product_hist tmp = (Procurement_product_hist)AddProductGate.expected.get(searchindex);
              AddProductGate.expected.set(searchindex, new Procurement_product_hist(tmp.getIndex(), tmp.getDate(), tmp.getName_product(), id_product, tmp.getSize_first(), tmp.getSize_second() + size_product, tmp.getSize_total() + size_product, tmp.getUser(), true));

            }
            else
            {
              /*
              *
              *
              * ERROR
              *
              */
            /*
              AddProductGate.expected.add(new Procurement_product_hist(indexend, AddProductSizeNow.this.Datenow(), sn, id_product, sid, size_product, sid + size_product, Repeat.user.getName(), false));
            */
            }
      
            lbl.setText("Добавленно.");
            lbl.setTextFill(Color.GREEN);
            boxcode.getTextbox().setText("");
            box.getTextbox().setText("");
            size.setText("");
          }
          else
          {
            Dialogs.showErrorDialog(stage, "Отрицательное количество/отсутствие продукта с такима именем", "Error Dialog", "Ошибка");
          }
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Неверно введено наименование/количество!", "Error Dialog", "Ошибка");
          lbl.setText("Ошибка.");
          lbl.setTextFill(Color.RED);
        }
        
      }
      
    });
    box.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        if (t.getCode() == KeyCode.ENTER) {
          String sn = box.getText();
          int id_product = db.setID(sn);
          boxcode.getTextbox().setText("" + id_product);
        }
        
      }
      
    });
    boxcode.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        if (t.getCode() == KeyCode.ENTER) {
          try {
            int id_product = Integer.parseInt(boxcode.getText());
            String name_product = db.getName(id_product);
            box.getTextbox().setText(name_product);
          }
          catch (NumberFormatException ex)
          {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Неправильный код");
          }
          
        }
        
      }
      

    });
    boxcode.getListview().setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try {
          int id_product = Integer.parseInt(boxcode.getText());
          String name_product = db.getName(id_product);
          box.getTextbox().setText(name_product);
        }
        catch (NumberFormatException ex) {}

      }
      


    });
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(600.0D);
    stage.setHeight(280.0D);
    stage.show();
  }
  
}
