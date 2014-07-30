package projet;
public class InsertOperation extends Operation {
	int len;
	String str;

	InsertOperation(int uid, int ver, String str, int pos) {
		this.uid = uid;
		this.ver = ver;
		this.str = str;
		this.pos = pos;
		this.len = str.length();
	}

	@Override
	void execute(StringBuffer text) throws Exception {
		if ((pos < 1) || (pos > text.length() + 1))
			throw new Exception("Invalid position parameter");
		text.insert(pos-1, str);
//		return text;//text.substring(0, pos - 1) + str + text.substring(pos - 1);
	}

	@Override
	void executeMask(StringBuffer mask_text) throws Exception {
		if ((pos < 1) || (pos > mask_text.length() + 1))
			throw new Exception("Invalid position parameter");
		StringBuffer zeros = new StringBuffer("");
		for (int i = 0; i < len; i++)
			zeros.append('0');
		mask_text.insert(pos-1, zeros);
//		return mask_text;//mask_text.substring(0, pos - 1) + zeros + mask_text.substring(pos - 1);
	}

	@Override
	public String toString() {
		return "INSERT(" + str + ", " + pos + ")"+",user="+ uid + ", ver=" + ver;
	}

	@Override
	public Operation trans(Operation op2) {
		int u1 = this.uid, u2 = op2.uid, v1 = this.ver, v2 = op2.ver;
		assert v1 == v2;

		switch (op2.getClass().getSimpleName()) {
		case "InsertOperation":
			if (this.pos < op2.pos)
				return new InsertOperation(u1, v1 + 1, this.str, this.pos);
			else if ((this.pos == op2.pos) && (u1 < u2))
				return new InsertOperation(u1, v1 + 1, this.str, this.pos);
			else
				return new InsertOperation(u1, v1 + 1, this.str, this.pos + ((InsertOperation)op2).len);
		case "DeleteOperation":
			return new InsertOperation(u1, v1 + 1, this.str, this.pos);
		case "UpdateOperation":
			return new InsertOperation(u1, v1 + 1, this.str, this.pos);
		default:
			System.out.println("Unrecognized operation!!");
		}
		return null;
	}//trans()

	@Override
	void check(String text) throws Exception {
		System.out.println("Checking operation:"+this);
		if(this.pos<1 || this.pos>text.length()+1)
			throw new Exception("Wrong position parameter!");
	}

}//class
