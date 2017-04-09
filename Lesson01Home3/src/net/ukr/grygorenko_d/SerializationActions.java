package net.ukr.grygorenko_d;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

public class SerializationActions {
    private SerializableClass sc;
    private HashMap<String,String> map;

    public HashMap getMap() {
        return map;
    }

    public SerializationActions(SerializableClass sc) {
        super();
        this.sc = sc;
        this.map = new HashMap<>();
        createHashMap();
    }

    public SerializationActions() {
        super();
    }

    private final void createHashMap() {
        Class<?> cls = sc.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field field:fields) {
            if(field.isAnnotationPresent(SerializableField.class)){
                field.setAccessible(true);
                Object value = null;
                try {
                    value = field.get(sc);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
               map.put(field.getName(), value.toString());

            }
        }
    }

    public File serialization(){
        File file = new File("saved.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try(PrintWriter pw  = new PrintWriter(file)){
            map.forEach((key,value) -> pw.println(key + " : " + value));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return file;
    }

    public SerializableClass deserialize(File file){
        SerializableClass restored = new SerializableClass();
        Class<?> cls = restored.getClass();
        String[] read = fromFile(file);
        for (String line: read) {
            String[] pair = line.split(" : ");
            try {
                Field field = cls.getDeclaredField(pair[0]);
                if(Modifier.isPrivate(field.getModifiers())){
                    field.setAccessible(true);
                }
                Class<?> fieldType = field.getType();
                if(fieldType == char.class){
                    field.setChar(restored, Character.valueOf(pair[1].charAt(0)));
                }else if (fieldType == boolean.class){
                    field.set(restored, Boolean.valueOf(pair[1]));
                }else if (fieldType == int.class){
                    field.set(restored, Integer.valueOf(pair[1]));
                }else if (fieldType == long.class){
                    field.set(restored, Long.parseLong(pair[1]));
                }else if (fieldType == float.class){
                    field.set(restored,Float.parseFloat(pair[1]));
                }else if (fieldType == double.class){
                    field.set(restored, Double.parseDouble(pair[1]));
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    return restored;
    }

    private String[] fromFile(File file) {
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            StringBuilder sb = new StringBuilder();
            String str = "";
            while((str = br.readLine()) != null){
                sb.append(str).append(System.lineSeparator());
            }
            return sb.toString().split(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    return null;
    }
}
