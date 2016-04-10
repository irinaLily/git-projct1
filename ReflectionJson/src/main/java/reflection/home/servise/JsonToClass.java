package reflection.home.servise;

import reflection.home.annotations.CustomDateFormat;
import reflection.home.annotations.JsonValue;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Ирина on 08.04.2016.
 */
public class JsonToClass {
    public <T> T fromJson (String json,Class<T> clazz){
        String json1=json.replace('"',' ');
        String []mas=json1.split(",");
        Map<String,String> fieldobject=new HashMap<String, String>();
        Field []fields=clazz.getDeclaredFields();
        for (String str:mas) {
            String str1[] = str.split(":");
            for (String s:str1){}
            fieldobject.put(str1[0].trim(), str1[1].trim());
        }
        Set<Map.Entry<String,String>> set=fieldobject.entrySet();
       for (Map.Entry<String,String> mapEntry:set){
             System.out.println(mapEntry.getKey()+"+++++"+mapEntry.getValue());
        }
        //for (Field field:fields){}
        try {
            Object object=clazz.newInstance();
           // for (Map.Entry<String,String> mapEntry:set){
           for (Field field: clazz.getDeclaredFields()) {
               String fieldName = field.getName();
               field.setAccessible(true);
                   if (field.getAnnotations().length == 0) {
                       field.set(object, fieldobject.get(fieldName));
                   } else {
                       for (Annotation annotation : field.getDeclaredAnnotations()) {
                           if (annotation.annotationType().equals(CustomDateFormat.class)) {
                             field.set(object, LocalDate.parse(fieldobject.get(fieldName),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                           }
                           if (annotation.annotationType().equals(JsonValue.class)) {
                               JsonValue jsonValue = field.getDeclaredAnnotation(JsonValue.class);
                               field.set(object, fieldobject.get(jsonValue.name()));
                           }
                       }
                   }
               }
            return (T)object;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T)null;
    }
}
