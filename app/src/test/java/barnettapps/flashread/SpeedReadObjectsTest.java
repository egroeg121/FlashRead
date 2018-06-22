package barnettapps.flashread;



import barnettapps.flashread.SpeedReadObjects.*;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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

        List<SpeedReadObject> actualarray = new ArrayList<>();
        actualarray.add(actualString1); actualarray.add(actualString2);
        SpeedReadSection actualsection = new SpeedReadSection( actualarray );

        SpeedReadSection testsection = (SpeedReadSection)testString.split(" ");
        assertThat(testsection,equalTo(actualsection));
    }

    @Test
    public void testSpeedReadSection_split(){
        SpeedReadString testString1 = new SpeedReadString("This//Test.");
        SpeedReadString testString2 = new SpeedReadString("This2 Test2.");
        List<SpeedReadObject> testStringarray = new ArrayList<>();
        testStringarray.add(testString1); testStringarray.add(testString2);
        SpeedReadSection testSection = new SpeedReadSection(testStringarray);
        testSection = testSection.split("//");

        SpeedReadSection actualsection = new SpeedReadSection( testStringarray );
        assertThat(testSection,equalTo(actualsection));
    }


}
