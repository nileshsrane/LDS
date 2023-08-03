package com.dcd.contrl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.dcd.ShieldConstants;
import com.dcd.entity.GeoCordinates;
import com.dcd.vo.RequestObject;

public class MapGeocodeIndia extends HashMap<String, String>{

  DataOutputStream doo = null;
  DataInputStream dio;
  
  public MapGeocodeIndia() {

  }

  
  
  public String searchGeocodeByDistrict(String treePath) {
    
    try {
      String filePath = ShieldConstants.getInstance().workSpaceLocation + "//data//spacialdata//Regions_Geocodes_India_Districts.txt";
      File inputFile = new File(filePath);
      FileInputStream fio = new FileInputStream(inputFile);
      DataInputStream dio = new DataInputStream(fio);
      String line = null;
      while ((line = dio.readLine()) != null) {
        if (line.startsWith("**"))
          continue;
        if(line.startsWith(treePath)){
          return line.substring(line.indexOf("|"+1));
        }
      }
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    return null;
    
  }
  
  
  private void transformGADMToLDSFormat() {
    try {

      String filePath = ShieldConstants.getInstance().workSpaceLocation + "//data//spacialdata//gadm36_IND_3.kml";

        FileInputStream fio = new FileInputStream(new File(ShieldConstants.getInstance().workSpaceLocation+"/data/spacialdata/output.txt"));
         dio = new DataInputStream(fio);
        
      
      
      
      File inputFile = new File(filePath);
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();

      // System.out.println("Root element :" +
      // doc.getDocumentElement().getNodeName());

      NodeList nList = doc.getElementsByTagName("Placemark");
      
      for (int temp = 0; temp < nList.getLength(); temp++) {
        processPlacemark((Element) nList.item(temp));  
      }

      dio.close();
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    
  }
  
  @Override
  public String get(Object key) {
   
    String value = super.get(key);
    if(value == null) {
      
    }
    return value;
  }
  
  private  void processPlacemark(Element element) {

    Element eleExtendedData = (Element) element.getElementsByTagName("ExtendedData").item(0);
    Element eleSchemaData = (Element) eleExtendedData.getElementsByTagName("SchemaData").item(0);
    NodeList nodeList = eleSchemaData.getElementsByTagName("SimpleData");

    String treePath = "Asia";
    for (int temp = 0; temp < nodeList.getLength(); temp++) {
      Node nNode = nodeList.item(temp);
      treePath = treePath + "/" + nNode.getTextContent();
    }

    Element eleMultiGeometry = (Element) element.getElementsByTagName("MultiGeometry").item(0);
    Element elePlygon = (Element) eleMultiGeometry.getElementsByTagName("Polygon").item(0);
    Element eleOuterBoundaryLs = (Element) eleMultiGeometry.getElementsByTagName("outerBoundaryIs").item(0);
    Element eleLinearRing = (Element) eleOuterBoundaryLs.getElementsByTagName("LinearRing").item(0);
    NodeList cordinateNodeList = eleLinearRing.getElementsByTagName("coordinates");
    Node nNode = cordinateNodeList.item(0);

    String geoCodes = nNode.getTextContent();

    /*if(treePath.contains("Mumbai")) {
      System.out.println(treePath);
      System.out.println(geoCodes);

    }*/
    
    //put(treePath, geoCodes);
    //System.out.println(treePath+"|"+geoCodes);

    try {
      doo.writeBytes(treePath+"|"+geoCodes+System.lineSeparator());
    
    } catch (Exception e) {
       e.printStackTrace();
    }
    
    
    
  }

  public void processRequestObject(RequestObject ro) {
    
    String treePath = ro.getStrTreepathCallerLocation();
    
    Map<String, GeoCordinates> mapGeoCords = ro.getMapGeoCords();
    String geoData = get(treePath);
    
    String strLongi = geoData.substring(0, geoData.indexOf(","));
    String strLat = geoData.substring(geoData.indexOf(",")+1, geoData.indexOf(" "));
    
    
    float lat = Float.parseFloat(strLat);
    float longi = Float.parseFloat(strLongi);
    GeoCordinates geoCordinates = new GeoCordinates();
    geoCordinates.lat = lat;
    geoCordinates.longi = longi;
    geoCordinates.borderLineGeoData = geoData;
    treePath = treePath.substring(treePath.lastIndexOf("/")+1);
    mapGeoCords.put(treePath, geoCordinates);
    
    
  }
  
  public static void main(String[] args) {
    MapGeocodeIndia mapGeocodeIndia = new MapGeocodeIndia();
    String str = mapGeocodeIndia.searchGeocodeByDistrict("Asia/India/West Bengal/Murshidabad/Kandi");
    System.out.println(str);
    
  }
}
