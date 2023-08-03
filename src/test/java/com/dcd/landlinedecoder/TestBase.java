package com.dcd.landlinedecoder;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.dcd.ShieldConstants;

public class TestBase {

  private FileInputStream fileInputStream = null;
  private DataInputStream dataInputStream = null;
  private StringTokenizer tokenizer = null;

  protected void createTokenizer(String line, String delimiter) {
    tokenizer = new StringTokenizer(line, delimiter);
  }
  
  protected DataInputStream readFile( String fileName) {

    try {

      fileName = ShieldConstants.getInstance().workSpaceLocation+"//data/"+ fileName;
      fileInputStream = new FileInputStream(fileName);
        dataInputStream = new DataInputStream(fileInputStream);
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
      fileInputStream.close();
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

    readFile(path);
    List<String> lstLines = new ArrayList<String>();
    String line = null;
    while ((line = getNextLine()) != null) {
      if (line.startsWith("**")) continue;
      lstLines.add(line);
    }
    closeFile();

    return(lstLines);
  }
  
  
  
}
