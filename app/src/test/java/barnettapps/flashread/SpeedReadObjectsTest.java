package barnettapps.flashread;



import barnettapps.flashread.SpeedReadObjects.*;
import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadParagraph;
import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadStop;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class SpeedReadObjectsTest {

    String testString1 = "This Test <PARA-BREAK> Second.Test<PARA-BREAK>";
    String testString2 = "This2//Test2.Another Test";


    SpeedReadString testSRString1;
    SpeedReadString testSRString2;
    SpeedReadStop testSRStop1;
    SpeedReadParagraph testSRPara1;

    @Before
    public  void Test_Setup(){
        testSRString1 = new SpeedReadString(testString1);
        testSRString2 = new SpeedReadString(testString2);
        testSRStop1 = new SpeedReadStop();
        testSRPara1 = new SpeedReadParagraph();
        }

    @Test
    public void testSpeedReadString_getData(){
        assertEquals("Somewhere SRString.getData() Error",testSRString1.getData(),testString1);
    }

    @Test
    public void testSpeedStop_getData(){
        String test = testSRStop1.getData();
        assertEquals("Somewhere SRStop.getData() Error",test  ,".");
    }

}
