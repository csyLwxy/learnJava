package com.learn.corejava.ch5.objectAnalyzer;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();
        /**
         * Converts an object to a string representation that lists all fields.
         *  @param obj an object
         * @return a string with the object's class name and all field names and
         * values
         */
        public String toString(Object obj) {
            if (obj == null) {
                return "null";
            }
            if (visited.contains(obj)) {
                return "...";
            }
            visited.add(obj);
            Class cl = obj.getClass();
            if (cl == String.class) {
                return (String) obj;
            }
            if (cl.isArray()) {
                StringBuilder r = new StringBuilder(cl.getComponentType() + "[]{");
                for (int i = 0; i < Array.getLength(obj); i++) {
                    if (i > 0) {
                        r.append(",");
                    }
                    Object val = Array.get(obj, i);
                    if (cl.getComponentType().isPrimitive()) {
                        r.append(val);
                    } else {
                        r.append(toString(val));
                    }
                }
                return r + "}";
            }

            StringBuilder r = new StringBuilder(cl.getName());
            // inspect the fields of this class and all superclasses
            do {
                r.append("[");
                Field[] fields = cl.getDeclaredFields();
                AccessibleObject.setAccessible(fields, true);
                // get the names and values of all fields
                for (Field f : fields) {
                    if (!Modifier.isStatic(f.getModifiers()))
                        {
                        if (!r.toString().endsWith("[")) {
                            r.append(",");
                        }
                        r.append(f.getName()).append("=");
                        try {
                            Class t = f.getType();
                            Object val = f.get(obj);
                            if (t.isPrimitive()) {
                                r.append(val);
                            } else {
                                r.append(toString(val));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                r.append("]");
                cl = cl.getSuperclass();
            }
            while (cl != null);
            return r.toString();
        }
}
