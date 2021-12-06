package projekt2MR;

import java.sql.*;
import java.util.ArrayList;

public class DataBase {
	
	private Connection connection;
    private Statement statement;

    public DataBase() {

        try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);

            statement = connection.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS" +" pacjenci (Imie TEXT, Nazwisko TEXT, PESEL BIGINT)");
            statement.execute("CREATE TABLE IF NOT EXISTS" + " pacjentBadanie (PESEL BIGINT, data INTEGER, waga INTEGER, bmi INTEGER)");

            statement.close();
            connection.close();

        } catch (SQLException e){
            System.out.println("Cos poszlo nie tak w DataBase "+ e.getMessage());
        }
    }

    public void DodajPacjenta(String Imie, String Nazwisko, long PESEL) {

        try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "VALUES(" + "'"+Imie+"'" + "," + "'"+Nazwisko+"'" + "," + PESEL + ")";
            
            System.out.println(komenda);
            statement.execute("INSERT INTO pacjenci (Imie, Nazwisko, PESEL) " + komenda);
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Cos poszlo nie tak w DanePacjenta " + e.getMessage());
        }

    }
    
    public void UsunPacjenta(long Pesel) {
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "DELETE FROM pacjenci WHERE PESEL = " + Long.toString(Pesel);
            
            System.out.println(komenda);
            
            statement.execute(komenda);
            statement.close();
            connection.close();

        } catch (SQLException e) {
            System.out.println("Cos poszlo nie tak w UsunPacjenta " + e.getMessage());
        }

    }

    public void DodajBadanie(long Pesel, long data, int waga, int bmi) {

        try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "VALUES(" + Pesel + "," + data + "," + waga + ","+ bmi + ")";
            
            System.out.println(komenda);
            statement.execute("INSERT INTO pacjentBadanie (PESEL, data, waga, bmi)" + komenda);
            statement.close();
            connection.close();
            
        } catch (SQLException e) {
            System.out.println("Cos poszlo nie tak w DodajBadanie " + e.getMessage());
        }
    }
    
    public void UsunBadanie(long Pesel, long Waga)
    {
    	try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "DELETE FROM pacjentBadanie WHERE PESEL = " + Long.toString(Pesel) + " AND waga = " + Long.toString(Waga);
            
            System.out.println(komenda);
            statement.execute(komenda);
            statement.close();
            connection.close();
            
    	} catch (SQLException e) {
    		System.out.println("Coœ posz³o nie tak w UsunBadanie " + e.getMessage());
    	}
    	
    }
    
    public void UsunBadanie(long Pesel)
    {
    	try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "DELETE FROM pacjentBadanie WHERE PESEL = " + Long.toString(Pesel);
            
            System.out.println(komenda);
            statement.execute(komenda);
            statement.close();
            connection.close();
            
    	} catch (SQLException e) {
    		System.out.println("Coœ posz³o nie tak w UsunBadanie " + e.getMessage());
    	}
    	
    }
    
    public ArrayList<pacjent> pacjentLista() {
    		ArrayList<pacjent> ListaPacjentow = new ArrayList<>();
    		try {
                connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
                connection.setAutoCommit(true);
                statement = connection.createStatement();
                
                String komenda = "SELECT * FROM pacjenci";
                
                ResultSet rs = statement.executeQuery(komenda);
                pacjent pacj;
                while (rs.next()) {
                	pacj = new pacjent(rs.getString("Imie"), rs.getString("Nazwisko"), rs.getString("PESEL"));
                	ListaPacjentow.add(pacj);
                }
                
                
            } catch (SQLException e) {
                System.out.println("Cos poszlo nie tak w pacjentLista " + e.getMessage());
            }
    		return ListaPacjentow;
    }
    
    public ArrayList<badanie> pacjentBadanie(long Pesel) {
		ArrayList<badanie> ListaBadan = new ArrayList<>();
		try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = "SELECT data, waga, bmi FROM pacjentBadanie WHERE PESEL = " + Long.toString(Pesel);
            
            ResultSet rs = statement.executeQuery(komenda);
            badanie bad;
            while (rs.next()) {
            	bad = new badanie(rs.getDate("data"), rs.getString("waga"), rs.getString("bmi"));
            	ListaBadan.add(bad);
            }
            
        } catch (SQLException e) {
            System.out.println("Cos poszlo nie tak w pacjentBadanie " + e.getMessage());
        }
		return ListaBadan;
}
    
    public boolean SprawdzPesel(long Pesel) {
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Kasia\\eclipse-workspace\\projekt2MR\\src\\projekt2MR\\testjava.db");
            connection.setAutoCommit(true);
            statement = connection.createStatement();
            
            String komenda = " SELECT COUNT(PESEL) FROM pacjenci WHERE PESEL = " + Long.toString(Pesel);
            
            System.out.println(komenda);
            
            ResultSet rs = statement.executeQuery(komenda);
            
            int wart = rs.getInt(1);
            System.out.println(wart);
            if (wart == 1) {
            	statement.close();
                connection.close();
                rs.close();
                return true; //w bazie jest ju¿ taki pesel
            }
            else {
            	statement.close();
                connection.close();
                rs.close();
            }
           
        } catch (SQLException e) {
            System.out.println("Cos poszlo nie tak w SprawdzPesel " + e.getMessage());
        }
        return false;
    }
        
       
        
}
