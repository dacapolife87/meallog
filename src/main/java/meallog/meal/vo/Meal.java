package meallog.meal.vo;

import java.util.Date;

public class Meal {
	protected int 		idx;
	protected int 		user_idx;
	protected String 	user_name;
	protected String 	name;
	protected String 	category;
	protected String 	content;
	protected Date		eatdate;
	protected boolean 	bshare;
	protected String 	wheneat;
	protected String 	picpath;
	
	public int getIdx() {
		return idx;
	}
	public Meal setIdx(int idx) {
		this.idx = idx;
		return this;
	}
	public int getUserIdx() {
		return user_idx;
	}
	public Meal setUserIdx(int user_idx) {
		this.user_idx = user_idx;
		return this;
	}
	public String getUserName() {
		return user_name;
	}
	public Meal setUserName(String user_name) {
		this.user_name = user_name;
		return this;
	}
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
		return eatdate;
	}
	public Meal setEatDate(Date eatdate) {
		this.eatdate = eatdate;
		return this;
	}
	public boolean getShare() {
		return bshare;
	}
	public Meal setShare(boolean bshare) {
		this.bshare = bshare;
		return this;
	}
	public String getWhenEat() {
		return wheneat;
	}
	public Meal setWhenEat(String wheneat) {
		this.wheneat = wheneat;
		return this;
	}
	public String getPicPath() {
		return picpath;
	}
	public Meal setPicPath(String picpath) {
		this.picpath = picpath;
		return this;
	}
}
