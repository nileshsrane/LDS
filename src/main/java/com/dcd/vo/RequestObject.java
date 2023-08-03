package com.dcd.vo;

import java.util.List;
import java.util.Map;

import com.dcd.entity.GeoCordinates;
import com.dcd.entity.Location;

public class RequestObject {

  String strTreepathCallerLocation;
  String strCallerPhoneNumber;
  String strCallerAreaCode;
  byte[] bmpCallerCountryFlag;
  Location locCallerCountry;

  String uCC;
  String clientId;
  String geoData;
  Map<String, GeoCordinates> mapGeoCords;

  public RequestObject() { }

  public String getStrCallerPhoneNumber() {
    return strCallerPhoneNumber;
  }
  
  public void setStrCallerPhoneNumber(String strCallerPhoneNumber) {
    this.strCallerPhoneNumber = strCallerPhoneNumber;
  }

  public String getStrCallerAreaCode() {
    return strCallerAreaCode;
  }
  
  public void setStrCallerAreaCode(String strCallerAreaCode) {
    this.strCallerAreaCode = strCallerAreaCode;
  }

  public byte[] getBmpCallerCountryFlag() {
    return bmpCallerCountryFlag;
  }

  public void setBmpCallerCountryFlag(byte[] bmpCallerCountryFlag) {
    this.bmpCallerCountryFlag = bmpCallerCountryFlag;
  }

  public void setStrTreepathCallerLocation(String strTreepathCallerLocation) {
    this.strTreepathCallerLocation = strTreepathCallerLocation;
  }

  public String getStrTreepathCallerLocation() {
    return strTreepathCallerLocation;
  }

  public Location getLocCallerCountry() {
    return locCallerCountry;
  }

  public void setLocCallerCountry(Location locCallerCountry) {
    this.locCallerCountry = locCallerCountry;
  }
  
  public String getuCC() {
	return uCC;
}
  public void setuCC(String uCC) {
	  this.uCC = uCC;
  }

  public void setClientId(String clientId) {
	  this.clientId = clientId;
  }
  
  public String getClientId() {
	  return clientId;
  }

  public String getGeoData() {
    return geoData;
  }

  public void setGeoData(String geoData) {
    this.geoData = geoData;
  }

  public Map<String, GeoCordinates> getMapGeoCords() {
    return mapGeoCords;
  }

  public void setMapGeoCords(Map<String, GeoCordinates> mapGeoCords) {
    this.mapGeoCords = mapGeoCords;
  }


  
    
  
}
