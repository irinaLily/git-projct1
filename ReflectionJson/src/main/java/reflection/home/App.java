package reflection.home;

import reflection.home.servise.Human;
import reflection.home.servise.JsonToClass;
import reflection.home.servise.ServisJson;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Ирина on 05.04.2016.
 */
public class App {
    public static void main(String[] args) {
        ServisJson servisJson=new ServisJson();
        JsonToClass jsonToClass=new JsonToClass();
        Human human=new Human("Ivan","Petrov","footboll", LocalDate.of(1997,07,22));
        System.out.println(human);
        String str=servisJson.toJson(human);
        System.out.println(str);
        System.out.println(jsonToClass.fromJson(str,Human.class));





    }


}
