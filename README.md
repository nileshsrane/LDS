# LDS
LDS : Landline Decoder Service , actually, Landline area code decoder service.
       
2023/08/03: This project was implemented in 2015 by me (Nilesh S Rane) |linkedin.com/in/nilesh-s-rane.
            
            The goal was to have a utility application that can decode any ISD and STD codes in the world into address in descending order significance of administrative regions. For example, while being in India, 022 will decode to Earth/Asia/India/Maharashtra/Mumbai
            and from outside India 9122 should return the same address.

            The code is in Java and using contenporary Spring Boot version. You can use the latest version of Spring Boot and other dependencies by updating the Maven POM file supplied. The entry point is com.dcd.LDSSpringBootApplication. The data is in the data folder. 

            I started implemntation of decoder from India, then 7 SAARC countries, later all English speaking countries and then some selected countries in Europe and North America. You may find  data file for a country but no associated decoder. This is because, covering around 350 countries is a mamath task and I could not complete it single handedly. 

            The decoder development status is as follows:

            Country       Lowest Reagion Type

            India           Complete
            Nepal           Complete
            USA             Complete
            Afganistan      Complete
            Bhutan          Complete
            China           Complete
            Sri-Lanka       Complete
            Australia       Complete
            France          Complete
            Russia          Complete
            United Kingdon  Complete

            The data is sourced from ITU (https://www.itu.int/oth/T0202.aspx?parent=T0202) and converted in LDS address format.  Please check the data folder for country specific decoder data file. These files are read by the country specific decoder at the time of initialization.   

            If you have any question, feel free to contact me @ nsr_javadev@yahoo.com.

            Happy "de"-coding!!

            Nilesh S Rane
            linkedin.com/in/nilesh-s-rane
            Prop: Knowligent Systems

            




              