package com.dcd.landlinedecoder;



import com.dcd.entity.Location;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class CountryGeocode extends HashMap<String, Location> {


    private FileInputStream fileInputStream = null;
    private InputStream stream = null;
    private DataInputStream dataInputStream = null;
    private StringTokenizer tokenizer = null;
    boolean areaCodePopulated;

    public CountryGeocode(){

    }

    protected void createTokenizer(String line, String delimiter) {
        tokenizer = new StringTokenizer(line, delimiter);
    }

    protected DataInputStream readFile(String absoluteFilePath) {

        try {

                fileInputStream = new FileInputStream(absoluteFilePath);
                dataInputStream = new DataInputStream(stream);
                return dataInputStream;
        }
        catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

    protected void closeFile() {
        try {
            dataInputStream.close();
            stream.close();
            //fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected String getNextLine() {
        try {
            String line = dataInputStream.readLine();
            if (line != null)
                line = testTrimLeadingAndTrailingSpacesOfString(line);
            return line;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String testTrimLeadingAndTrailingSpacesOfString(String str) {
        return str.replaceAll("^\\s+|\\s+$", "");
    }

    protected ArrayList<String> readAllTekoens(String line) {
        createTokenizer(line, "/");
        String token = null;
        ArrayList<String> lstToken = new ArrayList<String>();
        while ((token = nextToken()) != null) {
            lstToken.add(token);
        }
        return lstToken;
    }

    protected String nextToken() {
        if (tokenizer.hasMoreTokens()) {
            String str = tokenizer.nextToken();
            str = testTrimLeadingAndTrailingSpacesOfString(str);
            return str;
        } else
            return null;
    }

    protected List<String> readAllLinesInFile(String path){

        readFile( path);
        List<String> lstLines = new ArrayList<String>();
        String line = null;
        while ((line = getNextLine()) != null) {
            if (line.startsWith("**")) continue;
            lstLines.add(line);
        }
        closeFile();

        return(lstLines);
    }


    private void populateAreaCodes() {
        readFile("Regions_Geocodes.txt");
        String line = null;
        while ((line = getNextLine()) != null) {
            if (line.startsWith("**")) continue;
            processLine(line);
        }
        closeFile();
        areaCodePopulated = true;
    }

    private void processLine(String line) {
        List<String> lst = readAllTekoens(line);

        String lat = lst.get(1);
        String longi = lst.get(2);
        String strCountryName = lst.get(3);
        Location location = new Location();
        location.lat = Float.parseFloat(lat);
        location.longi = Float.parseFloat(longi);

        put(strCountryName, location);

    }

    public Location getLocationOfCountry(String counrtyName){

        if (!areaCodePopulated) {
            populateAreaCodes();
        }

        counrtyName = counrtyName.replaceAll("_"," ");
        Location location = get(counrtyName);
        return  location;

    }

}
