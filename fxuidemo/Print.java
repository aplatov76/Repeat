package fxuidemo;

import Collection.Registration;
import Collection.Repot;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTable.PrintMode;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;




public class Print
  extends Frame
{
  public Print(ObservableList<Registration> prod)
  {
    DefaultTableModel p = new DefaultTableModel() {
      public boolean isCellEditable(int a, int b) {
        return false;
      }
      
    };
    p.setColumnIdentifiers(new String[] { "Время", "User", "Код", "Наименование", "Цена", "Кол-во", "Сумма" });
    int n = prod.size();
    double s = 0.0D;
    p.addRow(new String[] { "Время", "User", "Код", "Наименование", "Цена", "Кол-во", "Сумма" });
    for (int i = 0; i < n; i++) {
      s += ((Registration)prod.get(i)).getSum();
      p.addRow(new String[] { ((Registration)prod.get(i)).getData(), ((Registration)prod.get(i)).getUser(), Integer.toString(((Registration)prod.get(i)).getId()), ((Registration)prod.get(i)).getName(), Double.toString(((Registration)prod.get(i)).getPrice()), Integer.toString(((Registration)prod.get(i)).getSize()), Double.toString(((Registration)prod.get(i)).getSum()) });
    }
    
    p.addRow(new String[] { "", "", "", "", "", "Итого: ", Double.toString(s) });
    JTable table = new JTable(p);
    table.setGridColor(Color.black);
    table.setFont(new Font("Dialog", 0, 11));
    print(table);
  }
  
  public Print(ObservableList<Repot> collect_otchet, boolean cin)
  {
    DefaultTableModel p = new DefaultTableModel() {
      public boolean isCellEditable(int a, int b) {
        return false;
      }
    };
    p.setColumnIdentifiers(new String[] { "Код", "Наименование", "Цена", "Кол-во", "Сумма" });
    int n = collect_otchet.size();
    p.addRow(new String[] { "Код", "Наименование", "Цена", "Кол-во", "Сумма" });
    for (int i = 0; i < n; i++) {
      p.addRow(new String[] { ((Repot)collect_otchet.get(i)).getId(), ((Repot)collect_otchet.get(i)).getName(), ((Repot)collect_otchet.get(i)).getPrice(), ((Repot)collect_otchet.get(i)).getSize(), Double.toString(((Repot)collect_otchet.get(i)).getSum()) });
    }
    
    JTable table = new JTable(p);
    table.setGridColor(Color.black);
    table.setFont(new Font("Dialog", 0, 11));
    print(table);
  }
  
  public final void print(final JTable tb) {
    String lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
    
    try
    {
      UIManager.setLookAndFeel(lookAndFeel);
    }
    catch (ClassNotFoundException ex) {
      Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedLookAndFeelException ex) {
      Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    }
    


    EventQueue.invokeLater(new Runnable()
    {
      public void run() {
        Frame f = new Frame();
        f.setLayout(new BorderLayout());
        int n = tb.getColumnCount();
        if (n == 7) {
          tb.getColumnModel().getColumn(0).setPreferredWidth(50);
          tb.getColumnModel().getColumn(1).setPreferredWidth(60);
          tb.getColumnModel().getColumn(2).setPreferredWidth(40);
          tb.getColumnModel().getColumn(3).setPreferredWidth(420);
          tb.getColumnModel().getColumn(4).setPreferredWidth(60);
          tb.getColumnModel().getColumn(5).setPreferredWidth(60);
          tb.getColumnModel().getColumn(6).setPreferredWidth(70);
          f.setSize(760, 600);
        }
        if (n == 5) {
          tb.getColumnModel().getColumn(0).setPreferredWidth(50);
          tb.getColumnModel().getColumn(1).setPreferredWidth(420);
          tb.getColumnModel().getColumn(2).setPreferredWidth(60);
          tb.getColumnModel().getColumn(3).setPreferredWidth(60);
          tb.getColumnModel().getColumn(4).setPreferredWidth(70);
          f.setSize(670, 600);
        }
        
        f.add(tb, "West");
        
        f.setVisible(true);
        try
        {
          SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
          
          java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
          
          MessageFormat footer = new MessageFormat("Дата: " + sysdate);
          


          if (tb.print(JTable.PrintMode.FIT_WIDTH, footer, null)) {
            JOptionPane.showMessageDialog(f, "Печать завершена");
            f.dispose();
          }
          else {
            JOptionPane.showMessageDialog(f, "Печать прерванна/Ошибка печати");
            f.dispose();
          }
        }
        catch (PrinterException pe) {}
      }
    });
  }
}
