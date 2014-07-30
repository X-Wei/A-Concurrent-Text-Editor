package projet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ClientJFrame extends javax.swing.JFrame {
	 public JTextField textField;
	 public JLabel versionLabel;
	 public JTextField expTextField;
	 public JLabel jLabel2;
	 public JLabel clientNbLabel;
	 public JButton refreshBtn;
	 public JLabel infoLabel;
	 public JLabel jLabel1;
	 public JButton commitBtn;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ClientJFrame inst = new ClientJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public ClientJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Client");
			setSize(392, 194);
			{
				textField = new JTextField();
				getContentPane().add(textField);
				textField.setBounds(23, 44, 334, 22);
				textField.setEditable(false);
			}
			{
				versionLabel = new JLabel();
				getContentPane().add(versionLabel);
				versionLabel.setText("version=0");
				versionLabel.setBounds(23, 10, 121, 15);
			}
			{
				expTextField = new JTextField();
				getContentPane().add(expTextField);
				expTextField.setBounds(23, 105, 241, 22);
			}
			{
				commitBtn = new JButton();
				getContentPane().add(commitBtn);
				commitBtn.setText("commit");
				commitBtn.setBounds(270, 105, 87, 22);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("text:");
				jLabel1.setBounds(23, 29, 53, 15);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("operation:");
				jLabel2.setBounds(23, 85, 212, 15);
			}
			{
				clientNbLabel = new JLabel();
				getContentPane().add(clientNbLabel);
				clientNbLabel.setText("client number=0");
				clientNbLabel.setBounds(196, 10, 161, 15);
			}
			{
				refreshBtn = new JButton();
				getContentPane().add(refreshBtn);
				refreshBtn.setText("refresh");
				refreshBtn.setBounds(270, 132, 87, 22);
			}
			{
				infoLabel = new JLabel();
				getContentPane().add(infoLabel);
				infoLabel.setBounds(23, 159, 331, 15);
			}
			pack();
			this.setSize(376, 215);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	

}
