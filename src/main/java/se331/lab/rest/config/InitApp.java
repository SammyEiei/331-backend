package se331.lab.rest.config;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organizer;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizerRepository;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;
        final OrganizerRepository organizerRepository;

    public InitApp(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//        eventRepository.save(Event.builder()
        Organizer org1,org2,org3;
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder().name("Chiangmai").build());
        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CMU Building")
                .date("3rd Sept")
                .time("13.00-16.00 p.m.")
                .petAllowed(false)
                .build());

                tempEvent.setOrganizer(org1);
                org1.getOwnEvents().add(tempEvent);
                tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("08.00am-4.00 pm.")
                .petAllowed(false)
                .build());

                tempEvent.setOrganizer(org1);
                org1.getOwnEvents().add(tempEvent);
                tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .build());

                tempEvent.setOrganizer(org2);
                org2.getOwnEvents().add(tempEvent);
                tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .build());
                tempEvent.setOrganizer(org3);
                org3.getOwnEvents().add(tempEvent);
        // Initialize Organization data
           }
}