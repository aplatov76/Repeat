package Collection;


public class Repot
{
  private String id;
  
  private String name;
  
  private String size;
  
  private String price;
  private Double sum;
  
  public Repot()
  {
    id = "";
    name = "";
    size = "";
    price = "0.0";
    sum = Double.valueOf(0.0D);
  }
  
  public Repot(String name, String id, String size, String price, double sum) { this.id = id;
    this.name = name;
    this.size = size;
    this.price = price;
    this.sum = Double.valueOf(sum);
  }
  
  public void setId(String i)
  {
    id = i;
  }
  
  public void setName(String n) { name = n; }
  
  public void setSize(String s) {
    size = s;
  }
  
  public void setsum(double s) { sum = Double.valueOf(s); }
  
  public void setPrice(String p)
  {
    price = p;
  }
  
  public String getId() { return id; }
  
  public String getName() {
    return name;
  }
  
  public String getSize() { return size; }
  
  public String getPrice() {
    return price;
  }
  
  public double getSum() { return sum.doubleValue(); }
}
