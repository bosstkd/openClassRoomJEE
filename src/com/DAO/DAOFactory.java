package com.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOFactory {

	private String dataBase;
	private String username;
	private String password;
	
	
	
	public String getDataBase() {
		return dataBase;
	}



	public void setDataBase(String dataBase) {
		this.dataBase = dataBase;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public DAOFactory(String dataBase, String username, String password) {
		this.password = password;
		this.dataBase = dataBase;
		this.username = username;
	}
	
	public static DAOFactory getInstance(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/traduceropenclassroom", "root", "");

		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(dataBase, username, password);
		return  connexion;
	}
	
	public DAOTable getTraduceDAO() {
		return new traduceControllerDAO(this);
	}
	
	public DAOTableVideo getVideoDAO() {
		return new videoTabController(this);
	}
	
}
