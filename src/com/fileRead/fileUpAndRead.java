package com.fileRead;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.Part;

import com.DAO.DAOTableVideo;
import com.beans.fileBean;
import com.beans.videoBean;
import com.codification.codification;
import com.except.dbException;
import com.except.descriptionException;
import com.except.fileException;

public class fileUpAndRead {

	public List<fileBean> ecrireFichier( Part part, String nomFichier, String chemin, int TAILLE_TAMP ) throws IOException, fileException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        String extention = nomFichier.substring(nomFichier.length()-4, nomFichier.length());
      
        if(!extention.equals(".srt")) {
        	throw new fileException("Veuillez choisir un fichier .srt");
         }else {
        	try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMP);
            System.out.println(chemin + nomFichier);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMP);
            
            byte[] tampon = new byte[TAILLE_TAMP];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
        	
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
            
        }
        }
      
        return strIntoList(readFile(chemin + nomFichier));
    }
    
    public static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    } 	
    
    private List<fileBean> strIntoList(String readFile){
    	List<fileBean> lstToTraduce = new ArrayList<fileBean>();
    	
    	Scanner scan = new Scanner(readFile);
    	
    	String texte = "";
    	String du = "";
    	String au = "";
    	int ligne = 0;
    	
		while(scan.hasNextLine()){
			
			String str_1 = scan.nextLine()+"";
			
				switch (lineType(str_1)) {
				case 0:
					texte= texte+" "+str_1;
					break;
				case 1:
					ligne = Integer.parseInt(str_1);
					break;
				case 3:
					du = duAu(str_1, true);
					au = duAu(str_1, false);
					break;

				default:
					fileBean flBn = new fileBean();
					if(ligne != 0) {
						flBn.setLigne(ligne);
						flBn.setDu(du);
						flBn.setAu(au);
						flBn.setTexteOrg(texte);
						lstToTraduce.add(flBn);
						texte = "";
					}
					break;
				}

			
		}
    	
    	return lstToTraduce;
    }
    
    private String readFile(String path) throws IOException{
         String cd = "";	
         BufferedReader bf = new BufferedReader(new InputStreamReader( new FileInputStream(path), "UTF-8"));
         
         String line = null;
	     	while ((line = bf.readLine()) != null) {
	     		cd = cd + line + "\n";
	     	}		
		return cd;
	}
    
    
    
    private int lineType(String str) {
    	
    	if(str.length()<1) {
    		// ligne vide
    		return 2;
    	}else if(str.contains("-->")){
    		// duré
    		return 3;
    	}else {
    		try {
    			Integer.parseInt(str);
    			// numéro de ligne
    			return 1;
    		} catch (Exception e) { }
    	}
    	//  text
    	return 0;
    }
    
    
    
    private String duAu(String str, boolean position) {
    	if(position) 
    		return str.substring(0, 12);
    	else 
    		return str.substring(17, str.length());
    }
	
    
    
    public void discriptionVerif(String desc, DAOTableVideo daoTabVideo) throws dbException, descriptionException {
    	
    	if(desc.equals("") || desc.isEmpty()) {
    		throw new descriptionException("veuillez remplir le Nom de fichier svp !!");
    	}else {
    		codification COD = new codification();
	    	videoBean vb = daoTabVideo.findById(COD.cd_prs(desc)); 
	    	String str;
	    	try {
	    		 str = vb.getId();
			} catch (Exception e) {
				str = "";
			}

	    		if(str != null) {
		    		throw new descriptionException("video déja existante");
		    	}
		
    	}
    	
    	
    	
    }
    
}
