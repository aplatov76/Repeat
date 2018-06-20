package GUI;

import Collection.GroupProduct;
import Connect.ConnectDB;
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
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;





public class CorrectGroup
  extends Application
{
  public static ObservableList<GroupProduct> groups = FXCollections.observableArrayList();
  
  public CorrectGroup() {}
  
  public void start(final Stage stage) { final ConnectDB db = new ConnectDB();
    
    VBox root = new VBox();
    
    VBox button = new VBox();
    stage.setTitle("Управление группами");
    button.setSpacing(5.0D);
    final TableView tb = createTable();
    button.setId("but");
    Button add = new Button("Добавить");
    Button correct = new Button("Изменить");
    Button delet = new Button("Удалить");
    Button exit = new Button("Выход");
    add.setId("dark-blue");
    correct.setId("dark-blue");
    delet.setId("dark-blue");
    exit.setId("dark-blue");
    delet.setMinWidth(85.0D);
    exit.setMinWidth(85.0D);
    button.getChildren().addAll(new Node[] { add, correct, delet, exit });
    
    root.setPadding(new Insets(0.0D, 10.0D, 0.0D, 0.0D));
    root.getChildren().add(tb);
    correct.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        int n = tb.getSelectionModel().getSelectedIndex();
        System.out.print("Номер : " + n);
        if (n != -1)
        {



          String name = Dialogs.showInputDialog(stage, "Введите новое имя");
          
          if (name != null) {
            int id = ((GroupProduct)CorrectGroup.groups.get(n)).getId();
            db.updateGroup(id, name);
            CorrectGroup.groups.set(n, new GroupProduct(n, id, name));
          }
        } else {
          Dialogs.showInformationDialog(stage, "Ничего не выбрано.");
        }
      }
    });
    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        String name = Dialogs.showInputDialog(stage, "Введите новое имя");
        
        if (name != null) {
          int id = db.addGroup(name);
          
          CorrectGroup.groups.add(new GroupProduct(CorrectGroup.groups.size(), id, name));
        } else {
          Dialogs.showInformationDialog(stage, "Наименование некорректно.");
        }
        
      }
    });
    delet.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        int n = tb.getSelectionModel().getSelectedIndex();
        
        if (n != -1)
        {
          Dialogs.DialogResponse t = Dialogs.showConfirmDialog(stage, "Удалить выбранную группу?");
          
          if (t.equals(Dialogs.DialogResponse.YES))
          {




            int id = ((GroupProduct)CorrectGroup.groups.get(n)).getId();
            if (db.validateDeletGroup(id)) {
              db.deletGroup(id);
              CorrectGroup.groups.remove(n);
            } else {
              Dialogs.showErrorDialog(stage, "Нельзя удалить группу , есть входящие в нее категории.");
            }
          }
        } else {
          Dialogs.showErrorDialog(stage, "Выберите категорию");
        }
      }
    });
    exit.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        stage.close();
      }
    });
    BorderPane b = new BorderPane();
    b.setCenter(root);
    b.setRight(button);
    b.setId("bp");
    b.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    Scene scene = new Scene(b);
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(600.0D);
    stage.setHeight(450.0D);
    stage.show();
  }
  
  private TableView createTable() {
    TableView<GroupProduct> table = new TableView();
    
    ConnectDB db = new ConnectDB();
    
    table.columnResizePolicyProperty();
    table.setId("tablefont");
    TableColumn n = new TableColumn("№");
    n.setMinWidth(50.0D);
    n.setMaxWidth(60.0D);
    n.setCellValueFactory(new PropertyValueFactory("n"));
    


    TableColumn id = new TableColumn("Код");
    id.setMinWidth(60.0D);
    id.setMaxWidth(60.0D);
    id.setCellValueFactory(new PropertyValueFactory("id"));
    

    TableColumn name = new TableColumn("Наименование");
    name.setMinWidth(150.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setItems(groups);
    

    table.getColumns().addAll(new TableColumn[] { n, id, name });
    db.loadGroupTable(groups);
    
    return table;
  }
}
