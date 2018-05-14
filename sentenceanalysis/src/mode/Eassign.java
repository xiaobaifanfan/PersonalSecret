package mode;

public class Eassign extends attribute {
	public String addr;
	public String idname;
	public int offset;
	public String type;
	public int width;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getIdname() {
		return idname;
	}
	public void setIdname(String idname) {
		this.idname = idname;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public Eassign() {
		
	}
	public Eassign(String addr,String name,int offset,int width,String type) {
		this.addr=addr;
		this.idname=idname;
		this.width=width;
		this.offset=offset;
		this.type=type;
	}
}
