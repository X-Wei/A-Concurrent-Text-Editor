package projet;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Client extends Thread {
	int uid;
	String exp;
	String mask;
	String text;//model text
	int version;
	ClientJFrame clientGUI;

	Client(int id) {
		this.uid = id;
		this.exp = null;
		this.text = Server.getCurrentText();//retrieve the current version of text
		this.version = Server.getcurrentVersion();
		this.mask = Server.getMask();//retrieve the current mask
		clientGUI = new ClientJFrame();
		clientGUI.clientNbLabel.setText("client id=" + uid);
		clientGUI.setVisible(true);
	}

	@Override
	public void run() {
		try {
			clientGUI.textField.setText(text);
			clientGUI.versionLabel.setText("version=" + version);

			clientGUI.commitBtn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					commiteBtnClicked(evt);
				}
			});//commit button
			clientGUI.refreshBtn.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					updateGUI();
					clientGUI.infoLabel.setText("refreshed.");
					clientGUI.expTextField.setText("");
				}
			});//refresh button

		} catch (Exception e) {
			e.printStackTrace();
		}
	}//run()

	private void commiteBtnClicked(MouseEvent evt) {
		try {
			//parse operation
			exp = clientGUI.expTextField.getText();
			exp = exp.substring(0, 7) + uid + "," + version + "," + exp.substring(7);
			Operation op = Operation.strToExp(exp);//parse operation
			op.check(text);//check correctness of op 

			if (op instanceof DeleteOperation) {//FOR DEL OPERATION: change to a series of DELETE CHAR operation!!
				int len=((DeleteOperation)op).len;//ex. del(2,3) ==> del(2,1), del(2+1,1), del(2+2,1)
				for(int i=0;i<len;i++){
//				for(int i=len-1;i>=0;i--){
					DeleteOperation dop=new DeleteOperation(uid, version, op.pos+i, 1);
					dop = (DeleteOperation) Operation.ViewToModel(dop, mask);
					Server.treatExp(dop);//commit operation: OT & execution
				}
			} else {//other operations
				op = Operation.ViewToModel(op, mask); 
				Server.treatExp(op);//commit operation: OT & execution
			}

			//4. refresh information
			updateGUI();
			clientGUI.infoLabel.setText("operation commited.");
			clientGUI.expTextField.setText("");
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e);
			clientGUI.expTextField.setText("");
			clientGUI.infoLabel.setText("Wrong input:"+e.getMessage());//"Wrong input! Please input again!!!"
		}
	}//commiteBtnClicked()

	private void updateGUI() {
		text = Server.getCurrentText();//retrieve the current version
		mask = Server.getMask();
		version = Server.getcurrentVersion();
		clientGUI.textField.setText(text);
		clientGUI.versionLabel.setText("version=" + version);
	}//updateGUI()

}//class
