package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.entity.Participant;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizerRepository;
import se331.lab.rest.repository.ParticipantRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    final EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {
        // Create organizers
        Organizer org1 = createOrganizer("Tech Conference Co.");
        Organizer org2 = createOrganizer("Art Festival Org.");
        Organizer org3 = createOrganizer("Music Event Ltd.");

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
}