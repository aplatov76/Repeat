package Collection;


public class Prices
{
  private int code;
  private int id;  
  private String name;
  private int group;
  private Integer size;
  private Double price;
  private int actual_status;
  
  public Prices()
  {
    code = 0;
    id = 0;
    name = "";
    size = 0;
    group = 0;
    price = 0.0D;
    actual_status = 0;
  }
  
  public Prices(int id, int code, String name, int group, int size, double price, int as) {
    this.code = code;
    this.id = id;
    this.name = name;
    this.size = size;
    this.group = group;
    this.price = price;
    actual_status = as;
  }
  
  public void setId(int i)
  {
    id = i;
  }
  
  public void setCode(int i) {
    code = i;
  }
  
  public void setName(String n) { name = n; }
  
  public void setSize(int s) {
    size = s;
  }
  
  public void setGroup(int s) { group = s; }
  
  public void setPrice(double p)
  {
    price = p;
  }
  
  public int getId() { return id; }
  
  public String getName() {
    return name;
  }
  
  public int getSize() { return size; }
  
  public double getPrice() {
    return price;
  }
  
  public int getGroup() { return group; }
  
  public int getCode() {
    return code;
  }
  
  public void setActual_status(int actual_status) { this.actual_status = actual_status; }
  
  public int getActual_status()
  {
    return actual_status;
  }
}
