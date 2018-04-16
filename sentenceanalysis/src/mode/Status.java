package mode;

public class Status {
private Project project;
private int  tip;
private boolean judgeEq;
public boolean isJudgeEq() {
	return judgeEq;
}
public void setJudgeEq(boolean judgeEq) {
	this.judgeEq = judgeEq;
}
public Project getProject() {
	return project;
}
public void setProject(Project project) {
	this.project = project;
}
public int getTip() {
	return tip;
}
public void setTip(int tip) {
	this.tip = tip;
}


public Status() {
	
}
public Status(Project project,int tip,boolean judgeEq) {
	this.judgeEq=judgeEq;
	this.project=project;
	this.tip=tip;
}
}
