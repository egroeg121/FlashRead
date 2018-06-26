package barnettapps.flashread;

import org.junit.Before;
import org.junit.Test;

import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadParagraph;
import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadStop;
import barnettapps.flashread.SpeedReadObjects.SpeedReadObject;
import barnettapps.flashread.SpeedReadObjects.SpeedReadSection;
import barnettapps.flashread.SpeedReadObjects.SpeedReadString;

import static org.junit.Assert.assertEquals;

public class SpeedReadStringTests {


    String testString1 = "This Test <PARA-BREAK> Second.Test<PARA-BREAK>";
    String testString2 = "This2//Test2.Another Test";


    SpeedReadString testSRString1;
    SpeedReadString testSRString2;
    SpeedReadStop testSRStop1;
    SpeedReadParagraph testSRPara1;
    SpeedReadSection testSRSection1;
    SpeedReadSection testSRSection2;
    SpeedReadSection testSRSection3;

    @Before
    public  void Test_Setup(){
        testSRString1 = new SpeedReadString(testString1);
        testSRString2 = new SpeedReadString(testString2);
        testSRStop1 = new SpeedReadStop();
        testSRPara1 = new SpeedReadParagraph();
    }


    @Test
    public void testSpeedString_Length(){
        assertEquals("Somewhere SRString.getLength() Error", testSRString1.getCharLength(),testString1.length());
    }



    @Test
    public void testSpeedReadString_split_specialcharacter(){

        // TODO FInish Test

        SpeedReadSection outputSec= (SpeedReadSection) testSRString1.split(testSRStop1);
        //assertEquals(outputSec.split(testSRStop1),actualSection);

    }

    @Test
    public void testSpeedReadString_split_multicharacter(){

        // TODO Finish Test

        SpeedReadSection outputSec= (SpeedReadSection) testSRString1.split(testSRPara1);
        //assertEquals(outputSec.split(testSRStop1),actualSection);

    }


}
