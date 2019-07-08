package edu.msudenver.cs;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

import static org.junit.Assert.*;

class JdnssArgs {
    private int backlog;
}

public class JPOFPTest {
    @Test
    public void simple() {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("jdnss.xml"));
            Preferences.importPreferences(is);
            is.close();
        } catch (IOException | InvalidPreferencesFormatException e) {
            e.printStackTrace();
            return;
        }

        Preferences prefs = Preferences.userRoot().node("edu/msudenver/cs/jdnss");
        try {
            for (String s : prefs.keys()) {
                System.out.println(s);
            }
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }

        new JPOFP(new JdnssArgs(), prefs);
    }
}