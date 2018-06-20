package Collection;

import javafx.beans.property.SimpleStringProperty;


public class GateReg
{
  private SimpleStringProperty n;
  private final SimpleStringProperty name;
  private final SimpleStringProperty id;
  private final SimpleStringProperty size;
  private final SimpleStringProperty price;
  private double sum;
  
  public GateReg()
  {
    n = new SimpleStringProperty("");
    name = new SimpleStringProperty("");
    id = new SimpleStringProperty();
    size = new SimpleStringProperty();
    price = new SimpleStringProperty("Итого: ");
    sum = 0.0;
  }
  
  public GateReg(int pid, String name, String id, String size, String price, double sum) { n = new SimpleStringProperty("" + pid);
    this.name = new SimpleStringProperty(name);
    this.id = new SimpleStringProperty(id);
    this.size = new SimpleStringProperty(size);
    this.price = new SimpleStringProperty(price);
    this.sum = sum;
  }
  
  public void setName(String nam) { name.set(nam); }
  
  public void setN(String nid) {
    n.set(nid);
  }
  
  public String getName() { return name.get(); }
  
  public void setId(String i)
  {
    id.set(i);
  }
  
  public String getId() { return id.get(); }
  
  public void setSize(String i) {
    size.set(i);
  }
  
  public String getSize() { return size.get(); }
  
  public void setPrice(String i) {
    price.set(i);
  }
  
  public String getPrice() { return price.get(); }
  
  public void setSum(double i) {
    sum = i;
  }
  
  public double getSum() { return sum; }
  
  public String getN() {
    return n.get();
  }
}
