package Popup;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PFilterWeb
{
  private Popup main;    
  
  public Popup getMain()
  {
    return main;
  } 

  public PFilterWeb()
  {
    main = new Popup();
    VBox vboxfiltr = createFiltr(main);
    
    main.getContent().add(vboxfiltr);
  }
  
  private VBox createFiltr(final Popup popupfiltr) {
    VBox menu = new VBox();
    
    menu.setSpacing(8.0D);
    menu.setId("pfilter");
    menu.setPadding(new Insets(10.0D, 10.0D, 10.0D, 10.0D));   
    
    Button check = new Button("Печать чеков");
    Button ttn = new Button("Товарные накладные");
    Button repeat = new Button("Продажи");   
    Button run = new Button("Доставки"); 
    Button exit = new Button("Закрыть");   
    
    check.setId("dark-blue");
    ttn.setId("dark-blue");
    repeat.setId("dark-blue");
    exit.setId("dark-blue");
    run.setId("dark-blue");
    
    HBox button = new HBox();
    button.setSpacing(8.0D);
    button.getChildren().addAll(check, ttn, repeat, run, exit);
    
    check.setMinSize(160.0D, 40.0D);
    ttn.setMinSize(160.0D, 40.0D);
    repeat.setMinSize(160.0D, 40.0D);
    exit.setMinSize(160.0D, 40.0D);
    run.setMinSize(160.0D, 40.0D);
    
    
    final Controller c = new Controller();
    
    check.setOnMouseClicked(new EventHandler<MouseEvent>()
    {

      public void handle(MouseEvent event)
      {
        try {
                
                c.openBrowser(new ActionEvent(),"http://10.8.0.1/java/pp/");
            } catch (Exception ex) {
                //Logger.getLogger();
            }
      }     

    });
    ttn.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {        
        try {
                c.openBrowser(new ActionEvent(),"http://10.8.0.1/java/ttn/");
            } catch (Exception ex) {
                //Logger.getLogger();
            }
      }
      
    });
    
    repeat.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {       
        try {
            
            c.openBrowser(new ActionEvent(),"http://10.8.0.1/java/repeat/stock/");
            } catch (Exception ex) {
                //Logger.getLogger();
            }
      }
      
    });
    
    run.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {       
        try {
            c.openBrowser(new ActionEvent(),"http://10.8.0.1/java/run/demo/_index.php");
            } catch (Exception ex) {
                //Logger.getLogger();
            }
      }
      
    });
    
    exit.setOnMouseClicked(new EventHandler<MouseEvent>()
    {
      public void handle(MouseEvent event)
      {       
        popupfiltr.hide();
      }
      
    });
    
    menu.getChildren().addAll(check, ttn, repeat, run, exit);
    return menu;
  }
}

class Controller extends Application {

    public void openBrowser(ActionEvent actionEvent, String uri) throws Exception {

        getHostServices().showDocument(uri);
        //getHostServices().getWebContext().setSlot(0, "");
    }
    public void openBrowser(ActionEvent actionEvent, String uri, int id_check) throws Exception {

        getHostServices().showDocument(uri);
        getHostServices().getWebContext().setSlot(0, id_check);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }
}