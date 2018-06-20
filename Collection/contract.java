package Collection;


public class contract
{
  private int id;
  
  private String name;
  
  private String snum;
  
  private String adress;
  
  private String year;
  
  private String pascout;
  
  private String phone;
  
  private String dstart;
  
  private String dend;
  private double total_amount;
  private double amount_paid;
  private double remaining_sum;
  private boolean status;
  private String user;
  
  public contract(int i, String fio, String snum, String adres, String ye, String doccout, String phon, String dst, String den, double ta, double ap, double rs, boolean st, String u)
  {
    id = i;
    name = fio;
    this.snum = snum;
    adress = adres;
    year = ye;
    pascout = doccout;
    
    phone = phon;
    
    dstart = dst;
    dend = den;
    total_amount = ta;
    amount_paid = ap;
    remaining_sum = rs;
    status = st;
    user = u;
  }
  
  public void setId(int i) {
    id = i;
  }
  
  public void setDstart(String d) { dstart = d; }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setTotal_amount(double ta) { total_amount = ta; }
  
  public void setAmount_paid(double ap) {
    amount_paid = ap;
  }
  
  public void setRemaining_sum(double rs) { remaining_sum = rs; }
  
  public void setUser(String us) {
    user = us;
  }
  
  public void setDend(String ds) {
    dend = ds;
  }
  
  public void setSnum(String sn) { snum = sn; }
  
  public void setYear(String ye) {
    year = ye;
  }
  
  public void setAdress(String res) { adress = res; }
  
  public void setStatus(boolean st) {
    status = st;
  }
  
  public void setPascout(String st) { pascout = st; }
  
  public void setPhone(String st) {
    phone = st;
  }
  
  public int getId() { return id; }
  
  public String getDstart() {
    return dstart;
  }
  
  public String getName() { return name; }
  
  public double getTotal_amount() {
    return total_amount;
  }
  
  public double getAmount_paid() { return amount_paid; }
  
  public double getRemaining_sum() {
    return remaining_sum;
  }
  
  public String getUser() { return user; }
  
  public String getYear() {
    return year;
  }
  
  public String getDend() { return dend; }
  
  public String getSnum() {
    return snum;
  }
  
  public boolean getStatus() { return status; }
  
  public String getAdress() {
    return adress;
  }
  
  public String getPascout() { return pascout; }
  
  public String getPhone() {
    return phone;
  }
}
