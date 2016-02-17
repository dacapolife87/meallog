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
	
	public int getidx() {
		return idx;
	}
	public Meal setidx(int idx) {
		this.idx = idx;
		return this;
	}
	public String getusername() {
		return username;
	}
	public Meal setusername(String user_name) {
		this.username = user_name;
		return this;
	}
	public String getname() {
		return name;
	}
	public Meal setname(String name) {
		this.name = name;
		return this;
	}
	public String getcategory() {
		return category;
	}
	public Meal setcategory(String category) {
		this.category = category;
		return this;
	}
	public String getcontent() {
		return content;
	}
	public Meal setcontent(String content) {
		this.content = content;
		return this;
	}
	public Date geteatdate() {
		return eatdate;
	}
	public Meal seteatdate(Date eatdate) {
		this.eatdate = eatdate;
		return this;
	}
	public boolean getshare() {
		return share;
	}
	public Meal setshare(boolean share) {
		this.share = share;
		return this;
	}
	public String getwheneat() {
		return wheneat;
	}
	public Meal setwheneat(String wheneat) {
		this.wheneat = wheneat;
		return this;
	}
	public String getpicpath() {
		return picpath;
	}
	public Meal setpicpath(String picpath) {
		this.picpath = picpath;
		return this;
	}
}
