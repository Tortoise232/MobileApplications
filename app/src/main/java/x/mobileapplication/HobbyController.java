package x.mobileapplication;

import android.app.Application;
import android.content.Context;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Petean Mihai on 11/5/2017.
 */

public class HobbyController {
    public HobbyRepository repo;
    public HobbyController(){
        try {
            repo = new HobbyRepository();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<String> getHobbies(){
        ArrayList<String> hobbyStrings = new ArrayList<>();
        ArrayList<Hobby> hobbies = repo.getHobbies();
        for(Hobby hobby: hobbies){
            hobbyStrings.add(hobby.toString());
        }
        return hobbyStrings;
    }
    public void addHobby(String name, String description, int hours){
        repo.addHobby(new Hobby(hours,name,description));
        System.out.println("added hobby faaam: " + getHobbies().size());
    }
}
