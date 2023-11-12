package ro.fasttrackit;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.*;

public class Gym {
    private final List<GymMember> members;
    private final Map<GymMember, Duration> subscriptions;

    public Gym(List<GymMember> members) {
        this.members = new ArrayList<>(members);
        this.subscriptions = new HashMap<>();
    }

    public void registerTime(GymMember member, Duration duration) {
        if (subscriptions.get(member).toHours() > duration.toHours()) {
            Duration currentRemainingTime = subscriptions.get(member);
            Duration newRemainingTime = currentRemainingTime.minus(duration);
            subscriptions.put(member, newRemainingTime);
        } else {
            System.err.printf("%s needs to extend the subscription%n", member.name());
        }
        generateReport();
    }

    public Double averageAgeOfMembers() {
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .map(GymMember::birthdate)
                .mapToInt(birthDate -> presentYear - birthDate.getYear())
                .average()
                .orElseThrow(() -> new IllegalArgumentException("No members to calculate for."));
    }

    public Optional<GymMember> maximumAgeOfMembers() {
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .min((m1, m2) -> m1.birthdate().compareTo(m2.birthdate()));
    }

    public Optional<GymMember> minimumAgeOfMembers() {
        int presentYear = LocalDateTime.now().getYear();
        return members.stream()
                .max((m1, m2) -> m1.birthdate().compareTo(m2.birthdate()));
    }

    public long totalRemainingTime() {
        long time = 0;
        for (Duration value : subscriptions.values()) {
            time += value.toHours();
        }
        return time;
    }

    public Map<GymMember, Duration> addTime(String memberName, int time) {
        GymMember key = findMemberByName(memberName);
        Duration value = Duration.ofHours(time);
        subscriptions.put(key, value);
        return subscriptions;
    }

    public String informationAboutMember(String memberName) {
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

    public void generateReport() {
        List<String> red = new ArrayList<>();
        List<String> yellow = new ArrayList<>();
        List<String> green = new ArrayList<>();
        subscriptions.forEach((k, v) -> {
            if (v.toHours() < 10) {
                red.add(k.name());
            } else if (v.toHours() < 30) {
                yellow.add(k.name());
            } else
                green.add(k.name());
        });

        String fileName = String.valueOf(LocalDateTime.now()).replaceAll(":", "_");
        String path = "src/main/resources/remaining-time-report-" + fileName + ".txt";

        try (Writer writer = new FileWriter(path)) {
            writer.write("RED: %s".formatted(red));
            writer.write("\n");
            writer.write("YELLOW: %s".formatted(yellow));
            writer.write("\n");
            writer.write("GREEN: %s".formatted(green));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
