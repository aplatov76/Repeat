package GUI;

import Collection.Cassa;
import Collection.contract;
import Connect.ConnectDB;
import fxuidemo.Repeat;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public final class ContractPaid
  extends Application
{
  int index;
  
  public ContractPaid(int i)
  {
    index = i;
    start(new Stage());
  }
  
  public void start(final Stage stage)
  {
    VBox pane = new VBox();
    
    final ConnectDB db = new ConnectDB();
    final TextField name = new TextField();
    System.out.print("index: " + index);
    name.setText(((contract)Contracts.contract.get(index)).getName());
    final TextField summa = new TextField();
    name.setDisable(true);
    Button ok = new Button("Готово");
    ok.setId("dark-blue");
    
    Button close = new Button("Отмена");
    close.setId("dark-blue");
    pane.setSpacing(8.0D);
    TextField box = new TextField();
    box.setText(Integer.toString(((contract)Contracts.contract.get(index)).getId()));
    box.setDisable(true);
    box.setMinWidth(100.0D);
    
    HBox but = new HBox();
    but.setSpacing(5.0D);
    but.getChildren().addAll(new Node[] { ok, close });
    pane.getChildren().addAll(new Node[] { new Label("№ контракта:"), box, new Label("ФИО Заказчика"), name, new Label("Введите вносимую сумму:"), summa, but });
    pane.setId("bp");
    
    pane.setPadding(new Insets(20.0D, 20.0D, 20.0D, 20.0D));
    Scene scene = new Scene(pane);
    
    close.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        db.closeConnect();
        stage.close();
      }
      
    });
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event) {
        try {
          double paidadd = Double.parseDouble(summa.getText());
          double total = ((contract)Contracts.contract.get(index)).getTotal_amount();
          double paidnow = ((contract)Contracts.contract.get(index)).getAmount_paid();
          double remain = ((contract)Contracts.contract.get(index)).getRemaining_sum();
          int idc = ((contract)Contracts.contract.get(index)).getId();
          boolean st = true;
          if ((paidadd > 0.0D) && (paidadd <= remain)) {
            paidnow += paidadd;
            remain -= paidadd;
            if ((total == paidnow) && (remain == 0.0D)) {
              st = false;
              Dialogs.showInformationDialog(stage, "Сумма по договору внесена полностью, договор закрыт.");
            }
            ((contract)Contracts.contract.get(index)).setAmount_paid(paidnow);
            ((contract)Contracts.contract.get(index)).setRemaining_sum(remain);
            Contracts.contract.set(index, new contract(idc, name.getText(), ((contract)Contracts.contract.get(index)).getSnum(), ((contract)Contracts.contract.get(index)).getAdress(), ((contract)Contracts.contract.get(index)).getYear(), ((contract)Contracts.contract.get(index)).getPascout(), ((contract)Contracts.contract.get(index)).getPhone(), ((contract)Contracts.contract.get(index)).getDstart(), ((contract)Contracts.contract.get(index)).getDend(), total, paidnow, remain, st, ((contract)Contracts.contract.get(index)).getUser()));
            if(db.connectCheck()){
                Cassa.cassa += paidadd;
                Cassa.setCassa();
                db.setContractUpdatePaid(idc, paidnow, remain, st);
                db.setContractPaid(idc, paidadd, remain, Repeat.user.getName());
                stage.close();
            } else Dialogs.showErrorDialog(stage, "Ошибка номер 403. Нет соединения", "Error Dialog", "");
          }
          else {
            Dialogs.showErrorDialog(stage, "Ошибка 408, сумма отрицательна или превышает остаток.", "Error Dialog", "Что то не верно");
          }
        } catch (NumberFormatException ex) {
          Dialogs.showErrorDialog(stage, "Ошибка 407, неправильно введена сумма ", "Error Dialog", "Что то не верно");
        }
        
      }
      

    });
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    stage.setScene(scene);
    stage.setWidth(400.0D);
    stage.setHeight(270.0D);
    stage.show();
  }
  
  private String getIdUser() {
    return "";
  }
}
