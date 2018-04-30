package mode;

public class StateTran {
	public int startstatus;
	public int endstatus;
	public String constring;

	public int getStartstatus() {
		return startstatus;
	}
	public void setStartstatus(int startstatus) {
		this.startstatus = startstatus;
	}
	public int getEndstatus() {
		return endstatus;
	}
	public void setEndstatus(int endstatus) {
		this.endstatus = endstatus;
	}
	public String getConstring() {
		return constring;
	}
	public void setConstring(String constring) {
		this.constring = constring;
	}
	public StateTran() {
}
	public StateTran(int startstatus,int endstatus,String constring) {
		this.startstatus=startstatus;
		this.endstatus=endstatus;
		this.constring=constring;
		
	}
}