package barnettapps.flashread;



import barnettapps.flashread.SpeedReadObjects.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;


public class SpeedReadObjectsTest {


    @Test
    public void testSpeedReadString_getData(){
        SpeedReadString testString = new SpeedReadString("Test");

        assertEquals("Can't get data of String",testString.getData(),"Test");
    }

    @Test
    public void testSpeedReadString_split(){
        SpeedReadString testString = new SpeedReadString("Test Test2");

        SpeedReadString actualString1 = new SpeedReadString("Test");
        SpeedReadString actualString2 = new SpeedReadString("Test2");
        SpeedReadString[] actualarray = {actualString1,actualString2};
        SpeedReadSection actualsection = new SpeedReadSection( actualarray );

        SpeedReadSection testsection = (SpeedReadSection)testString.split(" ");
        assertThat(testsection,equalTo(actualsection));
    }

    @Test
    public void testSpeedReadString_split2(){
        SpeedReadString testString = new SpeedReadString("This is a new Test.");

        SpeedReadString actualString1 = new SpeedReadString("This");
        SpeedReadString actualString2 = new SpeedReadString("is");
        SpeedReadString actualString3 = new SpeedReadString("a");
        SpeedReadString actualString4 = new SpeedReadString("new");
        SpeedReadString actualString5 = new SpeedReadString("Test.");
        SpeedReadString[] actualarray = {actualString1,actualString2,actualString3,actualString4,actualString5};
        SpeedReadSection actualsection = new SpeedReadSection( actualarray );

        SpeedReadSection testsection = (SpeedReadSection)testString.split(" ");
        assertThat(testsection,equalTo(actualsection));
    }

    @Test
    public void testSpeedReadSection_split(){
        SpeedReadString testString1 = new SpeedReadString("This//Test.");
        SpeedReadString testString2 = new SpeedReadString("This2 Test2.");
        SpeedReadString[] testStringarray = {testString1,testString2};
        SpeedReadSection testSection = new SpeedReadSection(testStringarray);
        testSection = testSection.split("//");

        SpeedReadSection actualsection = new SpeedReadSection( testStringarray );
        assertThat(testSection,equalTo(actualsection));
    }


}
