package Collection;



public class MetallOtchet
{
  private String index;
  

  private String id;
  

  private String name;
  
  private String price;
  
  private double summa;
  
  private String size;
  

  public MetallOtchet(int in, int i, String n, double p, double su, int si)
  {
    index = String.valueOf(in);
    id = String.valueOf(i);
    name = n;
    price = String.valueOf(p);
    summa = su;
    size = String.valueOf(si);
  }
  
  public MetallOtchet(double sum) {
    index = "";
    id = "";
    name = "";
    price = "Итого: ";
    summa = sum;
    size = "";
  }
  
  public void setId(int i) {
    id = String.valueOf(i);
  }
  
  public void setIndex(int in) { index = String.valueOf(in); }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setPrice(double p) { price = String.valueOf(p); }
  
  public void setSumma(double s) {
    summa = s;
  }
  
  public void setSize(int s) { size = String.valueOf(s); }
  
  public String getSize() {
    return size;
  }
  
  public String getId() { return id; }
  
  public String getIndex() {
    return index;
  }
  
  public String getPrice() {
    return price;
  }
  
  public double getSumma() { return summa; }
  
  public String getName() {
    return name;
  }
}
