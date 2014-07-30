package projet;
public class DeleteOperation extends Operation {
	int len;

	DeleteOperation(int uid, int ver, int pos, int len) {
		this.uid = uid;
		this.ver = ver;
		this.pos = pos;
		this.len = len;
	}

	@Override
	void execute(StringBuffer text) throws Exception {
		assert len==1;//!!CAN only treat the len=1 cases!!, if len>1, have to change to several DEL operations==> look at Client.java
//		return text;
	}

	@Override
	void executeMask(StringBuffer mask_text) throws Exception {
		assert len==1;//!!CAN only treat the len=1 cases!!, if len>1, have to change to several DEL operations==> look at Client.java
//		return mask_text.substring(0, pos - 1) + "1" + mask_text.substring(pos);//or use StringBuildersetCharAt(4,'1')
		mask_text.setCharAt(pos-1, '1');
//		return mask_text;
	}//executeMask()

	@Override
	public String toString() {
		return "DELETE("+ pos + ", " + len + ")"+",user="+ uid + ", ver=" + ver;
	}

	@Override
	public Operation trans(Operation op2) {
		int u1 = this.uid, u2 = op2.uid, v1 = this.ver, v2 = op2.ver;
		assert v1 == v2;

		switch (op2.getClass().getSimpleName()) {
		case "InsertOperation":
			if (this.pos < op2.pos)
				return new DeleteOperation(u1, v1 + 1, this.pos, this.len);
			else
				return new DeleteOperation(u1, v1 + 1, this.pos + ((InsertOperation) op2).len, this.len);
		case "DeleteOperation":
			return new DeleteOperation(u1, v1 + 1, this.pos, this.len);
		case "UpdateOperation":
			return new DeleteOperation(u1, v1 + 1, this.pos, this.len);
		default:
			System.out.println("Unrecognized operation!!");
		}
		return null;
	}//trans()

	@Override
	void check(String text) throws Exception{
		System.out.println("Checking operation:"+this);
		if(this.pos<1 || this.pos+this.len-1>text.length())
			throw new Exception("Wrong position or length parameter!");
	}

}//class

