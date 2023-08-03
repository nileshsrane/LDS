package com.dcd.contrl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dcd.entity.GeoCordinates;
import com.dcd.entity.Location;
import com.dcd.landlinedecoder.CountryGeocode;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.landlinedecoder.LandlineDecoderWorld;
import com.dcd.map.MapCountryCodeCountryISOCode;
import com.dcd.map.MapCountrySpecificLandlineDecoder;
import com.dcd.vo.RequestObject;

@RestController
public class LDSController {

  private MapCountrySpecificLandlineDecoder mapCountrySpecificLandlineDecoder;
  //private MapCountryCodeCountryISOCode mapCoutryISOCountry;
  private CountryGeocode countryGeocode;
  private LandlineDecoderWorld ldw;
  
  
  MapGeocodeWorld mapGeocodeWorld;
  

  public LDSController() {

    //TODO for testing purpose
    //mapCountrySpecificLandlineDecoder = new MapCountrySpecificLandlineDecoder();
    //ldw = new LandlineDecoderWorld();
    
    
    
    //mapCoutryISOCountry = new MapCountryCodeCountryISOCode();
    
    //countryGeocode = new CountryGeocode();

    mapGeocodeWorld = new MapGeocodeWorld();
    
    
  }

  private LandlineDecoderAbstract getCountrySpecificLandlineDecoderByISDCode(String strPhoneNumber) {

    int endIndex = (strPhoneNumber.length() > 6) ? 6 : strPhoneNumber.length();
    for (int i = endIndex; i > 0; i--) {
      String code = strPhoneNumber.substring(0, i);
      LandlineDecoderAbstract areaDecoderCountry = mapCountrySpecificLandlineDecoder.get(code);
      if (areaDecoderCountry != null)
        return areaDecoderCountry;
    }
    return null;
  }

  @RequestMapping(value = "/lad", method = RequestMethod.POST, headers = "Accept=application/json")
  @ResponseBody
  public RequestObject getCallerInfo(@RequestBody RequestObject ro) {

    String strPhoneNumber = ro.getStrCallerPhoneNumber();

    if (strPhoneNumber.startsWith("+")) {
      processPlusNumber(ro);
    } else if (strPhoneNumber.startsWith("0")) {
      processLocalNumber(ro);
    } else {
      // not decodable.
    }

    getCallerGeoLocation(ro);
    
    return ro;
  }

  public void getCallerGeoLocation(RequestObject ro) {

    
    
    
    
    

}
  
  
  private void processPlusNumber(RequestObject ro) {

    String strPhoneNumber = ro.getStrCallerPhoneNumber();
    strPhoneNumber = strPhoneNumber.substring(1);

    // check if country specific decoder available and pass the control to it
    LandlineDecoderAbstract csld = getCountrySpecificLandlineDecoderByISDCode(strPhoneNumber);
    if (csld != null) {
      csld.getCallerInfo(ro);
    }

    // if country specific decoder not available then pass control to world landline
    // decoder
    else if (ro.getStrTreepathCallerLocation() == null) {
      ldw.getCallerInfo(ro);
    }
  }

  private void processLocalNumber(RequestObject ro) {
    String iSDCode = ro.getuCC();
    if (iSDCode != null && iSDCode.startsWith("+"))
      iSDCode = iSDCode.substring(1);
    LandlineDecoderAbstract localDecoder = mapCountrySpecificLandlineDecoder.get(iSDCode);

    // This case means 0 <area code> <phone number>
    if (localDecoder != null) {
      localDecoder.getCallerInfo(ro);
      // requestObject.setStrTreepathCallerCountry(localDecoder.getUserCountryTreePath());
    }

  }

  

  public void setMapCountrySpecificLandlineDecoder(
      MapCountrySpecificLandlineDecoder mapCountrySpecificLandlineDecoder) {
    this.mapCountrySpecificLandlineDecoder = mapCountrySpecificLandlineDecoder;
  }

  public void setMapGeocodeWorld(MapGeocodeWorld mapGeocodeWorld) {
    this.mapGeocodeWorld = mapGeocodeWorld;
  }
  
  

}
