package com.src.srv;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.DAO.DAOFactory;
import com.DAO.DAOTableVideo;
import com.beans.fileBean;
import com.codification.codification;
import com.except.dbException;
import com.except.descriptionException;
import com.except.fileException;
import com.fileRead.fileUpAndRead;

/**
 * Servlet implementation class upFileSrv
 */
@WebServlet("/upFileSrv")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024 * 1,  // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15, // 15 MB
        location            = "C:/Uploads"
)
public class upFileSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static final int TAILLE_TAMP = 10240;
   // public static final String FILE_PATH = "/WEB-INF/resources/";
    private DAOTableVideo videoDAO;   
	
    
    public void init() throws ServletException {
    	DAOFactory daoFact = DAOFactory.getInstance();
    	this.videoDAO = daoFact.getVideoDAO();
    }
    
    public upFileSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/fileUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description"); 
		
		try {
			fileUpAndRead ff = new fileUpAndRead();
			ff.discriptionVerif(description, videoDAO);
			
//-------------------------------------------------			
			request.setAttribute("description", description);
			codification COD = new codification();
			
			request.setAttribute("videoID", COD.cd_prs(description));
			
			fileUpAndRead fl = new fileUpAndRead();
			
			  // On récupère le champ du fichier
	        Part part = request.getPart("fichier");
	            
	        // On vérifie qu'on a bien reçu un fichier
	        String nomFichier = fl.getNomFichier(part);
	
	        // Si on a bien un fichier
	        if (nomFichier != null && !nomFichier.isEmpty()) {
	            String nomChamp = part.getName();
	            // Corrige un bug du fonctionnement d'Internet Explorer
	             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
	                    .substring(nomFichier.lastIndexOf('\\') + 1);
	            // On écrit définitivement le fichier sur le disque
	            try {
	            	 String FILE_PATH = new java.io.File( "." ).getCanonicalPath();
				        System.out.println("Current dir:"+FILE_PATH);
	            	List<fileBean> lfb = fl.ecrireFichier(part, nomFichier, FILE_PATH, TAILLE_TAMP);
	            	
	            	request.setAttribute("listToTraduce", lfb);
	            	request.setAttribute(nomChamp, nomFichier);
	            	
				} catch (fileException e) {
					request.setAttribute("erreur", e.getMessage());
				}	          
	        }
//-------------------------------			
			
		} catch (dbException | descriptionException e) {
			request.setAttribute("erreur", e.getMessage());
		}

		
		doGet(request, response);
	}
	
	
	

	

}
