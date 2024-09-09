package se331.lab.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.rest.entity.Event;
import se331.lab.rest.entity.Organization;
import se331.lab.rest.repository.EventRepository;
import se331.lab.rest.repository.OrganizationRepository;


@Component
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    OrganizationRepository organizationRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CMU Building")
                .date("3rd Sept")
                .time("13.00-16.00 p.m.")
                .petAllowed(false)
                .organizer("CAMT").build());

        eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU Convention hall")
                .date("21th Jan")
                .time("08.00am-4.00 pm.")
                .petAllowed(false)
                .organizer("CMU").build());

        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petAllowed(false)
                .organizer("Chiang Mai").build());

        eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petAllowed(true)
                .organizer("Chiang Mai Municipality").build());
        // Initialize Organization data
        organizationRepository.save(Organization.builder()
                .name("Tech Corp")
                .address("123 Main St")
                .contact("555-1234")
                .email("info@techcorp.com")
                .website("https://www.techcorp.com")
                .build());

        organizationRepository.save(Organization.builder()
                .name("Eco Green")
                .address("456 Green Way")
                .contact("555-5678")
                .email("info@ecogreen.com")
                .website("https://www.ecogreen.com")
                .build());
        organizationRepository.save(Organization.builder()
                .name("Health Inc.")
                .address("789 Health Blvd")
                .contact("555-9876")
                .email("contact@healthinc.com")
                .website("https://www.healthinc.com")
                .build());

    }
}