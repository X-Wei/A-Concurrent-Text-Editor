package projet;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class ServerJFrame extends javax.swing.JFrame {
	public JButton newClientBtn;
	public JTextArea logTextArea;
	public JLabel clientNbLabel;
	private JScrollPane jScrollPane2;
	private JScrollPane jScrollPane1;
	public JLabel jLabel3;
	public JTextArea historyTextArea;
	public JLabel versionLabel;
	public JLabel jLabel2;
	public JTextField currentTextField;
	public JLabel jLabel1;

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ServerJFrame inst = new ServerJFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public ServerJFrame() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setTitle("Server");
			{
				newClientBtn = new JButton();
				getContentPane().add(newClientBtn, "Center");
				newClientBtn.setText("New Client");
				newClientBtn.setBounds(225, 330, 147, 46);
				newClientBtn.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent evt) {
						newClientBtnMouseClicked(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Log of operations:");
				jLabel1.setBounds(12, 92, 173, 15);
			}
			{
				currentTextField = new JTextField();
				getContentPane().add(currentTextField);
				currentTextField.setBounds(147, 39, 205, 22);
				currentTextField.setEditable(false);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("current text:");
				jLabel2.setBounds(24, 42, 105, 15);
			}
			{
				versionLabel = new JLabel();
				getContentPane().add(versionLabel);
				versionLabel.setText("version=0");
				versionLabel.setBounds(24, 12, 139, 15);
			}
			{
				clientNbLabel = new JLabel();
				getContentPane().add(clientNbLabel);
				clientNbLabel.setText("0 clients");
				clientNbLabel.setBounds(148, 12, 154, 15);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("version history:");
				jLabel3.setBounds(197, 92, 155, 15);
			}
			{
				logTextArea = new JTextArea();
				logTextArea.setEditable(false);
				logTextArea.setBounds(12, 172, 163, 190);
//				logTextArea.setPreferredSize(new java.awt.Dimension(163, 65));
				jScrollPane1 = new JScrollPane(logTextArea);
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(12, 119, 166, 210);
			}
			
			{
				historyTextArea = new JTextArea();
				historyTextArea.setEditable(false);
//				getContentPane().add(historyTextArea);
				historyTextArea.setBounds(119, 230, 152, 190);
			}
			{
				jScrollPane2 = new JScrollPane(historyTextArea);
				getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(197, 119, 155, 205);
			}
			pack();
			this.setSize(383, 411);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}//initGUI

	private void newClientBtnMouseClicked(MouseEvent evt) {
//		System.out.println("commitBtn.mouseClicked, event="+evt);
		Server.newClient();
	}
}//class ServerJFrame
