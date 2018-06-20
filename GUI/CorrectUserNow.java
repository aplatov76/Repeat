package GUI;

import Connect.ConnectDB;
import fxuidemo.MD5;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public final class CorrectUserNow
  extends Application
{
  private TextField name;
  private PasswordField pass;
  private PasswordField repeatpass;
  private ChoiceBox<String> group;
  private int id_user;
  
  public CorrectUserNow(String n, String g, int id)
  {
    start(new Stage());
    name.setText(n);
    group.getSelectionModel().select(0);
    id_user = id;
  }
  

  public void start(final Stage stage)
  {
    GridPane pane = new GridPane();
    name = new TextField();
    pass = new PasswordField();
    repeatpass = new PasswordField();
    final ConnectDB db = new ConnectDB();
    ObservableList gr = FXCollections.observableArrayList();
    db.loadRules(gr);
    group = new ChoiceBox(gr);
    Button ok = new Button("Готово");
    Button close = new Button("Отмена");
    
    ok.setId("dark-blue");
    close.setId("dark-blue");
    
    pane.setHgap(8.0D);
    pane.setVgap(8.0D);
    
    pane.add(new Label("Login: "), 0, 0);
    pane.add(name, 1, 0);
    pane.add(new Label("Pass: "), 0, 1);
    pane.add(pass, 1, 1);
    pane.add(new Label("Repeat: "), 0, 2);
    pane.add(repeatpass, 1, 2);
    pane.add(new Label("Группа: "), 0, 3);
    pane.add(group, 1, 3);
    pane.setId("bp");
    HBox but = new HBox();
    but.setSpacing(5.0D);
    but.getChildren().addAll(new Node[] { ok, close });
    pane.add(but, 1, 4);
    pane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    Scene scene = new Scene(pane);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        MD5 md5 = new MD5();
        if ((!name.getText().equals("")) && (pass.getText().equals(repeatpass.getText())) && (!pass.getText().equals(""))) {
          String main = (String)group.getValue();
          int to = main.indexOf(" ");
          int groupid = Integer.parseInt(main.substring(0, to));
          

          db.setCorectUser(id_user, groupid, name.getText(), md5.getHash(pass.getText()));
          db.loadUser(CorrectUser.prod);
          stage.close();
        } else {
          Dialogs.showErrorDialog(stage, "Ooops, there was an error!", "Error Dialog", "Что то не верно");
        }
        
      }
    });
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(400.0D);
    stage.setHeight(230.0D);
    stage.show();
  }
  
  public int getIdUser() {
    return id_user;
  }
}
