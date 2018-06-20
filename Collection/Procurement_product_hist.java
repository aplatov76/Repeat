package Collection;


public class Procurement_product_hist
{
  private int index;
  
  private String date;
  
  private String name_product;
  
  private int id_product;
  
  private int size_first;
  
  private int size_second;
  
  private int size_total;
  
  private String user;
  
  private String status;
  

  public Procurement_product_hist(int i, String d, String n, int ip, int sf, int ss, int st, String u, boolean stt)
  {
    index = i;
    date = d;
    name_product = n;
    id_product = ip;
    size_first = sf;
    size_second = ss;
    size_total = st;
    user = u;
    status = (stt ? "no" : "yes");
  }
  
  public Procurement_product_hist(int i, String d, String n, int ip, int st, String u)
  {
    index = i;
    date = d;
    name_product = n;
    id_product = ip;
    size_first = 0;
    size_second = st;
    size_total = 0;
    user = u;
    status = "ok";
  }
  
  public void setIndex(int i)
  {
    index = i;
  }
  
  public void setId_product(int ip) {
    id_product = ip;
  }
  
  public void setSize_first(int sf) { size_first = sf; }
  
  public void setSize_second(int i) {
    size_second = i;
  }
  
  public void setSize_total(int i) { size_total = i; }
  
  public void setDate(String d)
  {
    date = d;
  }
  
  public void setName_product(String d) { name_product = d; }
  
  public void setUser(String d) {
    user = d;
  }
  
  public void setStatus(boolean stt) { status = (stt ? "yes" : "no"); }
  


  public int getIndex()
  {
    return index;
  }
  
  public int getId_product() {
    return id_product;
  }
  
  public int getSize_first() { return size_first; }
  
  public int getSize_second() {
    return size_second;
  }
  
  public int getSize_total() { return size_total; }
  
  public String getDate()
  {
    return date;
  }
  
  public String getName_product() { return name_product; }
  
  public String getUser() {
    return user;
  }
  
  public String getStatus() {
    return status;
  }
}
