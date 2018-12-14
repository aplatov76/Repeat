package GUI;

import Collection.AdminPane;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
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
    //final TextField sname = new TextField();
    
    final CheckBox c_stock_0 = new CheckBox();
    final CheckBox c_stock_1 = new CheckBox();
    
    HBox c_stock = new HBox();
    c_stock.getChildren().addAll(new Label("Воронцово: "),c_stock_0,new Label("Ильинск: "),c_stock_1);
    
    final TextField stock = new TextField();
    final TextField stock_0 = new TextField("0");
    final TextField stock_1 = new TextField("0");
    
    final ChoiceBox<String> chois = new ChoiceBox(group);
    final ChoiceBox<Integer> helf = new ChoiceBox();

    final TextField size = new TextField();
    final TextField price = new TextField();
    
    final TextField min_remainder = new TextField();
    final TextField articul = new TextField() {
    @Override public void replaceText(int start, int end, String text) {
        // If the replaced text would end up being invalid, then simply
        // ignore this call!
        if (!text.matches("[а-я]")) {
            super.replaceText(start, end, text);
        }
    }

    @Override public void replaceSelection(String text) {
        if (!text.matches("[а-я]")) {
            super.replaceSelection(text);
        }
    }
};
    
    name.setMinWidth(500.0D);
    /*grid.add(new Label("Полное наименование: "), 0, 0);
    grid.add(name, 1, 0);*/
    Label art_l = new Label("Артикул: ");
    art_l.setTextFill(Color.RED);
    
    grid.add(art_l, 0, 1);
    grid.add(articul, 1, 1);
    grid.add(new Label("Наименование: "), 0, 2);
    grid.add(name, 1, 2);
    grid.add(new Label("Группа товара: "), 0, 3);
    grid.add(chois, 1, 3);
  /*grid.add(new Label("Вес в списке: "), 0, 3);
    grid.add(helf, 1, 3);*/
    grid.add(new Label("Склад: "), 0, 4);
    grid.add(c_stock, 1, 4);
    grid.add(new Label("Склад В: "), 0, 5);
    grid.add(stock_0, 1, 5);
    grid.add(new Label("Склад И: "), 0, 6);
    grid.add(stock_1, 1, 6);
    grid.add(new Label("Количество: "), 0, 7);
    grid.add(size, 1, 7);
    grid.add(new Label("Cтоимость: "), 0, 8);
    grid.add(price, 1, 8);
    grid.add(new Label("Мин. остаток: "), 0, 9);
    grid.add(min_remainder, 1, 9);
    
    size.setDisable(true);
    
    Button ok = new Button("Готово");
    Button close = new Button("Закрыть");
    ok.setMinSize(60.0D, 40.0D);
    close.setMinSize(60.0D, 40.0D);
    HBox button = new HBox();
    button.setSpacing(5.0D);
    button.getChildren().addAll(new Node[] { ok, close });
    grid.add(button, 1, 10);
    Scene scene = new Scene(grid);
    grid.setId("add");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    
    /*stock.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String stock_text = stock.getText();
        try {
            int stock = Integer.parseInt(stock_text);
                        
            switch(stock){
                case 0: {
                    stock_1.setDisable(true);  
                    stock_0.setDisable(false); 
                    break;
                }
                case 1: {
                    stock_0.setDisable(true); 
                    stock_1.setDisable(false); 
                    break;
                }
                case 10: {
                    stock_1.setDisable(false);
                    stock_0.setDisable(false);
                    break;
                }
                default:{
                    stock_1.setDisable(true);
                    stock_0.setDisable(true);
                    break;
                }
            }       
        }
        catch (NumberFormatException ex) {
            
                stock_1.setDisable(true);
                stock_0.setDisable(true);
        
        }
        
      }
    }); 
    */
    c_stock_0.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
          
          boolean stock_0i = c_stock_0.selectedProperty().get();
          boolean stock_1i = c_stock_1.selectedProperty().get();
          
          if (!stock_0i) {
              stock_0.setDisable(true);
          } else {
              stock_0.setDisable(false);
          }
          if (!stock_1i) {
              stock_1.setDisable(true);
          } else {
              stock_1.setDisable(false);
          }

          //System.out.println(stock_0i +" "+stock_1i);
          
      }
    });
    
    c_stock_1.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
          
          boolean stock_0i = c_stock_0.selectedProperty().get();
          boolean stock_1i = c_stock_1.selectedProperty().get();
          
          if(!stock_0i)stock_0.setDisable(true);
            else stock_0.setDisable(false);
          if(!stock_1i)stock_1.setDisable(true);
            else stock_1.setDisable(false);
          
          //System.out.println(stock_0i +" "+stock_1i);
          
      }
    });
    
    c_stock_0.setOnKeyPressed(null);
    stock_0.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String stock_t_0 = stock_0.getText();
        String stock_t_1 = stock_1.getText();
        
        try {
            
            int stock_0i = Integer.parseInt(stock_t_0);
            int stock_1i = Integer.parseInt(stock_t_1);  
            
            int result = stock_0i + stock_1i;
            
            size.setText(String.valueOf(result));
      
        }
        catch (NumberFormatException ex) {
        
        }
        
      }
    });
    stock_1.setOnKeyReleased(new EventHandler<KeyEvent>()
    {
      public void handle(KeyEvent t)
      {
        String stock_t_0 = stock_0.getText();
        String stock_t_1 = stock_1.getText();
        
        try {
            
            int stock_0i = Integer.parseInt(stock_t_0);
            int stock_1i = Integer.parseInt(stock_t_1);  
            
            int result = stock_0i + stock_1i;
            
            size.setText(String.valueOf(result));
      
        }
        catch (NumberFormatException ex) {
        
        }
        
      }
    }); 
    
    ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        //String new_name;// = name.getText();
        String snew_name = name.getText();
        String new_name = name.getText();
        int choisindex = chois.getSelectionModel().getSelectedIndex();
        int helfindex = 1;//helf.getSelectionModel().getSelectedIndex();
        //System.out.println(snew_name + " " + new_name+ " "+choisindex + " "+helfindex);
        
        String stock_t_0 = stock_0.getText();
        String stock_t_1 = stock_1.getText();
        
        int stock_0i = Integer.parseInt(stock_t_0);
        int stock_1i = Integer.parseInt(stock_t_1); 
        
        int min_remainder_i = Integer.parseInt(min_remainder.getText());
        String articul_i = articul.getText();
            
        int c_stock_result = -1;
        
        if(c_stock_0.selectedProperty().get())c_stock_result = 0;
        if(c_stock_1.selectedProperty().get())c_stock_result = 1;
        if(c_stock_1.selectedProperty().get() && c_stock_0.selectedProperty().get())c_stock_result = 10;
        
        if ((!new_name.equals("")) && (!snew_name.equals("")) && (choisindex != -1) && (helfindex != -1) && (c_stock_result != -1) && min_remainder_i > 0) {
          try {
            String p = (String)chois.getSelectionModel().getSelectedItem();
            int to = p.indexOf(" ");
            p = p.substring(0, to);
            int group = Integer.parseInt(p);
            if(db.connectCheck()){
                db.setProductAdmin(snew_name, snew_name, group, helfindex, Integer.parseInt(size.getText()), Double.parseDouble(price.getText()),c_stock_result,stock_0i,stock_1i,min_remainder_i, articul_i);
            
                int newid = db.setID(snew_name);
                int index_next = Repeat.admprod.size();
            
                AdminPane new_product = new AdminPane(index_next, newid, snew_name, snew_name, group, helfindex, Integer.parseInt(size.getText()), Double.parseDouble(price.getText()), 0,c_stock_result,stock_0i,stock_1i,min_remainder_i,articul_i);
                db.setLog(new_product, new_product, 0, Repeat.user.getName());
            
                Repeat.admprod.add(new_product);
                stage.close();
            }
            else Dialogs.showErrorDialog(stage, "Ошибка номер 403. Нет соединения", "Error Dialog", "");
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
        helf.setValue(1);    
      }
      

    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>() {
      public void handle(MouseEvent event) {
        db.closeConnect();
        stage.close();

      }
      
    });
    stage.setWidth(750.0D);
    stage.setHeight(360.0D);
    stage.show();
  }
  
  public int getWhere(int g, int h) {
    return Repeat.admprod.size();
  }
}
