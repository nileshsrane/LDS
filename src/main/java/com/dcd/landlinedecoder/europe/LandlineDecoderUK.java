package com.dcd.landlinedecoder.europe;


import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.vo.RequestObject;

import java.util.HashMap;
import java.util.List;

public class LandlineDecoderUK extends LandlineDecoderAbstract {

    boolean areaCodePopulated = false;
    HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(760);



    @Override
    public String getUserCountryTreePath() {
        return "Earth/Europe/United Kingdom";
    }


    private void populateAreaCodes() {
        readFile( "RegionsAndLandline_Europe_UK.txt");
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
        String countryCode = lst.get(lst.size() - 1);
        String regionTreepath = line.substring(line.indexOf("/") + 1, line.indexOf(countryCode) - 1);
        countryCode = ShieldConstants.COUNTRY_CODE_UK + countryCode;
        mpAreaCodeRegion.put(countryCode, regionTreepath);

    }

    public void getCallerInfo(RequestObject requestObject) {

        if (!areaCodePopulated) {
            populateAreaCodes();
        }

        //default should be India region
        requestObject.setStrTreepathCallerLocation("Europe/United Kingdom");
        requestObject.setStrCallerAreaCode(ShieldConstants.COUNTRY_CODE_UK);

        String phoneNumber = requestObject.getStrCallerPhoneNumber();

        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_UK + "");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", ShieldConstants.COUNTRY_CODE_UK + "");
        } else if (phoneNumber.startsWith("+" + ShieldConstants.COUNTRY_CODE_UK)) {
            phoneNumber = phoneNumber.substring(1);
        }

        int endIndex = (phoneNumber.length() > 6) ? 6 : phoneNumber.length();
        String areaCode = null;

        for (int i = endIndex; i > 1; i--) {
            areaCode = phoneNumber.substring(0, i);
            String value = mpAreaCodeRegion.get(areaCode);
            if (value != null) {
                requestObject.setStrTreepathCallerLocation(value);
                requestObject.setStrCallerAreaCode(areaCode);
                break;
            }//if
        }//outer for

    }

}