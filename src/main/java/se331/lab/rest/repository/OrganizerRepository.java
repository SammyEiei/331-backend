package se331.lab.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.rest.entity.Organizer;

import java.util.Optional;

public interface  OrganizerRepository extends JpaRepository<Organizer, Long> {
    Organizer findByName(String name);  // เมธอดนี้จะค้นหา Organizer จาก name

}
