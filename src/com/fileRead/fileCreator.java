package com.fileRead;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.beans.fileBeanForDb;

public class fileCreator {

	private String fileTextCreator(List<fileBeanForDb> fbFDb, boolean org) {
		String str = "";
		  for(fileBeanForDb f:fbFDb) {
			  str = str+f.getLigne()+"\n"; 
			  str = str+f.getDu()+" --> "+f.getAu()+"\n";
			  if(org) {
				  if(f.getTexteOrg().length()>43) {
					  str = str+f.getTexteOrg().substring(0, 42)+"\n";
					  str = str+f.getTexteOrg().substring(42, f.getTexteOrg().length())+"\n";
				  }else {
					  str = str+f.getTexteOrg()+"\n";
				  }
			  }else{
				  if(f.getTexteTraduit().length()>43) {
					  str = str+f.getTexteTraduit().substring(0, 42)+"\n";
					  str = str+f.getTexteTraduit().substring(42, f.getTexteTraduit().length())+"\n";
				  }else {
					  str = str+f.getTexteTraduit()+"\n";
				  }
			  }
			  str = str+"\n";
		  }
		  str = str.substring(0, str.length()-1);
		return str;
	}
	
	public void fileGenerator(String path,List<fileBeanForDb> fbFDb, boolean org) throws IOException {
		String texte = fileTextCreator(fbFDb, org);
		File file = new File(path);

        // If file doesn't exists, then create it
        if (!file.exists()) file.createNewFile();

        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(texte);
        bw.close();
	}
	

	
}
