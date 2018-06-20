package Collection;


public class Log_view
{
  private String index;
  
  private String data;
  
  private int idop;
  
  private int idproduct;
  
  private String name_product;
  
  private int group_product;
  
  private int ostatok;
  
  private double price;
  
  private int type;
  private String user;
  
  public String getIndex()
  {
    return index;
  }
  
  public void setIndex(String index) {
    this.index = index;
  }
  
  public String getData() {
    return data;
  }
  
  public void setData(String data) {
    this.data = data;
  }
  
  public int getIdop() {
    return idop;
  }
  
  public void setIdop(int idop) {
    this.idop = idop;
  }
  
  public int getIdproduct() {
    return idproduct;
  }
  
  public void setIdproduct(int idproduct) {
    this.idproduct = idproduct;
  }
  
  public String getName_product() {
    return name_product;
  }
  
  public void setName_product(String name_product) {
    this.name_product = name_product;
  }
  
  public int getGroup_product() {
    return group_product;
  }
  
  public void setGroup_product(int group_product) {
    this.group_product = group_product;
  }
  
  public int getOstatok() {
    return ostatok;
  }
  
  public void setOstatok(int ostatok) {
    this.ostatok = ostatok;
  }
  
  public double getPrice() {
    return price;
  }
  
  public void setPrice(double price) {
    this.price = price;
  }
  
  public int getType() {
    return type;
  }
  
  public void setType(int type) {
    this.type = type;
  }
  
  public String getUser() {
    return user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  

  public Log_view(int ix, String da, int ip, int it, String nt, int gp, int ok, double pe, int te, String ur)
  {
    index = String.valueOf(ix);
    data = da;
    idop = ip;
    idproduct = it;
    name_product = nt;
    group_product = gp;
    ostatok = ok;
    price = pe;
    type = te;
    user = ur;
  }
}
