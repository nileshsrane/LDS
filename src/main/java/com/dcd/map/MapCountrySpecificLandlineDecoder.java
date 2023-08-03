package com.dcd.map;


import java.util.HashMap;

import com.dcd.ShieldConstants;
import com.dcd.landlinedecoder.LandlineDecoderAbstract;
import com.dcd.landlinedecoder.LandlineDecoderBase;
import com.dcd.landlinedecoder.asia.LandlineDecoderAfganistan;
import com.dcd.landlinedecoder.asia.LandlineDecoderChina;
import com.dcd.landlinedecoder.australia.LandlineDecoderAustralia;
import com.dcd.landlinedecoder.asia.LandlineDecoderBhutan;
import com.dcd.landlinedecoder.asia.LandlineDecoderIndia;
import com.dcd.landlinedecoder.asia.LandlineDecoderNepal;
import com.dcd.landlinedecoder.asia.LandlineDecoderSriLanka;
import com.dcd.landlinedecoder.europe.LandlineDecoderRussia;
import com.dcd.landlinedecoder.europe.LandlineDecoderUK;
import com.dcd.landlinedecoder.na.LandlineDecoderUSA;

public class MapCountrySpecificLandlineDecoder extends HashMap<String, LandlineDecoderAbstract> {

  public MapCountrySpecificLandlineDecoder() {
    init();
  }

  public void init( )  {

      //Asia*******************************************************************************
		LandlineDecoderIndia landlineDecoderIndia = new LandlineDecoderIndia();
      super.put(ShieldConstants.COUNTRY_CODE_BHARAT, landlineDecoderIndia);

      LandlineDecoderAfganistan landlineDecoderAfganistan = new LandlineDecoderAfganistan();
      super.put(ShieldConstants.COUNTRY_CODE_AFGHANISTAN, landlineDecoderAfganistan);

      LandlineDecoderSriLanka landlineDecoderSriLanka = new LandlineDecoderSriLanka();
      super.put(ShieldConstants.COUNTRY_CODE_SRI_LANKA, landlineDecoderSriLanka);

      LandlineDecoderNepal landlineDecoderNepal = new LandlineDecoderNepal();
      super.put(ShieldConstants.COUNTRY_CODE_NEPAL, landlineDecoderNepal);

      LandlineDecoderBhutan landlineDecoderBhutan = new LandlineDecoderBhutan();
      super.put(ShieldConstants.COUNTRY_CODE_BHUTAN, landlineDecoderBhutan);

      LandlineDecoderChina landlineDecoderChina = new LandlineDecoderChina();
      super.put(ShieldConstants.COUNTRY_CODE_CHINA, landlineDecoderChina);

      //North America************************************************************************
      LandlineDecoderUSA landlineDecoderUSA = new LandlineDecoderUSA();
      super.put(ShieldConstants.COUNTRY_CODE_NANP, landlineDecoderUSA);

      //Europe*******************************************************************************
      LandlineDecoderUK landlineDecoderUK = new LandlineDecoderUK();
      super.put(ShieldConstants.COUNTRY_CODE_UK, landlineDecoderUK);

      LandlineDecoderRussia landlineDecoderRussia = new LandlineDecoderRussia();
      super.put(ShieldConstants.COUNTRY_CODE_RUSSIA, landlineDecoderRussia);

      //Australia*******************************************************************************
      LandlineDecoderAustralia landlineDecoderAustralia = new LandlineDecoderAustralia();
      super.put(ShieldConstants.COUNTRY_CODE_AUSTRALIA, landlineDecoderAustralia);

      String path = "Earth/Europe/Ireland";
      LandlineDecoderBase landlineDecoder = new LandlineDecoderBase(75, path,
                                                           "RegionsAndLandline_Europe_Ireland.txt",
                                                                             ShieldConstants.COUNTRY_CODE_IRELAND  );
      super.put(ShieldConstants.COUNTRY_CODE_IRELAND, landlineDecoder);

      path = "Earth/Europe/Switzerland";
      landlineDecoder = new LandlineDecoderBase(50, path,
                                                         "RegionsAndLandline_Europe_Switzerland.txt",
                                                                           ShieldConstants.COUNTRY_CODE_SWITZERLAND  );
      super.put(ShieldConstants.COUNTRY_CODE_SWITZERLAND, landlineDecoder);

      path = "Earth/Europe/Czech Republic";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_CzechRepublic.txt",
              ShieldConstants.COUNTRY_CODE_CZECH_REPUBLIC  );
      super.put(ShieldConstants.COUNTRY_CODE_CZECH_REPUBLIC, landlineDecoder);


