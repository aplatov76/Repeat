package GUI;

import Collection.Rules;
import Connect.ConnectDB;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;




public class AddRule
  extends Application
{
  public AddRule()
  {
    start(new Stage());
  }
  
  public void start(final Stage stage) {
    GridPane pane = new GridPane();
    final ConnectDB db = new ConnectDB();
    pane.setVgap(8.0D);
    pane.setHgap(8.0D);
    pane.add(new Label("Наименование : "), 0, 0);
    final TextField name = new TextField();
    pane.add(name, 1, 0);
    pane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    HBox box = new HBox();
    box.setSpacing(8.0D);
    Button ok = new Button("Ok");
    Button close = new Button("Close");
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        db.setNewRule(name.getText());
        CorrectUser.rules.add(new Rules(name.getText(), false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false));
        stage.close();
      }
    });
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        stage.close();
      }
    });
    box.getChildren().addAll(new Node[] { ok, close });
    pane.add(box, 1, 1);
    pane.setId("add");
    
    Scene scene = new Scene(pane);
    
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(400.0D);
    stage.setHeight(230.0D);
    stage.show();
  }
}
