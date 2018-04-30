package mode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Project {
public int pro_num;
public int dot_positon;
public HashSet<String> successors;

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



public Project() {
	
}
public Project(int pro_num,int dot_position,HashSet<String> successors) {
	this.pro_num=pro_num;
	this.dot_positon=dot_position;
	this.successors=successors;
}
public HashSet<String> getSuccessors() {
	return successors;
}
public void setSuccessors(HashSet<String> successors) {
	this.successors = successors;
}
}
