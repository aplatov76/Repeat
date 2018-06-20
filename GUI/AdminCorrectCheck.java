package GUI;

import Collection.Person;
import Collection.Procurement_product_hist;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public final class AdminCorrectCheck
  extends Application
{
  private String name_p;
  private int size_p;
  private int id_p;
  private int index;
  
  public AdminCorrectCheck(String name_product, int id_product, int size_product, int ind)
  {
    name_p = name_product;
    id_p = id_product;
    size_p = size_product;
    index = ind;
    start(new Stage());
  }
  
  public void start(final Stage stage)
  {
    final ConnectDB db = new ConnectDB();
    

    VBox pane = new VBox();
    pane.setId("bp");
    Button ok = new Button("Добавить");
    
    Button close = new Button("Закрыть");
    final Label lbl = new Label();
    ok.setId("dark-blue");
    close.setId("dark-blue");
    pane.setSpacing(8.0D);
    
    TextField box = new TextField();
    box.setText(name_p);
    box.setDisable(true);
    TextField boxcode = new TextField();
    boxcode.setText(Integer.toString(id_p));
    boxcode.setDisable(true);
    box.setMinWidth(570.0D);
    boxcode.setMinWidth(570.0D);
    
    final TextField size = new TextField();
    size.setText(Integer.toString(size_p));
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
        stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        try {
          int sid = Integer.parseInt(size.getText());
          if (sid > 0) {
            String user_name = Repeat.user.getName();
            
            Procurement_product_hist isnow = (Procurement_product_hist)AddProductGate.expected.get(index);
            int size_f = isnow.getSize_first();
            
            AddProductGate.expected.set(index, new Procurement_product_hist(isnow.getIndex(), isnow.getDate(), isnow.getName_product(), isnow.getId_product(), size_f, sid, size_f += sid, user_name, true));
            db.updateSizeCorrectAdded(id_p, sid, user_name);
            stage.close();
          } else {
            Dialogs.showErrorDialog(stage, "Количество отрицательно", "Error Dialog", "Ошибка");
          }
          
        }
        catch (NumberFormatException ex)
        {
          Dialogs.showErrorDialog(stage, "Неверно введено количество", "Error Dialog", "Ошибка");
          lbl.setText("Ошибка.");
          lbl.setTextFill(Color.RED);
        }
        
      }
      
    });
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(600.0D);
    stage.setHeight(280.0D);
    stage.show();
  }
}
