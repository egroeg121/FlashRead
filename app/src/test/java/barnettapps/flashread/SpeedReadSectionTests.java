package barnettapps.flashread;

import org.junit.Before;
import org.junit.Test;

import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadParagraph;
import barnettapps.flashread.SpeedReadObjects.Puncuation.SpeedReadStop;
import barnettapps.flashread.SpeedReadObjects.SpeedReadObject;
import barnettapps.flashread.SpeedReadObjects.SpeedReadSection;
import barnettapps.flashread.SpeedReadObjects.SpeedReadString;

import static org.junit.Assert.assertEquals;

public class SpeedReadSectionTests {



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
        SpeedReadObject[] testObjectArray1 = {testSRString1,testSRString2};
        testSRSection2 = new SpeedReadSection( testObjectArray1);
        SpeedReadObject[] testObjectArray2 = {testSRSection1,testSRString1,testSRSection2};
        testSRSection3 = new SpeedReadSection( testObjectArray2);
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
