package ro.fasttrackit;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gym gym = myGym();

        System.out.println("Average Age of Members: " + gym.averageAgeOfMembers());
//        System.out.println("Maximum Age of Members: " + gym.maximumAgeOfMembers());
//        System.out.println("Minimum Age of Members: " + gym.minimumAgeOfMembers());
//        System.out.println("Total Remaining Time: " + gym.totalRemainingTime());
//        System.out.println("Information About Member: " + gym.informationAboutMember("Levi"));
    }

    //Recomandarea lui Intellij
    private static Gym myGym() {
        List<GymMember> members = List.of(
                new GymMember("Mark", LocalDateTime.of(1994, 1, 2, 15, 22)),
                new GymMember("Levi", LocalDateTime.of(1985, 2, 3, 15, 22)),
                new GymMember("Andrei", LocalDateTime.of(1990, 3, 23, 15, 22)),
                new GymMember("Tudor", LocalDateTime.of(2000, 4, 7, 15, 22)));

        Gym gym = new Gym(members);

        gym.addTime("Mark", 5);
        gym.addTime("Levi", 15);
        gym.addTime("Andrei", 2);
        gym.addTime("Tudor", 35);
        return gym;
    }
}