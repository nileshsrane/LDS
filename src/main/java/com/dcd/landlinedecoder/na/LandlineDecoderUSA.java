package com.dcd.landlinedecoder.na;


import java.util.HashMap;
import java.util.List;

import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.vo.RequestObject;

public class LandlineDecoderUSA extends LandlineDecoderAbstract {

    boolean areaCodePopulated = false;
    HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(400);


    @Override
    public String getUserCountryTreePath() {
        return "Earth/North America/United States";
    }


    private void populateAreaCodes() {
        readFile( "RegionsAndLandline_NA_USA.txt");
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
        String countryCode = lst.get(lst.size()-1);
        String regionTreepath = line.substring(line.indexOf("/")+1, line.indexOf(countryCode)-1);
        countryCode = ShieldConstants.COUNTRY_CODE_NANP+countryCode;
        mpAreaCodeRegion.put(countryCode, regionTreepath);

    }

    public void getCallerInfo(RequestObject requestObject){

        if(!areaCodePopulated){
            populateAreaCodes();
        }

        //default should be India region
        requestObject.setStrTreepathCallerLocation("North America/United States");
        requestObject.setStrCallerAreaCode(ShieldConstants.COUNTRY_CODE_NANP);

        String phoneNumber = requestObject.getStrCallerPhoneNumber();
        //requestObject.setFlagId(mapGlabalAreaCodeRegion.getRegionByAreaCode(new Long(MapCountrySpecificDecoder.COUNTRY_CODE_BHARAT)).getId());
        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_NANP+"");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", ShieldConstants.COUNTRY_CODE_NANP+"");
        }
        else if (phoneNumber.startsWith("+"+ShieldConstants.COUNTRY_CODE_NANP)) {
            phoneNumber = phoneNumber.substring(1);
        }

        int endIndex = (phoneNumber.length() > 6) ? 6 :phoneNumber.length();
        String areaCode = null;

        for (int i = endIndex; i > 1; i--) {
            areaCode = phoneNumber.substring(0, i);
            String value = mpAreaCodeRegion.get(areaCode);
            if(value != null) {
                requestObject.setStrTreepathCallerLocation(value);
                requestObject.setStrCallerAreaCode(areaCode);
                break;
            }//if
        }//outer for

    }

}
