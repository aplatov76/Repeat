package GUI;

import Collection.Users;
import Connect.ConnectDB;
import fxuidemo.MD5;
import javafx.application.Application;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public final class AddUser
  extends Application
{
  private TextField name;
  private TextField group_user;
  private PasswordField pass;
  private PasswordField repeatpass;
  private ChoiceBox<String> group;
  private int id_user;
  
  public AddUser()
  {
    start(new Stage());
  }
  

  public void start(final Stage stage)
  {
    GridPane pane = new GridPane();
    name = new TextField();
    pass = new PasswordField();
    repeatpass = new PasswordField();
    group_user = new TextField();
    
    name.setMinWidth(450.0D);
    
    final ConnectDB db = new ConnectDB();
    ObservableList groups = FXCollections.observableArrayList();
    db.loadRules(groups);
    group = new ChoiceBox(groups);
    final CheckBox use_key = new CheckBox();
    
    Button ok = new Button("Готово");
    ok.setId("dark-blue");
    
    Button close = new Button("Отмена");
    close.setId("dark-blue");
    pane.setHgap(8.0D);
    pane.setVgap(8.0D);
    final HBox keyhbox = new HBox();
    final TextArea keytext = new TextArea();
    keytext.setText("No key");
    keytext.setMaxWidth(450.0D);
    keyhbox.setSpacing(5.0D);
    pane.add(new Label("Логин: "), 0, 0);
    pane.add(name, 1, 0);
    pane.add(new Label("Пароль: "), 0, 1);
    pane.add(pass, 1, 1);
    pane.add(new Label("Повтор: "), 0, 2);
    pane.add(repeatpass, 1, 2);
    pane.add(new Label("Роль: "), 0, 3);
    pane.add(group, 1, 3);
    pane.add(new Label("Роль: "), 0, 4);
    pane.add(group_user, 1, 4);
    pane.add(new Label("Ключ:"), 0, 5);
    pane.add(use_key, 1, 5);
    pane.add(keyhbox, 1, 6);
    HBox but = new HBox();
    but.setSpacing(5.0D);
    but.getChildren().addAll(new Node[] { ok, close });
    pane.add(but, 1, 7);
    pane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    pane.setId("bp");
    Scene scene = new Scene(pane);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        db.closeConnect();
        stage.close();
      }
      
    });
    use_key.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        if (use_key.isSelected())
        {
          keyhbox.getChildren().add(keytext);
          stage.setHeight(440.0D);
        }
        else
        {
          keyhbox.getChildren().remove(0);
          stage.setHeight(260.0D);
        }
        
      }
    });
    use_key.setOnKeyPressed(new EventHandler<KeyEvent>()
    {

      public void handle(KeyEvent t)
      {
        if (!use_key.isSelected()) {
          System.out.println(use_key.isSelected());
          keyhbox.getChildren().add(keytext);
          stage.setHeight(440.0D);
        }
        else
        {
          System.out.println(use_key.isSelected());
          keyhbox.getChildren().remove(0);
          stage.setHeight(260.0D);
        }
        
      }
      

    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        MD5 md5 = new MD5();
        
        String new_name = name.getText();
        String new_pass = pass.getText();
        String new_rpass = repeatpass.getText();
        String group_cassa = group_user.getText();
        
        if ((!new_name.equals("")) && (new_pass.equals(new_rpass)) && (!new_pass.equals("")) && (group.getSelectionModel().getSelectedIndex() != -1)) {
          String main = (String)group.getValue();
          int to = main.indexOf(" ");
          int groupid = Integer.parseInt(main.substring(0, to));
          boolean uk = use_key.isSelected();
          String tk = keytext.getText();
          int group_user_cassa = Integer.parseInt(group_cassa);
          int idbreak = db.setNewUser(new_name, md5.getHash(new_pass), groupid, uk, tk,group_user_cassa);
          
          CorrectUser.prod.add(new Users(new_name, "", main.substring(to + 2, main.length()), idbreak));
          stage.close();
        } else {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Проверьте вводимые данные.");
        }
        
      }
    });
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(600.0D);
    stage.setHeight(260.0D);
    stage.show();
  }
  
  public int getIdUser() {
    return id_user;
  }
}
