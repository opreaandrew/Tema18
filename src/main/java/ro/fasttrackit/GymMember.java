package ro.fasttrackit;

import java.time.LocalDateTime;

public record GymMember(
        String name,
        LocalDateTime birthdate
) {
}
