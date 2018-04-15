package mode;

public class Status {
private Project project;
private int  tip;
private boolean judge;
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
public boolean isJudge() {
	return judge;
}
public void setJudge(boolean judge) {
	this.judge = judge;
}
public Status() {
	
}
public Status(Project project,int tip,boolean judge) {
	this.judge=judge;
	this.project=project;
	this.tip=tip;
}
}
