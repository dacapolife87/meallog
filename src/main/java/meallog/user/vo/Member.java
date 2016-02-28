package meallog.user.vo;

import java.util.Date;

public class Member {
	protected int 		idx;
	protected String 	email;
	protected String 	password;
	protected String 	nick; //username
	protected Date		joinDate;
	protected Date		modifiedDate;
	protected String	kakaoToken;
	public int getIdx() {
		return idx;
	}
	public Member setIdx(int idx) {
		this.idx = idx;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getNick() {
		return nick;
	}
	public Member setNick(String nick) {
		this.nick = nick;
		return this;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public Member setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
		return this;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
	public String getkakaoToken() {
		return kakaoToken;
	}
	public Member setkakaoToken(String kakaoToken) {
		this.kakaoToken = kakaoToken;
		return this;
	}
}
