package mode;

public class ConstantAtri extends attribute{
public int address;
public String name;
public int value;
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}
public int getAddress() {
	return address;
}
public void setAddress(int address) {
	this.address = address;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ConstantAtri() {
}
public ConstantAtri(int address,String name,int value)
{
	this.address=address;
	this.name=name;
	this.value=value;
}
}
