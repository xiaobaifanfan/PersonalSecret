package mode;

import java.util.List;

public class Project {
public int pro_num;
public int dot_positon;
public List<String> successors;
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
public List<String> getSuccessors() {
	return successors;
}
public void setSuccessors(List<String> successors) {
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
public Project(int pro_num,int dot_position,List<String> successors,boolean operator) {
	this.pro_num=pro_num;
	this.dot_positon=dot_position;
	this.operator=operator;
	this.successors=successors;
}
public static void main(String[] args) {
	System.out.println("dddddddddd");
	return ;
}
}
