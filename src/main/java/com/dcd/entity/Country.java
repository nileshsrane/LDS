package com.dcd.entity;

import java.util.List;

public class Country {

    String name;
    String iSDCode;
    String iSOCode;
    String countryFlagFileName;


    public Country(List<String> lst){

        name = lst.get(0);
        iSDCode = lst.get(1);
        iSOCode = lst.get(2);
        countryFlagFileName = lst.get(3).replace(" ", "_");


    }

    public String getName() {
        return name;
    }

    public String getiSDCode() {
        return iSDCode;
    }

    public String getiSOCode() {
        return iSOCode;
    }

    public String getCountryFlagFileName() {
        return countryFlagFileName;
    }


}
