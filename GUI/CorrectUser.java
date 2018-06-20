package GUI;

import Collection.Rules;
import Collection.Users;
import Connect.ConnectDB;
import java.net.URL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class CorrectUser
{
  public CorrectUser() {}
  
  public static ObservableList<Users> prod = ;
  public static ObservableList<Rules> rules = FXCollections.observableArrayList();
  
  public void start(Stage stage) { BorderPane root = new BorderPane();
    

    TableView tbu = createTable();
    TableView tbr = createTableRules();
    VBox box = createBut(stage, tbu, tbr);
    VBox tb = new VBox();
    tb.setSpacing(10.0D);
    root.setId("bp");
    box.setSpacing(10.0D);
    root.setRight(box);
    
    tb.getChildren().addAll(new Node[] { tbu, tbr });
    tb.getChildren().add(setLabel());
    root.setLeft(tb);
    Scene scene = new Scene(root);
    box.setId("but");
    root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    scene.getStylesheets().add(getClass().getResource("/fxuidemo/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(1095.0D);
    stage.setHeight(600.0D);
    stage.show();
  }
  

  private VBox createBut(final Stage stage, final TableView tb, final TableView tbr)
  {
    VBox node = new VBox();
    final ConnectDB db = new ConnectDB();
    
    Button add = new Button("Добавить");
    Button cor = new Button("Изменить");
    Button del = new Button("Удалить");
    Button close = new Button("Закрыть");
    
    add.setMinSize(100.0D, 40.0D);
    cor.setMinSize(100.0D, 40.0D);
    del.setMinSize(100.0D, 40.0D);
    close.setMinSize(100.0D, 40.0D);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        stage.close();
      }
      
    });
    del.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {
        int b = tb.getSelectionModel().getSelectedIndex();
        if (b != -1) {
          Dialogs.DialogResponse a = Dialogs.showConfirmDialog(stage, "Удалить " + ((Users)CorrectUser.prod.get(b)).getName() + " ?");
          if (a.name().equals("YES")) {
            db.deletUser(((Users)CorrectUser.prod.get(b)).getName());
            CorrectUser.prod.remove(b);
          }
          
        }
      }
    });
    cor.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = tb.getSelectionModel().getSelectedIndex();
        CorrectUserNow now; if (b != -1) {
          now = new CorrectUserNow(((Users)CorrectUser.prod.get(b)).getName(), ((Users)CorrectUser.prod.get(b)).getRule(), ((Users)CorrectUser.prod.get(b)).getIdUser());
        } else {
          Dialogs.showErrorDialog(stage, "Ничего не выбрано");
        }
      }
    });
    add.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        AddUser add = new AddUser();
      }
      
    });
    Label lbl = new Label("Rules");
    lbl.setTextFill(Color.FIREBRICK);
    Label lblc = new Label("Exit");
    lblc.setTextFill(Color.FIREBRICK);
    Button radd = new Button("Добавить");
    Button rclear = new Button("Удалить");
    Button rsave = new Button("Сохранить");
    radd.setMinSize(100.0D, 40.0D);
    rclear.setMinSize(100.0D, 40.0D);
    rsave.setMinSize(100.0D, 40.0D);
    
    rclear.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = tbr.getSelectionModel().getSelectedIndex();
        if (b != -1) {
          Dialogs.DialogResponse a = Dialogs.showConfirmDialog(stage, "Удалить " + ((Rules)CorrectUser.rules.get(b)).getName() + " ?");
          if (a.name().equals("YES")) {
            db.deletRule(((Rules)CorrectUser.rules.get(b)).getName());
            CorrectUser.rules.remove(b);
          }
        }
      }
    });
    rsave.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        int b = tbr.getSelectionModel().getSelectedIndex();
        if (b != -1) {
          db.setRules(((Rules)CorrectUser.rules.get(b)).getName(), ((Rules)CorrectUser.rules.get(b)).getRegistr().isSelected(), ((Rules)CorrectUser.rules.get(b)).getLook_price().isSelected(), ((Rules)CorrectUser.rules.get(b)).getAdd_product().isSelected(), ((Rules)CorrectUser.rules.get(b)).getClear_product().isSelected(), ((Rules)CorrectUser.rules.get(b)).getCorrect_product().isSelected(), ((Rules)CorrectUser.rules.get(b)).getLook_history().isSelected(), ((Rules)CorrectUser.rules.get(b)).getLook_journal().isSelected(), ((Rules)CorrectUser.rules.get(b)).getCorrect_user().isSelected(), ((Rules)CorrectUser.rules.get(b)).getUse_group().isSelected(), ((Rules)CorrectUser.rules.get(b)).getFull_otchet().isSelected(), ((Rules)CorrectUser.rules.get(b)).getFull_registration().isSelected(), ((Rules)CorrectUser.rules.get(b)).getInstallment_paid().isSelected(), ((Rules)CorrectUser.rules.get(b)).getCustomer_order().isSelected(), ((Rules)CorrectUser.rules.get(b)).getOrder_product_add().isSelected(), ((Rules)CorrectUser.rules.get(b)).getOrder_product_cor().isSelected(), ((Rules)CorrectUser.rules.get(b)).getOrder_product_del().isSelected(), ((Rules)CorrectUser.rules.get(b)).getZacup_product_add().isSelected(), ((Rules)CorrectUser.rules.get(b)).getZacup_product_cor().isSelected(), ((Rules)CorrectUser.rules.get(b)).getZacup_product_del().isSelected(), ((Rules)CorrectUser.rules.get(b)).getZacup_product_ok().isSelected(), ((Rules)CorrectUser.rules.get(b)).getLook_hist_metall().isSelected());
          




















          Dialogs.showInformationDialog(stage, "Данные успешно сохранены", null);
        } else {
          Dialogs.showErrorDialog(stage, "Ничего не выбрано");
        }
      } });
    radd.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        AddRule a = new AddRule();
      }
    });
    node.getChildren().addAll(new Node[] { add, cor, del, lbl, radd, rclear, rsave, lblc, close });
    return node;
  }
  
  private TableView createTable() {
    TableView<Users> table = new TableView();
    
    ConnectDB mysql = new ConnectDB();
    

    table.columnResizePolicyProperty();
    
    TableColumn name = new TableColumn("Имя пользователя");
    name.setMinWidth(200.0D);
    name.setMaxWidth(200.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    


    TableColumn rule = new TableColumn("Роль");
    rule.setMinWidth(60.0D);
    rule.setCellValueFactory(new PropertyValueFactory("rule"));
    


    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setId("tablefont");
    table.setItems(prod);
    table.setMaxWidth(890.0D);
    table.setMaxHeight(180.0D);
    table.getColumns().addAll(new TableColumn[] { name, rule });
    mysql.loadUser(prod);
    return table;
  }
  
  private TableView createTableRules() {
    TableView<Rules> table = new TableView();
    
    ConnectDB mysql = new ConnectDB();
    

    table.columnResizePolicyProperty();
    
    TableColumn name = new TableColumn("Роль");
    name.setMinWidth(100.0D);
    name.setCellValueFactory(new PropertyValueFactory("name"));
    


    TableColumn reg = new TableColumn("1");
    reg.setMaxWidth(45.0D);
    reg.setCellValueFactory(new PropertyValueFactory("registr"));
    

    TableColumn rule = new TableColumn("2");
    rule.setMaxWidth(45.0D);
    rule.setCellValueFactory(new PropertyValueFactory("look_price"));
    

    TableColumn add_product = new TableColumn("3");
    add_product.setMaxWidth(45.0D);
    add_product.setCellValueFactory(new PropertyValueFactory("add_product"));
    

    TableColumn clear_product = new TableColumn("4");
    clear_product.setMaxWidth(45.0D);
    clear_product.setCellValueFactory(new PropertyValueFactory("clear_product"));
    

    TableColumn correct_product = new TableColumn("5");
    correct_product.setMaxWidth(45.0D);
    correct_product.setCellValueFactory(new PropertyValueFactory("correct_product"));
    

    TableColumn look_history = new TableColumn("6");
    look_history.setMaxWidth(45.0D);
    look_history.setCellValueFactory(new PropertyValueFactory("look_history"));
    

    TableColumn look_journal = new TableColumn("7");
    look_journal.setMaxWidth(45.0D);
    look_journal.setCellValueFactory(new PropertyValueFactory("look_journal"));
    

    TableColumn correct_user = new TableColumn("8");
    correct_user.setMaxWidth(45.0D);
    correct_user.setCellValueFactory(new PropertyValueFactory("correct_user"));
    

    TableColumn use_group = new TableColumn("9");
    use_group.setMaxWidth(45.0D);
    use_group.setCellValueFactory(new PropertyValueFactory("use_group"));
    
    TableColumn full_otchet = new TableColumn("10");
    full_otchet.setMaxWidth(45.0D);
    full_otchet.setCellValueFactory(new PropertyValueFactory("full_otchet"));
    
    TableColumn full_registration = new TableColumn("11");
    full_registration.setMaxWidth(45.0D);
    full_registration.setCellValueFactory(new PropertyValueFactory("full_registration"));
    
    TableColumn installment_paid = new TableColumn("12");
    installment_paid.setMaxWidth(45.0D);
    installment_paid.setCellValueFactory(new PropertyValueFactory("installment_paid"));
    
    TableColumn customer_order = new TableColumn("13");
    customer_order.setMaxWidth(45.0D);
    customer_order.setCellValueFactory(new PropertyValueFactory("customer_order"));
    

    TableColumn order_product_add = new TableColumn("14");
    order_product_add.setMaxWidth(45.0D);
    order_product_add.setCellValueFactory(new PropertyValueFactory("order_product_add"));
    

    TableColumn order_product_cor = new TableColumn("15");
    order_product_cor.setMaxWidth(45.0D);
    order_product_cor.setCellValueFactory(new PropertyValueFactory("order_product_cor"));
    

    TableColumn order_product_del = new TableColumn("16");
    order_product_del.setMaxWidth(45.0D);
    order_product_del.setCellValueFactory(new PropertyValueFactory("order_product_del"));
    
    TableColumn zacup_product_add = new TableColumn("17");
    zacup_product_add.setMaxWidth(45.0D);
    zacup_product_add.setCellValueFactory(new PropertyValueFactory("zacup_product_add"));
    
    TableColumn zacup_product_cor = new TableColumn("18");
    zacup_product_cor.setMaxWidth(45.0D);
    zacup_product_cor.setCellValueFactory(new PropertyValueFactory("zacup_product_cor"));
    
    TableColumn zacup_product_del = new TableColumn("19");
    zacup_product_del.setMaxWidth(45.0D);
    zacup_product_del.setCellValueFactory(new PropertyValueFactory("zacup_product_del"));
    
    TableColumn zacup_product_ok = new TableColumn("20");
    zacup_product_ok.setMaxWidth(45.0D);
    zacup_product_ok.setCellValueFactory(new PropertyValueFactory("zacup_product_ok"));
    
    TableColumn look_hist_metall = new TableColumn("21");
    look_hist_metall.setMaxWidth(45.0D);
    look_hist_metall.setCellValueFactory(new PropertyValueFactory("look_hist_metall"));
    

    table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    table.setId("tablefont");
    
    table.setItems(rules);
    table.setMaxWidth(890.0D);
    table.setMaxHeight(180.0D);
    table.getColumns().addAll(new TableColumn[] { name, reg, rule, add_product, clear_product, correct_product, look_history, look_journal, correct_user, use_group, full_otchet, full_registration, installment_paid, customer_order, order_product_add, order_product_cor, order_product_del, zacup_product_add, zacup_product_cor, zacup_product_del, zacup_product_ok, look_hist_metall });
    



    mysql.loadRulesCorrect(rules);
    
    return table;
  }
  
  private HBox setLabel() {
    HBox main = new HBox();
    main.setSpacing(8.0D);
    HBox m1 = new HBox();
    HBox m2 = new HBox();
    
    Label l = new Label("1 - Регистрация продукта\n2 - Просмотр прайс листа\n3 - Добавление категории\n4 - Удаление категории\n5 - Корректировка категории\n6 - Просморт историю\n7 - Просмотр журнала\n8 - Работа с пользователями\n9 - Управление группами\n10 - Просмотр полного отчета");
    








    Label r = new Label("11 - Просмотр полной регистр.\n12  Продажа в рассрочку\n13  Cоздание договора поставки\n14. Добавлять записи в список прод. \"Под заказ\"\n15. Изменять записи в списке прод. \"Под заказ\"\n16. Удалять записи в списке прод. \"Под заказ\"\n17. Добавлять записи в список закупок\n18. Изменять записи в списке закупок\n19. Удалять записи в списке закупок\n20. Одобрять группы закупок");
    








    Label d = new Label("21 - Просмотр истории металл.\n");
    
    r.setTextFill(Color.ORANGE);
    l.setTextFill(Color.ORANGE);
    d.setTextFill(Color.ORANGE);
    m1.getChildren().add(l);
    m2.getChildren().addAll(new Node[] { r, d });
    
    main.getChildren().addAll(new Node[] { m1, m2 });
    return main;
  }
}
