package Collection;


public class AdminPane
{
  private Integer number;
  
  private Integer id;
  
  private String name;
  
  private String shortname;
  
  private Integer group;
  
  private Integer helf;
  
  private Integer size;
  
  private Double price;
  private int actual_status;
  
  public AdminPane()
  {
    number = Integer.valueOf(0);
    id = Integer.valueOf(0);
    name = "A";
    shortname = "B";
    group = Integer.valueOf(1);
    helf = Integer.valueOf(2);
    size = Integer.valueOf(3);
    price = Double.valueOf(4.0D);
    actual_status = 5;
  }
  
  public AdminPane(int num, int idp, String n, String shortname, int gr, int h, int s, double p, int as) {
    number = Integer.valueOf(num);
    id = Integer.valueOf(idp);
    name = n;
    this.shortname = shortname;
    group = Integer.valueOf(gr);
    helf = Integer.valueOf(h);
    size = Integer.valueOf(s);
    price = Double.valueOf(p);
    actual_status = as;
  }
  
  public void setNumber(int num) {
    number = Integer.valueOf(num);
  }
  
  public void setId(int idp) { id = Integer.valueOf(idp); }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setShort(String s) { shortname = s; }
  
  public void setGroup(int p)
  {
    group = Integer.valueOf(p);
  }
  
  public void setHelf(int s) { helf = Integer.valueOf(s); }
  
  public void setSize(int s) {
    size = Integer.valueOf(s);
  }
  
  public void setPrice(double p) { price = Double.valueOf(p); }
  
  public int getId()
  {
    return id.intValue();
  }
  
  public int getNumber() {
    return number.intValue();
  }
  
  public String getName() {
    return name;
  }
  
  public String getShortname() { return shortname; }
  
  public int getGroup()
  {
    return group.intValue();
  }
  
  public int getHelf()
  {
    return helf.intValue();
  }
  
  public int getSize() { return size.intValue(); }
  
  public double getPrice() {
    return price.doubleValue();
  }
  
  public void setActual_status(int actual_status) {
    this.actual_status = actual_status;
  }
  
  public int getActual_status() {
    return actual_status;
  }
}
