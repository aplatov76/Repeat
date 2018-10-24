package Collection;

public class AdminPane {

    private Integer number;
    private Integer id;
    private String name;
    private String shortname;
    private Integer group;
    private Integer helf;
    private Integer size;
    private Double price;
    private int actual_status;
    private int stock;
    private int stock_size_0;
    private int stock_size_1;
    private int min_remainder;
    private String articul;

    public AdminPane() {
        number = Integer.valueOf(0);
        id = Integer.valueOf(0);
        name = "A";
        shortname = "B";
        group = Integer.valueOf(1);
        helf = Integer.valueOf(2);
        size = Integer.valueOf(3);
        price = Double.valueOf(4.0D);
        actual_status = 5;
        stock = 0;
        stock_size_0 = 0;
        stock_size_1 = 0;
        min_remainder = 0;
        articul = "";
    }

    public AdminPane(int num, int idp, String n, String shortname, int gr, int h, int s, double p, int as, int stock, int stock_size_0,int stock_size_1,int min_remainder,String articul) {
        number = Integer.valueOf(num);
        id = Integer.valueOf(idp);
        name = n;
        this.shortname = shortname;
        group = Integer.valueOf(gr);
        helf = Integer.valueOf(h);
        size = Integer.valueOf(s);
        price = Double.valueOf(p);
        actual_status = as;
        this.stock = stock;
        this.stock_size_0 = stock_size_0;
        this.stock_size_1 = stock_size_1;
        this.min_remainder = min_remainder;
        this.articul = articul;
    }

    public void setArticul(String articul) {
        this.articul = articul;
    }

    public String getArticul() {
        return articul;
    }

    public void setMin_remainder(int min_remainder) {
        this.min_remainder = min_remainder;
    }

    public int getMin_remainder() {
        return min_remainder;
    }

    public void setNumber(int num) {
        number = Integer.valueOf(num);
    }

    public void setId(int idp) {
        id = Integer.valueOf(idp);
    }

    public void setName(String n) {
        name = n;
    }

    public void setShort(String s) {
        shortname = s;
    }

    public void setGroup(int p) {
        group = Integer.valueOf(p);
    }

    public void setHelf(int s) {
        helf = Integer.valueOf(s);
    }

    public void setSize(int s) {
        size = Integer.valueOf(s);
    }

    public void setStock(int s) {
        stock = s;
    }
    
    public void setStock_size_0(int s) {
        stock_size_0 = s;
    }

    public void setStock_size_1(int s) {
        stock_size_1 = s;
    }    

    public void setPrice(double p) {
        price = Double.valueOf(p);
    }

    public int getId() {
        return id.intValue();
    }

    public int getNumber() {
        return number.intValue();
    }

    public String getName() {
        return name;
    }

    public String getShortname() {
        return shortname;
    }

    public int getStock() {
        return stock;
    }
    
    public int getStock_size_0() {
        return stock_size_0;
    }
    public int getStock_size_1() {
        return stock_size_1;
    }
    public int getGroup() {
        return group.intValue();
    }

    public int getHelf() {
        return helf.intValue();
    }

    public int getSize() {
        return size.intValue();
    }

    public double getPrice() {
        return price.doubleValue();
    }

    public void setActual_status(int actual_status) {
        this.actual_status = actual_status;
    }

    public int getActual_status() {
        return actual_status;
    }
}
