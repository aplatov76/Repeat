package Collection;



public class MetallList
{
  private int index;
  

  private int id;
  
  private String name;
  
  private double price;
  
  private double summa;
  
  private int size;
  

  public MetallList(int in, int i, String n, double p, double su, int si)
  {
    index = in;
    id = i;
    name = n;
    price = p;
    summa = su;
    size = si;
  }
  
  public void setId(int i) {
    id = i;
  }
  
  public void setIndex(int in) { index = in; }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setPrice(double p) { price = p; }
  
  public void setSumma(double s) {
    summa = s;
  }
  
  public void setSize(int s) { size = s; }
  
  public int getSize() {
    return size;
  }
  
  public int getId() { return id; }
  
  public int getIndex() {
    return index;
  }
  
  public double getPrice() {
    return price;
  }
  
  public double getSumma() { return summa; }
  
  public String getName() {
    return name;
  }
}