      path = "Earth/Europe/Austria";
      landlineDecoder = new LandlineDecoderBase(25, path,
              "RegionsAndLandline_Europe_Austria.txt",
              ShieldConstants.COUNTRY_CODE_AUSTRIA  );
      super.put(ShieldConstants.COUNTRY_CODE_AUSTRIA, landlineDecoder);

      path = "Earth/Europe/Albania";
      landlineDecoder = new LandlineDecoderBase(25, path,
              "RegionsAndLandline_Europe_Albania.txt",
              ShieldConstants.COUNTRY_CODE_ALBANIA  );
      super.put(ShieldConstants.COUNTRY_CODE_ALBANIA, landlineDecoder);


      path = "Earth/Europe/Bosnia_Herzegovina";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_Bosnia_Herzegovina.txt",
              ShieldConstants.COUNTRY_CODE_Bosnia_Herzegovina  );
      super.put(ShieldConstants.COUNTRY_CODE_Bosnia_Herzegovina, landlineDecoder);

      path = "Earth/Europe/Germany";
      landlineDecoder = new LandlineDecoderBase(200, path,
              "RegionsAndLandline_Europe_Germany.txt",
              ShieldConstants.COUNTRY_CODE_GERMANY  );
      super.put(ShieldConstants.COUNTRY_CODE_GERMANY, landlineDecoder);

      path = "Earth/Europe/Poland";
      landlineDecoder = new LandlineDecoderBase(150, path,
              "RegionsAndLandline_Europe_Poland.txt",
              ShieldConstants.COUNTRY_CODE_POLAND);
      super.put(ShieldConstants.COUNTRY_CODE_POLAND, landlineDecoder);

      path = "Earth/Europe/Bulgaria";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_Bulgaria.txt",
              ShieldConstants.COUNTRY_CODE_BULGARIA  );
  super.put(ShieldConstants.COUNTRY_CODE_BULGARIA, landlineDecoder);

      path = "Earth/Europe/Croatia";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_Croatia.txt",
              ShieldConstants.COUNTRY_CODE_CROATIA  );
      super.put(ShieldConstants.COUNTRY_CODE_CROATIA, landlineDecoder);

      path = "Earth/Europe/Cyprus";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_Cyprus.txt",
              ShieldConstants.COUNTRY_CODE_CYPRUS  );
      super.put(ShieldConstants.COUNTRY_CODE_CYPRUS, landlineDecoder);


              path = "Earth/Europe/Estonia";
      landlineDecoder = new LandlineDecoderBase(30, path,
              "RegionsAndLandline_Europe_Estonia.txt",
              ShieldConstants.COUNTRY_CODE_Estonia  );
      super.put(ShieldConstants.COUNTRY_CODE_Estonia, landlineDecoder);

      path = "Earth/Europe/Finland";
      landlineDecoder = new LandlineDecoderBase(20, path,
              "RegionsAndLandline_Europe_Finland.txt",
              ShieldConstants.COUNTRY_CODE_Finland  );
      super.put(ShieldConstants.COUNTRY_CODE_Finland, landlineDecoder);

      path = "Earth/Europe/Greece";
      landlineDecoder = new LandlineDecoderBase(275, path,
              "RegionsAndLandline_Europe_Greece.txt",
              ShieldConstants.COUNTRY_CODE_Greece  );
      super.put(ShieldConstants.COUNTRY_CODE_Greece, landlineDecoder);

      path = "Earth/Europe/Hungary";
      landlineDecoder = new LandlineDecoderBase(75, path,
              "RegionsAndLandline_Europe_Hungary.txt",
              ShieldConstants.COUNTRY_CODE_Hungary  );
      super.put(ShieldConstants.COUNTRY_CODE_Hungary, landlineDecoder);

      path = "Earth/Europe/Italy";
      landlineDecoder = new LandlineDecoderBase(175, path,
              "RegionsAndLandline_Europe_Italy.txt",
              ShieldConstants.COUNTRY_CODE_Italy  );
      super.put(ShieldConstants.COUNTRY_CODE_Italy, landlineDecoder);

      path = "Earth/Europe/Latvia";
      landlineDecoder = new LandlineDecoderBase(40, path,
              "RegionsAndLandline_Europe_Latvia.txt",
              ShieldConstants.COUNTRY_CODE_Latvia  );
      super.put(ShieldConstants.COUNTRY_CODE_Latvia, landlineDecoder);

      path = "Earth/Europe/Lithuania";
      landlineDecoder = new LandlineDecoderBase(70, path,
              "RegionsAndLandline_Europe_Lithuania.txt",
              ShieldConstants.COUNTRY_CODE_Lithuania  );
      super.put(ShieldConstants.COUNTRY_CODE_Lithuania, landlineDecoder);

      path = "Earth/Europe/Montenegro";
      landlineDecoder = new LandlineDecoderBase(70, path,
              "RegionsAndLandline_Europe_Montenegro.txt",
              ShieldConstants.COUNTRY_CODE_Montenegro  );
      super.put(ShieldConstants.COUNTRY_CODE_Montenegro, landlineDecoder);

      path = "Earth/Europe/Netherlands";
      landlineDecoder = new LandlineDecoderBase(200, path,
              "RegionsAndLandline_Europe_Netherlands.txt",
              ShieldConstants.COUNTRY_CODE_Netherlands);
      super.put(ShieldConstants.COUNTRY_CODE_Netherlands, landlineDecoder);

      path = "Earth/Europe/Norway";
      landlineDecoder = new LandlineDecoderBase(45, path,
              "RegionsAndLandline_Europe_Norway.txt",
              ShieldConstants.COUNTRY_CODE_Norway);
      super.put(ShieldConstants.COUNTRY_CODE_Norway, landlineDecoder);

      path = "Earth/Europe/Romania";
      landlineDecoder = new LandlineDecoderBase(60, path,
              "RegionsAndLandline_Europe_Romania.txt",
              ShieldConstants.COUNTRY_CODE_Romania);
      super.put(ShieldConstants.COUNTRY_CODE_Romania, landlineDecoder);

      path = "Earth/Europe/Serbia";
      landlineDecoder = new LandlineDecoderBase(50, path,
              "RegionsAndLandline_Europe_Serbia.txt",
              ShieldConstants.COUNTRY_CODE_Serbia);
      super.put(ShieldConstants.COUNTRY_CODE_Serbia, landlineDecoder);

      path = "Earth/Europe/Slovakia";
      landlineDecoder = new LandlineDecoderBase(35, path,
              "RegionsAndLandline_Europe_Slovakia.txt",
              ShieldConstants.COUNTRY_CODE_Slovakia);
      super.put(ShieldConstants.COUNTRY_CODE_Slovakia, landlineDecoder);

      path = "Earth/Europe/Slovenia";
      landlineDecoder = new LandlineDecoderBase(10, path,
              "RegionsAndLandline_Europe_Slovenia.txt",
              ShieldConstants.COUNTRY_CODE_Slovenia);
      super.put(ShieldConstants.COUNTRY_CODE_Slovenia, landlineDecoder);

      path = "Earth/Europe/Spain";
      landlineDecoder = new LandlineDecoderBase(10, path,
              "RegionsAndLandline_Europe_Spain.txt",
              ShieldConstants.COUNTRY_CODE_Spain);
      super.put(ShieldConstants.COUNTRY_CODE_Spain, landlineDecoder);

      path = "Earth/Europe/Sweden";
      landlineDecoder = new LandlineDecoderBase(325, path,
              "RegionsAndLandline_Europe_Sweden.txt",
              ShieldConstants.COUNTRY_CODE_Sweden);
      super.put(ShieldConstants.COUNTRY_CODE_Sweden, landlineDecoder);

      path = "Earth/Europe/Iceland";
      landlineDecoder = new LandlineDecoderBase(354, path,
              "RegionsAndLandline_Europe_Iceland.txt",
              ShieldConstants.COUNTRY_CODE_Iceland);
      super.put(ShieldConstants.COUNTRY_CODE_Iceland, landlineDecoder);

  }
  

}
