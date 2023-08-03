package com.dcd.landlinedecoder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class DataImportGeneral {

  FileInputStream fileInputStream = null;
  DataInputStream dataInputStream = null;
  StringTokenizer tokenizer = null;
  

  public DataImportGeneral() {
  } 
  
  protected DataInputStream readFile(String absoluteFilePath){
    try {
        fileInputStream = new FileInputStream(absoluteFilePath);
        dataInputStream = new DataInputStream(fileInputStream);
        return dataInputStream;
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
   }

   protected void closeFile(){
     try {
      dataInputStream.close();
      fileInputStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
   }
   
   
   protected boolean checkIfComentedLine(String line){
     if(line.contains("**")){
       return true;
     }
     else return false;
   }
   
   
   protected String getNextLine(){
     try {
       String line = dataInputStream.readLine();
       
       if(line!=null) line = testTrimLeadingAndTrailingSpacesOfString(line);
       return line;
     } catch (Exception e) {
        e.printStackTrace();        
     }
     return null;
   }
   
   protected void createTokenizer(String line, String delimiter){
     tokenizer = new StringTokenizer(line, delimiter);
   }

   protected String nextToken(){
     if(tokenizer.hasMoreTokens()){
       String str = tokenizer.nextToken();
       str = testTrimLeadingAndTrailingSpacesOfString(str);
       return str;
     }
     else return null;
   }
   
   private List<String> lstTokens = new  ArrayList<String>();
   protected List<String> readAllTokens(String line, String token){
     createTokenizer(line, token);
     lstTokens.clear();
     String region = null;
     while( (region = nextToken()) != null ){
       lstTokens.add(region);
     }
     return lstTokens;
   }
   
   protected String testTrimLeadingAndTrailingSpacesOfString(String str){
     return str.replaceAll("^\\s+|\\s+$", "");
   }
   
   
}
