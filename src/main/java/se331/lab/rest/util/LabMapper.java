package se331.lab.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.lab.rest.entity.*;
import se331.lab.rest.security.user.User;

import java.util.List;

@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);

    EventDTO getEventDTO(Event event);
    List<EventDTO> getEventDTO(List<Event> events);

    OrganizerDTO getOrganizerDTO(Organizer organizer);
    List<OrganizerDTO> getOrganizerDTO(List<Organizer> organizers);

    @Mapping(target = "roles",source = "user.roles")
    OrganizerAuthDTO getOrganizerAuthDTO(Organizer organizer);

    ParticipantDTO getParticipantDTO(Participant participant);
    List<ParticipantDTO> getParticipantDTO(List<Participant> participants);
    OrganizerDTO getOrganizerDTO(User user);

    // Add mappings for EventParticipantDTO
    EventParticipantDTO getEventParticipantDTO(Participant participant);
    List<EventParticipantDTO> getEventParticipantDTO(List<Participant> participants);
}