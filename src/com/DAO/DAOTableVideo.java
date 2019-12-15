package com.DAO;

import java.util.List;

import com.beans.videoBean;
import com.except.dbException;

public interface DAOTableVideo {

	public List<videoBean> findAll() throws dbException;
	public void insert(videoBean u) throws dbException;
	public boolean delete(videoBean u) throws dbException;
	public videoBean findById(String id) throws dbException;
	
}
