package GUI;

import Collection.AdminPane;
import Collection.Person;
import Collection.Procurement_product_hist;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import java.net.URL;
import java.util.LinkedHashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddProductGate {

    public AddProductGate() {
    }

    public static final ObservableList<Procurement_product_hist> expected = FXCollections.observableArrayList();
    ;
  public static int typecontract = 0;

    public void start(Stage stage) {
        stage.setTitle("Прием товара");
        BorderPane root = new BorderPane();
        TableView<Procurement_product_hist> table = createTable();
        root.setLeft(table);
        VBox box = createBut(stage, table);
        root.setId("bp");
        box.setSpacing(10.0D);
        root.setRight(box);

        Scene scene = new Scene(root);
        box.setId("but");
        root.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
        scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
        stage.setScene(scene);
        String title = typecontract == 0 ? "Закупки ожидающие проверки" : "История закупок";
        stage.setTitle(title);
        stage.setTitle(title);
        stage.setWidth(1150.0D);
        stage.setHeight(600.0D);
        stage.show();
    }

    private VBox createBut(final Stage stage, final TableView table) {
        VBox node = new VBox();

        Button ok = new Button("Готово");
        ok.setDisable(!Repeat.user.getRules(19));
        Button add = new Button("Добавить");
        add.setDisable(!Repeat.user.getRules(16));
        Button correct = new Button("Изменить");
        correct.setDisable(!Repeat.user.getRules(17));
        Button delet = new Button("Удалить");
        delet.setDisable(!Repeat.user.getRules(18));

        Button print = new Button("Печать");
        Button close = new Button("Закрыть");

        ok.setMinSize(100.0D, 40.0D);
        add.setMinSize(100.0D, 40.0D);
        print.setMinSize(100.0D, 40.0D);
        close.setMinSize(100.0D, 40.0D);
        correct.setMinSize(100.0D, 40.0D);
        delet.setMinSize(100.0D, 40.0D);

        ok.setId("dark-blue");
        add.setId("dark-blue");
        print.setId("dark-blue");
        close.setId("dark-blue");
        correct.setId("dark-blue");
        delet.setId("dark-blue");

        add.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                AddProductSizeNow gdb = new AddProductSizeNow();
            }

        });
        ok.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                if (AddProductGate.expected.size() != 0) {
                    updateSize();
                    updateTable();
                } else {
                    Dialogs.showErrorDialog(stage, "Очередь проверки пуста/The queue is empty check.", "Error Dialog", "Ошибка");
                }
            }
        });
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                stage.close();
            }
        });
        correct.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int b = table.getSelectionModel().getSelectedIndex();
                AdminCorrectCheck abc;
                if (b != -1) {
                    int idp = ((Procurement_product_hist) AddProductGate.expected.get(b)).getId_product();
                    int sid = ((Procurement_product_hist) AddProductGate.expected.get(b)).getSize_second();

                    abc = new AdminCorrectCheck(((Procurement_product_hist) AddProductGate.expected.get(b)).getName_product(), idp, sid, b);
                }
            }
        });
        delet.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                int b = table.getSelectionModel().getSelectedIndex();
                if (b != -1) {
                    AddProductGate.this.deletProductGate(((Procurement_product_hist) AddProductGate.expected.get(b)).getId_product());
                    AddProductGate.expected.remove(b);
                    Dialogs.showInformationDialog(stage, "Удалено из очереди.", "Error Dialog", "Удаление записи");
                }

            }
        });
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
            }

        });
        print.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
            }

        });
        node.getChildren().addAll(new Node[]{ok, add, correct, delet, print, close});
        return node;
    }

    private TableView createTable() {
        TableView<Procurement_product_hist> table = new TableView<Procurement_product_hist>();
        table.setStyle("-fx-font: normal 11 Arial;");
        table.columnResizePolicyProperty();

        TableColumn id = new TableColumn("№ опер.");
        id.setMinWidth(40.0D);

        id.setCellValueFactory(new PropertyValueFactory("index"));

        TableColumn name = new TableColumn("Наименование товара");
        name.setMinWidth(390.0D);
        name.setCellValueFactory(new PropertyValueFactory("name_product"));

        TableColumn dstart = new TableColumn("Код");
        dstart.setMinWidth(90.0D);
        dstart.setCellValueFactory(new PropertyValueFactory("id_product"));

        TableColumn dend = new TableColumn("Остаток");
        dend.setMinWidth(90.0D);
        dend.setCellValueFactory(new PropertyValueFactory("size_first"));

        TableColumn price = new TableColumn("Добавленно");
        price.setMinWidth(90.0D);
        price.setCellValueFactory(new PropertyValueFactory("size_second"));

        TableColumn sum = new TableColumn("Итого");
        sum.setMinWidth(90.0D);
        sum.setCellValueFactory(new PropertyValueFactory("size_total"));

        TableColumn remain = new TableColumn("Статус");
        remain.setMinWidth(50.0D);
        remain.setCellValueFactory(new PropertyValueFactory("status"));

        TableColumn user = new TableColumn("User");
        user.setMinWidth(90.0D);
        user.setCellValueFactory(new PropertyValueFactory("user"));

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.setMaxWidth(962.0D);
        table.getColumns().addAll(id, name, dstart, dend, price, sum, remain, user);

        table.setItems(expected);
        return table;
    }

    public void getContract() {
        ConnectDB mysql = new ConnectDB();
        mysql.getProcurementProductHist(expected);
    }

    public void updateSize() {
        ConnectDB mysql = new ConnectDB();
        mysql.updateSizeAfterAdded();
    }

    public void updateTable() {
        ConnectDB mysql = new ConnectDB();
        int ns = expected.size();
        LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
        int n = Repeat.admprod.size();
        for (int i = 0; i < n; i++) {
            map.put(Integer.valueOf(((AdminPane) Repeat.admprod.get(i)).getId()), Integer.valueOf(i));
        }
        for (int i = 0; i < ns; i++) {
            int index = ((Integer) map.get(Integer.valueOf(((Procurement_product_hist) expected.get(i)).getId_product()))).intValue();
            AdminPane isnow = (AdminPane) Repeat.admprod.get(index);
            int size_total = mysql.getSize(isnow.getId());
            Repeat.admprod.set(index, new AdminPane(index, isnow.getId(), isnow.getName(), isnow.getShortname(), isnow.getGroup(), isnow.getHelf(), size_total, isnow.getPrice(), 0,isnow.getStock(),isnow.getStock_Size_0(),isnow.getStock_Size_1()));
        }
        expected.clear();
    }

    private void deletProductGate(int id) {
        ConnectDB mysql = new ConnectDB();
        mysql.deletProductGate(id);
    }
}
