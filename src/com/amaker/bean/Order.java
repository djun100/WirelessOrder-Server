package com.amaker.bean;

public class Order {
	private String id;
	private String name;
	private String description;
	private String type;
	private String version;
	private String imgage_path;
	private String price;
	private String is_delete;
	private String update_at;
	private String create_at;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getImgage_path() {
		return imgage_path;
	}
	public void setImgage_path(String imgage_path) {
		this.imgage_path = imgage_path;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getIs_delete() {
		return is_delete;
	}
	public void setIs_delete(String is_delete) {
		this.is_delete = is_delete;
	}
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	public String getCreate_at() {
		return create_at;
	}
	public void setCreate_at(String create_at) {
		this.create_at = create_at;
	}
	public static void main(String[] a){
		int i = 1;
		while(i<32)
			System.out.print("\"" + i++ + "\"" + ",");
	}
}
