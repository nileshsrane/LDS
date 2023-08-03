package com.dcd.landlinedecoder.asia;

import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.*;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.vo.RequestObject;

import java.util.HashMap;
import java.util.List;

public class LandlineDecoderChina extends LandlineDecoderAbstract {

    boolean areaCodePopulated = false;
    HashMap<String, String> mpAreaCodeRegion = new HashMap<String, String>(334);


    @Override
    public String getUserCountryTreePath() {
        return "Earth/Asia/China";
    }


    private void populateAreaCodes() {
        readFile("RegionsAndLandline_Asia_China.txt");
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
        countryCode = ShieldConstants.COUNTRY_CODE_CHINA+countryCode;
        mpAreaCodeRegion.put(countryCode, regionTreepath);

    }

    public void getCallerInfo(RequestObject ro){

        if(!areaCodePopulated){
            populateAreaCodes();
        }

        //default should be India region
        ro.setStrTreepathCallerLocation("Asia/China");
        ro.setStrCallerAreaCode(ShieldConstants.COUNTRY_CODE_CHINA);

        String phoneNumber = ro.getStrCallerPhoneNumber();

        //requestObject.setCCC(ShieldConstants.COUNTRY_CODE_CHINA);
        if (phoneNumber.startsWith("0")) {
            phoneNumber = phoneNumber.replaceFirst("0", ShieldConstants.COUNTRY_CODE_CHINA+"");
        }
        else if (phoneNumber.startsWith("+"+ShieldConstants.COUNTRY_CODE_CHINA)) {
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
