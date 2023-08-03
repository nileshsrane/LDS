package com.dcd;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.annotation.Bean;

import com.dcd.contrl.MapGeocodeIndia;
import com.dcd.contrl.MapGeocodeWorld;

public class ShieldConstants {
	
	  public String workSpaceLocation = null;

	  public static final String SERVER_NAME = "192.168.71.1";
	  public static final String SERVER_PORT = "8080";
	  public static final String APP_NAME = "";

	  
	//Asia
	  public static final String COUNTRY_CODE_AFGHANISTAN = "93";
	  public static final String COUNTRY_CODE_BHARAT = "91";
	  public static final String COUNTRY_CODE_SRI_LANKA = "94";
	  public static final String COUNTRY_CODE_NEPAL = "977";
	  public static final String COUNTRY_CODE_BHUTAN = "975";
	  public static final String COUNTRY_CODE_CHINA = "86";

	  //2020/02/15: Decoder logic is not implemented for following two countries.
	  public static final String COUNTRY_CODE_PAKISTAN = "92";
	  public static final String COUNTRY_CODE_BURMA = "95";

	  //Europe
	  public static final String COUNTRY_CODE_UK = "44";
	  public static final String COUNTRY_CODE_RUSSIA = "7";
	  public static final String COUNTRY_CODE_IRELAND = "353";
	  public static final String COUNTRY_CODE_SWITZERLAND = "41";
	  public static final String COUNTRY_CODE_CZECH_REPUBLIC = "420";
	  public static final String COUNTRY_CODE_AUSTRIA = "43";
	  public static final String COUNTRY_CODE_ALBANIA = "355";
	  public static final String COUNTRY_CODE_Bosnia_Herzegovina = "387";
	  public static final String COUNTRY_CODE_POLAND = "48";
	  public static final String COUNTRY_CODE_GERMANY = "49";
	  public static final String COUNTRY_CODE_BULGARIA = "359";
	  public static final String COUNTRY_CODE_CROATIA = "385";
	  public static final String COUNTRY_CODE_CYPRUS = "357";
	  public static final String COUNTRY_CODE_Estonia = "372";
	  public static final String COUNTRY_CODE_Finland = "358";
	  public static final String COUNTRY_CODE_Greece = "30";
	  public static final String COUNTRY_CODE_Hungary = "36";
	  public static final String COUNTRY_CODE_Italy = "39";
	  public static final String COUNTRY_CODE_Latvia = "371";
	  public static final String COUNTRY_CODE_Lithuania = "370";
	  public static final String COUNTRY_CODE_Montenegro = "382";
	  public static final String COUNTRY_CODE_Netherlands = "31";
	  public static final String COUNTRY_CODE_Norway = "47";
	  public static final String COUNTRY_CODE_Romania = "40";
	  public static final String COUNTRY_CODE_Serbia = "381";
	  public static final String COUNTRY_CODE_Slovakia = "421";
	  public static final String COUNTRY_CODE_Slovenia = "386";
	  public static final String COUNTRY_CODE_Spain = "34";
	  public static final String COUNTRY_CODE_Sweden = "46";
	  public static final String COUNTRY_CODE_Iceland = "354";
	  //North America
	  public static final String COUNTRY_CODE_NANP = "1"; // Just 1 means USA There are around 20 sub codes for non USA regions.

	  //Australia
	  public static final String COUNTRY_CODE_AUSTRALIA = "61";
	  
	  private static ShieldConstants shieldConstants;
	  
	  
	  private ShieldConstants() {
	    /*ApplicationHome home = new ApplicationHome(LDSSpringBootApplication.class);
	    
	    workSpaceLocation = home.getDir().getAbsolutePath();
	    System.out.println("*********************************************"+workSpaceLocation);
	    workSpaceLocation = workSpaceLocation.substring(0, workSpaceLocation.indexOf("LDS")+3);*/
	    System.out.println();
	    workSpaceLocation = "C:\\Users\\Neo\\Documents\\NeoSpaceSynchronised\\Development\\workspaces\\STSworkspace\\LDS";
	    
	    
    }
	  
	  public static ShieldConstants getInstance( ) {
	    
	    if(shieldConstants == null) {
	      shieldConstants = new ShieldConstants();
	    }
	    
	    return shieldConstants;
	    
	  }
	  
	  public static String getServerURL(){
		    return "http://"+SERVER_NAME+":"+SERVER_PORT+"/"+APP_NAME;
      }
	  
	  public static String getLandlineServiceURL(){
		 return getServerURL()+"/"+"lad";
	  }
	
	  
	  MapGeocodeIndia mapGeocodeIndia;
	  
	  MapGeocodeWorld mapGeocodeWorld;
	  
	  
	  
	  @Bean
	  public MapGeocodeWorld getMapGeocodeWorld() {
      return mapGeocodeWorld;
    }

    public void setMapGeocodeWorld(MapGeocodeWorld mapGeocodeWorld) {
      this.mapGeocodeWorld = mapGeocodeWorld;
    }

    @Bean
	  public MapGeocodeIndia getMapGeocodeIndia() {
      return mapGeocodeIndia;
    }
    
    public void setMapGeocodeIndia(MapGeocodeIndia mapGeocodeIndia) {
      this.mapGeocodeIndia = mapGeocodeIndia;
    }
    
	  
}
