package com.dcd.landlinedecoder;

import java.util.Map;
import java.util.StringTokenizer;

import org.junit.Test;

import com.dcd.contrl.MapGeocodeWorld;
import com.dcd.entity.GeoCordinates;
import com.dcd.vo.RequestObject;

public class TestMapGeocodeWorld {

  //@Test
  public void testSimple() {
    
    RequestObject ro = new RequestObject();
    ro.setStrTreepathCallerLocation("Asia/India/Maharashtra/Sindhudurg/Kudal");
    MapGeocodeWorld mapGeocodeWorld = new MapGeocodeWorld();
    mapGeocodeWorld.processRequestObject(ro);
    
    Map<String, GeoCordinates> lst = ro.getMapGeoCords();
    StringTokenizer tokenizer = new StringTokenizer(ro.getStrTreepathCallerLocation());
    
    for (String geoCordinates : lst.keySet()) {
      GeoCordinates cordinates = lst.get(geoCordinates);
      System.out.println(geoCordinates+":"+cordinates.lat+" , "+cordinates.longi);
      //System.out.println(tokenizer.nextToken()+"  "+cordinates.lat+"  "+cordinates.longi);
    }
    
  }
  
}
