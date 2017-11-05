package x.mobileapplication;

import java.util.ArrayList;

/**
 * Created by Petean Mihai on 11/5/2017.
 */

public class HobbyController {
    private ArrayList<Hobby> hobbies = new ArrayList<>();
    public ArrayList<String> getHobbies(){
        ArrayList<String> hobbyStrings = new ArrayList<>();
        for(Hobby hobby: hobbies){
            hobbyStrings.add(hobby.toString());
        }
        return hobbyStrings;
    }
    public void addHobby(String name, String description, int hours){
        hobbies.add(new Hobby(hours,name,description));
        System.out.println("added hobby faaam: " + getHobbies().size());
    }
}
