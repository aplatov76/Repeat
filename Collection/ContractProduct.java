package Collection;



public class ContractProduct
{
  public int id;
  
  public int id_product;
  
  public String name_product;
  
  public double price;
  
  public int size;
  
  public double summa;
  

  public ContractProduct(int i, int ip, String name, double p, int s, double sum)
  {
    id = i;
    id_product = ip;
    name_product = name;
    price = p;
    size = s;
    summa = sum;
  }
  
  public void setId(int i) {
    id = i;
  }
  
  public void setId_Product(int ip) { id_product = ip; }
  
  public void setNameProduct(String n) {
    name_product = n;
  }
  
  public void setPrice(double p) { price = p; }
  
  public void setSize(int s) {
    size = s;
  }
  
  public void setSumma(double s) { summa = s; }
  
  public int getId()
  {
    return id;
  }
  
  public int getId_Product() { return id_product; }
  
  public String getName_Product() {
    return name_product;
  }
  
  public double getPrice() { return price; }
  
  public int getSize() {
    return size;
  }
  
  public double getSumma() { return summa; }
}
