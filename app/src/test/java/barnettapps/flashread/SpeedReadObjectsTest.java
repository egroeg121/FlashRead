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
    SpeedReadSection testSRSection1;
    SpeedReadSection testSRSection2;
    SpeedReadSection testSRSection3;

    @Before
    public  void Test_Setup(){
        testSRString1 = new SpeedReadString(testString1);
        testSRString2 = new SpeedReadString(testString2);
        testSRSection1 = new SpeedReadSection(testSRString1);
        testSRStop1 = new SpeedReadStop();
        testSRPara1 = new SpeedReadParagraph();
        SpeedReadObject[] testObjectArray1 = {testSRString1,testSRString2};
        testSRSection2 = new SpeedReadSection( testObjectArray1);
        SpeedReadObject[] testObjectArray2 = {testSRSection1,testSRString1,testSRSection2};
        testSRSection3 = new SpeedReadSection( testObjectArray2);
    }

    @Test
    public void testSpeedReadString_getData(){
        assertEquals("Somewhere SRString.getData() Error",testSRString1.getData(),testString1);
    }

    @Test
    public void testSpeedString_Length(){
        assertEquals("Somewhere SRString.getLength() Error", testSRString1.getCharLength(),testString1.length());
    }

    @Test
    public void testSpeedStop_getData(){
        String test = testSRStop1.getData();
        assertEquals("Somewhere SRStop.getData() Error",test  ,".");
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

    @Test
    public void testSpeedReadSection_split(){

        // TODO Finish Test

        SpeedReadSection outputSec= testSRSection2.split(testSRStop1);
        //assertEquals(outputSec.split(testSRStop1),actualSection);

    }

    @Test
    public void testSpeedReadSection_split_CharLength(){



        assertEquals("Char length is not the same", testSRSection2.split(testSRStop1).getCharLength(),testSRSection2.getCharLength());

    }

    @Test
    public void testSpeedReadSection_Flatten(){

        SpeedReadSection actualFlatten = new SpeedReadSection( testSRString1 );
        actualFlatten.add( testSRString1 ); actualFlatten.add( testSRString1 ); actualFlatten.add( testSRString2 );
        SpeedReadSection testFlatten = new SpeedReadSection( testSRSection3.flatten() ) ;

        assertEquals("Char length is not the same", testFlatten,actualFlatten );

    }

}
