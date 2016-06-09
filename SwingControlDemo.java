import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SwingControlDemo {
	
	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

	public SwingControlDemo(){
		prepareGUI();
	}	

	private void prepareGUI(){
		mainFrame = new JFrame("Java SWING Examples");
		mainFrame.setSize(500,300);
		
		mainFrame.setLayout(new GridLayout(3, 1));
		
		headerLabel = new JLabel("",JLabel.CENTER );		
		headerLabel.setOpaque(true);
		headerLabel.setBackground(Color.green);
		statusLabel = new JLabel("",JLabel.CENTER);        
		statusLabel.setForeground(Color.RED);
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.YELLOW);

		statusLabel.setSize(350,100);
		mainFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent){
			        System.exit(0);
		    }        
		});    
		
		//Creation dun Control Panel
		controlPanel = new JPanel();
		//Assignation d'un Layout (FlowLayout)
		controlPanel.setLayout(new FlowLayout());

		//Ajout des composants au JFrame
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.add(statusLabel);
		
		//Pour centrer le JFrame a lecran
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width/2 - mainFrame.getWidth()/2, dim.height/2 - mainFrame.getHeight()/2);
		//Rendre le JFrame visible (pas visible par défaut)
		mainFrame.setVisible(true);  
	}	
	
	private void showEventDemo(){
		headerLabel.setText("Control in action: Button"); 

		JButton okButton = new JButton("OK");
		JButton submitButton = new JButton("Submit");
		JButton cancelButton = new JButton("Cancel");

		okButton.setActionCommand("OK");
		submitButton.setActionCommand("Submit");
		cancelButton.setActionCommand("Cancel");

		okButton.addActionListener(new ButtonClickListener()); 
		submitButton.addActionListener(new ButtonClickListener()); 
		cancelButton.addActionListener(new ButtonClickListener()); 

		controlPanel.add(okButton);
		controlPanel.add(submitButton);
		controlPanel.add(cancelButton);   
		controlPanel.addMouseListener(new CustomMouseListener());

		mainFrame.setVisible(true);  
	}

	private class ButtonClickListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();  
		    if (command.equals( "OK" ))  {
		    	statusLabel.setText("Ok Button clicked.");
		    }
		    else if( command.equals( "Submit" ) )  {
		    	statusLabel.setText("Submit Button clicked."); 
		    }
		    else  {
		    	statusLabel.setText("Cancel Button clicked.");
		    }  	
		}		
	}	
	
	private class CustomMouseListener implements MouseListener {
	      public void mouseClicked(MouseEvent e) {
	          statusLabel.setText("Mouse Clicked: ("+e.getX()+", "+e.getY() +")");
	       }

	       public void mousePressed(MouseEvent e) {
	       }

	       public void mouseReleased(MouseEvent e) {
	       }

	       public void mouseEntered(MouseEvent e) {
	       }

	       public void mouseExited(MouseEvent e) {
	       }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingControlDemo swingControlDemo = new SwingControlDemo();  
		swingControlDemo.showEventDemo();   
	}

}
