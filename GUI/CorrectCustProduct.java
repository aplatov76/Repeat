package GUI;

import Collection.ListCustProduct;
import Collection.Person;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import java.io.PrintStream;
import java.net.URL;
import javafx.application.Application;
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

public class CorrectCustProduct
  extends Application
{
  private int select;
  ListCustProduct temp;
  
  public CorrectCustProduct(int s)
  {
    select = s;
    temp = ((ListCustProduct)ListCustomerProduct.list.get(s));
  }
  

  public void start(final Stage stage)
  {
    final ConnectDB db = new ConnectDB();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    grid.setHgap(5.0D);
    grid.setVgap(5.0D);
    ObservableList<String> group = FXCollections.observableArrayList();
    db.loadGroupList(group);
    
    int iget = group.size();
    
    for (int i = 0; i < iget; i++) {
      int to = ((String)group.get(i)).indexOf(" ");
      String p = ((String)group.get(i)).substring(0, to);
      
      if (temp.getGroup() == Integer.parseInt(p)) {
        iget = i;
        break;
      }
    }
    System.out.print("Nomer " + iget + "\n");
    final TextField name = new TextField(temp.getName());
    final ChoiceBox<String> chois = new ChoiceBox(group);
    chois.getSelectionModel().select(iget);
    final TextField price = new TextField("" + temp.getPrice());
    name.setMinWidth(500.0D);
    grid.add(new Label("Полное наименование: "), 0, 0);
    grid.add(name, 1, 0);
    grid.add(new Label("Группа товара: "), 0, 1);
    grid.add(chois, 1, 1);
    grid.add(new Label("Cтоимость: "), 0, 2);
    grid.add(price, 1, 2);
    
    Button ok = new Button("Готово");
    Button close = new Button("Закрыть");
    ok.setMinSize(60.0D, 40.0D);
    close.setMinSize(60.0D, 40.0D);
    HBox button = new HBox();
    button.setSpacing(5.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    grid.add(button, 1, 3);
    Scene scene = new Scene(grid);
    grid.setId("add");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        String cor_name = name.getText();
        if ((chois.getSelectionModel().getSelectedIndex() != -1) && (!cor_name.equals(""))) {
          String p = (String)chois.getValue();
          try {
            int to = p.indexOf(" ");
            p = p.substring(0, to);
            int gr = Integer.parseInt(p);
            double pr = Double.parseDouble(price.getText());
            
            db.updatePriceCustProduct(temp.getId_product(), cor_name, gr, pr);
            db.setLog(temp, 1, Repeat.user.getName());
            

            ListCustomerProduct.list.set(select, new ListCustProduct(temp.getId(), temp.getId_product(), cor_name, gr, pr));
            stage.close();
          } catch (NumberFormatException ex) {
            Dialogs.showErrorDialog(stage, "Вводимые данные некорректны");
          }
        }
        else {
          Dialogs.showErrorDialog(stage, "Вводимые данные некорректны");
        }
        
      }
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        stage.close();
      }
    });
    stage.setScene(scene);
    
    stage.setWidth(750.0D);
    stage.setHeight(230.0D);
    stage.show();
  }
}
