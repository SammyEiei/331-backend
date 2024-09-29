package se331.lab.rest.dao;

import se331.lab.rest.entity.Organizer;

import java.util.Optional;

public interface OrganizerDao {
    Optional<Organizer> findById(Long id);
}
