package mode;

public class stack_node {
public int statuspro;
public Symbol token;
public attribute attr;

public int getStatuspro() {
	return statuspro;
}
public void setStatuspro(int statuspro) {
	this.statuspro = statuspro;
}

public Symbol getToken() {
	return token;
}
public void setToken(Symbol token) {
	this.token = token;
}
public attribute getAttr() {
	return attr;
}
public void setAttr(attribute attr) {
	this.attr = attr;
}
public stack_node() {
}
public stack_node(int statuspro,Symbol token,attribute attr)
{
	this.statuspro=statuspro;
	this.token=token;
	this.attr=attr;
}
}
