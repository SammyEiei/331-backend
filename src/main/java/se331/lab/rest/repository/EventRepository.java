package se331.lab.rest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import se331.lab.rest.entity.Event;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAll();
    Page<Event> findByTitle(String title, Pageable pageRequest);
    Page<Event> findByTitleContainingOrDescriptionContaining(String title, String description, Pageable pageRequest);
    Page<Event> findByTitleContainingAndDescriptionContaining(String title, String description, Pageable pageable);
//    In the previous implementation (with the OR condition), the query would return any event where either the title or the description contained the search term.
//	•	Now, with the AND condition, the query will only return events where both the title and the description contain the search term.

    // New method to include organizer name in the search
    Page<Event> findByTitleContainingOrDescriptionContainingOrOrganizer_NameContaining(
        String title, String description, String organizerName, Pageable pageRequest);

}
