package barnettapps.flashread;

import org.junit.Before;
import org.junit.Test;

import barnettapps.flashread.SpeedReadObjects.*;
import barnettapps.flashread.SpeedReadObjects.Puncuation.*;

import static org.junit.Assert.assertEquals;

public class SpeedReadGeneratorTest{

    String testString1 = "This is the test String. Hopefully, It will break down properly.";


    SpeedReadString testSRString1;
    SpeedReadGenerator testGenerator;
    SpeedReadSection testSRSection1;

    @Before
    public  void Test_Setup(){
        testSRString1 = new SpeedReadString(testString1);
        testGenerator = new SpeedReadGenerator();
        testSRSection1 = new SpeedReadSection( testSRString1 );
    }

    @Test
    public void testSpeedReadGenerator_Split(){
        SpeedReadSection testSection = testGenerator.generate( testString1 );


        //assertEquals("Somewhere SRString.getData() Error",testSRString1.getData(),testString1);
    }

    @Test
    public void testSpeedReadGenerator_flatten(){}
    SpeedReadSection testSection = testGenerator.generate( testString1 );
    SpeedReadSection flatSec = testGenerator.flatten( testSection );

}
