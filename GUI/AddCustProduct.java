package GUI;

import Collection.ListCustProduct;
import Collection.Person;
import Connect.ConnectDB;
import fxuidemo.Repeat;
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
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;




public class AddCustProduct
  extends Application
{
  public AddCustProduct() {}
  
  public void start(final Stage stage)
  {
    final ConnectDB db = new ConnectDB();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10.0D, 20.0D, 10.0D, 20.0D));
    grid.setHgap(5.0D);
    grid.setVgap(5.0D);
    ObservableList group = FXCollections.observableArrayList();
    ObservableList<Integer> ir = FXCollections.observableArrayList();
    db.loadGroupList(group);
    ir.add(Integer.valueOf(1));
    
    final TextField name = new TextField();
    final ChoiceBox<String> chois = new ChoiceBox(group);
    final TextField price = new TextField();
    
    final Label top = new Label();
    

    name.setMinWidth(500.0D);
    grid.add(top, 0, 0, 2, 1);
    grid.add(new Label("Наименование: "), 0, 1);
    grid.add(name, 1, 1);
    grid.add(new Label("Группа товара: "), 0, 2);
    grid.add(chois, 1, 2);
    grid.add(new Label("Cтоимость: "), 0, 3);
    grid.add(price, 1, 3);
    
    Button ok = new Button("Готово");
    Button close = new Button("Закрыть");
    ok.setMinSize(60.0D, 40.0D);
    close.setMinSize(60.0D, 40.0D);
    HBox button = new HBox();
    button.setSpacing(5.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    
    grid.add(button, 1, 4);
    Scene scene = new Scene(grid);
    grid.setId("add");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        int index = chois.getSelectionModel().getSelectedIndex();
        if (index != -1) {
          try {
            String nam = name.getText();
            String p = (String)chois.getSelectionModel().getSelectedItem();
            int to = p.indexOf(" ");
            p = p.substring(0, to);
            int group = Integer.parseInt(p);
            double pr = Double.parseDouble(price.getText());
            
            int newidproduct = db.addCustomerProduct(nam, group, pr);
            int endindex = ListCustomerProduct.list.size();
            ListCustProduct abc = new ListCustProduct(endindex, newidproduct, nam, group, pr);
            

            db.setLog(abc, 0, Repeat.user.getName());
            
            ListCustomerProduct.list.add(abc);
            
            name.setText("");
            price.setText("");

          }
          catch (NumberFormatException ex) {}

        }
        else
        {
          top.setText("Выберите группу");
          top.setTextFill(Color.RED);
        }
        
      }
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        stage.close();
      }
      
    });
    stage.setWidth(750.0D);
    stage.setHeight(220.0D);
    stage.show();
  }
}
