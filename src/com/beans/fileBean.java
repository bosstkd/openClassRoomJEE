package com.beans;

public class fileBean {

	private int ligne;
	private String du;
	private String au;
	private String texteOrg;
	private String texteTraduit;
	public fileBean() {
		this.texteOrg = "";
	}
	
	

	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		this.ligne = ligne;
	}
	public String getDu() {
		return du;
	}
	public void setDu(String du) {
		this.du = du;
	}
	public String getAu() {
		return au;
	}
	public void setAu(String au) {
		this.au = au;
	}
	public String getTexteOrg() {
		return texteOrg;
	}
	public void setTexteOrg(String texteOrg) {
		this.texteOrg = texteOrg;
	}
	public String getTexteTraduit() {
		return texteTraduit;
	}
	public void setTexteTraduit(String texteTraduit) {
		this.texteTraduit = texteTraduit;
	}

	
	
}
