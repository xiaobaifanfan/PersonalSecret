package mode;

public class Symbol {
public int type;
public String name;
public int offset;
public int getType() {
	return type;
}
public void setType(int type) {
	this.type = type;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getOffset() {
	return offset;
}
public void setOffset(int offset) {
	this.offset = offset;
}
public Symbol() {
	
}
public Symbol(int type,String name,int offset)
{
	this.type=type;
	this.name=name;
	this.offset=offset;
}


}
