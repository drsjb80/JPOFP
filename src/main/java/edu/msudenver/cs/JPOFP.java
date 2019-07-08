package edu.msudenver.cs;

import java.lang.reflect.Field;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;


public class JPOFP {
    private final Field[] fields;

    public JPOFP(Object object, Preferences prefs) {
        fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
        }

        try {
            for (String key: prefs.keys()) {
                System.out.println("key: " + key);
                Field field = getField(key);
                System.out.println("field: " + field);

                if (field == null) {
                    continue;
                }

                System.out.println("type: " + field.getType());

                try {
                    String type = field.getType().toString();
                    switch (type) {

                        case "String":
                            field.set(object, prefs.get(key, ""));
                            break;
                        case "boolean":
                            field.set(object, prefs.getBoolean(key, false));
                            break;
                        case "double":
                            field.set(object, prefs.getDouble(key, 0));
                            break;
                        case "float":
                            field.set(object, prefs.getFloat(key,0));
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
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }

    private Field getField(String key) {
        for (Field field : fields) {
            String name = field.getName();

            if (name.equals(key))
                return (field);
        }

        return (null);
    }
}
