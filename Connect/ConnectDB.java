package Connect;

import Collection.AdminPane;
import Collection.HistoryMetallOtchet;
import Collection.ListCustProduct;
import Collection.Log_view;
import Collection.MetallList;
import Collection.Person;
import Collection.Prices;
import Collection.Procurement_product_hist;
import Collection.Registration;
import Collection.Repot;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Properties;
import javafx.collections.ObservableList;

public class ConnectDB {

    static Properties properties;
    static String url;

    public ConnectDB() {
        properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "767690");
        properties.setProperty("useUnicode", "true");
        properties.setProperty("characterEncoding", "cp1251");

        url = "jdbc:mysql://localhost/solnce";
    }

    public Person autorizeuser(String name, String pass) {
        Person user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT * FROM `passworld` WHERE `name` = '" + name + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if ((rs.next())
                    && (pass.equals(rs.getString(2)))) {
                user = loadRules(rs.getInt(3));
                user.setName(rs.getString(1));
                user.setId_user(rs.getInt(4));
                user.setId_rules(rs.getInt(3));
                user.setUse_key(rs.getInt(5));
            }

            rs.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return user;
    }

    public void loadUser(ObservableList<Collection.Users> user) {
        user.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            String query = "SELECT `rules`.`name`, `passworld`.`name`,`passworld`.`pass`,`passworld`.`rules`,`passworld`.`id_user` FROM `passworld`,`rules` WHERE `rules`.`id` = `passworld`.`rules`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                user.add(new Collection.Users(rs.getString(2), rs.getString(3), rs.getString(1), rs.getInt(5)));
            }

            rs.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public Person loadRules(int i) {
        Person user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT * FROM `rules` WHERE `id` = '" + i + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                user = new Person();

                user.setRules(rs.getBoolean(3), 0);
                user.setRules(rs.getBoolean(4), 1);
                user.setRules(rs.getBoolean(5), 2);
                user.setRules(rs.getBoolean(6), 3);
                user.setRules(rs.getBoolean(7), 4);
                user.setRules(rs.getBoolean(8), 5);
                user.setRules(rs.getBoolean(9), 6);
                user.setRules(rs.getBoolean(10), 7);
                user.setRules(rs.getBoolean(11), 8);
                user.setRules(rs.getBoolean(12), 9);
                user.setRules(rs.getBoolean(13), 10);
                user.setRules(rs.getBoolean(14), 11);
                user.setRules(rs.getBoolean(15), 12);
                user.setRules(rs.getBoolean(16), 13);
                user.setRules(rs.getBoolean(17), 14);
                user.setRules(rs.getBoolean(18), 15);
                user.setRules(rs.getBoolean(19), 16);
                user.setRules(rs.getBoolean(20), 17);
                user.setRules(rs.getBoolean(21), 18);
                user.setRules(rs.getBoolean(22), 19);
                user.setRules(rs.getBoolean(23), 20);
            }
            rs.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return user;
    }

    public void loadRulesCorrect(ObservableList<Collection.Rules> rules) {
        rules.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT * FROM `rules`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                rules.add(new Collection.Rules(rs.getString(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getBoolean(6), rs.getBoolean(7), rs.getBoolean(8), rs.getBoolean(9), rs.getBoolean(10), rs.getBoolean(11), rs.getBoolean(12), rs.getBoolean(13), rs.getBoolean(14), rs.getBoolean(15), rs.getBoolean(16), rs.getBoolean(17), rs.getBoolean(18), rs.getBoolean(19), rs.getBoolean(20), rs.getBoolean(21), rs.getBoolean(22), rs.getBoolean(23)));
            }

            rs.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void loadProduct(ObservableList<String> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `short_name` FROM `prais` WHERE `actual_status` != 2;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void loadProductId(ObservableList<Integer> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `id` FROM `prais` WHERE `actual_status` != 2;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(Integer.valueOf(rs.getInt(1)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void loadCustProductId(ObservableList<Integer> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `id` FROM  `customer_order_product`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(Integer.valueOf(rs.getInt(1)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void loadGroupTable(ObservableList<Collection.GroupProduct> data) {
        int n = 0;
        data.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select * FROM `group_product`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(new Collection.GroupProduct(n++, rs.getInt(2), rs.getString(1)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadGroupList(ObservableList<String> data) {
        data.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select * FROM `group_product`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            data.add("0 Все категории");
            while (rs.next()) {
                data.add(rs.getInt(2) + " " + rs.getString(1));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadRules(ObservableList<String> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select * FROM `rules`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(rs.getInt(1) + "  " + rs.getString(2));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public double price(String name) {
        double a = 0.0D;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "SELECT `value` FROM `prais` WHERE `short_name` = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                a = rs.getDouble(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

    public double price(int id) {
        double a = 0.0D;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "SELECT `value` FROM `prais` WHERE `id` = '" + id + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                a = rs.getDouble(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

    public int setID(String name) {
        int c = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "SELECT `id` FROM `prais` WHERE `short_name` = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                c = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public void reliz_day(int idto, String name, int balance, int col_t, int stock_event, double price, double stoimost, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            SimpleDateFormat s = new SimpleDateFormat("HH:mm");

            java.sql.Time time = java.sql.Time.valueOf(s.format(new java.util.Date()) + ":00");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());

            stmt.executeUpdate("INSERT INTO `registration`(`idop`, `data`, `time`, `idto`, `name`,`balance`,`size`,`stock`, `price`, `sum`, `user`) VALUES ('0','" + sysdate + "','" + time + "','" + idto + "','" + name + "','" + balance + "','" + col_t + "','"+stock_event+"','" + price + "','" + stoimost + "','" + user + "')");

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLog(AdminPane p, AdminPane prev, int type, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `log`(`date`,`idop`,`idproduct`,`name`,`group_product`,`ostatok`,`price`,`type`,`user`) VALUES (UNIX_TIMESTAMP(),'0','" + p.getId() + "'," + "'" + p.getShortname() + "'," + "'" + p.getGroup() + "'," + "'" + p.getSize() + "'," + "'" + p.getPrice() + "'," + "'" + type + "'," + "'" + user + "');");

            int maxindex = 0;
            ResultSet rs = stmt.executeQuery("SELECT MAX(idop) AS idop FROM log;");
            if (rs.next()) {
                maxindex = rs.getInt(1);
            }
            if (type == 1) {
                stmt.executeUpdate("INSERT INTO `revision_log_product`(`id`,`id_operation`,`id_product`,`name_product`,`group_product`,`ostatok`,`price`) VALUES ('0','" + maxindex + "'," + "'" + prev.getId() + "'," + "'" + prev.getShortname() + "'," + "'" + prev.getGroup() + "'," + "'" + prev.getSize() + "'," + "'" + prev.getPrice() + "');");
            }

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLog(ListCustProduct p, int type, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `log`(`date`,`idop`,`idproduct`,`name`,`group_product`,`ostatok`,`price`,`type`,`user`) VALUES (UNIX_TIMESTAMP(),'0','" + p.getId() + "'," + "'" + p.getName() + "'," + "'" + p.getGroup() + "'," + "'0'," + "'" + p.getPrice() + "'," + "'" + type + "'," + "'" + user + "');");

            int maxindex = 0;
            ResultSet rs = stmt.executeQuery("SELECT MAX(idop) AS idop FROM log;");
            if (rs.next()) {
                maxindex = rs.getInt(1);
            }
            if (type == 1) {
                stmt.executeUpdate("INSERT INTO `revision_log_product`(`id`,`id_operation`,`id_product`,`name_product`,`group_product`,`ostatok`,`price`) VALUES ('0','" + maxindex + "'," + "'" + p.getId() + "'," + "'" + p.getName() + "'," + "'" + p.getGroup() + "'," + "'0'," + "'" + p.getPrice() + "');");
            }

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStartSum(double price, String pc, String user_name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `start_work_day`(`date`, `start_sum`,`id`,`user_name`) VALUES ('" + sysdate + "','" + price + "','" + 0 + "','" + user_name + "');");
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int[] getSize(int id) {
        int[] size = new int[3];// = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `sell`,`stock_0`,`stock_1` FROM `prais` WHERE `id` = '" + id + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                size[0] = rs.getInt(1);
                size[1] = rs.getInt(2);
                size[2] = rs.getInt(3);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return size;
    }

    public int[] getSize(String name) {
        int[] size = new int[3];// = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `sell`,`stock_0`,`stock_1` FROM `prais` WHERE `short_name` = '" + name + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                size[0] = rs.getInt(1);
                size[1] = rs.getInt(2);
                size[2] = rs.getInt(3);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return size;
    }

    public int setProductAdmin(String name, String sname, int gr, int h, int size, double p,int stock,int stock_0,int stock_1) {
        int b = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("INSERT INTO `prais` (`id`,`name`,`short_name`,`group`,`helf`,`value`,`sell`,`actual_status`, `stock`, `stock_0`, `stock_1`) VALUES ('0','" + name + "'," + "'" + sname + "'," + "'" + gr + "'," + "'" + h + "'," + "'" + p + "'," + "'" + size + "'," + "'0','"+stock+"','"+stock_0+"','"+stock_1+"');");
            // next запрос INSERT INTO `stock_price` (`id`, `id_price`, `stock_0`, `stock_1`) VALUES (NULL, '"++"', '10', '20');
            ResultSet rs = stmt.executeQuery("SELECT `id` FROM `prais` WHERE `name` = '" + name + "'");
            if (rs.next()) {
                b = rs.getInt(1);
            }
            rs.close();

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public double getStartSum(String user_name, boolean full_short) {
        double start_sum = 0.0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `start_sum` FROM `start_work_day` WHERE `date` = '" + sysdate + "' and `user_name` = '" + user_name + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                start_sum = rs.getDouble(1);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return start_sum;
    }

    public LinkedHashMap<String, Double> getStartSum(String data) {
        LinkedHashMap<String, Double> map = new LinkedHashMap<String, Double>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `start_sum`,`user_name` FROM `start_work_day` WHERE `date` = '" + data + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            double ipsum = 0.0D;
            while (rs.next()) {
                map.put(rs.getString(2), Double.valueOf(rs.getDouble(1)));
                ipsum += rs.getDouble(1);
            }

            map.put("Cтарт: ", Double.valueOf(ipsum));

            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return map;
    }

    public int getSize(int id, String date) {
        int size = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            String query = "SELECT `size` FROM `registration` WHERE `idto` = '" + id + "' AND `data` = '" + date + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                size += rs.getInt(1);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return size;
    }

    public String getGroup(int id) {
        String group = "?";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT * FROM `group_product` WHERE `id` = '" + id + "'";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                group = rs.getString(1);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return group;
    }

    public int addGroup(String name) {
        int rid = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "INSERT INTO `group_product` (name,id) VALUES ('" + name + "','0');";
            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);
            ResultSet rs = stmt.executeQuery("SELECT * FROM `group_product` WHERE `name` = '" + name + "';");
            if (rs.next()) {
                rid = rs.getInt(2);
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return rid;
    }

    public void deletGroup(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "DELETE FROM `group_product` WHERE `id` = '" + id + "'";
            Statement stmt = connection.createStatement();

            stmt.executeUpdate(query);
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void setSize(int id, int full, int stock_event,int stock_size) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "";
            if(stock_event == 0)query = "UPDATE `solnce`.`prais` SET `sell` = '" + full + "', `stock_0` =  '"+stock_size+"' WHERE `prais`.`id` = '" + id + "'";
                else query = "UPDATE `solnce`.`prais` SET `sell` = '" + full + "', `stock_1` =  '"+stock_size+"' WHERE `prais`.`id` = '" + id + "'";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSessionBecap(ObservableList<Registration> prod) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysd = new java.sql.Date(new java.util.Date().getTime());

            String query = "SELECT * FROM `registration` WHERE `data` = '" + sysd + "'";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            double c = 0.0D;
            while (rs.next()) {
                prod.add(new Registration(rs.getTime(3).toString().substring(0, 5), rs.getString(5), Integer.valueOf(rs.getInt(4)), Integer.valueOf(rs.getInt(7)),rs.getInt(8), rs.getDouble(9), rs.getDouble(10), rs.getString(11)));
                c += rs.getDouble(9);
            }

            query = "SELECT SUM(summa_paid) AS sum_with FROM `contract_paid` WHERE `data_paid` = '" + sysd + "'";

            rs = stmt.executeQuery(query);
            if (rs.next()) {
                c += rs.getDouble(1);
            }

            query = "SELECT SUM(`summa`) AS sum_with FROM `metall_reg` WHERE `data` = '" + sysd + "';";
            rs = stmt.executeQuery(query);
            double mr = 0.0D;
            if (rs.next()) {
                mr += rs.getDouble(1);
            }
            c -= mr;
            Collection.Cassa.cassa = c;
            Collection.Cassa.rasxod = mr;

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getSessionBecap(ObservableList<Registration> prod, String user_name) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysd = new java.sql.Date(new java.util.Date().getTime());

            String query = "SELECT * FROM `registration` WHERE `data` = '" + sysd + "' and `user` = '" + user_name + "'";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            double c = 0.0D;
            while (rs.next()) {
                prod.add(new Registration(rs.getTime(3).toString().substring(0, 5), rs.getString(5), Integer.valueOf(rs.getInt(4)), Integer.valueOf(rs.getInt(7)),rs.getInt(8), rs.getDouble(9), rs.getDouble(10), rs.getString(11)));
                c += rs.getDouble(9);
            }
            query = "SELECT SUM(summa_paid) AS sum_with FROM `contract_paid` WHERE `data_paid` = '" + sysd + "' and `user` = '" + user_name + "'";

            rs = stmt.executeQuery(query);
            if (rs.next()) {
                c += rs.getDouble(1);
            }

            query = "SELECT SUM(`summa`) AS sum_with FROM `metall_reg` WHERE `data` = '" + sysd + "';";
            rs = stmt.executeQuery(query);
            double metallrasxod = 0.0D;
            if (rs.next()) {
                metallrasxod += rs.getDouble(1);
            }
            c -= metallrasxod;
            Collection.Cassa.cassa = c;
            Collection.Cassa.rasxod = metallrasxod;

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getOtchet(ObservableList<Repot> prod, String usr_name) {
        prod.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysd = new java.sql.Date(new java.util.Date().getTime());

            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `idto` , `name` , `price` , SUM(size) AS sum_size, SUM(sum) AS sum_with FROM `registration` WHERE `data` = '" + sysd + "' and `user` = '" + usr_name + "' GROUP BY `idto`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                prod.add(new Repot(rs.getString(2), Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(4)), Double.toString(price(rs.getString(2))), rs.getDouble(5)));
            }
            query = "SELECT SUM(sum) AS sum_with FROM `registration` WHERE `data` = '" + sysd + "' and `user` = '" + usr_name + "';";
            rs = stmt.executeQuery(query);
            double sum_with = 0.0D;
            if (rs.next()) {
                sum_with = rs.getDouble(1);
            }
            prod.add(new Repot("", "", "", "Итого : ", sum_with));

            query = "SELECT `id_contract`, SUM(summa_paid) AS sum_with FROM `contract_paid` WHERE `data_paid` = '" + sysd + "' and `user`= '" + usr_name + "' GROUP BY `id_contract`";

            rs = stmt.executeQuery(query);
            double contracts_summa_paid = 0.0D;
            while (rs.next()) {
                prod.add(new Repot("Договор поставки/рассрочки ", Integer.toString(rs.getInt(1)), "1", "", rs.getDouble(2)));
                contracts_summa_paid += rs.getDouble(2);
            }

            if (contracts_summa_paid != 0.0D) {
                prod.add(new Repot("", "", "", "Итого: ", contracts_summa_paid));
            }

            query = "SELECT `code`, `name` , `price`, SUM(`size`) AS sum_size, SUM(`summa`) AS sum_with FROM `metall_reg` WHERE `data` = '" + sysd + "' GROUP BY `code`;";

            rs = stmt.executeQuery(query);
            double sum_with_metall = 0.0D;

            while (rs.next()) {
                prod.add(new Repot(rs.getString(2), Integer.toString(rs.getInt(1)), String.valueOf(rs.getInt(4)), String.valueOf(rs.getDouble(3)), rs.getDouble(5)));
                sum_with_metall += rs.getDouble(5);
            }
            if (sum_with_metall != 0.0D) {
                prod.add(new Repot("", "", "", "Итого: ", sum_with_metall));
            }

            query = "SELECT `start_sum` FROM `start_work_day` WHERE `user_name` ='" + usr_name + "' and `date` = '" + sysd + "';";
            rs = stmt.executeQuery(query);
            double cassa_start_short = 0.0D;

            if (rs.next()) {
                cassa_start_short = rs.getDouble(1);
            }

            prod.add(new Repot("", "", "", "Старт : ", cassa_start_short));
            prod.add(new Repot("", "", "", "Касса : ", cassa_start_short + sum_with + contracts_summa_paid - sum_with_metall));
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getOtchet(ObservableList<Repot> prod) {
        prod.remove(0, prod.size());
        try {
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysd = new java.sql.Date(new java.util.Date().getTime());

            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `idto` , `name` , `price` , SUM(size) AS sum_size, SUM(sum) AS sum_with FROM `registration` WHERE `data` = '" + sysd + "' GROUP BY `idto`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Repot(rs.getString(2), Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(4)), Double.toString(rs.getDouble(3)), rs.getDouble(5)));
            }
            query = "SELECT SUM(sum) AS sum_with FROM `registration` WHERE `data` = '" + sysd + "';";
            rs = stmt.executeQuery(query);
            double sum_with = 0.0D;
            if (rs.next()) {
                sum_with = rs.getDouble(1);
            }
            prod.add(new Repot("", "", "", "Итого : ", sum_with));

            query = "SELECT `id_contract`, SUM(summa_paid) AS sum_with FROM `contract_paid` WHERE `data_paid` = '" + sysd + "' GROUP BY `id_contract`";

            rs = stmt.executeQuery(query);
            double contracts_summa_paid = 0.0D;
            while (rs.next()) {
                prod.add(new Repot("Договор поставки/рассрочки ", Integer.toString(rs.getInt(1)), "1", "", rs.getDouble(2)));
                contracts_summa_paid += rs.getDouble(2);
            }

            if (contracts_summa_paid != 0.0D) {
                prod.add(new Repot("", "", "", "Итого: ", contracts_summa_paid));
            }
            query = "SELECT `code`, `name` , `price`, SUM(`size`) AS sum_size, SUM(`summa`) AS sum_with FROM `metall_reg` WHERE `data` = '" + sysd + "' GROUP BY `code`;";

            rs = stmt.executeQuery(query);
            double sum_with_metall = 0.0D;

            while (rs.next()) {
                prod.add(new Repot(rs.getString(2), Integer.toString(rs.getInt(1)), String.valueOf(rs.getInt(4)), String.valueOf(rs.getDouble(3)), rs.getDouble(5)));
                sum_with_metall += rs.getDouble(5);
            }
            if (sum_with_metall != 0.0D) {
                prod.add(new Repot("", "", "", "Итого: ", sum_with_metall));
            }

            LinkedHashMap<String, Double> map = getStartSum(sysd.toString());
            Object[] key = map.keySet().toArray();
            int n = key.length - 1;
            if (n > 1) {
                for (int i = 0; i < n; i++) {
                    prod.add(new Repot("", "", "", i + 1 + ". " + key[i].toString(), ((Double) map.get(key[i])).doubleValue()));
                }
            }
            prod.add(new Repot("", "", "", "Cтарт: ", ((Double) map.get(key[n])).doubleValue()));
            prod.add(new Repot("", "", "", "Касса : ", ((Double) map.get("Cтарт: ")).doubleValue() + sum_with + contracts_summa_paid - sum_with_metall));
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getOtchet(ObservableList<Repot> prod, String a, String b) {
        prod.remove(0, prod.size());
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `idto` , `name` , `price` , SUM(size) AS sum_size, SUM(sum) AS sum_with FROM `registration` WHERE `data` BETWEEN '" + a + "'AND '" + b + "' GROUP BY `idto`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            double sum_with = 0.0D;
            while (rs.next()) {
                prod.add(new Repot(rs.getString(2), Integer.toString(rs.getInt(1)), Integer.toString(rs.getInt(4)), Double.toString(price(rs.getString(2))), rs.getDouble(5)));
                sum_with += rs.getDouble(5);
            }

            prod.add(new Repot("", "", "", "Итого : ", sum_with));

            query = "SELECT `id_contract`, SUM(summa_paid) AS sum_with FROM `contract_paid` WHERE `data_paid` BETWEEN '" + a + "'AND '" + b + "' GROUP BY `id_contract`";

            rs = stmt.executeQuery(query);
            double contracts_summa_paid = 0.0D;
            while (rs.next()) {
                prod.add(new Repot("Договор поставки/рассрочки ", Integer.toString(rs.getInt(1)), "1", "", rs.getDouble(2)));
                contracts_summa_paid += rs.getDouble(2);
            }

            if (contracts_summa_paid != 0.0D) {
                prod.add(new Repot("", "", "", "Итого: ", contracts_summa_paid));
            }

            if (a.equals(b)) {
                LinkedHashMap<String, Double> map = getStartSum(a);

                Object[] key = map.keySet().toArray();

                int n = key.length - 1;

                if (n > 1) {
                    for (int i = 0; i < n; i++) {
                        prod.add(new Repot("", "", "", i + 1 + ". " + key[i].toString(), ((Double) map.get(key[i])).doubleValue()));
                    }
                }
                prod.add(new Repot("", "", "", "Cтарт: ", ((Double) map.get(key[n])).doubleValue()));

                prod.add(new Repot("", "", "", "Касса : ", ((Double) map.get("Cтарт: ")).doubleValue() + sum_with + contracts_summa_paid));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getGrapicsDate(String a, String b) {
        ObservableList<String> category = javafx.collections.FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `data` FROM `registration` WHERE `data` BETWEEN '" + a + "'AND '" + b + "' GROUP BY `data`;");

            while (rs.next()) {
                category.add(rs.getDate(1).toString());
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void getProductForStatus(ObservableList<Prices> prod, int status) {
        try {
            String actual = "";
            prod.remove(0, prod.size());
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            if (status == 0) {
                actual = " WHERE `actual_status` = 0;";
            }
            if (status == 1) {
                actual = " WHERE `actual_status` = 1;";
            }
            if (status == 2) {
                actual = " WHERE `actual_status` = 2;";
            }
            String query = "SELECT * FROM `prais`" + actual;
            ResultSet rs = stmt.executeQuery(query);
            int e = 0;
            while (rs.next()) {
                prod.add(new Prices(rs.getInt(1), e++, rs.getString(3), rs.getInt(4), rs.getInt(7), rs.getDouble(6), rs.getInt(8)));
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProductForStatusAdmin(ObservableList<AdminPane> prod, int status) {
        try {
            String actual = "";
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            if (status == 0) {
                actual = " WHERE `actual_status` = 0;";
            }
            if (status == 1) {
                actual = " WHERE `actual_status` = 1;";
            }
            if (status == 2) {
                actual = " WHERE `actual_status` = 2;";
            }
            String query = "SELECT * FROM `prais`" + actual;
            ResultSet rs = stmt.executeQuery(query);
            int index = 0;

            while (rs.next()) {
                // не отработанно
                prod.add(new AdminPane(index++, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(7), rs.getDouble(6), rs.getInt(8),0,0,0));
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getPaneAdmin(ObservableList<AdminPane> prod) {
        try {
            int index = 1;
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            //(`id`, `name`, `short_name`, `group`, `helf`, `value`, `sell`, `actual_status`, `stock`, `stock_0`, `stock_1`)
            String query = "SELECT `id`,0,`short_name`,`group`,`helf`,`value`,`sell`,`actual_status`,`stock`,`stock_0`,`stock_1` FROM `prais`;";
            //String query = "select * from `prais`  right join `stock_price` on `prais`.`id` = `stock_price`.`id_price`";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                // int склада не реализован
               prod.add(new AdminPane(index++, rs.getInt(1), rs.getString(3), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getInt(7), rs.getDouble(6), rs.getInt(8),rs.getInt(9),rs.getInt(10),rs.getInt(11)));
               //(int num, int idp, String n, String shortname, int gr, int h, int s, double p, int as, int stock, int stock_size_0,int stock_size_1)
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGroupPrices(ObservableList<Prices> prod, int gr, int s) {
        try {
            prod.remove(0, prod.size());
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            int idgroup = gr;
            String query = "SELECT * FROM `prais` WHERE `group` = '" + idgroup + "' ORDER BY `id` ASC";
            ResultSet rs = stmt.executeQuery(query);

            int e = 0;
            while (rs.next()) {
                prod.add(new Prices(rs.getInt(1), e++, rs.getString(3), idgroup, rs.getInt(7), rs.getDouble(6), rs.getInt(8)));
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGroupProductAdmin(ObservableList<AdminPane> prod, int gr, int s) {
        try {
            int index = 1;

            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            int idgroup = gr;
            //select * from `prais`  right join `stock_price` on `prais`.`id` = `stock_price`.`id_price` WHERE `prais`.`id` = 2;
            //String query = "SELECT * FROM `prais` WHERE `group` = '" + idgroup + "' ORDER BY `helf` ASC";
            String query = "select * from `prais`  right join `stock_price` on `prais`.`id` = `stock_price`.`id_price` WHERE `group` = '" + idgroup + "' ORDER BY `helf` ASC;";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                // int значение склада ни как не используется
                prod.add(new AdminPane(index, rs.getInt(1), rs.getString(2), rs.getString(3), idgroup, rs.getInt(5), rs.getInt(7), rs.getDouble(6), rs.getInt(8),0,rs.getInt(11),rs.getInt(12)));
                index++;
            }

            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getCustomerProductGroup(ObservableList<ListCustProduct> prod, String gr) {
        try {
            prod.remove(0, prod.size());
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            int idgroup = Integer.parseInt(gr);
            int count = 0;
            String query = "SELECT * FROM  `customer_order_product`  WHERE `group` = '" + idgroup + "';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                prod.add(new ListCustProduct(count++, rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getDouble(3)));
            }
            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateGroup(int id, String gname) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "UPDATE `group_product` SET `name` = '" + gname + "' WHERE `id` = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void deletProduct(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM `solnce`.`prais` WHERE `prais`.`id` = '" + id + "'";

            stmt.execute(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletUser(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM `solnce`.`passworld` WHERE `passworld`.`name` = '" + name + "';";
            stmt.execute(query);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletRule(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM `solnce`.`rules` WHERE `rules`.`name` = '" + name + "';";
            stmt.execute(query);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePrice(int id, String name, String sname, int group, int helf, double price, int size, int actual_status,int stock, int stock_0, int stock_1) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "UPDATE `prais` SET `name` = '" + name + "',`short_name` = '" + sname + "',`group` = '" + group + "',`helf` = '" + helf + "',`value` = '" + price + "',`sell` = '" + size + "',`actual_status`= '" + actual_status + "', `stock` = '"+stock+"',`stock_0` = '"+stock_0+"', `stock_1` = '"+stock_1+"' WHERE `id` = '" + id + "'";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getHistory(String a1, String a2, ObservableList<Collection.History> prod) {
        try {
            prod.remove(0, prod.size());
            Class.forName("com.mysql.jdbc.Driver");
            String query = "SELECT * FROM `registration` WHERE `data` BETWEEN '" + a1 + "'AND '" + a2 + "';";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Collection.History(rs.getInt(1), rs.getDate(2).toString(), rs.getString(5), rs.getInt(6), rs.getInt(4), rs.getInt(7), rs.getDouble(8), rs.getDouble(9), rs.getString(10)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Log_view> getJournal(String a1, String a2, boolean add, boolean clear, boolean cor) {
        ObservableList<Log_view> data = javafx.collections.FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String validate = "";

            String query = "SELECT * FROM `log`  where FROM_UNIXTIME(`date`) >= '" + a1 + "' and FROM_UNIXTIME(`date`) <= '" + a2 + "' " + validate + ";";

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            int index = 0;
            while (rs.next()) {
                long e = rs.getLong(1);
                java.util.Date date = new java.util.Date(e * 1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String time = sdf.format(date);

                if ((add) && (rs.getInt(8) == 0)) {
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }
                if ((cor) && (rs.getInt(8) == 1)) {
                    data.add(getRevision_product(rs.getInt(2), index, time));
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }

                if ((clear) && (rs.getInt(8) == 2)) {
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public ObservableList<Log_view> getJournalFiltr(String a1, String a2, int idproduct) {
        ObservableList<Log_view> data = javafx.collections.FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String validate = "";

            String query = "SELECT * FROM `log`  where FROM_UNIXTIME(`date`) >= '" + a1 + "' and FROM_UNIXTIME(`date`) <= '" + a2 + "' " + validate + " and `idproduct` = '" + idproduct + "';";

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            int index = 0;
            while (rs.next()) {
                long e = rs.getLong(1);
                java.util.Date date = new java.util.Date(e * 1000L);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String time = sdf.format(date);

                if (rs.getInt(8) == 0) {
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }
                if (rs.getInt(8) == 1) {
                    data.add(getRevision_product(rs.getInt(2), index, time));
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }

                if (rs.getInt(8) == 2) {
                    data.add(new Log_view(index++, time, rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8), rs.getString(9)));
                }
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public Log_view getRevision_product(int id, int index, String time) {
        Log_view log1 = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            String query = "SELECT * FROM `revision_log_product` WHERE `id_operation` = '" + id + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                log1 = new Log_view(index, time, id, rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getDouble(7), 1, "");
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return log1;
    }

    public void setCorectUser(int id, int g, String name, String pass) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "UPDATE `solnce`.`passworld` SET `name` = '" + name + "',`pass` = '" + pass + "', `rules` = '" + g + "' WHERE `passworld`.`id_user` = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int setNewUser(String name, String pass, int rule, boolean use_key, String key) {
        int idbreak = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            int uk = use_key ? 1 : 0;
            stmt.executeUpdate("INSERT INTO passworld (name,pass,rules,id_user,use_key,flashkey) VALUES ('" + name + "','" + pass + "', '" + rule + "','0','" + uk + "','" + key + "');");
            ResultSet rs = stmt.executeQuery("SELECT `id_user` FROM `passworld` WHERE `name` = '" + name + "';");
            if (rs.next()) {
                idbreak = rs.getInt(1);
            }
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return idbreak;
    }

    public void setRules(String name, boolean a, boolean b, boolean c, boolean d, boolean ec, boolean f, boolean g, boolean t, boolean ug, boolean fo, boolean fr, boolean ip, boolean co, boolean opa, boolean opc, boolean opd, boolean zpa, boolean zpc, boolean zpd, boolean zpo, boolean lhm) {
        try {
            int a1 = a ? 1 : 0;
            int b1 = b ? 1 : 0;
            int c1 = c ? 1 : 0;
            int d1 = d ? 1 : 0;
            int ec1 = ec ? 1 : 0;
            int f1 = f ? 1 : 0;
            int g1 = g ? 1 : 0;
            int t1 = t ? 1 : 0;
            int u1 = ug ? 1 : 0;
            int f2 = fo ? 1 : 0;
            int f3 = fr ? 1 : 0;
            int ip1 = ip ? 1 : 0;
            int co1 = co ? 1 : 0;
            int opa1 = opa ? 1 : 0;
            int opc1 = opc ? 1 : 0;
            int opd1 = opd ? 1 : 0;
            int zpa1 = zpa ? 1 : 0;
            int zpc1 = zpc ? 1 : 0;
            int zpd1 = zpd ? 1 : 0;
            int zpo1 = zpo ? 1 : 0;
            int lhm1 = lhm ? 1 : 0;

            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "UPDATE `solnce`.`rules` SET `regist_product` = '" + a1 + "' ," + "`look_price` = '" + b1 + "' ," + "`add_product` = '" + c1 + "'," + "`clear_product` = '" + d1 + "'," + "`correct_product` = '" + ec1 + "'," + "`look_history` = '" + f1 + "'," + "`look_journal` = '" + g1 + "'," + "`correct_user` = '" + t1 + "'," + "`use_group` = '" + u1 + "'," + "`full_otchet` = '" + f2 + "'," + "`full_registration` = '" + f3 + "'," + "`installment_paid` = '" + ip1 + "'," + "`customer_order` = '" + co1 + "'," + "`order_product_add` = '" + opa1 + "'," + "`order_product_cor` = '" + opc1 + "'," + "`order_product_del` = '" + opd1 + "'," + "`zacup_product_add` = '" + zpa1 + "'," + "`zacup_product_cor` = '" + zpc1 + "'," + "`zacup_product_del` = '" + zpd1 + "'," + "`zacup_product_ok` = '" + zpo1 + "'," + "`look_hist_metall` = '" + lhm1 + "'" + " WHERE `rules`.`name` = '" + name + "'";

            stmt.executeUpdate(query);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setNewRule(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO rules (id,name,regist_product,look_price,add_product,clear_product,correct_product,look_history,look_journal,correct_user,use_group,full_otchet,full_registration,installment_paid,customer_order,order_product_add,order_product_cor,order_product_del,zacup_product_add,zacup_product_add,zacup_product_cor,zacup_product_del,zacup_product_ok,look_hist_metall) VALUES ('0','" + name + "', '0', '0','0','0','0','0','0','0','0','0','0''0','0','0','0','0','0','0','0','0','0');");

            stmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getName(int id) {
        String n = "Sorry d'not name.";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `short_name` FROM `prais` WHERE `id` = '" + id + "'";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                n = rs.getString(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public String getNameOrder(int id) {
        String n = "Sorry d'not name.";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `short_name` FROM `prais` WHERE `id` = '" + id + "' UNION SELECT `name` FROM `customer_order_product` WHERE `id` ='" + id + "';";

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                n = rs.getString(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public ObservableList<Integer> getHelf(int id) {
        int max = 0;
        ObservableList<Integer> helf = javafx.collections.FXCollections.observableArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("Select MAX(`helf`) FROM `prais` WHERE `group` = '" + id + "';");

            if (rs.next()) {
                max = rs.getInt(1);
            }
            max += 10;

            for (int i = 1; i <= max; i++) {
                helf.add(Integer.valueOf(i));
            }
        } catch (ClassNotFoundException | SQLException e) {
        }

        return helf;
    }

    public void getIdGraphicsSize(String a, String b, int idto, ObservableList<String> datashow, ObservableList<Integer> sizeshow) {
        datashow.clear();
        sizeshow.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `data`, SUM(size) AS size_with FROM `registration` WHERE `data` BETWEEN '" + a + "'AND '" + b + "' AND `idto` = '" + idto + "'  GROUP BY `data`;");
            while (rs.next()) {
                datashow.add(rs.getString(1));
                sizeshow.add(Integer.valueOf(rs.getInt(2)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void getContractsList(int i, int j, ObservableList<Collection.contract> prod) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            String query = null;
            if (i == 1) {
                query = "SELECT * FROM `contracts` WHERE `status` ='1' and `type_contract` = '" + j + "';";
            }
            if (i == 0) {
                query = "SELECT * FROM `contracts` WHERE `status` ='0' and `type_contract` = '" + j + "';";
            }
            if (i == 2) {
                query = "SELECT * FROM `contracts` WHERE `type_contract` = '" + j + "';";
            }
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Collection.contract(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5).toString(), rs.getString(6), rs.getString(7), rs.getDate(8).toString(), rs.getDate(9).toString(), rs.getDouble(10), rs.getDouble(11), rs.getDouble(12), rs.getBoolean(13), rs.getString(14)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int setNewContract(String fio, String num, String adress, String year, String doccout, String phone, String dend, double total, double paid, double remain, boolean status, String user, int type) {
        int index = -1;
        int st = status ? 1 : 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            java.sql.Date sys = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `contracts` (id,fio,num,adress,year,doccout,phone,dstart,dend,total,paid,remain,status,user,type_contract) VALUES ('0','" + fio + "'," + "'" + num + "', '" + adress + "'," + "'" + year + "','" + doccout + "','" + phone + "','" + sysdate + "','" + dend + "'," + "'" + total + "','" + paid + "'," + "'" + remain + "','" + st + "','" + user + "','" + type + "');");

            ResultSet rs = stmt.executeQuery("SELECT id FROM `contracts` where `num`='" + num + "' and `dend` = '" + dend + "';");
            if (rs.next()) {
                index = rs.getInt(1);
            }
            stmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    public void setListContract(int id, int idproduct, String nameproduct, int size, double price, double sum) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            java.sql.Date sys = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `solnce`.`contract_artical` (`id_contract`,`id_product`,`name_object`,`price`,`size` ,`summa` ,`cindex`)VALUES ('" + id + "','" + idproduct + "', '" + nameproduct + "', '" + price + "', '" + size + "', '" + sum + "','0');");

            stmt.close();
            connection.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setContractPaid(int id_contract, double summa, double remain, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `contract_paid`(`cindex`, `id_contract`, `data_paid`, `summa_paid`,`remain_paid`,`user`) VALUES ('0','" + id_contract + "','" + sysdate + "','" + summa + "','" + remain + "','" + user + "');");

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Integer> setContractUpdatePaid(int id, double paid, double remain, boolean status) {
        ObservableList<Integer> size = javafx.collections.FXCollections.observableArrayList();
        int st = status ? 1 : 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "UPDATE `solnce`.`contracts` SET `paid` = '" + paid + "',`remain` = '" + remain + "',`status` = '" + st + "' WHERE `contracts`.`id` ='" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return size;
    }

    public void loadProductCustomerOrder(ObservableList<String> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `short_name` FROM `prais` UNION Select `name` FROM `customer_order_product`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(rs.getString(1));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProductIdOrder(ObservableList<String> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `id` FROM `prais` UNION Select `id` FROM `customer_order_product`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(rs.getString(1));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadProductCustomer(ObservableList<ListCustProduct> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select * FROM `customer_order_product`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            int count = 0;
            while (rs.next()) {
                data.add(new ListCustProduct(count++, rs.getInt(1), rs.getString(2), rs.getInt(4), rs.getDouble(3)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public double priceCustomerOrder(String name) {
        double a = 0.0D;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "SELECT `value` FROM `prais` WHERE `short_name` = '" + name + "' UNION SELECT `price_product` FROM `customer_order_product` WHERE `name` = '" + name + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                a = rs.getDouble(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

    public int getIdProductOrder(String name) {
        int c = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "SELECT `id` FROM `prais` WHERE `short_name` = '" + name + "' UNION SELECT `id` FROM `customer_order_product` WHERE `name` = '" + name + "';";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                c = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public int setAddProductSizeIsnow(String name_product, int id_product, int size_second, String user, boolean sttus) {
        int indexend = -1;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            int st = sttus ? 1 : 0;

            String query = "SELECT COUNT( * )FROM `procurement_product_hist` WHERE `status` = '1' AND `id_product` = '" + id_product + "';";

            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                if (rs.getInt(1) == 0) {
                    stmt.executeUpdate("INSERT INTO `procurement_product_hist` (`id`, `date`, `name_product`, `id_product`, `size_second`, `user`,`status`) VALUES ('0', '" + sysdate + "', '" + name_product + "', '" + id_product + "', '" + size_second + "', '" + user + "','" + st + "');");
                } else {
                    query = "SELECT `size_second` FROM `procurement_product_hist` WHERE `status` = '1' and  `id_product` = '" + id_product + "';";
                    rs = stmt.executeQuery(query);
                    int sell_product = rs.next() ? rs.getInt(1) : 0;
                    int size_total = size_second + sell_product;
                    stmt.executeUpdate("UPDATE `procurement_product_hist` SET `size_second` = '" + size_total + "',`user` = '" + user + "' WHERE `status` = '1' and `id_product` ='" + id_product + "';");
                }
                rs = stmt.executeQuery("Select `id` From `procurement_product_hist` where `id_product` = '" + id_product + "' and `status` = '1';");
                if (rs.next()) {
                    indexend = rs.getInt(1);
                }
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return indexend;
    }

    public void getProcurementProductHist(ObservableList<Procurement_product_hist> prod) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");

            String query = "SELECT `procurement_product_hist`.* , `prais`.`sell` FROM `procurement_product_hist`,`prais` where `procurement_product_hist`.`status` = '1' and `prais`.`id` =`procurement_product_hist`.`id_product`;";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Procurement_product_hist(rs.getInt(1), rs.getDate(2).toString(), rs.getString(3), rs.getInt(4), rs.getInt(8), rs.getInt(5), rs.getInt(5) + rs.getInt(8), rs.getString(7), rs.getBoolean(6)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int getProcurementActivCount() {
        int index = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String query = "SELECT COUNT( * )FROM `procurement_product_hist` WHERE `status` =1;";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                index = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return index;
    }

    public void updateSizeAfterAdded() {
        int idbreak = -1;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            Statement stms = connection.createStatement();

            String query = "SELECT `id_product`,`size_second`,`sell` FROM `procurement_product_hist`,`prais` WHERE `procurement_product_hist`.`status` = '1' and `prais`.`id` = `procurement_product_hist`.`id_product`;";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {

                int size_total = rs.getInt(2) + rs.getInt(3);
                stms.executeUpdate("UPDATE `prais` SET `sell` = '" + size_total + "' WHERE `id` = '" + rs.getInt(1) + "';");
                stms.executeUpdate("UPDATE `procurement_product_hist` SET `status` = '0' WHERE `status` = '1' and `id_product` = '" + rs.getInt(1) + "';");
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSizeCorrectAdded(int id_product, int size_total, String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("UPDATE `procurement_product_hist` SET `size_second` = '" + size_total + "',`user` = '" + user + "' WHERE `status` = '1' and `id_product` ='" + id_product + "';");

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletProductGate(int id_product) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM `procurement_product_hist` WHERE `status` = '1' and `id_product` = '" + id_product + "'";
            stmt.execute(query);
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProcurementHist(ObservableList<Procurement_product_hist> prod, int d) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            String query = "SELECT * FROM `procurement_product_hist` where `status` = 0 and `date` = '" + sysdate + "';";
            if (d == 7) {
                String[] mas = sysdate.toString().split("-");
                int year = Integer.parseInt(mas[0]);
                int month = Integer.parseInt(mas[1]);
                int day = Integer.parseInt(mas[2]);

                if (day > 7) {
                    day -= d;
                } else if (month != 1) {
                    day = 31 + day - d;
                    month--;
                } else {
                    year--;
                    month = 12;
                    day = 31 + day - d;
                }

                String nmonth = month < 10 ? "0" + month : Integer.toString(month);
                String nday = day < 10 ? "0" + day : Integer.toString(day);
                String nyear = Integer.toString(year) + "-" + nmonth + "-" + nday;

                query = "SELECT * FROM `procurement_product_hist` where `status` = 0 and `date` BETWEEN '" + nyear + "'AND '" + sysdate + "';";
            }

            if (d == 30) {
                String[] mas = sysdate.toString().split("-");
                int year = Integer.parseInt(mas[0]);
                int month = Integer.parseInt(mas[1]);
                int day = Integer.parseInt(mas[2]);

                if (month != 1) {
                    month--;
                } else {
                    year--;
                    month = 12;
                }

                String nmonth = month < 10 ? "0" + month : Integer.toString(month);
                String nday = day < 10 ? "0" + day : Integer.toString(day);
                String nyear = Integer.toString(year) + "-" + nmonth + "-" + nday;

                query = "SELECT * FROM `procurement_product_hist` where `status` = 0 and `date` BETWEEN '" + nyear + "'AND '" + sysdate + "';";
            }

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Procurement_product_hist(rs.getInt(1), rs.getDate(2).toString(), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(7)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProcurementHist(ObservableList<Procurement_product_hist> prod, String d1, String d2) {
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            String query = "SELECT * FROM `procurement_product_hist` where `status` = 0 and `date` BETWEEN '" + d1 + "'AND '" + d2 + "';";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                prod.add(new Procurement_product_hist(rs.getInt(1), rs.getDate(2).toString(), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(7)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int addCustomerProduct(String name, int gr, double p) {
        int b = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            stmt.executeUpdate("INSERT INTO `customer_order_product` (`id`,`name`, `price_product`, `group`) VALUES ('0','" + name + "','" + p + "','" + gr + "');");
            ResultSet rs = stmt.executeQuery("SELECT `id` FROM `customer_order_product` WHERE `name` = '" + name + "'");
            if (rs.next()) {
                b = rs.getInt(1);
            }
            rs.close();

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    public void updatePriceCustProduct(int id, String name, int group, double price) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "UPDATE `customer_order_product` SET `name` = '" + name + "',`price_product` = '" + price + "',`group` = '" + group + "' WHERE `customer_order_product`.`id` = '" + id + "';";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletCustProduct(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "DELETE FROM  `customer_order_product` WHERE  `customer_order_product`.`id` = '" + id + "'";

            stmt.execute(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean validateDeletGroup(int id) {
        boolean r = true;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT COUNT( * ) FROM `prais` WHERE `group` = '" + id + "';";
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            if ((rs.next()) && (rs.getInt(1) != 0)) {
                r = false;
            }
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }

        return r;
    }

    public void loadMetallName(ObservableList<String> data) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "Select `name` FROM `metall_price`";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                data.add(rs.getString(1));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public double getMetallPrice(String name) {
        double a = 0.0D;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "SELECT `price` FROM `metall_price` WHERE `name` = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                a = rs.getDouble(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

    public int getMetallProcent(String name) {
        int a = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            String query = "SELECT `procent` FROM `metall_price` WHERE `name` = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                a = rs.getInt(1);
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return a;
    }

    public void setMetallLog(MetallList ml) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());
            stmt.executeUpdate("INSERT INTO `metall_reg`(`id`,`data`,`code`,`name`, `price`,`size`,`summa`) VALUES ('0','" + sysdate + "','" + ml.getId() + "','" + ml.getName() + "'," + "'" + ml.getPrice() + "','" + ml.getSize() + "','" + ml.getSumma() + "');");

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public int getMetallID(String name) {
        int c = -1;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            String query = "SELECT `id` FROM `metall_price` WHERE `name` = '" + name + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                c = rs.getInt(1);
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    public void getBecapMetall(ObservableList<MetallList> data) {
        try {
            data.clear();
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());

            String query = "Select * FROM `metall_reg` where `data` ='" + sysdate + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int start = 1;
            while (rs.next()) {
                data.add(new MetallList(start++, rs.getInt(3), rs.getString(4), rs.getDouble(5), rs.getDouble(7), rs.getInt(6)));
            }
            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void getMetallOtchet(ObservableList<Collection.MetallOtchet> prod) {
        prod.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());

            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `id` ,`code`, `name` , `price`, SUM(`size`) AS sum_size, SUM(`summa`) AS sum_with FROM `metall_reg` WHERE `data` = '" + sysdate + "' GROUP BY `code`;";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            double sum_with = 0.0D;
            int index = 1;
            while (rs.next()) {
                prod.add(new Collection.MetallOtchet(index++, rs.getInt(2), rs.getString(3), rs.getDouble(4), rs.getDouble(6), rs.getInt(5)));
                sum_with += rs.getDouble(6);
            }

            prod.add(new Collection.MetallOtchet(sum_with));

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getHistoryMetall(ObservableList<HistoryMetallOtchet> prod, String d1, String d2, int typegroup, int typesort) {
        prod.clear();
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            String selectgroup = "";

            if (typegroup != -1) {
                selectgroup = "`code` = " + typegroup + " AND";
            }

            String query = "";

            if (typesort == 0) {
                query = "SELECT id,data,code,name,size,summa,price FROM `metall_reg` WHERE " + selectgroup + " `data` >= '" + d1 + "' and `data` <= '" + d2 + "';";
            }
            if (typesort == 1) {
                query = "SELECT id,data,code,name,SUM(size) as Size_sum,SUM(summa) AS Summa_sum,ROUND(avg(price),3) FROM `metall_reg` WHERE " + selectgroup + " `data` >= '" + d1 + "' and `data` <= '" + d2 + "' GROUP BY `data`;";
            }
            if (typesort == 2) {
                query = "SELECT id,data,code,name,SUM(size) as Size_sum,SUM(summa) AS Summa_sum,ROUND(avg(price),3) FROM `metall_reg` WHERE  `data` >= '" + d1 + "' and `data` <= '" + d2 + "' GROUP BY `code`;";
            }

            if (typesort == 3) {
                query = "SELECT `id`,`data`,`code`,`name`,SUM(`size`) as Size_sum,SUM(`summa`) AS Summa_sum,ROUND(avg(`price`),3) FROM `metall_reg` WHERE  `data` >= '" + d1 + "' and `data` <= '" + d2 + "' GROUP BY `data`,`code`;";
            }
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int index = 0;
            while (rs.next()) {
                prod.add(new HistoryMetallOtchet(index++, rs.getDate(2).toString(), rs.getString(4), rs.getInt(3), rs.getInt(5), rs.getDouble(7), rs.getDouble(6), "null"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getHistoryMetall(ObservableList<HistoryMetallOtchet> prod, int d) {
        prod.clear();
        try {
            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());

            String query = "SELECT id,data,code,name,size,summa,price FROM `metall_reg` WHERE `data` = '" + sysdate + "';";
            if (d == 7) {
                String[] mas = sysdate.toString().split("-");
                int year = Integer.parseInt(mas[0]);
                int month = Integer.parseInt(mas[1]);
                int day = Integer.parseInt(mas[2]);

                if (day > 7) {
                    day -= d;
                } else if (month != 1) {
                    day = 31 + day - d;
                    month--;
                } else {
                    year--;
                    month = 12;
                    day = 31 + day - d;
                }

                String nmonth = month < 10 ? "0" + month : Integer.toString(month);
                String nday = day < 10 ? "0" + day : Integer.toString(day);
                String nyear = Integer.toString(year) + "-" + nmonth + "-" + nday;

                query = "SELECT id,data,code,name,size,summa,price FROM `metall_reg` WHERE  `data` BETWEEN '" + nyear + "' AND '" + sysdate + "';";
            }
            if (d == 30) {
                String[] mas = sysdate.toString().split("-");
                int year = Integer.parseInt(mas[0]);
                int month = Integer.parseInt(mas[1]);
                int day = Integer.parseInt(mas[2]);

                if (month != 1) {
                    month--;
                } else {
                    year--;
                    month = 12;
                }

                String nmonth = month < 10 ? "0" + month : Integer.toString(month);
                String nday = day < 10 ? "0" + day : Integer.toString(day);
                String nyear = Integer.toString(year) + "-" + nmonth + "-" + nday;

                query = "SELECT id,data,code,name,size,summa,price FROM `metall_reg` WHERE `data` BETWEEN '" + nyear + "' AND '" + sysdate + "';";
            }

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(query);
            int index = 0;
            while (rs.next()) {
                prod.add(new HistoryMetallOtchet(index++, rs.getDate(2).toString(), rs.getString(4), rs.getInt(3), rs.getInt(5), rs.getDouble(7), rs.getDouble(6), "null"));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void getGraphicsSizeMetall(String a, String b, int idto, ObservableList<String> datashow, ObservableList<Integer> sizeshow) {
        datashow.clear();
        sizeshow.clear();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `data`, SUM(size) AS size_with FROM `metall_reg` WHERE `data` BETWEEN '" + a + "'AND '" + b + "' AND `code` = '" + idto + "'  GROUP BY `data`;");
            while (rs.next()) {
                datashow.add(rs.getString(1));
                sizeshow.add(Integer.valueOf(rs.getInt(2)));
            }

            rs.close();
            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
        }
    }

    public void getProductForFilter1(ObservableList<Prices> prod, int offnosize, int d, int group) {
        try {
            int index = 1;

            java.sql.Date sysdate = new java.sql.Date(new java.util.Date().getTime());

            String[] mas = sysdate.toString().split("-");
            int year = Integer.parseInt(mas[0]);
            int month = Integer.parseInt(mas[1]);
            int day = Integer.parseInt(mas[2]);

            if (day > d) {
                day -= d;
            } else if (day == d) {
                month--;
                day = 30;
            } else if (month * 31 + day > d) {
                int nm = month * 31 + day - d;
                month = nm / 31;
                if (month == 0) {
                    month = 1;
                }
                if (nm > 31) {
                    day = nm - month * 31;
                } else {
                    day = nm;
                }
            } else {
                int m = d / 31;
                int dn = d - m * 31;
                month = 12 + month - m;
                day = 31 - dn;
                year--;
            }

            String nmonth = month < 10 ? "0" + month : Integer.toString(month);
            String nday = day < 10 ? "0" + day : Integer.toString(day);
            String nyear = Integer.toString(year) + "-" + nmonth + "-" + nday;

            String offonsize = offnosize == 0 ? "`sell` = 0 " : "`sell` != 0 ";
            String qgroup = "`prais`.`group` = " + group + " AND";

            prod.clear();
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "SELECT `prais`.`id`,`prais`.`short_name`,`prais`.`group`,`prais`.`sell`,`prais`.`value`,`prais`.`actual_status` FROM `prais` WHERE " + qgroup + " " + offonsize + " AND `id` NOT IN ( SELECT `registration`.`idto` FROM `registration` WHERE `registration`.`data` >= '" + nyear + "');";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                prod.add(new Prices(rs.getInt(1), index++, rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getDouble(5), rs.getInt(6)));
            }

            rs.close();
            stmt.close();

            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setGroupStatus(ObservableList<Prices> prod, int status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String query = "";
            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            int n = prod.size();

            for (int i = 0; i < n; i++) {
                query = query + "UPDATE `solnce`.`prais` SET `actual_status` = '" + status + "' WHERE `prais`.`id` = '" + ((Prices) prod.get(i)).getId() + "';\n";
            }
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void setProductStatus(int id_product, int status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, properties);
            Statement stmt = connection.createStatement();

            String query = "UPDATE `solnce`.`prais` SET `actual_status` = '" + status + "' WHERE `prais`.`id` = '" + id_product + "';";
            stmt.executeUpdate(query);

            stmt.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getKeyUser(int id_user) {
        String key = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, properties);
            String query = "SELECT `flashkey` FROM `passworld` WHERE `id_user` = '" + id_user + "';";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                key = rs.getString(1);
            }
            rs.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error");
        }
        return key;
    }
}
