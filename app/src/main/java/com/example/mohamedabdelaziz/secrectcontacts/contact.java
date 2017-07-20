package com.example.mohamedabdelaziz.secrectcontacts;

/**
 * Created by Mohamed Abd ELaziz on 7/20/2017.
 */

public class contact {

    String name , number ;

    public contact(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
