package mode;

public class DiscriminantAtri {
public String name;
public int truelist;
public int falselist;
public int truequad;
public int falsequad;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getTruelist() {
	return truelist;
}
public void setTruelist(int truelist) {
	this.truelist = truelist;
}
public int getFalselist() {
	return falselist;
}
public void setFalselist(int falselist) {
	this.falselist = falselist;
}
public int getTruequad() {
	return truequad;
}
public void setTruequad(int truequad) {
	this.truequad = truequad;
}
public int getFalsequad() {
	return falsequad;
}
public void setFalsequad(int falsequad) {
	this.falsequad = falsequad;
}
public DiscriminantAtri() {
}
public DiscriminantAtri(String name,int truelist,int falselist,int truequad,int falsequad) {
	this.name=name;
	this.truelist=truelist;
	this.falselist=falselist;
	this.truequad=truequad;
	this.falsequad=falsequad;
	
}
}
