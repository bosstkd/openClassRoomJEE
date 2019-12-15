package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.videoBean;
import com.except.dbException;

public class videoTabController implements DAOTableVideo {

	private DAOFactory daoFactory;
	
	public videoTabController(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	

	@Override
	public void insert(videoBean u) throws dbException {

		String req = "insert into videotab(ID, designation) values ('"+u.getId()+"','"+u.getVideoName()+"')";
		System.out.println(req);
		PreparedStatement stm;
		try {
		
			stm = daoFactory.getConnection().prepareStatement(req);
			
			try {
				stm.execute(req);
			} catch (Exception e) {
				e.printStackTrace();
				 throw new dbException("Erreur d'insertion.");
			}
		} catch (SQLException e1) {
			   throw new dbException("Probleme de communication avec la base de données.");
		} 
	}

	@Override
	public boolean delete(videoBean u) throws dbException {
		String req = "delete from videotab where ID like '"+u.getId()+"' ";
		Statement stm;
		try {
			stm = daoFactory.getConnection().createStatement();
			try {
				stm.executeUpdate(req);
				return true;
			} catch (Exception e) {
				
				 throw new dbException("Erreur de suppression.");
			}
		} catch (SQLException e1) {
			   throw new dbException("Probleme de communication avec la base de données.");
		}
	}

	@Override
	public videoBean findById(String id) throws dbException {
		String req = "select ID, designation from videotab where ID like '"+id+"' ";
		videoBean vb = new videoBean();
		
		try {
			Statement stm = daoFactory.getConnection().createStatement();
			
			ResultSet rst = stm.executeQuery(req);
			System.out.println("req");
			while(rst.next()) {
				System.out.println(rst.getString(1));
				vb.setId(rst.getString(1));
				vb.setVideoName(rst.getString(2));
			}
			
			
		} catch (SQLException e) {
			throw new dbException("Erreur de communication avec la base de données");
		}
		
		return vb;
	}
	
	@Override
	public List<videoBean> findAll() throws dbException {
		String req = "select ID, designation from videotab ";
		List<videoBean> lvb = new ArrayList<videoBean>();
		try {
			Statement stm = daoFactory.getConnection().createStatement();
			ResultSet rst = stm.executeQuery(req);
			while(rst.next()) {
				videoBean vb = new videoBean();
				vb.setId(rst.getString(1));
				vb.setVideoName(rst.getString(2));
				lvb.add(vb);
			}
		} catch (SQLException e) {
			throw new dbException("Erreur de communication avec la base de données");
		}
		return lvb;
	}

	
}
