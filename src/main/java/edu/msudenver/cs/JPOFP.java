package edu.msudenver.cs;

import java.lang.reflect.Field;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

// Java Populate Object From Preferences
public class JPOFP {
    public static void doJPOFP(Object object, Preferences prefs) {
        final Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            // System.out.println("Field: " + fieldName);

            field.setAccessible(true);

            try {
                for (String key: prefs.keys()) {
                    // System.out.println("Key: " + key);
                    if (fieldName.equals(key)) {
                        setField(object, prefs, field, key);
                    }
                }
            } catch (BackingStoreException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setField(Object object, Preferences prefs, Field field, String key) {
        String type = field.getType().toString();

        try {
            switch (type) {
                case "class java.lang.String":
                    field.set(object, prefs.get(key, ""));
                    break;
                case "boolean":
                    field.set(object, prefs.getBoolean(key, false));
                    break;
                case "double":
                    field.set(object, prefs.getDouble(key, 0));
                    break;
                case "float":
                    // System.out.println("Before: " + field.getFloat(object));
                    field.set(object, prefs.getFloat(key,0));
                    // System.out.println("Before: " + field.getFloat(object));
                    break;
                case "int":
                    field.set(object, prefs.getInt(key, 0));
                    break;
                case "long":
                    field.set(object, prefs.getLong(key, 0));
                    break;
                default:
                    System.out.println("Unknown type: " + type);
                    break;
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
