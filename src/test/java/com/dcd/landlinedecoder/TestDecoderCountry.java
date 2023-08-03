package com.dcd.landlinedecoder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TestDecoderCountry {

  DataInputStream dataInputStream;
  FileInputStream fileInputStream;
  StringTokenizer tokenizer = null;
  //public static final String workSpaceLocation = ShieldConstants.workSpaceLocation;
  
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
   
   protected String getNextLine(){
     try {
       return dataInputStream.readLine();
     } catch (Exception e) {
        e.printStackTrace();        
     }
     return null;
   }
   
   protected void createTokenizer(String line, String delimiter){
     tokenizer = new StringTokenizer(line, delimiter);
   }

   protected String nextToken(){
     if(tokenizer.hasMoreTokens()) return tokenizer.nextToken();
     else return null;
   }
   
   protected ArrayList<String> readAllTekoens(String line){
     createTokenizer(line, "/");
     String token = null;
     ArrayList<String> lstToken = new ArrayList<String>();
     while((token = nextToken()) != null ){
       lstToken.add(token);
     }
     return lstToken;
   }
  
   protected ArrayList<String> processNumberSeries(String iSDNumber, String areaCode , String strNumbers){
     createTokenizer(strNumbers, ",");
     ArrayList<String> lstCodes = new ArrayList<String>();
     String token = null;
     while((token = nextToken()) != null){
       if(token.contains("-")){
         int start = Integer.parseInt(token.substring(0, token.indexOf('-')));
         int end = Integer.parseInt(token.substring(token.indexOf('-')+1));
         for (int i = start; i <= end; i++) {
           lstCodes.add(iSDNumber+areaCode+i);
         }
         
       }
       else{
         lstCodes.add(iSDNumber+areaCode+token);
       }
     }
     return lstCodes;
     
   }//protected ArrayList<String> processNumberSeries(String iSDNumber, String areaCode , String strNumbers)
   
}
