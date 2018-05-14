package mode;

public class delaration extends attribute {
public String addr;
public int width;
public String getAddr() {
	return addr;
}
public void setAddr(String addr) {
	this.addr = addr;
}
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public delaration() {
	
}
public delaration(int width,String addr) {
	this.width=width;
	this.addr=addr;
}
}
