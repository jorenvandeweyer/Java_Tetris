package tetris.highscores;

import java.sql.*;

public class Highscores {

    private static Connection connection ;

    /**
     * Make connection with highscore database
     * @return ranks in string form
     */
    public static String getHighscores() {
        
        try {     
            
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("waiting for connection...");
            connection = DriverManager.getConnection("jdbc:mysql://ip:port/database","username","password");
            
            System.out.println("waiting for statement...");
            Statement stmnt = connection.createStatement();
            String sql = "SELECT id, name, score FROM highscores ORDER BY score DESC LIMIT 5";
            ResultSet rs = stmnt.executeQuery(sql);
            
            String ranking = "";
            int rank = 1;
            while(rs.next()){
               int id  = rs.getInt("id");
               String name = rs.getString("name");
               int score = rs.getInt("score");

               System.out.print("ID: " + id);
               System.out.print(", name: " + name);
               System.out.print(", score: " + score);
               
               ranking = ranking + rank++ +".  "+ name + ": " + score + "\r\n";
            }
            
            
            rs.close();
            stmnt.close();
            connection.close();
            
            return ranking;
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC failed!");
            return "connection failed";
        } catch (SQLException ex) {
            System.out.println("Connection failed!");
            return "connection failed";
        }

    }
    
    /**
     * Get ranking compared with other players
     * @param nameme your name
     * @param points your points
     * @return your rank in the ranking
     */
    public static String getRanking(String nameme, int points){
        try {     
            
            Class.forName("com.mysql.jdbc.Driver");
            
            System.out.println("waiting for connection...");
            connection = DriverManager.getConnection("jdbc:mysql://ip:port/database","user","password");
            
            System.out.println("waiting for statement...");
            Statement stmnt = connection.createStatement();
            String sql = "SELECT name, score, FIND_IN_SET( score, (SELECT GROUP_CONCAT( score ORDER BY score DESC ) FROM highscores )) AS rank FROM highscores WHERE name =  '" + nameme+ "' AND score = '" + points + "' ";
            ResultSet rs = stmnt.executeQuery(sql);
            
            String ranking = "";
            
            while(rs.next()){
               int rank  = rs.getInt("rank");
               String name = rs.getString("name");
               int score = rs.getInt("score");

               System.out.print("#" + rank);
               System.out.print(", name: " + name);
               System.out.print(", score: " + score);
               
               ranking = ranking + "#" + rank + "   " + name + ": " + score + "\r\n";
            }
            
            
            rs.close();
            stmnt.close();
            connection.close();
            
            return ranking;
            
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC failed!");
            return "connection failed";
        } catch (SQLException ex) {
            System.out.println("Connection failed!");
            return "connection failed";
        }        
    }    

    /**
     * Make connection with ranking database and uploade score
     * @param name name
     * @param score score
     */    
    public static void setHighscores(String name, int score){
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("waiting for connection...");
            connection = DriverManager.getConnection("jdbc:mysql://ip:port/database","user","password");

            // the mysql insert statement
            String query = " insert into highscores (name, score)"
            + " values (?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = connection.prepareStatement(query);
            preparedStmt.setString (1, name);
            preparedStmt.setInt    (2, score);

            // execute the preparedstatement
            preparedStmt.execute();

            connection.close();
        }
        catch (Exception e)
        {
          System.err.println("Got an exception!");
          System.err.println(e.getMessage());
        }        
    }
}
