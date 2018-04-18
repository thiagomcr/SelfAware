package edu.gcccd.csis;

import org.junit.Assert;
import org.junit.Test;
import java.io.File;
import static org.junit.Assert.*;

public class SelfAwareTest {

    @Test
    public void sortTest(){
        Language.sort();
        assertEquals("synchronized", SelfAware.ReservedWords[0]);
        assertTrue(Language.ReservedWords[15].length() >= Language.ReservedWords[16].length());
    }



    @Test
    public void occurrencesTest() throws Exception {
        final String code = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                SelfAware.class.getName().replace(".", File.separator) + ".java";
        SelfAware sa = new SelfAware();
        assertEquals(34, sa.occurrences(code));
        sa.append(code, "\n//synchronized");
        assertEquals(35, sa.occurrences(code));
    }
}