package Collection;


public class Registration
{
  private String data;
  
  private String name;
  
  private Integer id;
  
  private Integer size;
  
  private Double price;
  
  private Double sum;
  
  private String user;
  

  public Registration()
  {
    data = null;
    
    name = "";
    id = Integer.valueOf(0);
    size = Integer.valueOf(0);
    price = Double.valueOf(0.0D);
    sum = Double.valueOf(0.0D);
    user = "";
  }
  
  public Registration(String date, String name, Integer id, Integer size, double price, double sum, String user) { data = date;
    this.id = id;
    this.name = name;
    this.size = size;
    this.price = Double.valueOf(price);
    this.sum = Double.valueOf(sum);
    this.user = user;
  }
  
  public void setData(String d) {
    data = d;
  }
  
  public void setId(int i) { id = Integer.valueOf(i); }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setSize(int s) { size = Integer.valueOf(s); }
  
  public void setsum(double s) {
    sum = Double.valueOf(s);
  }
  
  public void setPrice(double p) { price = Double.valueOf(p); }
  
  public void setUser(String u) {
    user = u;
  }
  
  public String getData() { return data; }
  
  public int getId() {
    return id.intValue();
  }
  
  public String getName() { return name; }
  
  public int getSize() {
    return size.intValue();
  }
  
  public double getPrice() { return price.doubleValue(); }
  
  public double getSum() {
    return sum.doubleValue();
  }
  
  public String getUser() { return user; }
}
