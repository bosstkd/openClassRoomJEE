package com.src.srv;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.DAO.DAOFactory;
import com.DAO.DAOTable;
import com.DAO.DAOTableVideo;
import com.beans.fileBeanForDb;
import com.beans.videoBean;
import com.except.dbException;

import com.fileRead.fileCreator;

/**
 * Servlet implementation class downFileSrv
 */
@WebServlet("/downFileSrv")
public class downFileSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOTableVideo videoDAO;   
	private DAOTable traducedDAO; 	
	    
	// private static String FILE_PATH = "/WEB-INF/resources/";
	
	    public void init() throws ServletException {
	    	DAOFactory daoFact = DAOFactory.getInstance();
	    	this.videoDAO = daoFact.getVideoDAO();
	    	this.traducedDAO = daoFact.getTraduceDAO();
	    }

    public downFileSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<videoBean> lvb = new ArrayList<videoBean>();
		try {
			lvb = videoDAO.findAll();
			request.setAttribute("lvb", lvb);
		} catch (dbException e) {
			request.setAttribute("erreur", e.getMessage());
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/fileDown.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String selected = request.getParameter("selection");
		
		if(selected == null) {
			request.setAttribute("erreur", "Veuillez choisir une video svp !!");
		}else {
			  if (request.getParameter("download") != null) {

				  String orgStr = request.getParameter("org");
				 
				  boolean org = true;
				  if(orgStr == null) org = false;

				try {
					
					 List<fileBeanForDb> fbFDb = traducedDAO.findById(selected);
					 fileCreator fc = new fileCreator();
					 String FILE_PATH = new java.io.File( "." ).getCanonicalPath();
				        System.out.println("Current dir:"+FILE_PATH);
					
				        fc.fileGenerator(FILE_PATH+selected+".srt", fbFDb, org);
					
			         String fileType = "";
			         response.setContentType(fileType);
			         response.setHeader("Content-disposition","attachment; filename="+selected+".srt");


			         File my_file = new File(FILE_PATH+selected+".srt");
			         
			         // This should send the file to browser
			         OutputStream out = response.getOutputStream();
			         FileInputStream in = new FileInputStream(my_file);
			         byte[] buffer = new byte[4096];
			         int length;
			         while ((length = in.read(buffer)) > 0){
			            out.write(buffer, 0, length);
			         }
			         in.close();
			         out.flush();

					 
				} catch (dbException e) {
					request.setAttribute("erreur", e.getMessage());
				}
				  
				 
				  
			    } else if (request.getParameter("delete") != null) {
			    	
					try {
						videoBean vb = videoDAO.findById(selected);
						traducedDAO.delete(vb);
						videoDAO.delete(vb);
					} catch (dbException e) {
						request.setAttribute("erreur", e.getMessage());
					}
			    	
			    }
		}
		
		
		
		doGet(request, response);
	}

}
