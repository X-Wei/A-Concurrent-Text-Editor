package projet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Server {

	private static StringBuffer currentText = new StringBuffer("ABC");
	private static StringBuffer mask = new StringBuffer("");
	private static ArrayList<Operation> operations_log = new ArrayList<Operation>();// operations performed
	private static LinkedList<String> opin = readFile("operations.txt");
	private static Map<Integer, Integer> userVersion = new HashMap<Integer, Integer>();// latest version of each user
	private static int currentVersion = 0;
	private static int clientNb = 0;
	private static ServerJFrame serverGUI = new ServerJFrame();

////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		for (int i = 0; i < currentText.length(); i++)
//			mask += "0";//initialize mask
			mask.append('0');
		System.out.println("Original text: " + currentText);
		serverGUI.setVisible(true);
		updateGUI();
	}// end of main()
////////////////////////////////////////////////////////////////////////////


	public static synchronized void treatExp(Operation op) throws Exception {
		// 1. treat user id / user version
		if (!userVersion.containsKey(op.uid))
			userVersion.put(op.uid, op.ver);
		else if (userVersion.get(op.uid) > op.ver)
			throw new Exception("Decreasing version numbers");
		else
			userVersion.put(op.uid, op.ver);
		
		//2. operational transformations 
		for (int i = op.ver; i < operations_log.size(); i++)
			op = op.trans(operations_log.get(i));

		//3. execute the transformed operation
		operations_log.add(op);
		op.execute(currentText);//update text
		op.executeMask(mask);//update mask
		currentVersion++;

		// 4. print current status (current text + current log)
		System.out.println("\n******");
		System.out.println("Version " + currentVersion + ", Text: " + getCurrentText());
		System.out.println(currentText);
		System.out.println(mask);
		System.out.println("log:");
		for (Operation opi : operations_log)
			System.out.println(opi);
		updateGUI();
	}//treatExp()

	public static synchronized void updateGUI() {
		serverGUI.currentTextField.setText(getCurrentText());
		serverGUI.versionLabel.setText("version=" + currentVersion);
		serverGUI.clientNbLabel.setText(clientNb + " clients");
		String log = "";
		for (Operation opi : operations_log)
			log += opi.toString() + '\n';
		serverGUI.logTextArea.setText(log);
		serverGUI.historyTextArea.append(getCurrentText() + ", version=" + currentVersion + '\n');// save
	}
	
	public static void writeStrToFile(String str, String filePath) {
		try {
			File file = new File(filePath);
			PrintStream ps = new PrintStream(new FileOutputStream(file));
			ps.println(str);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}// writeStrToFile()

	public static LinkedList<String> readFile(String filePath) {
		LinkedList<String> res = new LinkedList<String>();
		try {
			BufferedReader in = new BufferedReader(new FileReader(filePath));
			String line = null;
			while ((line = in.readLine()) != null)
				res.add(line);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	} // readFile()

	public synchronized static String getCurrentText() {// give current text (AFTER MASK...)
		String result = "";
		for (int i = 0; i < currentText.length(); i++) {
			if (mask.charAt(i) == '0') {
				result += currentText.charAt(i);
			}
		}
		return result;
	}//getCurrentText()

	public synchronized static int getcurrentVersion() {
		return currentVersion;
	}

	public synchronized static String getMask() {
		return mask.toString();
	}

	public static synchronized void newClient() {
		Client c = new Client(++clientNb);
		c.start();
		serverGUI.clientNbLabel.setText(clientNb + " clients");
	}

}// class Server

