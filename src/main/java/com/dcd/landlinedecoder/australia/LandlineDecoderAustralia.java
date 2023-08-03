package com.dcd.landlinedecoder.australia;


import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.vo.RequestObject;

import java.util.HashMap;
import java.util.List;

public class LandlineDecoderAustralia extends LandlineDecoderAbstract {

    boolean areaCodePopulated = false;
    HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(123);

    @Override
    public String getUserCountryTreePath() {
        return "Earth/Australia/Australia";
    }


    private void populateAreaCodes() {
        readFile("RegionsAndLandline_Australia_Australia.txt");
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
        countryCode = ShieldConstants.COUNTRY_CODE_AUSTRALIA + countryCode;
        mpAreaCodeRegion.put(countryCode, regionTreepath);

    }

    public void getCallerInfo(RequestObject requestObject) {

        if (!areaCodePopulated) {
            populateAreaCodes();
        }

        //default should be India region
        requestObject.setStrTreepathCallerLocation("Australia/Australia");
        requestObject.setStrCallerAreaCode(ShieldConstants.COUNTRY_CODE_AUSTRALIA);

        String phoneNumber = requestObject.getStrCallerPhoneNumber();

        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_AUSTRALIA + "");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", ShieldConstants.COUNTRY_CODE_AUSTRALIA + "");
        } else if (phoneNumber.startsWith("+" + ShieldConstants.COUNTRY_CODE_AUSTRALIA)) {
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

