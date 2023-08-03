package com.dcd.landlinedecoder;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.dcd.ShieldConstants;
import com.dcd.contrl.LDSController;
import com.dcd.vo.RequestObject;

public class TestLDSController extends TestBase{

  public String makeJSONCall(String phoneNumber, String userCountryCode) throws Exception {
    HttpClient httpClient = null;
    try {
      httpClient = new DefaultHttpClient();
      String url = ShieldConstants.getLandlineServiceURL();
      HttpPost httpGet = new HttpPost(url);
      httpGet.setHeader("Accept", "application/json");
      httpGet.setHeader("Content-type", "application/json");
      ObjectMapper objectMapper = new ObjectMapper();

      RequestObject lad = new RequestObject();
      lad.setClientId("11626561");
      lad.setStrCallerPhoneNumber(phoneNumber);
      lad.setuCC(userCountryCode);

      String jsonData = objectMapper.writeValueAsString(lad);
      httpGet.setEntity(new StringEntity(jsonData));

      HttpResponse response = httpClient.execute(httpGet);
      HttpEntity entity = response.getEntity();

      lad = objectMapper.readValue(entity.getContent(), RequestObject.class);
      if (lad != null) {

        return lad.getStrTreepathCallerLocation();
      } else {
        return null;
      }

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
  }

  

  private void testAllCasesInFile(String fileName, String countryCode) throws Exception{
    
    List<String> lstLines = readAllLinesInFile(fileName);
    
    for (String string : lstLines) {
      
      if(string.startsWith("**")) continue;
      String expectedTreePath = string.substring(0, string.lastIndexOf("/"));
      expectedTreePath = expectedTreePath.substring(expectedTreePath.indexOf("/")+1);
      String areaCode = string.substring(string.lastIndexOf("/")+1);
      
      String treePath = makeJSONCall("+"+countryCode+areaCode, countryCode);
      
      if(treePath.equals(expectedTreePath)) {
        System.out.println("Success ");
      }
      else {
        System.out.println("Expected : "+ expectedTreePath+ "  received : "+treePath );
        System.exit(0);
      }
      
      treePath = makeJSONCall("0"+areaCode, countryCode);
      
      if(treePath.equals(expectedTreePath)) {
        System.out.println("Success ");
      }
      else {
        System.out.println("Expected : "+ expectedTreePath+ "  received : "+treePath );
        System.exit(0);
      }
      
    }
    
  }
  
  private void testAllCasesInFileWorld(String fileName, String countryCode) throws Exception{
    
    List<String> lstLines = readAllLinesInFile(fileName);
    
    for (String string : lstLines) {
      
      if(string.startsWith("**")) continue;
      String expectedTreePath = string.substring(0, string.lastIndexOf("/"));
      expectedTreePath = expectedTreePath.substring(expectedTreePath.indexOf("/")+1);
      String areaCode = string.substring(string.lastIndexOf("/")+1);
      
      String treePath = makeJSONCall("+"+areaCode, countryCode);
      
      if(treePath.equals(expectedTreePath)) {
        System.out.println("Success ");
      }
      else {
        System.out.println("Expected : "+ expectedTreePath+ "  received : "+treePath );
        System.exit(0);
      }
      
      
      
    }
    
  }
  
//@Test
 public void testInterStateNumbers() throws Exception {
   String treePath = makeJSONCall("022", "91");
   System.out.println(treePath);
 }

 // @Test
 public void testInterStateNumbers2() throws Exception {
   String treePath = makeJSONCall("+9122", "91");
   System.out.println(treePath);
 }

 //@Test
 public void testInterStateNumbers3() throws Exception {
   String treePath = makeJSONCall("+9122", "1");
   System.out.println(treePath);
 }
 
  //@Test
  public void testIndia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Bharat.txt", "91");
    
  }
  
