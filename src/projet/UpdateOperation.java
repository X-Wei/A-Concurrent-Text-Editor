package projet;

public class UpdateOperation extends Operation {
	char c;//new char to update

	UpdateOperation(int uid, int ver, int pos, char c) {
		this.uid = uid;
		this.ver = ver;
		this.pos = pos;
		this.c = c;
	}

	@Override
	void execute(StringBuffer text) throws Exception {
		System.out.println("pos=" + pos + ", length=" + text.length());
		if ((pos < 1) || (pos > text.length() + 1))
			throw new Exception("Invalid position parameter");
		text.setCharAt(pos-1, this.c);
//		return text;// text.substring(0, pos - 1) + this.c + text.substring(pos);
	}

	@Override
	void executeMask(StringBuffer mask_text) throws Exception {
		if ((pos < 1) || (pos > mask_text.length() + 1))
			throw new Exception("Invalid position parameter");
//		return mask_text.substring(0, pos - 1) + '0' + mask_text.substring(pos);//change mask into 0
//		return mask_text;//will not change mask string
	}

	@Override
	public String toString() {
		return "UPDATE(" + pos + "," + c + ")" + ",user=" + uid + ", ver=" + ver;
	}

	@Override
	public Operation trans(Operation op2) {
		int u1 = this.uid, u2 = op2.uid, v1 = this.ver, v2 = op2.ver;
		assert v1 == v2;

		switch (op2.getClass().getSimpleName()) {
		case "InsertOperation":
			if (this.pos >= op2.pos)
				return new UpdateOperation(u1, v1 + 1, this.pos + ((InsertOperation) op2).len, this.c);
			else
				return new UpdateOperation(u1, v1 + 1, this.pos, this.c);
		case "DeleteOperation":
				return new UpdateOperation(u1, v1 + 1, this.pos, this.c);
		case "UpdateOperation":
			if(this.pos!=op2.pos)
				return new UpdateOperation(u1, v1 + 1, this.pos, this.c);
			else//subject to the user with lower id when conflict
				return new UpdateOperation(u1, v1 + 1, this.pos, u1<u2?this.c:((UpdateOperation)op2).c); 
		default:
			System.out.println("Unrecognized operation!!");
		}
		return null;
	}//trans()

	@Override
	void check(String text) throws Exception {
		System.out.println("Checking operation:" + this);
		if (this.pos < 1 || this.pos > text.length())
			throw new Exception("Wrong position parameter!");
	}

}//class
