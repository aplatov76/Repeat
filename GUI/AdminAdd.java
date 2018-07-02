package GUI;

import Collection.AdminPane;
import Collection.Person;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import java.net.URL;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminAdd
  extends Application
{
  public AdminAdd() {}
  
  public void start(final Stage stage)
  {
    final ConnectDB db = new ConnectDB();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    grid.setHgap(5.0D);
    grid.setVgap(5.0D);
    ObservableList group = FXCollections.observableArrayList();
    
    db.loadGroupList(group);
    

    final TextField name = new TextField();
    final TextField sname = new TextField();
    final ChoiceBox<String> chois = new ChoiceBox(group);
    final ChoiceBox<Integer> helf = new ChoiceBox();

    final TextField size = new TextField();
    final TextField price = new TextField();
    name.setMinWidth(500.0D);
    grid.add(new Label("Полное наименование: "), 0, 0);
    grid.add(name, 1, 0);
    grid.add(new Label("Cокращенное наимен.: "), 0, 1);
    grid.add(sname, 1, 1);
    grid.add(new Label("Группа товара: "), 0, 2);
    grid.add(chois, 1, 2);
    grid.add(new Label("Вес в списке: "), 0, 3);
    grid.add(helf, 1, 3);
    grid.add(new Label("Количество: "), 0, 4);
    grid.add(size, 1, 4);
    grid.add(new Label("Cтоимость: "), 0, 5);
    grid.add(price, 1, 5);
    
    Button ok = new Button("Готово");
    Button close = new Button("Закрыть");
    ok.setMinSize(60.0D, 40.0D);
    close.setMinSize(60.0D, 40.0D);
    HBox button = new HBox();
    button.setSpacing(5.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    grid.add(button, 1, 6);
    Scene scene = new Scene(grid);
    grid.setId("add");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        String new_name = name.getText();
        String snew_name = name.getText();
        int choisindex = chois.getSelectionModel().getSelectedIndex();
        int helfindex = helf.getSelectionModel().getSelectedIndex();
        
        if ((!new_name.equals("")) && (!snew_name.equals("")) && (choisindex != -1) && (helfindex != -1)) {
          try {
            String p = (String)chois.getSelectionModel().getSelectedItem();
            int to = p.indexOf(" ");
            p = p.substring(0, to);
            int group = Integer.parseInt(p);
            
            db.setProductAdmin(name.getText(), sname.getText(), group, helfindex, Integer.parseInt(size.getText()), Double.parseDouble(price.getText()));
            
            int newid = db.setID(sname.getText());
            int index_next = Repeat.admprod.size();
            
            AdminPane new_product = new AdminPane(index_next, newid, name.getText(), sname.getText(), group, helfindex, Integer.parseInt(size.getText()), Double.parseDouble(price.getText()), 0);
            db.setLog(new_product, new_product, 0, Repeat.user.getName());
            
            Repeat.admprod.add(new_product);
            stage.close();
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Ошибка , проверьте правильность данных.");
          }
        } else {
          Dialogs.showErrorDialog(stage, "Ошибка , проверьте правильность данных.");
        }
      }
    });
    chois.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
    {
      public void changed(ObservableValue ov, Number value, Number new_value)
      {
        String p = (String)chois.getItems().get(new_value.intValue());
        int to = p.indexOf(" ");
        p = p.substring(0, to);
        
        int group = Integer.parseInt(p);
        
        helf.getItems().removeAll(new Integer[0]);
        helf.setItems(db.getHelf(group));
      }
      

    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        stage.close();
      }
      
    });
    stage.setWidth(750.0D);
    stage.setHeight(300.0D);
    stage.show();
  }
  
  public int getWhere(int g, int h) {
    return Repeat.admprod.size();
  }
}
