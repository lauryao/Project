import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

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
			source.setPassword("Eltinto66"); 
			source.setMaxConnections(10);
			
			connection = source.getConnection();
			
			Statement orderSELECT = connection.createStatement();
			
			//
			
			JFrame mainFrame = new JFrame(" JDBC Update Example");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			Object[][] data = new Object[0][3];
			String[] columnNames = {"Article code",
                    "Article description",
                    "quantite"};

			ResultSet myResult = orderSELECT.executeQuery("SELECT bpart_code, bpart_description, bpart_qte FROM bp_articles ");
			int r=0;
			JTable table = new JTable(new DefaultTableModel(data, columnNames));
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			while  (myResult.next() ) {
				
				String code = myResult.getString("bpart_code");
				String description = myResult.getString("bpart_description");
				Integer quantite = myResult.getInt("bpart_qte");
								 
				model.addRow(new Object[]{code, description, quantite});

				//JOptionPane.showMessageDialog(mainFrame, " Resultat => Code : " + code + " Description : "+description);
				
			}
						
			table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		    table.setFillsViewportHeight(true);
		    
		    //Create the scroll pane and add the table to it.
	        JScrollPane scrollPane = new JScrollPane(table);
	        
	        JPanel newContentPane = new JPanel();
	        newContentPane.add(scrollPane);
	        
	        newContentPane.setOpaque(true); //content panes must be opaque
	        mainFrame.setContentPane(newContentPane);
	        
	        //Display the window.
	        mainFrame.pack();
	        mainFrame.setVisible(true);
	        
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
