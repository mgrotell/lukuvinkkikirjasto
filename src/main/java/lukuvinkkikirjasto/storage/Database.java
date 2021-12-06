package lukuvinkkikirjasto.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private String databaseUrl;

    public Database(boolean test) {
        if (test) {
            this.databaseUrl = "jdbc:sqlite:test.db";
        } else {
            this.databaseUrl = "jdbc:sqlite:tip.db";
        }
        
    }
    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(this.databaseUrl);
            
        } catch (SQLException e) {
            System.out.println("Getting database connection failed" + e.getMessage());
        }
        return conn;
    }
    public void createTables(Connection db) {
        try {
            Statement s = db.createStatement();
            s.execute("CREATE TABLE IF NOT EXISTS tips ("+
                "id INTEGER PRIMARY KEY,"+
                "header TEXT,"+
                "description TEXT,"+
                "creator TEXT,"+
                "url url,"+
                "tags TEXT,"+
                "type TEXT,"+
                "courses TEXT,"+
                "comment TEXT);");

            s.close();
            db.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            
        }

    }


}
