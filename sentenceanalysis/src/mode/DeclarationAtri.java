package mode;

public class DeclarationAtri extends attribute {
public int width;
public String type;
public String name;
public int getWidth() {
	return width;
}
public void setWidth(int width) {
	this.width = width;
}
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public DeclarationAtri() {
}
public DeclarationAtri(int width,String name,String type) {
	this.width=width;
	this.name=name;
	this.type=type;
}
}
