package com.src.srv;

import java.io.IOException;
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

/**
 * Servlet implementation class toDbSend
 */
@WebServlet("/toDbSend")
public class toDbSend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private DAOTableVideo videoDAO;   
	private DAOTable traducedDAO; 	
	    
	    public void init() throws ServletException {
	    	DAOFactory daoFact = DAOFactory.getInstance();
	    	this.videoDAO = daoFact.getVideoDAO();
	    	this.traducedDAO = daoFact.getTraduceDAO();
	    }
	    
    public toDbSend() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean err = false;		
		String id = request.getParameter("videoID");
		videoBean vb = new videoBean();
		vb.setId(id);
		vb.setVideoName(request.getParameter("description"));
		try {
			videoDAO.insert(vb);
			
			
			String str = request.getParameter("nl_1");
			try {
				Integer.parseInt(str);
				boolean getInfo = true;
				int i = 1;
				List<fileBeanForDb> fbfDB = new ArrayList<fileBeanForDb>();
				
				while (getInfo) {
					try {
						Integer.parseInt(request.getParameter("nl_"+i));
						fileBeanForDb fb = new fileBeanForDb();
						fb.setId(id);
						fb.setLigne(Integer.parseInt(request.getParameter("nl_"+i)));
						fb.setDu(request.getParameter("du_"+i));
						fb.setAu(request.getParameter("au_"+i));
						fb.setTexteOrg(request.getParameter("texteOrg_"+i).replaceAll("'", "''"));
						fb.setTexteTraduit(request.getParameter("trad_"+i).replaceAll("'", "''"));
						fbfDB.add(fb);						
					} catch (Exception e) {
						getInfo = false;
					}
					i++;
				}
				
				
				
				
				for(fileBeanForDb fb : fbfDB) 
					if(fb != null) {
						try {
							traducedDAO.insert(fb);
						} catch (dbException e) {
							err = true;
							request.setAttribute("erreur", e.getMessage());
						}
					}
				
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		} catch (dbException e1) {
			err = true;
			request.setAttribute("erreur", e1.getMessage());
		}
		
		if(err) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/ErreurPage.jsp").forward(request, response);
		}else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/success.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
