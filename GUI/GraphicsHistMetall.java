package GUI;

import Connect.ConnectDB;
import autofilltextbox.AutoFillTextBox;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphicsHistMetall
  extends Application
{
  private String a = "";
  private String b = "";
  
  public GraphicsHistMetall(String a, String b) { this.a = a;
    this.b = b;
    start(new Stage());
  }
  
  public final void start(Stage stage) {
    final ConnectDB db = new ConnectDB();
    GridPane grid = new GridPane();
    grid.setPadding(new Insets(5.0D, 5.0D, 5.0D, 5.0D));
    grid.setHgap(5.0D);
    grid.setVgap(5.0D);
    VBox checkp = new VBox();
    VBox namep = new VBox();
    checkp.setSpacing(5.0D);
    namep.setSpacing(5.0D);
    
    final ObservableList<String> datashow = FXCollections.observableArrayList();
    final ObservableList<Integer> sizeshow = FXCollections.observableArrayList();
    
    CategoryAxis xAxis = new CategoryAxis();
    
    xAxis.setTickLabelFill(Color.BLACK);
    
    NumberAxis yAxis = new NumberAxis();
    yAxis.setTickLabelFill(Color.BLACK);
    yAxis.setTickLabelGap(10.0D);
    yAxis.setSide(Side.LEFT);
    yAxis.setAutoRanging(false);
    yAxis.setUpperBound(2000.0D);
    yAxis.setMinorTickCount(3);
    

    final LineChart<String, Number> chart = new LineChart(xAxis, yAxis);
    
    chart.setLayoutX(50.0D);
    chart.setLayoutY(10.0D);
    chart.setCursor(Cursor.CROSSHAIR);
    


    chart.setMinWidth(1300.0D);
    chart.setMinHeight(630.0D);
    chart.setTitle("C " + a + " по " + b);
    chart.setTitleSide(Side.TOP);
    
    chart.setLegendSide(Side.BOTTOM);
    chart.setAlternativeColumnFillVisible(true);
    chart.setAlternativeRowFillVisible(false);
    chart.setHorizontalGridLinesVisible(true);
    chart.setVerticalGridLinesVisible(true);
    

    final ObservableList<LineChart.Series> series = FXCollections.observableArrayList();
    
    ObservableList data = FXCollections.observableArrayList();
    
    db.loadMetallName(data);
    final AutoFillTextBox box = new AutoFillTextBox(data);
    box.setListLimit(100);
    
    box.setMinWidth(700.0D);
    box.setId("box");
    Button ok = new Button("Добавить");
    ok.setId("dark-blue");
    ok.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        GraphicsHistMetall.this.addChart(box.getText(), series, datashow, sizeshow, db, chart);
      }
    });
    grid.add(chart, 0, 1);
    HBox checkh = new HBox();
    checkh.setPadding(new Insets(10.0D, 5.0D, 5.0D, 50.0D));
    checkh.setSpacing(5.0D);
    checkh.setAlignment(Pos.CENTER);
    checkh.getChildren().addAll(new Node[] { box, ok });
    
    grid.add(checkh, 0, 0);
    
    Scene scene = new Scene(grid);
    grid.setId("bp");
    scene.getStylesheets().add(getClass().getResource("/css/login.css").toExternalForm());
    
    stage.setScene(scene);
    Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    stage.setWidth(sSize.width);
    stage.setHeight(sSize.height);
    stage.show();
  }
  


  public static void main(String[] args) { Application.launch(args); }
  
  private final void addChart(String name, ObservableList<LineChart.Series> series, ObservableList<String> datashow, ObservableList<Integer> sizeshow, ConnectDB db, LineChart<String, Number> chart) {
    int id = db.getMetallID(name);
    
    if (id != -1) {
      series.add(new LineChart.Series());
      int size = series.size() - 1;
      
      ((LineChart.Series)series.get(size)).setName(name);
      db.getGraphicsSizeMetall(a, b, id, datashow, sizeshow);
      int n = datashow.size();
      
      for (int i = 0; i < n; i++) { ((LineChart.Series)series.get(size)).getData().add(new LineChart.Data(datashow.get(i), sizeshow.get(i)));
      }
      chart.getData().addAll(new LineChart.Series[] { (LineChart.Series)series.get(size) });
    }
  }
}
