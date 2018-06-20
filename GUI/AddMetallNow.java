package GUI;

import Collection.Cassa;
import Collection.MetallList;
import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;












public final class AddMetallNow
  extends Application
{
  public AddMetallNow()
  {
    start(new Stage());
  }
  
  public void start(final Stage stage)
  {
    GridPane pane = new GridPane();
    pane.setHgap(5.0D);
    pane.setVgap(5.0D);
    final ConnectDB db = new ConnectDB();
    ObservableList<String> data = FXCollections.observableArrayList();
    db.loadMetallName(data);
    final AutoFillTextBox box = new AutoFillTextBox(data);
    box.setListLimit(20);
    box.getListview().setMinHeight(100.0D);
    box.setMinWidth(400.0D);
    box.setId("box");
    final TextField summa = new TextField();
    final TextField price = new TextField();
    final TextField size = new TextField();
    size.setText("1");
    
    summa.setDisable(true);
    
    Button ok = new Button("Готово");
    ok.setId("dark-blue");
    Button close = new Button("Отмена");
    close.setId("dark-blue");
    
    HBox but = new HBox();
    but.setSpacing(5.0D);
    but.getChildren().addAll(new Node[] { ok, close });
    
    pane.add(new Label("Наименование: "), 0, 0);
    pane.add(box, 1, 0);
    pane.add(new Label("Коли-во: "), 0, 1);
    pane.add(size, 1, 1);
    pane.add(new Label("Цена: "), 0, 2);
    pane.add(price, 1, 2);
    pane.add(new Label("Сумма: "), 0, 3);
    pane.add(summa, 1, 3);
    pane.add(but, 1, 4);
    pane.setId("bp");
    
    pane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    Scene scene = new Scene(pane);
    
    box.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        if (t.getCode() == KeyCode.ENTER) {
          String sn = box.getText();
          try {
            int siz = Integer.parseInt(size.getText());
            int procent = db.getMetallProcent(sn);
            double p = db.getMetallPrice(sn);
            price.setText(String.valueOf(p));
            if ((siz > 0) && (p > 0.0D)) {
              double d = siz * p;
              d -= d / 100.0D * procent;
              summa.setText(Double.toString(new BigDecimal(d).setScale(0, RoundingMode.HALF_DOWN).doubleValue()));
            }
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Проверьте параметры");
            size.setText("1");
            price.setText("0.0");
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
          int siz = Integer.parseInt(size.getText());
          int procent = db.getMetallProcent(sn);
          double p = db.getMetallPrice(sn);
          price.setText(String.valueOf(p));
          if ((siz > 0) && (p > 0.0D)) {
            double d = siz * p;
            d -= d / 100.0D * procent;
            summa.setText(Double.toString(new BigDecimal(d).setScale(0, RoundingMode.HALF_DOWN).doubleValue()));
          }
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Проверьте параметры");
          size.setText("1");

        }
        
      }
      

    });
    size.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String sn = box.getText();
        try {
          int siz = Integer.parseInt(size.getText());
          int procent = db.getMetallProcent(sn);
          
          double p = Double.parseDouble(price.getText());
          
          if ((siz > 0) && (p > 0.0D)) {
            double d = siz * p;
            d -= d / 100.0D * procent;
            summa.setText(Double.toString(new BigDecimal(d).setScale(0, RoundingMode.HALF_DOWN).doubleValue()));
          }
        }
        catch (NumberFormatException ex) {
          size.setText("");
        }
        
      }
      

    });
    price.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String sn = box.getText();
        try {
          int siz = Integer.parseInt(size.getText());
          int procent = db.getMetallProcent(sn);
          
          double p = Double.parseDouble(price.getText());
          
          if ((siz > 0) && (p > 0.0D)) {
            double d = siz * p;
            d -= d / 100.0D * procent;
            summa.setText(Double.toString(new BigDecimal(d).setScale(0, RoundingMode.HALF_DOWN).doubleValue()));

          }
          


        }
        catch (NumberFormatException ex) {}
      }
      

    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        try {
          String name = box.getText();
          double sum = Double.parseDouble(summa.getText());
          
          double pp = Double.parseDouble(price.getText());
          int siz = Integer.parseInt(size.getText());
          
          if ((sum > 0.0D) && (siz > 0)) {
            int id = db.getMetallID(name);
            int end = AddMetall.ml.size() + 1;
            MetallList a = new MetallList(end, id, name, pp, sum, siz);
            AddMetall.ml.add(a);
            Cassa.cassa -= sum;
            Cassa.rasxod += sum;
            Cassa.setCassa();
            
            db.setMetallLog(a);
            stage.close();
          }
          else {
            Dialogs.showErrorDialog(stage, "Ошибка 408, неправильно введена сумма ", "Error Dialog", "Проверьте вводимые данные");
          }
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ошибка 409, неправильно введена сумма ", "Error Dialog", "Ошибка конвертации");
        }
        
      }
      

    });
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(580.0D);
    stage.setHeight(200.0D);
    stage.show();
  }
  
  private String getIdUser() {
    return "";
  }
}
