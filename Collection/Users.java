package Collection;



public class Users
{
  private String name;
  

  private String pass;
  
  private String rule;
  
  private int id_user;
  

  public Users(String name, String pass, String rule, int iduser)
  {
    this.name = name;
    this.pass = pass;
    this.rule = rule;
    id_user = iduser;
  }
  
  public void setId_user(int id_user) {
    this.id_user = id_user;
  }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setPass(String p) { pass = p; }
  
  public void setRule(String r) {
    rule = r;
  }
  
  public String getName() {
    return name;
  }
  
  public String getPass() {
    return pass;
  }
  
  public String getRule() { return rule; }
  
  public int getIdUser() {
    return id_user;
  }
}
