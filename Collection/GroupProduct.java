package Collection;


public class GroupProduct
{
  private String name;
  
  private int n;
  
  private int id;
  
  public GroupProduct(int n1, int id1, String name1)
  {
    name = name1;
    n = n1;
    id = id1;
  }
  
  public void setN(int n1) {
    n = n1;
  }
  
  public void setName(String p) { name = p; }
  
  public void setId(int id1) {
    id = id1;
  }
  
  public String getName() {
    return name;
  }
  
  public int getN() {
    return n;
  }
  
  public int getId() { return id; }
}
