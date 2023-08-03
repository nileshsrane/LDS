package com.dcd.landlinedecoder.asia;


import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.vo.RequestObject;

import java.util.HashMap;
import java.util.List;

public class LandlineDecoderAfganistan extends LandlineDecoderAbstract {


    boolean areaCodePopulated = false;
    HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(100);



    @Override
    public String getUserCountryTreePath() {
        return "Earth/Asia/Afghanistan";
    }

    private void populateAreaCodes() {

        readFile( "RegionsAndLandline_Asia_Afghanistan.txt");
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
        countryCode = ShieldConstants.COUNTRY_CODE_AFGHANISTAN+countryCode;
        mpAreaCodeRegion.put(countryCode, regionTreepath);
    }

    public void getCallerInfo(RequestObject ro){


        if(!areaCodePopulated){
            populateAreaCodes();
        }

        //default should be India region
        ro.setStrTreepathCallerLocation("Asia/Afghanistan");
        ro.setStrCallerAreaCode(ShieldConstants.COUNTRY_CODE_AFGHANISTAN);

        String phoneNumber = ro.getStrCallerPhoneNumber();
        //requestObject.setFlagId(mapGlabalAreaCodeRegion.getRegionByAreaCode(new Long(MapCountrySpecificDecoder.COUNTRY_CODE_BHARAT)).getId());
        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_AFGHANISTAN+"");
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", ShieldConstants.COUNTRY_CODE_AFGHANISTAN+"");
        }
        else if (phoneNumber.startsWith("+"+ShieldConstants.COUNTRY_CODE_AFGHANISTAN)) {
            phoneNumber = phoneNumber.substring(1);
        }

        int endIndex = (phoneNumber.length() > 6) ? 6 :phoneNumber.length();
        String areaCode = null;

        for (int i = endIndex; i > 1; i--) {
            areaCode = phoneNumber.substring(0, i);
            String value = mpAreaCodeRegion.get(areaCode);
            if(value != null) {
                ro.setStrTreepathCallerLocation(value);
                ro.setStrCallerAreaCode(areaCode);
                break;
            }//if
        }//outer for

    }
}
