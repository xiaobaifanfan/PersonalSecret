package mode;

import java.util.HashSet;
import java.util.List;

public class Project {
public int pro_num;
public int dot_positon;
public HashSet<String> successors;
boolean operator;
public int getPro_num() {
	return pro_num;
}
public void setPro_num(int pro_num) {
	this.pro_num = pro_num;
}
public int getDot_positon() {
	return dot_positon;
}
public void setDot_positon(int dot_positon) {
	this.dot_positon = dot_positon;
}

public HashSet<String> getSuccessors() {
	return successors;
}
public void setSuccessors(HashSet<String> successors) {
	this.successors = successors;
}
public boolean isOperator() {
	return operator;
}
public void setOperator(boolean operator) {
	this.operator = operator;
}
public Project() {
	
}
public Project(int pro_num,int dot_position,HashSet<String> successors,boolean operator) {
	this.pro_num=pro_num;
	this.dot_positon=dot_position;
	this.operator=operator;
	this.successors=successors;
}

}
