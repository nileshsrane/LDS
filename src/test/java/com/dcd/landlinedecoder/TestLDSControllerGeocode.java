package com.dcd.landlinedecoder;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.dcd.contrl.MapGeocodeIndia;
import com.dcd.vo.RequestObject;

public class TestLDSControllerGeocode extends TestBase{

  

  MapGeocodeIndia mapGeocodeIndia = new MapGeocodeIndia();
  

  private void testAllCasesInFile(String fileName, String countryCode) throws Exception{
    
    List<String> lstLines = readAllLinesInFile(fileName);
    
    int total = 0;
    int failCount = 0;
    
    for (String string : lstLines) {
      
      if(string.startsWith("**")) continue;
      String expectedTreePath = string.substring(0, string.lastIndexOf("/"));
      expectedTreePath = expectedTreePath.substring(expectedTreePath.indexOf("/")+1);
      String areaCode = string.substring(string.lastIndexOf("/")+1);
      
      String data = mapGeocodeIndia.get(expectedTreePath);
      
      total++;
      if(data != null) {
        System.out.println("Success for "+expectedTreePath);
        
      }
      else {
        
        //System.exit(0);
        failCount++;
      }
    }

    System.out.println("Total count "+total);
    System.out.println("Fail count "+failCount);
    
  }
 
  
  
  
  //@Test
  public void testIndia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Bharat.txt", "91");
    
  }
  
  
  
  
  
  
  
  
  //@Test
  public void testAustralia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Australia_Australia.txt", "61");
  }
  
  
  
  
  
  //@Test
  public void testEurope_UK() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_UK.txt", "44");
  } 
  
  
}
