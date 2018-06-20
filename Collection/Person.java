package Collection;


public class Person
{
  private String user;
  
  private int id_user;
  
  private int id_rules;
  
  private int use_key;
  
  private boolean registr_product;
  
  private boolean look_price;
  
  private boolean add_product;
  
  private boolean clear_product;
  private boolean correct_product;
  private boolean look_history;
  private boolean look_journal;
  private boolean correct_user;
  private boolean use_group;
  private boolean full_otchet;
  private boolean full_registration;
  private boolean installment_paid;
  private boolean customer_order;
  private boolean order_product_add;
  private boolean order_product_cor;
  private boolean order_product_del;
  private boolean zacup_product_add;
  private boolean zacup_product_cor;
  private boolean zacup_product_del;
  private boolean zacup_product_ok;
  private boolean look_hist_metall;
  
  public Person()
  {
    user = "";
    id_user = 0;
    id_rules = 0;
    use_key = 0;
    registr_product = false;
    look_price = false;
    add_product = false;
    clear_product = false;
    correct_product = false;
    look_history = false;
    look_journal = false;
    correct_user = false;
    use_group = false;
    full_otchet = false;
    full_registration = false;
    installment_paid = false;
    customer_order = false;
    order_product_add = false;
    order_product_cor = false;
    order_product_del = false;
    zacup_product_add = false;
    zacup_product_cor = false;
    zacup_product_del = false;
    zacup_product_ok = false;
    look_hist_metall = false;
  }
  

  public void setRules(boolean r, int i)
  {
    switch (i) {
    case 0: 
      registr_product = r;
      break;
    
    case 1: 
      look_price = r;
      break;
    
    case 2: 
      add_product = r;
      break;
    
    case 3: 
      clear_product = r;
      break;
    
    case 4: 
      correct_product = r;
      break;
    
    case 5: 
      look_history = r;
      break;
    
    case 6: 
      look_journal = r;
      break;
    

    case 7: 
      correct_user = r;
      break;
    
    case 8: 
      use_group = r;
      break;
    
    case 9: 
      full_otchet = r;
      break;
    
    case 10: 
      full_registration = r;
      break;
    
    case 11: 
      installment_paid = r;
      break;
    
    case 12: 
      customer_order = r;
      break;
    

    case 13: 
      order_product_add = r;
      break;
    
    case 14: 
      order_product_cor = r;
      break;
    
    case 15: 
      order_product_del = r;
      break;
    
    case 16: 
      zacup_product_add = r;
      break;
    
    case 17: 
      zacup_product_cor = r;
      break;
    
    case 18: 
      zacup_product_del = r;
      break;
    
    case 19: 
      zacup_product_ok = r;
      break;
    

    case 20: 
      look_hist_metall = r;
    }
    
  }
  

  public boolean getRules(int i)
  {
    switch (i) {
    case 0:  return registr_product;
    case 1:  return look_price;
    case 2: 
      return add_product;
    case 3:  return clear_product;
    case 4: 
      return correct_product;
    case 5:  return look_history;
    case 6:  return look_journal;
    case 7:  return correct_user;
    case 8:  return use_group;
    case 9:  return full_otchet;
    case 10:  return full_registration;
    case 11:  return installment_paid;
    case 12:  return customer_order;
    case 13:  return order_product_add;
    case 14:  return order_product_cor;
    case 15:  return order_product_del;
    case 16:  return zacup_product_add;
    case 17:  return zacup_product_cor;
    case 18:  return zacup_product_del;
    case 19:  return zacup_product_ok;
    case 20:  return look_hist_metall;
    }
    return false;
  }
  
  public void setName(String name) { user = name; }
  
  public String getName() {
    return user;
  }
  
  public void setId_user(int id_user) {
    this.id_user = id_user;
  }
  
  public int getId_user() {
    return id_user;
  }
  
  public void setId_rules(int id_rules) {
    this.id_rules = id_rules;
  }
  
  public int getId_rules() {
    return id_rules;
  }
  
  public void setUse_key(int use_key) { this.use_key = use_key; }
  
  public int getUse_key()
  {
    return use_key;
  }
}
