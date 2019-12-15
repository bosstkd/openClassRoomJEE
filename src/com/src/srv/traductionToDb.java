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
import com.beans.fileBean;

/**
 * Servlet implementation class traductionToDb
 * @param <fileBean>
 */
@WebServlet("/traductionToDb")
public class traductionToDb extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DAOTableVideo videoDAO;   
	private DAOTable traducedDAO; 	
	    
	    public void init() throws ServletException {
	    	DAOFactory daoFact = DAOFactory.getInstance();
	    	this.videoDAO = daoFact.getVideoDAO();
	    	this.traducedDAO = daoFact.getTraduceDAO();
	    }

	
    public traductionToDb() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		String videoID = request.getParameter("videoID");
		String str = request.getParameter("nl_1");
		List<fileBean> lfb = new ArrayList<fileBean>();
		try {
			Integer.parseInt(str);
			boolean getInfo = true;
			int i = 1;
			
			while (getInfo) {
				try {
					Integer.parseInt(request.getParameter("nl_"+i));
					fileBean fb = new fileBean();
					fb.setLigne(Integer.parseInt(request.getParameter("nl_"+i)));
					fb.setDu(request.getParameter("du_"+i));
					fb.setAu(request.getParameter("au_"+i));
					fb.setTexteOrg(request.getParameter("texteOrg_"+i));
					
					if(request.getParameter("trad_"+i).equals("")) {
						fb.setTexteTraduit("texte a traduire");
					}else {
						fb.setTexteTraduit(request.getParameter("trad_"+i));
					}
					
					
					lfb.add(fb);
				} catch (Exception e) {
					getInfo = false;
				}
				i++;
			}
			request.setAttribute("description", description);
			request.setAttribute("videoID", videoID);
			request.setAttribute("lfb", lfb);
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/traducedStat.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
