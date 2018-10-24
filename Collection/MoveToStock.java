package Collection;


public class MoveToStock
{
  private int index;
  private String name;
  private Integer id;
  private Integer size;
  private String user;  
  private int stock;
  

  public MoveToStock()
  { 
    index = 0;
    name = "";
    id = 0;
    size = 0;
    user = "";
    stock = 0;
  }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
  
  public MoveToStock(int index, String name, Integer id, Integer size, Integer stock, String user) { 
    this.index = index;
    this.id = id;
    this.name = name;
    this.size = size;
    this.user = user;
    this.stock = stock;
  }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }

  
  public void setId(int i) { id = i; }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setSize(int s) { 
        size = s; 
  }
  
  public void setUser(String u) {
    user = u;
  }
  
  public int getId() {
    return id;
  }
  
  public String getName() { return name; }
  
  public int getSize() {
    return size;
  }  
  
  public String getUser() { return user; }
}
