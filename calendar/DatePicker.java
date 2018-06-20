package calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;






public class DatePicker
  extends StackPane
{
  private boolean isSundayFirstDay = true;
  private Calendar today;
  private ObjectProperty<Date> dateProperty;
  private final DayCell[] dayCells;
  private int month;
  private int year;
  private static final int COLUMN_NUMBER = 7;
  private static final int ROW_NUMBER = 6;
  private static final double CELL_HEIGHT = 20.0D;
  private static final double CELL_WIDTH = 25.0D;
  private static final double ARROW_SIZE = 6.0D;
  private static final String DATEPICKER_OTHERMONTH = "datepicker-othermonth-cell";
  private static final String DATEPICKER_TODAY = "datepicker-today-cell";
  private static final String DATEPICKER_MONTH = "datepicker-month-cell";
  private static final String DATEPICKER_WEEKDAY = "datepicker-weekday-row";
  private static final String DATEPICKER_MONTHYEAR = "datepicker-monthyear-row";
  private static final String DATEPICKER_ARROW = "DatepickerArrow";
  private static final String DATEPICKER_DAYCELL = "DatepickerDayCell";
  private static final String DATEPICKER_TODAYBUTTON = "DatepickerTodayButton";
  private static final String DATEPICKER_TODAYBUTTONBOX = "DatepickerTodayButtonBox";
  private String[] cellStyleList = { "datepicker-othermonth-cell", "datepicker-today-cell", "datepicker-month-cell" };
  



  public DatePicker()
  {
    VBox pickerBox = new VBox();
    setId("DatePicker");
    today = Calendar.getInstance();
    
    isSundayFirstDay = (today.getFirstDayOfWeek() == 1);
    month = today.get(2);
    year = today.get(1);
    dateProperty = new SimpleObjectProperty(new GregorianCalendar().getTime());
    

    HBox monthYearRow = new HBox();
    final Label monthYear = new Label(getMonthYear(new GregorianCalendar().getTime()));
    monthYear.setMinHeight(30.0D);
    monthYear.setMinWidth(150.0D);
    monthYear.setAlignment(Pos.CENTER);
    monthYearRow.getStyleClass().add("datepicker-monthyear-row");
    Path decrementArrow = new Path();
    decrementArrow.setId("DatepickerArrow");
    decrementArrow.getElements().addAll(new PathElement[] { new MoveTo(0.0D, 6.0D), new LineTo(0.0D, -6.0D), new LineTo(-6.0D, 0.0D), new LineTo(0.0D, 6.0D) });
    
    decrementArrow.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent me) {
        DatePicker.access$010(DatePicker.this);
        if (month < 0) {
          month = 11;
          DatePicker.access$110(DatePicker.this);
        }
        monthYear.setText(DatePicker.this.getMonthYear(new GregorianCalendar(year, month, 1).getTime()));
        DatePicker.this.setDayCells();
        me.consume();
      }
    });
    Path inreamentArrow = new Path();
    inreamentArrow.setId("DatepickerArrow");
    inreamentArrow.getElements().addAll(new PathElement[] { new MoveTo(0.0D, 6.0D), new LineTo(0.0D, -6.0D), new LineTo(6.0D, 0.0D), new LineTo(0.0D, 6.0D) });
    
    inreamentArrow.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent me) {
        DatePicker.access$008(DatePicker.this);
        if (month > 11) {
          month = 0;
          DatePicker.access$108(DatePicker.this);
        }
        monthYear.setText(DatePicker.this.getMonthYear(new GregorianCalendar(year, month, 1).getTime()));
        DatePicker.this.setDayCells();
        me.consume();
      }
    });
    monthYearRow.getChildren().addAll(new Node[] { decrementArrow, monthYear, inreamentArrow });
    monthYearRow.setAlignment(Pos.CENTER);
    

    HBox firstLetterOfDayRow = new HBox();
    firstLetterOfDayRow.getStyleClass().add("datepicker-weekday-row");
    String[] weekDays = getFirstLettersOfDays();
    for (int i = 0; i < weekDays.length; i++) {
      Label cell = new Label(weekDays[i]);
      cell.setMinHeight(20.0D);
      cell.setMinWidth(25.0D);
      cell.setAlignment(Pos.CENTER);
      firstLetterOfDayRow.getChildren().add(cell);
    }
    pickerBox.getChildren().addAll(new Node[] { monthYearRow, firstLetterOfDayRow });
    


    dayCells = new DayCell[42];
    int index = 0;
    for (int i = 0; i < 6; i++) {
      HBox row = new HBox();
      for (int j = 0; j < 7; j++) {
        DayCell cell = createCell(0, 0, 0);
        row.getChildren().add(cell);
        cell.setId("DatepickerDayCell");
        dayCells[(index++)] = cell;
      }
      pickerBox.getChildren().add(row);
    }
    
    Button todayButton = new Button("Today");
    todayButton.setId("DatepickerTodayButton");
    todayButton.setOnAction(new EventHandler<ActionEvent>()
    {
      public void handle(ActionEvent ae)
      {
        dateProperty.setValue(new GregorianCalendar(0, 0, 0).getTime());
        dateProperty.setValue(new GregorianCalendar(today.get(1), today.get(2), today.get(5)).getTime());


      }
      



    });
    HBox todayButtonBox = new HBox();
    todayButtonBox.setId("DatepickerTodayButtonBox");
    todayButtonBox.setAlignment(Pos.CENTER);
    todayButtonBox.getChildren().add(todayButton);
    
    pickerBox.getChildren().add(todayButtonBox);
    
    getChildren().add(pickerBox);
    

    setDayCells();
  }
  


  public ObjectProperty<Date> dateProperty()
  {
    return dateProperty;
  }
  





  private String[] getFirstLettersOfDays()
  {
    String[] firstLettersOfDays = new String[7];
    int d = isSundayFirstDay ? 1 : 2;
    
    for (int i = 0; i < firstLettersOfDays.length; i++) {
      firstLettersOfDays[i] = new SimpleDateFormat("EEEE").format(new GregorianCalendar(0, 0, 3 + d + i).getTime()).substring(0, 1);
    }
    




    return firstLettersOfDays;
  }
  




  private String getMonthYear(Date date)
  {
    return new SimpleDateFormat("MMMM, YYYY").format(date);
  }
  





  private void setDayCells()
  {
    Calendar calendar = Calendar.getInstance();
    calendar.set(1, year);
    calendar.set(2, month);
    calendar.set(5, 1);
    
    int firstDayOfMonth = calendar.get(7) - 1;
    
    if (!isSundayFirstDay) {
      firstDayOfMonth += 6;
      if (firstDayOfMonth > 7)
        firstDayOfMonth -= 7;
    }
    int daysInMonth = calendar.getActualMaximum(5);
    calendar.set(2, month - 1);
    int daysInPreviousMonth = calendar.getActualMaximum(5);
    

    for (int i = 0; i < firstDayOfMonth; i++) {
      int m = month - 1;
      int y = year;
      if (m < 0) {
        m = 11;
        y--;
      }
      DayCell cell = dayCells[i];
      cell.setDate(daysInPreviousMonth - firstDayOfMonth + i + 1, m, y);
      cell.getStyleClass().removeAll(cellStyleList);
      cell.getStyleClass().add("datepicker-othermonth-cell");
    }
    

    int day = 1;
    for (int i = firstDayOfMonth; i < daysInMonth + firstDayOfMonth; i++) {
      DayCell cell = dayCells[i];
      cell.setDate(day++, month, year);
      calendar.set(1, year);
      calendar.set(2, month);
      calendar.set(5, cell.getDay());
      cell.getStyleClass().removeAll(cellStyleList);
      if (isToday(calendar)) {
        cell.getStyleClass().add("datepicker-today-cell");
      } else {
        cell.getStyleClass().add("datepicker-month-cell");
      }
    }
    
    day = 1;
    for (int i = firstDayOfMonth + daysInMonth; i < 42; i++) {
      int m = month + 1;
      int y = year;
      if (m > 11) {
        m = 0;
        y++;
      }
      DayCell cell = dayCells[i];
      cell.setDate(day++, m, y);
      cell.getStyleClass().removeAll(cellStyleList);
      cell.getStyleClass().add("datepicker-othermonth-cell");
    }
  }
  




  private boolean isToday(Calendar calendar)
  {
    return (today.get(1) == calendar.get(1)) && (today.get(2) == calendar.get(2)) && (today.get(5) == calendar.get(5));
  }
  








  private DayCell createCell(int dayNumber, int month, int year)
  {
    final DayCell cell = new DayCell(dayNumber, month, year);
    cell.setMinHeight(20.0D);
    cell.setMinWidth(25.0D);
    cell.setAlignment(Pos.CENTER);
    cell.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent me) {
        dateProperty.setValue(new GregorianCalendar(0, 0, 0).getTime());
        dateProperty.setValue(new GregorianCalendar(cell.getYear(), cell.getMonth(), cell.getDay()).getTime());
        



        me.consume();
      }
      
    });
    return cell;
  }
  

  private class DayCell
    extends Label
  {
    private int d;
    
    private int m;
    private int y;
    
    public DayCell(int day, int month, int year)
    {
      super();
    }
    
    public int getDay() {
      return d;
    }
    
    public int getMonth() {
      return m;
    }
    
    public int getYear() {
      return y;
    }
    
    public void setDate(int day, int month, int year) {
      d = day;
      setText(String.valueOf(day));
      m = month;
      y = year;
    }
  }
}
