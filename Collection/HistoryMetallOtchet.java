package Collection;


public class HistoryMetallOtchet
{
  private int index;
  
  private String data;
  
  private String name;
  private int code;
  private int size;
  private double remain;
  private double sum;
  private String user;
  
  public int getIndex()
  {
    return index;
  }
  
  public String getData() {
    return data;
  }
  
  public String getName() {
    return name;
  }
  
  public int getCode() {
    return code;
  }
  
  public int getSize() {
    return size;
  }
  
  public double getRemain() {
    return remain;
  }
  
  public double getSum() {
    return sum;
  }
  
  public String getUser() {
    return user;
  }
  
  public void setIndex(int index)
  {
    this.index = index;
  }
  
  public void setData(String data) {
    this.data = data;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public void setCode(int code) {
    this.code = code;
  }
  
  public void setSize(int size) {
    this.size = size;
  }
  
  public void setRemain(double remain) {
    this.remain = remain;
  }
  
  public void setSum(double sum) {
    this.sum = sum;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public HistoryMetallOtchet(int index, String data, String name, int code, int size, double remain, double sum, String user)
  {
    this.index = index;
    this.data = data;
    this.name = name;
    this.code = code;
    this.size = size;
    this.remain = remain;
    this.sum = sum;
    this.user = user;
  }
}
