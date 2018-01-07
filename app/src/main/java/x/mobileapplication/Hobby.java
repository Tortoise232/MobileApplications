package x.mobileapplication;

/**
 * Created by Petean Mihai on 11/5/2017.
 */

public class Hobby {
    private int hoursInvested;
    private String name;
    private String description;

    public Hobby(int hoursInvested, String name, String description) {
        this.hoursInvested = hoursInvested;
        this.name = name;
        this.description = description;
    }

    public Hobby(String hobbyStr) {
        String[] hobbyArr = hobbyStr.split("\n");
        this.name = hobbyArr[0];
        this.description = hobbyArr[1];
        this.hoursInvested = Integer.parseInt(hobbyArr[2]);
    }


    public int getHoursInvested() {
        return hoursInvested;
    }

    public void setHoursInvested(int hoursInvested) {
        this.hoursInvested = hoursInvested;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + '\n' +
                 description + '\n' +
                hoursInvested;
    }
}
