package mode;

import java.util.Vector;

public class Grammer {
public String left;
public Vector right;
public String getLeft() {
	return left;
}
public void setLeft(String left) {
	this.left = left;
}
public Vector getRight() {
	return right;
}
public void setRight(Vector right) {
	this.right = right;
}
public Grammer() {
	
}
public Grammer(String left,Vector right) {
	this.right=right;
	this.left=left;
}
}
