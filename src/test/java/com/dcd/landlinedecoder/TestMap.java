package com.dcd.landlinedecoder;

import org.junit.Test;

import com.dcd.ShieldConstants;
import com.dcd.map.MapCountryCodeCountryISOCode;
import com.dcd.map.MapCountrySpecificLandlineDecoder;
import com.dcd.vo.RequestObject;

public class TestMap {

	
	
	//@Test
	  public void testMapCountryCodeCountryISOCode() throws Exception{
	       
		MapCountryCodeCountryISOCode mp = new MapCountryCodeCountryISOCode();
		
	  }
	
	
	//@Test
	  public void testMapCountrySpecificLandlineDecoder() throws Exception{
	       
		MapCountrySpecificLandlineDecoder mp = new MapCountrySpecificLandlineDecoder();
		
		LandlineDecoderAbstract lds = mp.get(ShieldConstants.COUNTRY_CODE_BHARAT);
		RequestObject ro = new RequestObject();
		ro.setStrCallerPhoneNumber("+9122");
		lds.getCallerInfo(ro);
		
		System.out.println(ro.getStrTreepathCallerLocation());
		
	  }
	
	
	
	
}
