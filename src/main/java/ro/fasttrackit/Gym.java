package ro.fasttrackit;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Gym {
    private final List<GymMember> members;
    private final Map<GymMember, Duration> subscriptions;

    public Gym(List<GymMember> members) {
        this.members = new ArrayList<>(members);
        this.subscriptions = new HashMap<>();
    }

    public Map<GymMember, Duration> assignTime(String memberName, int time) {
        GymMember key = findMemberByName(memberName);
        Duration value = Duration.ofHours(time);
        subscriptions.put(key, value);
        return subscriptions;
    }

    public Double averageAgeOfMembers(){
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .map(GymMember::birthdate)
                .mapToInt(birthDate -> presentYear - birthDate.getYear())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No members to calculate for."));
    }

    public Optional<GymMember> maximumAgeOfMembers(){
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .max((m1, m2) -> m1.birthdate().compareTo(m2.birthdate()));
    }

    public Optional<GymMember> minimumAgeOfMembers(){
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .min((m1, m2) -> m1.birthdate().compareTo(m2.birthdate()));
    }

    public long totalRemainingTime(){
        long time = 0;
        for (Duration value : subscriptions.values()){
            time = value.toHours();
        }
        return time;
    }

    public String informationAboutMember(String memberName){
        GymMember member = findMemberByName(memberName);
        long remainingTime = subscriptions.get(member).toHours();
        return "%s born on %s, has %s hours left on his/her subscription".formatted(member.name(), member.birthdate(), remainingTime);
    }

    private GymMember findMemberByName(String memberName) {
        return members.stream()
                .filter(user -> user.name().equalsIgnoreCase(memberName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid name or no user named: " + memberName));
    }

}
