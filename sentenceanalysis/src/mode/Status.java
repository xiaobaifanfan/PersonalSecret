package mode;

public class Status {
 public Project project;

public Project getProject() {
	return project;
}

public void setProject(Project project) {
	this.project = project;
}

public Status(Project project) {
	// TODO Auto-generated constructor stub
	this.project=project;
}


public Status() {
	// TODO Auto-generated constructor stub
}

public int hashCode(Project pro) {
	return pro.getPro_num()*pro.getDot_positon();
}

public boolean equals(Project p1,Project p2) {
if(p1.getDot_positon()==p2.getDot_positon()&&p1.getPro_num()==p2.getPro_num())
 return true;
return false;
}

}