  //@Test
  public void testAfghanistan() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Afghanistan.txt", "93");
    
  }
  
  //@Test
  public void testBhutan() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Bhutan.txt", "975");
    
  }
  
  //@Test
  public void testChina() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_China.txt", "86");
    
  }
  
  /**
   *  This test case does not work as Myanmar decoder is absent. 
   *  Same as Bangladesh .
   * @throws Exception
   */
  //@Test
  public void testMyanmar() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Myanmar.txt", "95");
  }
  
  //@Test
  public void testNepal() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_Nepal.txt", "977");
  }
  
  //@Test
  public void testSriLanka() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Asia_SriLanka.txt", "94");
  }
  
  //@Test
  public void testUSA() throws Exception{
    testAllCasesInFileWorld("RegionsAndLandline_NA_USA.txt", "1");
  }
  
  //@Test
  public void testWorld() throws Exception{
    testAllCasesInFileWorld("RegionsAndLandline_World.txt", "1");
  }

  
  //@Test
  public void testAustralia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Australia_Australia.txt", "61");
  }
  
  
  //@Test
  public void testEurope_Albania() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Albania.txt", "355");
  }
  
  //@Test
  public void testEurope_Austriya() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Austria.txt", "43");
  }
  
  
  //@Test
  public void testEurope_BosniyaHarjegovina() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Bosnia_Herzegovina.txt", "387");
  }
  
  //@Test
  public void testEurope_Bulgaria() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Bulgaria.txt", "359");
  }
  
  //@Test
  public void testEurope_Criatia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Croatia.txt", "385");
  }
  
  //@Test
  public void testEurope_Cyprus() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Cyprus.txt", "357");
  }
  
  //@Test
  public void testEurope_CzechRepublic() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_CzechRepublic.txt", "420");
  }
  
  
  //@Test
  public void testEurope_Estonia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Estonia.txt", "372");
  }
  
  
  //@Test
  public void testEurope_Finland() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Finland.txt", "358");
  }
  
  
  /**
   * Not successfull due to falty area code file.
   * @throws Exception
   */
  //@Test
  public void testEurope_France() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_France.txt", "33");
  }
  
  //@Test
  public void testEurope_Germany() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Germany.txt", "49");
  }
  
  //@Test
  public void testEurope_Greece() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Greece.txt", "30");
  }
  
  //@Test
  public void testEurope_Hungary() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Hungary.txt", "36");
  }
  
  //@Test
  public void testEurope_Iceland() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Iceland.txt", "354");
  }
  
  
  //@Test
  public void testEurope_Italy() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Italy.txt", "39");
  }
  
  //@Test
  public void testEurope_Latvia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Latvia.txt", "371");
  }
  
  //@Test
  public void testEurope_Lithuania() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Lithuania.txt", "370");
  }
  
  //@Test
  public void testEurope_Montegonegro() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Montenegro.txt", "382");
  } 
  
  //@Test
  public void testEurope_Netherlands() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Netherlands.txt", "31");
  } 
  
  //@Test
  public void testEurope_Norway() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Norway.txt", "47");
  } 
 
  //@Test
  public void testEurope_Poland() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Poland.txt", "48");
  } 
  
  //@Test
  public void testEurope_Romania() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Romania.txt", "40");
  } 
  
  //@Test
  public void testEurope_Russia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Russia.txt", "7");
  } 
  
  //@Test
  public void testEurope_Serbia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Serbia.txt", "381");
  } 
  
  //@Test
  public void testEurope_Slovakia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Slovakia.txt", "421");
  } 
  
  //@Test
  public void testEurope_Slovenia() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Slovenia.txt", "386");
  } 
  
  //@Test
  public void testEurope_Spain() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Spain.txt", "34");
  } 
  
  //@Test
  public void testEurope_Sweden() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Sweden.txt", "46");
  } 
  
  //@Test
  public void testEurope_Switzerland() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_Switzerland.txt", "41");
  } 
  
  //@Test
  public void testEurope_UK() throws Exception{
    testAllCasesInFile("RegionsAndLandline_Europe_UK.txt", "44");
  } 
  
  //@Test
  public void testGeocodeFallBack() throws Exception{
    
    RequestObject ro = new RequestObject();
    LDSController ldsController = new LDSController();
    
    ro.setStrTreepathCallerLocation("Asia/India/Maharashtra/Mumbai/");
    ldsController.getCallerGeoLocation(ro);
    
  }
  
  
}
