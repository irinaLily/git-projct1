package reflection.home.servise;

import reflection.home.annotations.CustomDateFormat;
import reflection.home.annotations.JsonValue;

import java.beans.Transient;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by Ирина on 05.04.2016.
 */
public class Human implements Serializable{

    private String firstName;
    private String lasttName;
    @JsonValue(name = "fun")
    private String hobby;
    @CustomDateFormat(format ="dd-MM-yyyy")
    private LocalDate birthDate;

    public Human(String firstName, String lasttName, String hobby, LocalDate birthDate) {
        this.firstName = firstName;
        this.lasttName = lasttName;
        this.hobby = hobby;
        this.birthDate=birthDate;
    }

    public Human() {
        this.firstName = null;
        this.lasttName = null;
        this.hobby = null;
        this.birthDate=null;
    }

    @Override
    public String toString() {
        return  "firstName='" + firstName + '\'' +
                ", lasttName='" + lasttName + '\'' +
                ", hobby='" + hobby + '\'' +
                ", birthDate=" + birthDate ;
    }
}
