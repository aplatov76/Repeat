package Collection;



public class ListCustProduct
{
  int id;
  

  int id_product;
  
  String name;
  
  int group;
  
  double price;
  

  public ListCustProduct(int i, int ip, String n, int g, double p)
  {
    id = i;
    id_product = ip;
    name = n;
    group = g;
    price = p;
  }
  
  public void setId(int i)
  {
    id = i;
  }
  
  public void setId_product(int ip) {
    id_product = ip;
  }
  
  public void setName(String n) { name = n; }
  
  public void setGroup(int g) {
    group = g;
  }
  
  public void setPrice(double p) { price = p; }
  
  public int getId() {
    return id;
  }
  
  public int getId_product() { return id_product; }
  
  public int getGroup() {
    return group;
  }
  
  public String getName() { return name; }
  
  public double getPrice() {
    return price;
  }
}
