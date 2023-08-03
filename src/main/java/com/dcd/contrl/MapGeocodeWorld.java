package com.dcd.contrl;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dcd.ShieldConstants;
import com.dcd.entity.GeoCordinates;
import com.dcd.vo.RequestObject;

/**
 * 
 * @author Neo
 *
 */
@Repository
public class MapGeocodeWorld extends HashMap<String, GeoCordinates>{


  FileInputStream fileInputStream = null;
  DataInputStream dataInputStream = null;
  StringTokenizer tokenizer = null;
  
  MapGeocodeIndia mapGeocodeIndia;
  
  
  public MapGeocodeWorld(){
    String filePath = ShieldConstants.getInstance().workSpaceLocation+"//data//spacialdata//Regions_Geocodes_World.txt";
    
    List<String> lstLines = readAllLinesInFile(filePath);
    
    for (String string : lstLines) {
      if(string.startsWith("**")) continue;
      
      List<String> lstTokens = readAllTokens(string, "/");
      
      if(lstTokens.size() == 3) {
        String regionPath = lstTokens.get(0);
        
        String strLat = lstTokens.get(1);
        String strLongi = lstTokens.get(2);
        float lat = Float.parseFloat(strLat);
        float longi = Float.parseFloat(strLongi);
        GeoCordinates geoCordinates = new GeoCordinates();
        geoCordinates.lat = lat;
        geoCordinates.longi = longi;
        put(regionPath, geoCordinates);
        
      }
      else if(lstTokens.size() == 4) {
        String regionPath = lstTokens.get(0)+"/"+lstTokens.get(1);
        
        String strLat = lstTokens.get(2);
        String strLongi = lstTokens.get(3);
        float lat = Float.parseFloat(strLat);
        float longi = Float.parseFloat(strLongi);
        GeoCordinates geoCordinates = new GeoCordinates();
        geoCordinates.lat = lat;
        geoCordinates.longi = longi;
        put(regionPath, geoCordinates);
        
      }
      else if(lstTokens.size() == 5) {
        String regionPath = lstTokens.get(0)+"/"+lstTokens.get(1)+"/"+lstTokens.get(2);
        String strLat = lstTokens.get(3);
        String strLongi = lstTokens.get(4);
        float lat = Float.parseFloat(strLat);
        float longi = Float.parseFloat(strLongi);
        GeoCordinates geoCordinates = new GeoCordinates();
        geoCordinates.lat = lat;
        geoCordinates.longi = longi;
        put(regionPath, geoCordinates);
        
        
      }
      
    }
    
    mapGeocodeIndia = new MapGeocodeIndia();
    
    
  }
  
  protected List<String> readAllTokens(String line, String token){
    createTokenizer(line, token);

    List<String> lstTokens = new ArrayList<>();
    String region = null;
    while( (region = nextToken()) != null ){
      lstTokens.add(region);
    }
    return lstTokens;
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

  protected String testTrimLeadingAndTrailingSpacesOfString(String str) {
    return str.replaceAll("^\\s+|\\s+$", "");
  }

  protected DataInputStream readFile(String absoluteFilePath) {
    try {
      fileInputStream = new FileInputStream(absoluteFilePath);
      dataInputStream = new DataInputStream(fileInputStream);
      return dataInputStream;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  protected void closeFile() {
    try {
      dataInputStream.close();
      fileInputStream.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  protected List<String> readAllLinesInFile(String path) {

    readFile(path);
    List<String> lstLines = new ArrayList<String>();
    String line = null;
    while ((line = getNextLine()) != null) {
      if (line.startsWith("**"))
        continue;
      lstLines.add(line);
    }
    closeFile();

    return (lstLines);
  }
  
  public void processRequestObject(RequestObject ro) {
    
    if(ro.getStrTreepathCallerLocation() != null) {
  
      
      String treepath = ro.getStrTreepathCallerLocation();
      
      Map<String, GeoCordinates> mapGeoCo = new HashMap<String, GeoCordinates>();
      while(true) {
        GeoCordinates geoCordinates =  get(treepath);
        
        String key = treepath.substring(treepath.lastIndexOf("/")+1);
        if(geoCordinates != null) {
          mapGeoCo.put(key, geoCordinates);
          System.out.println("Found for "+treepath +"   Adding "+geoCordinates.lat+" ");
        }
        else {
          System.out.println("Could not find for"+treepath );  
        }
        
        if(treepath.contains("/")) {
          treepath = treepath.substring(0, treepath.lastIndexOf("/"));
        }
        else {
          break;
        }
        
      }//while
    
      ro.setMapGeoCords(mapGeoCo);
      mapGeocodeIndia.processRequestObject(ro);
    
    }


    
    
  }
  
  
  public static void main(String[] args) {
     MapGeocodeWorld mapGeocodeWorld = new MapGeocodeWorld();
       
   }
  
}
