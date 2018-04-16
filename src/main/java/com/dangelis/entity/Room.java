package com.dangelis.entity;

import java.util.List;

public class Room {
	private String _name;
	private String email;
	private List<Appointment>list;
	public String get_name() {
		return _name;
	}
	public void set_name(String _name) {
		this._name = _name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Appointment> getList() {
		return list;
	}
	public void setList(List<Appointment> list) {
		this.list = list;
	}
	public Room(String _name, String email, List<Appointment> list) {
		super();
		this._name = _name;
		this.email = email;
		this.list = list;
	}
	
	

}
