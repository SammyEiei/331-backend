package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizerRepository;
import se331.lab.rest.repository.ParticipantRepository;
import se331.lab.rest.security.user.Role;
import se331.lab.rest.security.user.User;
import se331.lab.rest.security.user.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;
    final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Create organizers with updated names
        Organizer org1 = createOrganizer("Camt");
        Organizer org2 = createOrganizer("Chiangmai");
        Organizer org3 = createOrganizer("CMU");

        // Create participants with updated data
        Participant p1 = createParticipant("Michael Brown", "321-654-0987");
        Participant p2 = createParticipant("Sofia Davis", "789-012-3456");
        Participant p3 = createParticipant("Liam Johnson", "456-789-0123");
        Participant p4 = createParticipant("Emma Wilson", "654-321-0987");
        Participant p5 = createParticipant("Noah Miller", "987-654-3210");

        // Store participants in a list for easy access
        List<Participant> participants = Arrays.asList(p1, p2, p3, p4, p5);

        // Create events
        Event event1 = createEvent(
                "Technology", "Innovate Tech Expo", "A showcase of the latest tech innovations",
                "Convention Center", "15th July", "9.00am-5.00pm.", false, org1
        );
        Event event2 = createEvent(
                "Art", "Modern Art Exhibition", "An exhibition of modern art pieces",
                "Art Gallery", "22nd August", "10.00am-6.00pm.", false, org2
        );
        Event event3 = createEvent(
                "Music", "Summer Music Festival", "Live performances by top artists",
                "City Park", "5th September", "2.00pm-11.00pm.", true, org3
        );
        Event event4 = createEvent(
                "Technology", "AI & Robotics Conference", "Exploring advancements in AI and robotics",
                "Tech Hub", "30th October", "8.00am-4.00pm.", false, org1
        );

        // Assign participants to events
        assignParticipantsToEvent(event1, p1, p2, p3);
        assignParticipantsToEvent(event2, p2, p4, p5);
        assignParticipantsToEvent(event3, p1, p3, p5);
        assignParticipantsToEvent(event4, p3, p4, p5);

        // Save events (if not already saved in createEvent)
        eventRepository.saveAll(Arrays.asList(event1, event2, event3, event4));

        addUsers();
    }

    private Organizer createOrganizer(String name) {
        Organizer organizer = Organizer.builder()
                .name(name)
                .ownEvents(new ArrayList<>())
                .build();
        return organizerRepository.save(organizer);
    }

    private Participant createParticipant(String name, String telNo) {
        Participant participant = Participant.builder()
                .name(name)
                .telNo(telNo)
                .eventHistory(new ArrayList<>())
                .build();
        return participantRepository.save(participant);
    }

    private Event createEvent(
            String category, String title, String description,
            String location, String date, String time, boolean petAllowed,
            Organizer organizer
    ) {
        Event event = Event.builder()
                .category(category)
                .title(title)
                .description(description)
                .location(location)
                .date(date)
                .time(time)
                .petAllowed(petAllowed)
                .participants(new ArrayList<>())
                .organizer(organizer)
                .build();

        // Add event to organizer's list
        organizer.getOwnEvents().add(event);

        return eventRepository.save(event);
    }

    private void assignParticipantsToEvent(Event event, Participant... participants) {
        event.getParticipants().addAll(Arrays.asList(participants));
        for (Participant participant : participants) {
            participant.getEventHistory().add(event);
        }
    }

    private void addUsers() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        User user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .build();

        User user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .build();

        User user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disableUser@user.com")
                .enabled(false)
                .build();

        user1.getRoles().add(Role.ROLE_USER);
        user1.getRoles().add(Role.ROLE_ADMIN);
        user2.getRoles().add(Role.ROLE_USER);
        user3.getRoles().add(Role.ROLE_USER);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}