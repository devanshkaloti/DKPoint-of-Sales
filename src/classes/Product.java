package classes;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author Devansh Kaloti
 * @version 1.0F
 *
 * Object for Product
 *
*/

public class Product extends DKHelpers{

    // Main properties
    public int id; public String description; public int category; public double price;
    public int qty = 0; public String imageUrl;

    public static final ArrayList<Product> PRODUCTS = new ArrayList<>(); // STATIC LIST OF ALL PRODUCTS, EVALUATION PURPOSES

    // Static Initializer, initialize all static products as used in evaluation purposes
    static {
        // Category 1, Starters
        Product p1 = new Product("Pizza Bread 1", 0, 4.00, "defaultPhotos/starters/1PizzaBread1.png");
        Product p2 = new Product("Pizza Bread 2", 0, 4.00, "defaultPhotos/starters/2PizzaBread2.png");
        Product p3 = new Product("Cut Potatoes", 0, 5.00, "defaultPhotos/starters/4FlatbreadPizza.png");
        Product p4 = new Product("Flat-bread Pizza", 0, 7.00, "defaultPhotos/starters/5OnionSoup.png");

        // Category 2, Main
        Product p5 = new Product("Cheese Lasagna", 1, 9.00, "defaultPhotos/main/1CheeseLasagna.png");
        Product p6 = new Product("Cheese Ravioli", 1, 9.00, "defaultPhotos/main/2CheeseRavioli.png");
        Product p7 = new Product("Vegetable Penne", 1, 8.00, "defaultPhotos/main/3VegetablePenne.png");
        Product p8 = new Product("Tacos", 1, 7.00, "defaultPhotos/main/4tacos.png");

        // Category 3, Salads
        Product p9 = new Product("Garden Salad", 2, 5.00, "defaultPhotos/salads/1gardensalad.png");
        Product p10 = new Product("Caesar Salad", 2, 5.00, "defaultPhotos/salads/2caesarsalad.png");
        Product p11 = new Product("Mediterranean Salad", 2, 6.00, "defaultPhotos/salads/3newsalad.png");

        // Category 5, Desserts
        Product p12 = new Product("Chocolate Mousse", 4, 9.00, "defaultPhotos/desserts/1cake.png");
        Product p13 = new Product("Apple Crisp", 4, 8.00, "defaultPhotos/desserts/2applecrisp.png");
        Product p14 = new Product("The Panookie", 4, 8.00, "defaultPhotos/desserts/3Panookie.png");
        Product p15 = new Product("Chocolate Brownie", 4, 6.00, "defaultPhotos/desserts/4brownie.png");
        Product p16 = new Product("Cheese Cake", 4, 9.00, "defaultPhotos/desserts/5cheesecake.png");
        Product p17 = new Product("Chocolate Explosion", 4, 9.00, "defaultPhotos/desserts/chocolateexplosion.png");

        // Category 4, Drinks
        Product p18 = new Product("Coke (HaHa No Water)", 3, 21.00, "defaultPhotos/drinks/coke.png");

        // Add to HashMap
        PRODUCTS.add(p1); PRODUCTS.add(p2); PRODUCTS.add(p3);PRODUCTS.add(p4);
        PRODUCTS.add(p5); PRODUCTS.add(p6); PRODUCTS.add(p7); PRODUCTS.add(p8);
        PRODUCTS.add(p9); PRODUCTS.add(p10); PRODUCTS.add(p11);
        PRODUCTS.add(p12); PRODUCTS.add(p13); PRODUCTS.add(p14); PRODUCTS.add(p15); PRODUCTS.add(p16); PRODUCTS.add(p17);
        PRODUCTS.add(p18);

    }


    /**
     * Constructor 1
     * @param id id of product
     * @param description Title of Product
     * @param category Category ID of product
     * @param price Price Double of product
     * @param image Image Url of product
     */
    public Product(int id, String description, int category, double price, String image) {
        this.id = id;
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageUrl = image;
    }

    /**
     * Constructor 2
     * @param description Title of product
     * @param category Category id of product
     * @param price Price Double of Product
     * @param image Image Url of Product
     */
    public Product(String description, int category, double price, String image) {
        this.category = category;
        this.description = description;
        this.price = price;
        this.imageUrl = image;
    }

    /**
     * Constructor 3
     * @param id Id of product
     */
    public Product(int id) {
        this.id = id;
    }

    /**
     * Create product in database, mySQL
     */
    public void create() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/custompos","java","java");

            Statement stmt=con.createStatement();
            stmt.executeUpdate(format(
                    "INSERT INTO products (description, category, price, image) VALUES ('%s', '%s', '%s', '%s');",
                    description, category, price, imageUrl));

            con.close();

        }catch(Exception e){ System.out.println(e); }

    }

    /**
     * Update product in database
     */
    public void update() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/custompos","java","java");

            Statement stmt=con.createStatement();
            stmt.executeUpdate(format(
                    "UPDATE products SET description = '%s', category = '%s', price = '%s', image = '%s' WHERE id = '%s'",
                    this.description, this.category, this.price, this.imageUrl, this.id));

            con.close();

        } catch(Exception e){ System.out.println(e); }
    }

    /**
     * Delete productin database
     */
    public void delete() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                     "jdbc:mysql://localhost:8889/custompos","java","java");

            Statement stmt=con.createStatement();
            stmt.executeUpdate(format("DELETE FROM products WHERE id = '%s'", this.id));

            con.close();

        }catch(Exception e){ System.out.println(e); }
    }


    /**
     * get details of product from ID, Static
     * @param id id of product
     * @return Product class
     */
    public Product detail(int id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/custompos","java","java");

            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery(format("SELECT * FROM products WHERE id = '%s'", id));

            if(rs.next()) {
                return new Product(
                        toInt(rs.getString("id")),
                        rs.getString("description"),
                        toInt(rs.getString("category")),
                        toDouble(rs.getString("price")),
                        rs.getString("image")
                );
            }
            con.close();

        }catch(Exception e){ System.out.println(e); }

        return null;
    }


//    public static Product detail(String description) {
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con= DriverManager.getConnection(
//                    "jdbc:mysql://localhost:8889/custompos","java","java");
//
//            Statement stmt=con.createStatement();
//            ResultSet rs = stmt.executeQuery(format("SELECT * FROM products WHERE description = '%s' LIMIT 1", description));
//
//            if(rs.next()) {
//                return new Product(
//                        toInt(rs.getString("id")),
//                        rs.getString("description"),
//                        toInt(rs.getString("category")),
//                        toDouble(rs.getString("price")),
//                        rs.getString("image")
//                );
//            }
//            con.close();
//
//        }catch(Exception e){ System.out.println(e); }
//
//        return null;
//    }

    /**
     * Get a list of all the products in the database
     * @return Arraylist containing all products
     */
    public static ArrayList<Product> getList() { // Return all products
        ArrayList<Product> data = new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:8889/custompos","java","java");

            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from products");

            while(rs.next()) {

                data.add(new Product(
                        toInt(rs.getString("id")),
                        rs.getString("description"),
                        toInt(rs.getString("category")),
                        toDouble(rs.getString("price")),
                        rs.getString("image")
                ));
            }
            con.close();

        }catch(Exception e){ System.out.println(e); }

        return data;
    }

    /**
     * Overriding default function: toString()
     * Used by ListView to display text of product
     * Made a useless toString to the most important toString :)
     * @return
     */
    @Override
    public String toString() {
        return format("%s x %s \t $%s", this.description, this.qty, (this.price * this.qty));
    }

}
