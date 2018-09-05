package Collection;

public class Registration_return {

    private String data_return;
    private String data_prodag;
    private String name;
    private Integer id;
    private Integer size;
    private Double price;
    private Double sum;
    private String user;
    private int stock;

    public Registration_return() {
        data_return = null;
        data_prodag = null;
        name = "";
        id = Integer.valueOf(0);
        size = Integer.valueOf(0);
        price = Double.valueOf(0.0D);
        sum = Double.valueOf(0.0D);
        user = "";
        stock = 0;
    }

    public Registration_return(String date_return, String data_prod, String name, Integer id, Integer size, Integer stock, double price, double sum, String user) {
        this.data_return = date_return;
        this.data_prodag = data_prod;
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = Double.valueOf(price);
        this.sum = Double.valueOf(sum);
        this.user = user;
        this.stock = stock;
    }

    public void setData_return(String data_return) {
        this.data_return = data_return;
    }

    public void setData_prodag(String data_prodag) {
        this.data_prodag = data_prodag;
    }

    public String getData_return() {
        return data_return;
    }

    public String getData_prodag() {
        return data_prodag;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStock() {
        return stock;
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

    public void setsum(double s) {
        sum = Double.valueOf(s);
    }

    public void setPrice(double p) {
        price = Double.valueOf(p);
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

    public double getPrice() {
        return price.doubleValue();
    }

    public double getSum() {
        return sum.doubleValue();
    }

    public String getUser() {
        return user;
    }
}