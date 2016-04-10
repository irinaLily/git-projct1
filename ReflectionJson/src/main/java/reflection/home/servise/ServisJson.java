package reflection.home.servise;

import reflection.home.annotations.CustomDateFormat;
import reflection.home.annotations.JsonValue;

import javax.swing.text.DateFormatter;
import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
/**
 * Created by Ирина on 05.04.2016.
 */
public class ServisJson {
    public String toJson(Object o) {
        Class<?> c=o.getClass();
        StringBuilder sb=new StringBuilder();
        for (Field field:c.getDeclaredFields()){
            String fieldName=field.getName();
            field.setAccessible(true);
            try {
                if(field.get(o)==null)continue;
                if(field.getAnnotations().length==0){
                    sb.append(toStringClacc(fieldName, field.get(o).toString()));
                }else {
                    for (Annotation annotation : field.getAnnotations()) {
                        if (annotation.annotationType().equals(Transient.class)) {
                            System.out.println("trnsient" + fieldName);
                        }
                        if (annotation.annotationType().equals(CustomDateFormat.class)) {
                            CustomDateFormat customDateFormat = field.getAnnotation(CustomDateFormat.class);
                            if (customDateFormat.format().equals(" ")) {
                                sb.append(toStringClacc(fieldName, field.get(o).toString()));
                            } else {
                                DateTimeFormatter dateTimeTemplate = DateTimeFormatter.ofPattern(customDateFormat.format());
                                LocalDate fieldValuel = (LocalDate) field.get(o);
                                sb.append(toStringClacc(fieldName, fieldValuel.format(dateTimeTemplate)));
                            }
                        } else if (annotation.annotationType().equals(JsonValue.class)) {
                            JsonValue nameJson = field.getAnnotation(JsonValue.class);
                            sb.append(toStringClacc(nameJson.name(), field.get(o).toString()));
                        }
                    }
                }
            }catch (IllegalAccessException e){
                System.out.println("not odject"); }
        }
     return sb.toString();
    }
    public String toStringClacc(String name,String value){
        StringBuilder sb=new StringBuilder();
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
        sb.append(":");
        sb.append("\"");
        sb.append(value);
        sb.append("\"");
        sb.append(",");
        return sb.toString();
    }


}
