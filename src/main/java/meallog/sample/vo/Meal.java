package meallog.sample.vo;

import java.util.Date;

public class Meal {
	protected String 	name;
	protected String 	category;
	protected String 	content;
	protected Date		eatDate;
	protected int 		whenEat;
	protected boolean 	share;
	protected String 	picPath;
	
	public String getName() {
		return name;
	}
	public Meal setName(String name) {
		this.name = name;
		return this;
	}
	public String getCategory() {
		return category;
	}
	public Meal setCategory(String category) {
		this.category = category;
		return this;
	}
	public String getContent() {
		return content;
	}
	public Meal setContent(String content) {
		this.content = content;
		return this;
	}
	public Date getEatDate() {
		return eatDate;
	}
	public Meal setEatDate(Date eatDate) {
		this.eatDate = eatDate;
		return this;
	}
	public int getWhenEat() {
		return whenEat;
	}
	public Meal setWhenEat(int whenEat) {
		this.whenEat = whenEat;
		return this;
	}
	public boolean getShare() {
		return share;
	}
	public Meal setShare(boolean share) {
		this.share = share;
		return this;
	}
	public String getPicPath() {
		return picPath;
	}
	public Meal setPicPath(String picPath) {
		this.picPath = picPath;
		return this;
	}
}
