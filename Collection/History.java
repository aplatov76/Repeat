package Collection;

public class History {

    private String data;

    private Integer idop;

    private String name;

    private Integer idpr;

    private Integer size;

    private Integer balance;
    private Integer stock;

    private Double price;

    private Double sum;
    private String user;

    public History() {
        data = null;
        idop = Integer.valueOf(0);
        name = "";
        balance = Integer.valueOf(0);
        idpr = Integer.valueOf(0);
        size = Integer.valueOf(0);
        price = Double.valueOf(0.0D);
        sum = Double.valueOf(0.0D);
        stock = -1;
        user = "";
    }

    public History(int idop, String date, String name, int bl, int id, int size, double price, double sum, String user, int stock) {
        this.idop = Integer.valueOf(idop);
        data = date;
        idpr = Integer.valueOf(id);
        this.name = name;
        balance = Integer.valueOf(bl);
        this.size = Integer.valueOf(size);
        this.price = Double.valueOf(price);
        this.sum = Double.valueOf(sum);
        this.user = user;
        this.stock = stock;
    }

    public void setIDop(int d) {
        idop = Integer.valueOf(d);
    }

    public void setData(String d) {
        data = d;
    }

    public void setIdpr(int i) {
        idpr = Integer.valueOf(i);
    }

    public void setName(String n) {
        name = n;
    }

    public void setSize(int s) {
        size = Integer.valueOf(s);
    }

    public void setBalance(int bl) {
        balance = Integer.valueOf(bl);
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

    public String getData() {
        return data;
    }

    public int getIdpr() {
        return idpr.intValue();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size.intValue();
    }

    public int getIdop() {
        return idop.intValue();
    }

    public int getBalance() {
        return balance.intValue();
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

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }
}
