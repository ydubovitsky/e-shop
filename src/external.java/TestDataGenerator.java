import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestDataGenerator {

    private static final String url = "jdbc:postgresql://localhost:5433/eshop";
    private static final String user = "postgres";
    private static final String password = "root";

    private static Connection con;
    private static PreparedStatement stmt;

    public static void main(String args[]) {

        try {
            con = DriverManager.getConnection(url, user, password);
            //addCategory();
            //addProducer();
            addProduct();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                stmt.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    private static void addCategory() {
        try {
            stmt = con.prepareStatement("INSERT INTO category (name, url, product_count) Values (?, ?, ?)");
            for (Category c : Category.values()) {
                // Заполняем параметры запроса
                stmt.setString(1, c.name());
                stmt.setString(2, c.getUrl());
                stmt.setInt(3, c.getCount());
                // Запрос не выполняется, а укладывается в буфер,
                //  который потом выполняется сразу для всех команд
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProducer() {
        try {
            stmt = con.prepareStatement("INSERT INTO producer (name, url, product_count) Values (?, ?, ?)");
            for (Producer c : Producer.values()) {
                // Заполняем параметры запроса
                stmt.setString(1, c.name());
                stmt.setString(2, c.getUrl());
                stmt.setInt(3, c.getCount());
                // Запрос не выполняется, а укладывается в буфер,
                //  который потом выполняется сразу для всех команд
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct() {
        try {
            stmt = con.prepareStatement("INSERT INTO product (name, description, image_link, price, id_category, id_producer) Values (?, ?, ?, ?, ?, ?)");
            PreparedStatement stmProducer = con.prepareStatement(
                    "SELECT id FROM producer");
            ResultSet resultSet = stmProducer.executeQuery();
            List<Integer> ids = new ArrayList<>();
            while (resultSet.next()) {
                ids.add(Integer.valueOf(resultSet.getString("id")));
            }

            PreparedStatement stmCategory = con.prepareStatement(
                    "SELECT id FROM category");
            ResultSet categ = stmProducer.executeQuery();
            List<Integer> idsc = new ArrayList<>();
            while (categ.next()) {
                idsc.add(Integer.valueOf(categ.getString("id")));
            }
            for (Product c : Product.values()) {
                // Заполняем параметры запроса
                stmt.setString(1, c.getName());
                stmt.setString(2, c.getDescription());
                stmt.setString(3, c.getImageLink());
                stmt.setDouble(4, c.getPrice());
                stmt.setInt(5, ids.get(new Random().nextInt(ids.size())));
                stmt.setInt(6, ids.get(new Random().nextInt(idsc.size())));
                // Запрос не выполняется, а укладывается в буфер,
                //  который потом выполняется сразу для всех команд
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
