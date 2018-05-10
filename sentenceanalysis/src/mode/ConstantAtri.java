package mode;

public class ConstantAtri extends attribute{
public int address;
public String name;
public String code;
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
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public ConstantAtri() {
}
public ConstantAtri(int address,String name,String code)
{
	this.address=address;
	this.code=code;
	this.name=name;
}
}
