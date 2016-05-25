import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.sql.ResultSet;

public class JDBCUpdateExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");

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
		JFrame mainFrame = new JFrame(" JDBC Update Example");

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres", "postgres",
					"Eltinto66");

			Statement orderSELECT = connection.createStatement();
			
			//Ordre SQL pour INSERT
			int nbLignes = orderSELECT.executeUpdate("INSERT INTO bp_articles (bpart_code, bpart_description, bpart_qte, bpart_pu) values ('C002','Coca Cola 75 cl',15, 6.90) ");
						
			JOptionPane.showMessageDialog(mainFrame, " Nb de lignes insérées : "+nbLignes);
			
			//Close ResultSet, Statement and connection
			orderSELECT.close();
			connection.close();
			
			
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}				

	}

}
