package GUI;

import Collection.AdminPane;
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
import javafx.scene.control.Tooltip;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import jfx.messagebox.MessageBox;

public class AdminCorrect
  extends Application
{
  private int select;
  
  public AdminCorrect(int select)
  {
    this.select = select;
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
    
    final AdminPane p_prev = (AdminPane)Repeat.admprod.get(select);
    
    int iget = group.size();
    
    for (int i = 0; i < iget; i++) {
      int to = ((String)group.get(i)).indexOf(" ");
      String p = ((String)group.get(i)).substring(0, to);
      
      if (p_prev.getGroup() == Integer.parseInt(p)) {
        iget = i;
        break;
      }
    }
    
    final TextField name = new TextField(p_prev.getName());
    final TextField sname = new TextField(p_prev.getShortname());
    final TextField stock = new TextField("" + p_prev.getStock());
    
    final TextField stock_0 = new TextField("" + p_prev.getStock_Size_0());
    final TextField stock_1 = new TextField("" + p_prev.getStock_Size_1());
    
    final ChoiceBox<String> chois = new ChoiceBox(group);
    chois.getSelectionModel().select(iget);
    final TextField helf = new TextField("" + p_prev.getHelf());
    
    helf.setTooltip(new Tooltip("Изменение веса в списке,\nпереместит вас в группу с товаром"));
    final TextField size = new TextField("" + p_prev.getSize());
    final TextField price = new TextField("" + p_prev.getPrice());
    final TextField actual_status = new TextField("" + p_prev.getActual_status());
    name.setMinWidth(500.0D);
    
    final Label warning = new Label();
    grid.add(new Label("Наименование: "), 0, 0);
    grid.add(name, 1, 0);
    //grid.add(new Label("Cокращенное наимен: "), 0, 1);
    //grid.add(sname, 1, 1);
    grid.add(new Label("Группа товара: "), 0, 2);
    grid.add(chois, 1, 2);
    //grid.add(new Label("Вес в списке: "), 0, 3);
    //grid.add(helf, 1, 3);
    grid.add(new Label("Склад 0: "), 0, 3);
    grid.add(stock_0, 1, 3);
    grid.add(new Label("Склад 1: "), 0, 4);
    grid.add(stock_1, 1, 4);
    grid.add(new Label("Общее: "), 0, 5);
    grid.add(size, 1, 5);
    grid.add(new Label("Cтоимость: "), 0, 6);
    grid.add(price, 1, 6);
    grid.add(new Label("Cтатус: "), 0, 6);
    grid.add(actual_status, 1, 6);
    
    Button ok = new Button("Готово");
    Button close = new Button("Закрыть");
    ok.setMinSize(60.0D, 40.0D);
    close.setMinSize(60.0D, 40.0D);
    HBox button = new HBox();
    button.setSpacing(5.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    grid.add(button, 1, 7);
    grid.add(warning, 1, 8, 2, 1);
    Scene scene = new Scene(grid);
    grid.setId("add");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    

    helf.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        warning.setText("Изменение веса в списке, переместит вас в группу с товаром.");
        warning.setTextFill(Color.YELLOW);

      }
      

    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        String cor_name = name.getText();
        String cor_sname = cor_name;//sname.getText();
        if ((chois.getSelectionModel().getSelectedIndex() != -1) && (!cor_name.equals("")) && (!cor_sname.equals(""))) {
          String p = (String)chois.getValue();
          String stock_full = stock.getText();
          String stock_0i = stock_0.getText();
          String stock_1i = stock_1.getText();
          
          try {
            int to = p.indexOf(" ");
            p = p.substring(0, to);
            int gr = Integer.parseInt(p);
            int hl = 1;//Integer.parseInt(helf.getText());
            int sp = Integer.parseInt(size.getText());
            double pr = Double.parseDouble(price.getText());
            int as = Integer.parseInt(actual_status.getText());

            int to_stock = stock_full.indexOf(" ");
            stock_full = stock_full.substring(0, to_stock);
            int stock_full_i = Integer.parseInt(stock_full);
            
            int to_stock_0 = stock_0i.indexOf(" ");
            stock_0i = stock_full.substring(0, to_stock_0);
            int stock_0_full = Integer.parseInt(stock_0i);
            
            int to_stock_1 = stock_1i.indexOf(" ");
            stock_1i = stock_full.substring(0, to_stock_1);
            int stock_1_full = Integer.parseInt(stock_1i);
                        
            db.updatePrice(p_prev.getId(), name.getText(), sname.getText(), gr, hl, pr, sp, as,stock_full_i,stock_0_full,stock_1_full);
            AdminPane p_next = new AdminPane(select, p_prev.getId(), cor_name, cor_sname, gr, hl, sp, pr, as,stock_full_i,stock_0_full,stock_1_full);
            db.setLog(p_next, p_prev, 1, Repeat.user.getName());

            Repeat.admprod.set(select, new AdminPane(select, p_prev.getId(), cor_name, cor_sname, gr, hl, sp, pr, as,stock_full_i,stock_0_full,stock_1_full));
            if (p_prev.getHelf() != hl) { db.getGroupProductAdmin(Repeat.admprod, gr, 0);
            }
            stage.close();
          } catch (NumberFormatException ex) {
            MessageBox.show(stage, "Вводимые данные некорректны", "Information dialog", 16842752);
          }
        }
        else {
          MessageBox.show(stage, "Вводимые данные некорректны", "Information dialog", 16842752);
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
    stage.setHeight(310.0D);
    stage.show();
  }
}
