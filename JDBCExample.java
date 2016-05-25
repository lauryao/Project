import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.ResultSet;


import org.postgresql.ds.PGPoolingDataSource;

public class JDBCExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------- PostgreSQL JDBC Connection Testing ------------");

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}

		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			PGPoolingDataSource source = new PGPoolingDataSource();
			source.setDataSourceName("Ma source de données Postgresql");
			source.setServerName("localhost");
			source.setDatabaseName("postgres");
			source.setPortNumber(5432);
			source.setUser("postgres");
			source.setPassword("Eltinto"); 
			source.setMaxConnections(10);
			
			connection = source.getConnection();
			
			Statement orderSELECT = connection.createStatement();
			
			//
			
			JFrame mainFrame = new JFrame(" JDBC Update Example");
			
			ResultSet myResult = orderSELECT.executeQuery("SELECT bpart_code, bpart_description FROM bp_articles ");
			while  (myResult.next()) {
				String code = myResult.getString("bpart_code");
				String description = myResult.getString("bpart_description");
				
				JOptionPane.showMessageDialog(mainFrame, " Resultat => Code : " + code + " Description : "+description);
				
			}
			
			//Close ResultSet, Statement and connection
			myResult.close();
			orderSELECT.close();
			connection.close();
						
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}			

	}

}
