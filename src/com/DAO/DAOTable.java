package com.DAO;

import java.util.List;

import com.beans.fileBeanForDb;
import com.beans.videoBean;
import com.except.dbException;


public interface DAOTable {

	public List<fileBeanForDb> findAll() throws dbException;
	public boolean insert(fileBeanForDb u) throws dbException;
	public boolean delete(videoBean u) throws dbException;
	public List<fileBeanForDb> findById(String id) throws dbException;
	
}
