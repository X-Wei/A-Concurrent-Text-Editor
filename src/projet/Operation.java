package projet;
abstract class Operation {
	int uid;
	int ver;
	int pos;

	abstract void execute(StringBuffer text) throws Exception;// execution of operation
	abstract void executeMask(StringBuffer mask_text) throws Exception;//execution on the mask string 
	abstract Operation trans( Operation op2);// operational transformation (TTF)
	abstract void check(String model_str) throws Exception;//check expression (in String form)
	
	public static Operation strToExp(String exp) throws Exception {// parse string into operation
		int uid = -1, ver = -1, pos = -1, len = -1;
		String str = "";
		char c;
		String[] arguments;
		Operation op;
		if(exp.length()<=8)
			throw new Exception("Cannot recognize operation!");;
		
		switch(exp.substring(0, 6)){
		case "insert":
			arguments = exp.substring(7, exp.length() - 1).split(",");
			uid = Integer.parseInt(arguments[0]);
			ver = Integer.parseInt(arguments[1]);
			str = arguments[2];
			pos = Integer.parseInt(arguments[3]);
			op = new InsertOperation(uid, ver, str, pos);
			break;
		case "delete":
			arguments = exp.substring(7, exp.length() - 1).split(",");
			uid = Integer.parseInt(arguments[0]);
			ver = Integer.parseInt(arguments[1]);
			pos = Integer.parseInt(arguments[2]);
			len = Integer.parseInt(arguments[3]);
			op = new DeleteOperation(uid, ver, pos, len);
			break;
		case "update":
			arguments = exp.substring(7, exp.length() - 1).split(",");
			uid = Integer.parseInt(arguments[0]);
			ver = Integer.parseInt(arguments[1]);
			pos = Integer.parseInt(arguments[2]);
			c = arguments[3].charAt(0);
			op = new UpdateOperation(uid, ver, pos, c);
			break;
		default:
			throw new Exception("Cannot recognize operation!");
		}
		return op;
	}//strToExp()

	public static Operation ViewToModel(Operation op,String mask){
		int n = 1, j = 1;
		while (j <= mask.length() && (n < op.pos || mask.charAt(j - 1) == '1')) {
			if (mask.charAt(j - 1) == '0')
				n++;
			j++;
		}
		op.pos = j;//change to the right position
		return op;
	}
}//class operation

