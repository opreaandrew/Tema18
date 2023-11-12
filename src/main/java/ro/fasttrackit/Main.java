package ro.fasttrackit;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Gym gym = myGym();


//        gym.registerTime(new GymMember("Nimrod",LocalDateTime.of(2010,11,15,20,33)), Duration.ofHours(5));

//        System.out.println("Average Age of Members: " + gym.averageAgeOfMembers());
//        System.out.println("Maximum Age of Members: " + gym.maximumAgeOfMembers());
//        System.out.println("Minimum Age of Members: " + gym.minimumAgeOfMembers());
//        System.out.println("Total Remaining Time: " + gym.totalRemainingTime());
//        System.out.println("Information About Member: " + gym.informationAboutMember("Levi"));
//        gym.generateReport();
    }

    //Recomandarea lui Intellij
    private static Gym myGym() {
        GymMember ana = new GymMember("Ana",LocalDateTime.of(1900,1,5,20,33));

        List<GymMember> members = List.of(

                //Stiu ca hour si minute nu-s relevante, n-am apucat sa ma informez de cum scap de ele.
                ana,
                new GymMember("Mark", LocalDateTime.of(1994, 1, 2, 15, 22)),
                new GymMember("Levi", LocalDateTime.of(1985, 2, 3, 15, 22)),
                new GymMember("Andrei", LocalDateTime.of(1990, 3, 23, 15, 22)),
                new GymMember("Tudor", LocalDateTime.of(2000, 4, 7, 15, 22)));

        Gym gym = new Gym(members);

        System.out.println(gym.addTime("Mark", 5));
        System.out.println(gym.addTime("Levi", 15));
        System.out.println(gym.addTime("Andrei", 2));
        System.out.println(gym.addTime("Tudor", 35));
        System.out.println(gym.addTime("Ana", 50));
        gym.registerTime(ana, Duration.ofHours(5));

        return gym;
    }
}