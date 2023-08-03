package com.dcd.landlinedecoder;

import java.util.List;

import com.dcd.vo.RequestObject;

public class LandlineDecoderBase extends LandlineDecoderAbstract{

    boolean areaCodePopulated;
    int initialCapacity;
    String strAreaCodeFileName;
    String strCountryCode;

    public LandlineDecoderBase(
                               int codeMapInitialcapacity,
                               String strTreepathUserCountry,
                               String strAreaCodeFileName,
                               String strCountryCode) {
        this.areaCodePopulated = false;
        this.initialCapacity = codeMapInitialcapacity;
        this.strAreaCodeFileName = strAreaCodeFileName;
        this.strCountryCode = strCountryCode;
        this.userCountryTreePath = strTreepathUserCountry;

    }

    @Override
    public String getUserCountryTreePath() {
        return userCountryTreePath;
    }

    private void populateAreaCodes() {
        readFile( strAreaCodeFileName);
        String line = null;
        while ((line = getNextLine()) != null) {
            if (line.startsWith("**")) continue;
            processLine(line);
        }
        closeFile();
    }

    private void processLine(String line) {
        List<String> lst = readAllTekoens(line);
        String regionName = line.substring(0, line.lastIndexOf("/"));
        String areaCode = lst.get(lst.size() - 1);
        String regionTreepath = line.substring(line.indexOf("/") + 1, line.indexOf(areaCode) - 1);
        areaCode = strCountryCode + areaCode;
        put(areaCode, regionTreepath);

    }

    public void getCallerInfo(RequestObject requestObject) {

        if (!areaCodePopulated) {
            populateAreaCodes();
        }

        String phoneNumber = requestObject.getStrCallerPhoneNumber();

        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_UK + "");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", strCountryCode);
        } else if (phoneNumber.startsWith("+" + strCountryCode)) {
            phoneNumber = phoneNumber.substring(1);

            // set default
            String defaultPath = getUserCountryTreePath();
            defaultPath = defaultPath.substring(defaultPath.indexOf("/")+1);
            requestObject.setStrTreepathCallerLocation(defaultPath);
            //requestObject.setStrTreepathCallerCountry(defaultPath);
            requestObject.setStrCallerAreaCode(strCountryCode);

        }

        int endIndex = (phoneNumber.length() > 7) ? 7 : phoneNumber.length();
        String areaCode = null;

        for (int i = endIndex; i > 1; i--) {
            areaCode = phoneNumber.substring(0, i);
            String value = get(areaCode);
            if (value != null) {
                requestObject.setStrTreepathCallerLocation(value);
                requestObject.setStrCallerAreaCode(areaCode);
                break;
            }//if
        }//outer for

    }

}
