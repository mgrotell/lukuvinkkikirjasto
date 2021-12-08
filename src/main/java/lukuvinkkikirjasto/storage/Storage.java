package lukuvinkkikirjasto.storage;

import lukuvinkkikirjasto.main.Tip;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Storage {


    private ArrayList<Tip> tips;
    private Database db;

    public Storage(boolean test) {
        this.tips = new ArrayList<>();
        try {
            this.db = new Database(test);
            this.db.createTables(this.db.getConnection());
        } catch (Exception e) {
            System.out.println(e.getMessage());

        }
    }

    public void addToStorage(Tip tip) {
        this.tips.add(tip);
        try {
            Connection connection = this.db.getConnection();
            PreparedStatement statement = connection.prepareStatement("INSERT INTO tips " +
                    "(header, description, creator, url, tags, type, courses, comment)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, tip.getHeader());
            statement.setString(2, tip.getDescription());
            statement.setString(3, tip.getCreator());
            statement.setString(4, tip.getUrl());
            statement.setString(5, tip.getTags());
            statement.setString(6, tip.getType());
            statement.setString(7, tip.getCourses());
            statement.setString(8, tip.getComment());
            statement.executeUpdate();
            connection.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTestDatabase() {
        try {
            Path pathToTestDatabase = FileSystems.getDefault().getPath("test.db");
            Files.delete(pathToTestDatabase);
        } catch (Exception e) {
            System.out.println("Test database was not deleted.");
        }
    }
    public void initializeTestDatabase() {
        this.db.createTables(this.db.getConnection());
    }

    public ArrayList<Tip> getStorage() {
        try {
            Connection connection = this.db.getConnection();
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT * FROM tips");

            return getTipsFromQuery(queryResult);

        } catch (SQLException e) {
            System.out.println(e.getSQLState());

        }

        return new ArrayList<Tip>();
    }

    public ArrayList<Tip> getTipsWithSearchTerm(String column, String term) {

        try {
            Connection connection = this.db.getConnection();
            String sql = "";
            if (column.equals("header")){
                sql = "SELECT * FROM tips WHERE header=?";
            } else if (column.equals("creator")){
                sql = "SELECT * FROM tips WHERE creator=?";
            } else if (column.equals("type")){
                sql = "SELECT * FROM tips WHERE type=?";
            } else if (column.equals("tags")){
                sql = "SELECT * FROM tips WHERE tags=?";
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, term);
            ResultSet queryResult = statement.executeQuery();
            return getTipsFromQuery(queryResult);



        } catch (SQLException e) {
            System.out.println(e);

        }

        return new ArrayList<Tip>();
    }
    public ArrayList<Tip> getTipsFromQuery(ResultSet queryResult) {
        ArrayList<Tip> tips = new ArrayList<>();
        try {
            while (queryResult.next()) {
                Tip tip = new Tip(
                        queryResult.getString("header"),
                        queryResult.getString("description"),
                        queryResult.getString("creator"),
                        queryResult.getString("url"),
                        queryResult.getString("type"),
                        queryResult.getString("tags"),
                        queryResult.getString("comment"),
                        queryResult.getString("courses")
                );
                tips.add(tip);

            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return tips;
    }
}