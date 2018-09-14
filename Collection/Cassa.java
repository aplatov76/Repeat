package Collection;

import javafx.scene.control.TextField;









public class Cassa
{
  public static double start = 0.0D;
  public static double cassa = 0.0D;
  public static double rasxod = 0.0D;
  
  public static TextField cassawind;
  public static TextField prixwind;
  public static TextField rasxwind;
  
  public Cassa()
  {
    cassawind = new TextField("Касса : " + cassa);
    prixwind = new TextField("Приход : " + cassa);
    rasxwind = new TextField("Расход : " + rasxod);
  }
  


  public TextField getCassa(int i)
  {
    cassawind.setText("Касса : " + cassa);
    prixwind.setText("Приход : " + (cassa - start + rasxod));
    return i == 0 ? cassawind : prixwind;
  }
  
  public TextField getRasxod() {
    rasxwind.setText("Расход: " + rasxod);
    return rasxwind;
  }
  
  public static void setCassa() { 
      if ((cassawind != null) && (prixwind != null) && (rasxwind != null)) {
            cassawind.setText("Касса : " + cassa);
            prixwind.setText("Приход : " + (cassa - start + rasxod));
            rasxwind.setText("Расход: " + rasxod);
    }
  }
}
