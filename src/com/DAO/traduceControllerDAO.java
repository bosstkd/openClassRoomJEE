package com.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.beans.fileBeanForDb;
import com.beans.videoBean;
import com.except.dbException;



public class traduceControllerDAO implements DAOTable{

	private DAOFactory daoFactory;

	public traduceControllerDAO(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public List<fileBeanForDb> findById(String id) throws dbException  {
		List<fileBeanForDb> u = new ArrayList<fileBeanForDb>();
		try {
			Statement stm = daoFactory.getConnection().createStatement();
			String req = "select ID, ligne, du, au, texteOrg, traduction from traductiontab where ID like '"+id+"' ";
			
			ResultSet rst = stm.executeQuery(req);
			
			while(rst.next()) {
				fileBeanForDb fb = new fileBeanForDb();
				fb.setId(rst.getString(1));
				fb.setLigne(rst.getInt(2));
				fb.setDu(rst.getString(3));
				fb.setAu(rst.getString(4));
				fb.setTexteOrg(rst.getString(5));
				fb.setTexteTraduit(rst.getString(6));
				u.add(fb);
			}
			
		} catch (SQLException e) {
			throw new dbException("Erreur de communication avec la base de données");
		}
		
		return u;
	}
	
	@Override
	public List<fileBeanForDb> findAll() throws dbException {
		List<fileBeanForDb> u = new ArrayList<fileBeanForDb>();
		try {
			Statement stm = daoFactory.getConnection().createStatement();
			String req = "select ID, ligne, du, au, texteOrg, traduction from traductiontab ";
			
			ResultSet rst = stm.executeQuery(req);
			
			while(rst.next()) {
				fileBeanForDb fb = new fileBeanForDb();
				fb.setId(rst.getString(1));
				fb.setLigne(rst.getInt(2));
				fb.setDu(rst.getString(3));
				fb.setAu(rst.getString(4));
				fb.setTexteOrg(rst.getString(5));
				fb.setTexteTraduit(rst.getString(6));
				u.add(fb);
			}
			
		} catch (SQLException e) {
			throw new dbException("Erreur de communication avec la base de données");
		}
		
		return u;
	}

	@Override
	public boolean insert(fileBeanForDb fb) throws dbException{
		String req = "insert into traductiontab(ID, ligne, du, au, texteOrg, traduction) values "
				+ "('"+fb.getId()+"', "+fb.getLigne()+", '"+fb.getDu()+"', '"+fb.getAu()+"', '"+fb.getTexteOrg()+"', '"+fb.getTexteTraduit()+"')";
		System.out.println(req);
		PreparedStatement stm;
		try {
			stm = daoFactory.getConnection().prepareStatement(req);
			try {
				stm.execute(req);
				return true;
			} catch (Exception e) {
				 throw new dbException("Erreur d'insertion.");
			}
		} catch (SQLException e1) {
			   throw new dbException("Probleme de communication avec la base de données.");
		}
	}





	@Override
	public boolean delete(videoBean u) throws dbException {
		String req = "delete from traductiontab where ID like '"+u.getId()+"' ";
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

	

}
