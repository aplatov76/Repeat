package Collection;

import javafx.scene.control.CheckBox;











public class Rules
{
  private String name;
  private CheckBox registr;
  private CheckBox look_price;
  private CheckBox add_product;
  private CheckBox clear_product;
  private CheckBox correct_product;
  private CheckBox look_history;
  private CheckBox look_journal;
  private CheckBox correct_user;
  private CheckBox use_group;
  private CheckBox full_otchet;
  private CheckBox full_registration;
  private CheckBox installment_paid;
  private CheckBox customer_order;
  private CheckBox order_product_add;
  private CheckBox order_product_cor;
  private CheckBox order_product_del;
  private CheckBox zacup_product_add;
  private CheckBox zacup_product_cor;
  private CheckBox zacup_product_del;
  private CheckBox zacup_product_ok;
  private CheckBox look_hist_metall;
  
  public Rules(String n, boolean r, boolean lp, boolean ap, boolean clp, boolean cp, boolean lh, boolean lj, boolean cu, boolean ug, boolean fo, boolean fr, boolean ip, boolean co, boolean cpa, boolean cpc, boolean cpd, boolean zpa, boolean zpc, boolean zpd, boolean zpo, boolean lhm)
  {
    name = n;
    registr = new CheckBox();
    registr.setSelected(r);
    look_price = new CheckBox();
    look_price.setSelected(lp);
    
    add_product = new CheckBox();
    add_product.setSelected(ap);
    
    clear_product = new CheckBox();
    clear_product.setSelected(clp);
    
    correct_product = new CheckBox();
    correct_product.setSelected(cp);
    
    look_history = new CheckBox();
    look_history.setSelected(lh);
    
    look_journal = new CheckBox();
    look_journal.setSelected(lj);
    
    correct_user = new CheckBox();
    correct_user.setSelected(cu);
    use_group = new CheckBox();
    use_group.setSelected(ug);
    full_otchet = new CheckBox();
    full_otchet.setSelected(fo);
    full_registration = new CheckBox();
    full_registration.setSelected(fr);
    installment_paid = new CheckBox();
    installment_paid.setSelected(ip);
    customer_order = new CheckBox();
    customer_order.setSelected(co);
    

    order_product_add = new CheckBox();
    order_product_add.setSelected(cpa);
    order_product_cor = new CheckBox();
    order_product_cor.setSelected(cpc);
    order_product_del = new CheckBox();
    order_product_del.setSelected(cpd);
    zacup_product_add = new CheckBox();
    zacup_product_add.setSelected(zpa);
    zacup_product_cor = new CheckBox();
    zacup_product_cor.setSelected(zpc);
    zacup_product_del = new CheckBox();
    zacup_product_del.setSelected(zpd);
    zacup_product_ok = new CheckBox();
    zacup_product_ok.setSelected(zpo);
    look_hist_metall = new CheckBox();
    look_hist_metall.setSelected(lhm);
  }
  
  public void setName(String n) {
    name = n;
  }
  
  public void setRegistr(boolean r) { registr.setSelected(r); }
  
  public void setLook_price(boolean lp) {
    look_price.setSelected(lp);
  }
  
  public void setAdd_product(boolean ap) { add_product.setSelected(ap); }
  
  public void setClear_product(boolean clp) {
    clear_product.setSelected(clp);
  }
  
  public void setCorrect_product(boolean cp) { correct_product.setSelected(cp); }
  
  public void setLook_history(boolean lk) {
    look_history.setSelected(lk);
  }
  
  public void setLook_journal(boolean lj) { look_journal.setSelected(lj); }
  
  public void setCorrect_user(boolean cu) {
    correct_user.setSelected(cu);
  }
  
  public void setUse_group(boolean ug) { use_group.setSelected(ug); }
  
  public void setFull_otchet(boolean fo) {
    full_otchet.setSelected(fo);
  }
  
  public void setFull_registration(boolean fr) { full_registration.setSelected(fr); }
  
  public void setInstallment_paid(boolean ip)
  {
    installment_paid.setSelected(ip);
  }
  
  public void setCustomer_order(boolean co) {
    customer_order.setSelected(co);
  }
  
  public void setOrder_product_add(boolean opd) {
    order_product_add.setSelected(opd);
  }
  
  public void setOrder_product_cor(boolean opd) { order_product_cor.setSelected(opd); }
  
  public void setOrder_product_del(boolean opd)
  {
    order_product_del.setSelected(opd);
  }
  
  public void setZacup_product_add(boolean opd) {
    zacup_product_add.setSelected(opd);
  }
  
  public void setZacup_product_cor(boolean opd) { zacup_product_cor.setSelected(opd); }
  
  public void setZacup_product_del(boolean opd) {
    zacup_product_del.setSelected(opd);
  }
  
  public void setZacup_product_ok(boolean opd) { zacup_product_ok.setSelected(opd); }
  
  public void setLook_hist_metall(boolean lhm) {
    look_hist_metall.setSelected(lhm);
  }
  
  public String getName() {
    return name;
  }
  
  public CheckBox getRegistr() { return registr; }
  
  public CheckBox getLook_price()
  {
    return look_price;
  }
  
  public CheckBox getAdd_product() {
    return add_product;
  }
  
  public CheckBox getClear_product() {
    return clear_product;
  }
  
  public CheckBox getCorrect_product() {
    return correct_product;
  }
  
  public CheckBox getLook_history() {
    return look_history;
  }
  
  public CheckBox getLook_journal()
  {
    return look_journal;
  }
  
  public CheckBox getCorrect_user() {
    return correct_user;
  }
  
  public CheckBox getUse_group() { return use_group; }
  
  public CheckBox getFull_otchet() {
    return full_otchet;
  }
  
  public CheckBox getFull_registration() { return full_registration; }
  
  public CheckBox getInstallment_paid() {
    return installment_paid;
  }
  
  public CheckBox getCustomer_order() {
    return customer_order;
  }
  
  public CheckBox getOrder_product_add() {
    return order_product_add;
  }
  
  public CheckBox getOrder_product_cor() { return order_product_cor; }
  
  public CheckBox getOrder_product_del() {
    return order_product_del;
  }
  
  public CheckBox getZacup_product_add() { return zacup_product_add; }
  
  public CheckBox getZacup_product_cor() {
    return zacup_product_cor;
  }
  
  public CheckBox getZacup_product_del() { return zacup_product_del; }
  
  public CheckBox getZacup_product_ok() {
    return zacup_product_ok;
  }
  
  public CheckBox getLook_hist_metall() { return look_hist_metall; }
}
