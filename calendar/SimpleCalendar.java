package calendar;

import java.util.Date;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Window;





public class SimpleCalendar
  extends VBox
{
  private Popup popup;
  final DatePicker datePicker;
  
  public SimpleCalendar()
  {
    popup = new Popup();
    popup.setAutoHide(true);
    popup.setAutoFix(true);
    popup.setHideOnEscape(true);
    
    datePicker = new DatePicker();
    datePicker.dateProperty().addListener(new ChangeListener<Date>()
    {
      public void changed(ObservableValue<? extends Date> ov, Date oldDate, Date newDate)
      {
        if (popup.isShowing()) {
          popup.hide();
        }
      }
    });
    popup.getContent().add(datePicker);
    
    final Button calenderButton = new Button();
    calenderButton.setId("CalenderButton");
    calenderButton.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent ae)
      {
        Parent parent = getParent();
        
        Point2D point = calenderButton.localToScene(0.0D, 0.0D);
        double layoutX = parent.getScene().getWindow().getX() + parent.getScene().getX() + point.getX();
        double layoutY = parent.getScene().getWindow().getY() + parent.getScene().getY() + point.getY();
        popup.show(SimpleCalendar.this, layoutX, layoutY);
      }
      

    });
    getChildren().add(calenderButton);
  }
  


  public ObjectProperty<Date> dateProperty()
  {
    return datePicker.dateProperty();
  }
}
