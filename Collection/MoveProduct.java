package Collection;

public class MoveProduct{

    private String data_return;
    private String name;
    private Integer id;
    private Integer size;
    private String user;
    private int stock;

    public MoveProduct() {
        data_return = null;
        name = "";
        id = Integer.valueOf(0);
        size = Integer.valueOf(0);
        user = "";
        stock = 0;
    }

    public MoveProduct(String date_return, String name, Integer id, Integer size,int stock, String user) {
        this.data_return = date_return;
        this.id = id;
        this.name = name;
        this.size = size;
        this.user = user;
        this.stock = stock;
    }

    public void setData_return(String data_return) {
        this.data_return = data_return;
    }

    public String getData_return() {
        return data_return;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getStock() {
        return  (stock == 0) ? "В" : "И";//stock;
    }

    public void setId(int i) {
        id = Integer.valueOf(i);
    }

    public void setName(String n) {
        name = n;
    }

    public void setSize(int s) {
        size = Integer.valueOf(s);
    }

    public void setUser(String u) {
        user = u;
    }

    public int getId() {
        return id.intValue();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size.intValue();
    }

    public String getUser() {
        return user;
    }
}