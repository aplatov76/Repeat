package Collection;


public class Prices
{
  private Integer code;
  
  private Integer id;
  
  private String name;
  
  private int group;
  
  private Integer size;
  
  private Double price;
  private int actual_status;
  
  public Prices()
  {
    code = Integer.valueOf(0);
    id = Integer.valueOf(0);
    name = "";
    size = Integer.valueOf(0);
    group = 0;
    price = Double.valueOf(0.0D);
    actual_status = 0;
  }
  
  public Prices(int id, int co, String name, int group, int size, double price, int as) {
    code = Integer.valueOf(co);
    this.id = Integer.valueOf(id);
    this.name = name;
    this.size = Integer.valueOf(size);
    this.group = group;
    this.price = Double.valueOf(price);
    actual_status = as;
  }
  
  public void setId(int i)
  {
    id = Integer.valueOf(i);
  }
  
  public void setCode(int i) {
    code = Integer.valueOf(i);
  }
  
  public void setName(String n) { name = n; }
  
  public void setSize(int s) {
    size = Integer.valueOf(s);
  }
  
  public void setGroup(int s) { group = s; }
  
  public void setPrice(double p)
  {
    price = Double.valueOf(p);
  }
  
  public int getId() { return id.intValue(); }
  
  public String getName() {
    return name;
  }
  
  public int getSize() { return size.intValue(); }
  
  public double getPrice() {
    return price.doubleValue();
  }
  
  public int getGroup() { return group; }
  
  public int getCode() {
    return code.intValue();
  }
  
  public void setActual_status(int actual_status) { this.actual_status = actual_status; }
  
  public int getActual_status()
  {
    return actual_status;
  }
}
