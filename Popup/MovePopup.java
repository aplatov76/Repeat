/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Popup;

import Collection.MoveProduct;
import Connect.ConnectDB;
import calendar.SimpleCalendar;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

/**
 *
 * @author andrew
 */

public class MovePopup {
    
    public GridPane node;
    
    public MovePopup(final TableView<MoveProduct> table, final Popup popupmenu, final Stage st,final ConnectDB db) {
        
    node = new GridPane();
        
    //final ConnectDB db = new ConnectDB();
    
    node.setId("but");
    node.setVgap(8.0D);
    node.setHgap(8.0D);
    
    SimpleCalendar simpleCalender = new SimpleCalendar();
    SimpleCalendar simpleCalendar = new SimpleCalendar();
    final TextField dateField = new TextField("Select date");
    final TextField dataField = new TextField("Select date");
    simpleCalender.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dateField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      
    });
    simpleCalendar.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        dataField.setText(new SimpleDateFormat("yyyy.MM.dd").format(newDate));
      }
      

    });
    final Popup popupfiltr = new Popup();
    

    Button ok = new Button("Показать");
    Button ext = new Button("Закрыть");
    
    ok.setId("dark-blue");
    ext.setId("dark-blue");
    
    HBox box = new HBox();
    
    box.setSpacing(8.0D);
    box.getChildren().addAll(new Node[] { ok, ext});
    
    node.add(simpleCalender, 0, 0);
    node.add(dateField, 1, 0);
    node.add(simpleCalendar, 0, 1);
    node.add(dataField, 1, 1);
    node.add(box, 1, 2);
    
    ext.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {

        if (popupfiltr.isShowing()) popupfiltr.hide();
        if (popupmenu.isShowing()) { popupmenu.hide();
        }
        
      }
    });
    //final Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();

    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        Pattern p = Pattern.compile("2[0]\\d{2}\\.([0][1-9]|[1][0-2])\\.([0][1-9]|[1-2][0-9]|[3][0-1])");
        
        Matcher m = p.matcher(dateField.getText());
        Matcher n = p.matcher(dataField.getText());
        if ((m.matches()) && (n.matches())) {
          //a.setItems(db.getJournal(dateField.getText(), dataField.getText() + " 23:59", add.selectedProperty().get(), del.selectedProperty().get(), cor.selectedProperty().get()));
           table.setItems(db.getMoveProductHistory(dateField.getText(), dataField.getText() + " 23:59"));
        } else {
          Dialogs.showErrorDialog(st, "Неверный формат даты");
        }
      }
    });
    }
}
