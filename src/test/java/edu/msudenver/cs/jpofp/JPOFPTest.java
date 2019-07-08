package edu.msudenver.cs.jpofp;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import static org.junit.Assert.*;

class JdnssArgs {
    String addresses;
    int backlog;
    long logback;
    float shortPi;
    double longPi;
    boolean debug;
}

public class JPOFPTest {
    @Test
    public void simple() {
        try (InputStream is = new BufferedInputStream(new FileInputStream("jdnss.xml"))) {
            Preferences.importPreferences(is);
        } catch (IOException | InvalidPreferencesFormatException e) {
            e.printStackTrace();
            return;
        }

        JdnssArgs jdnssArgs = new JdnssArgs();
        Preferences prefs = Preferences.userRoot().node("edu/msudenver/cs/jdnss");
        assertNull(jdnssArgs.addresses);
        assertEquals(0, jdnssArgs.backlog);
        assertEquals(0L, jdnssArgs.logback);
        assertEquals(0.0, jdnssArgs.shortPi, 0);
        assertEquals(0.0D, jdnssArgs.longPi, 0);
        assertFalse(jdnssArgs.debug);

        JPOFP.doJPOFP(jdnssArgs, prefs);

        assertEquals("TLS@0.0.0.0@853", jdnssArgs.addresses);
        assertEquals(5, jdnssArgs.backlog);
        assertEquals(5, jdnssArgs.logback);
        assertEquals(3.14, jdnssArgs.shortPi, 0.001);
        assertEquals(3.141592, jdnssArgs.longPi, 0.000000001);
        assertTrue(jdnssArgs.debug);
    }
}