package com.dcd.map;


import com.dcd.ShieldConstants;
import com.dcd.entity.Country;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MapCountryCodeCountryISOCode extends HashMap<String, Country> {

    //private InputStream inputStream = null;
    private FileInputStream fileInputStream = null;
    private DataInputStream dataInputStream = null;
    private StringTokenizer tokenizer = null;

    public MapCountryCodeCountryISOCode() {
       String filePath = ShieldConstants.getInstance().workSpaceLocation+"//data//CountryCodeICountryISOCodeMap.txt";
       importMapData(filePath);
    }

    private void importMapData(String dataFilePath) {

        readFile(dataFilePath);
        String line = null;
        while ((line = getNextLine()) != null) {
            if (line.startsWith("**")) continue;
            processLine(line);
        }
        closeFile();
    }

    private DataInputStream readFile(String fileName) {

        try {
        	
            fileInputStream = new FileInputStream(fileName);
            dataInputStream = new DataInputStream(fileInputStream);
            return dataInputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void closeFile() {
        try {
            dataInputStream.close();
            fileInputStream.close();
            //fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line) {
        createTokenizer(line, "/");
        ArrayList<String> lstTokens = readAllTekoens(line);
        String key = lstTokens.get(2);
        String value = lstTokens.get(1);
        Country country = new Country(lstTokens);
        put(key, country);


    }

    protected void createTokenizer(String line, String delimiter) {
        tokenizer = new StringTokenizer(line, delimiter);
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
}
