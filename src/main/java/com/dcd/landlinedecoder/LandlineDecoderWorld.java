package com.dcd.landlinedecoder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import com.dcd.ShieldConstants;
import com.dcd.vo.RequestObject;


public class LandlineDecoderWorld extends LandlineDecoderAbstract {

  boolean areaCodePopulated = false;
  HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(262);


  public void populateAreaCodes() {

    List<String> lstLines = readAllLinesInFile( "RegionsAndLandline_World.txt");

    for (String line : lstLines ) {
      if (line.startsWith("**")) continue;
      String strCountryName = line.substring(line.indexOf("/")+1, line.lastIndexOf("/"));
      String countryCode = line.substring(line.lastIndexOf("/")+1, line.length());
      mpAreaCodeRegion.put(countryCode, strCountryName);
    }

  }
  
  @Override
  public String getUserCountryTreePath() {
    return "Earth";
  }


  public void getCallerInfo(RequestObject requestObject){

    if(!areaCodePopulated){
      populateAreaCodes();
    }

    String strPhoneNumber = requestObject.getStrCallerPhoneNumber();

    if(strPhoneNumber.startsWith("+")){
      strPhoneNumber = strPhoneNumber.substring(1);
    }

    processCountryCode(requestObject);

  }

  private void processCountryCode(RequestObject requestObject) {
      int endIndex;
      String strPhoneNumber = requestObject.getStrCallerPhoneNumber();
     if(strPhoneNumber.startsWith("+")) strPhoneNumber = strPhoneNumber.substring(1);

      endIndex = (strPhoneNumber.length() > 7) ? 7 :strPhoneNumber.length();
      
      while(endIndex > 0) {
        String key = strPhoneNumber.substring(0, endIndex);
        String region = mpAreaCodeRegion.get(key);
        if(region != null) {
          requestObject.setStrTreepathCallerLocation(region);
          requestObject.setStrCallerAreaCode(key);
          //requestObject.setStrTreepathCallerCountry("/"+region);
          break;
        }
        else {
          endIndex--;
        }
      }

  }


  public byte[] readFlagData(String continentSlashfileName) {

    try {
      String filePath = "FlagsOfNations/"+continentSlashfileName+".png";
      filePath = ShieldConstants.getInstance().workSpaceLocation+"//"+filePath;
      
      FileInputStream fileInputStream = new FileInputStream(filePath);
      //DataInputStream dataInputStream = new DataInputStream(fileInputStream);
      
      int size = fileInputStream.available();

      byte[] data = new byte[size];
      fileInputStream.read(data);
      return data;

    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }


}
