package meallog.meal.vo;

import java.util.Date;



public class Meal {
	protected int 		idx;
	protected String 	username;
	protected String 	name;
	protected String 	category;
	protected String 	content;
	protected Date		eatdate;
	protected boolean 	share;
	protected String 	wheneat;
	protected String 	picpath;
	protected boolean	checkuser;
	
	public int getIDX() {
		return idx;
	}
	public Meal setIDX(int idx) {
		this.idx = idx;
		return this;
	}
	public String getUSERNAME() {
		return username;
	}
	public Meal setUSERNAME(String username) {
		this.username = username;
		return this;
	}
	public String getNAME() {
		return name;
	}
	public Meal setNAME(String name) {
		this.name = name;
		return this;
	}
	public String getCATEGORY() {
		return category;
	}
	public Meal setCATEGORY(String category) {
		this.category = category;
		return this;
	}
	public String getCONTENT() {
		return content;
	}
	public Meal setCONTENT(String content) {
		this.content = content;
		return this;
	}
	public Date getEATDATE() {
		return eatdate;
	}
	public Meal setEATDATE(Date eatdate) {
		this.eatdate = eatdate;
		return this;
	}
	public boolean getSHARE() {
		return share;
	}
	public Meal setSHARE(boolean share) {
		this.share = share;
		return this;
	}
	public String getWHENEAT() {
		return wheneat;
	}
	public Meal setWHENEAT(String wheneat) {
		this.wheneat = wheneat;
		return this;
	}
	public String getPICPATH() {
		return picpath;
	}
	public Meal setPICPATH(String picpath) {
		this.picpath = picpath;
		return this;
	}
	public boolean getcheckuser(){
		return checkuser;
	}
	public Meal setcheckuser(boolean checkuser){
		this.checkuser = checkuser;
		return this;
	}
	
}
