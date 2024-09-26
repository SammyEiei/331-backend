package se331.lab.rest.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Organizer {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Exclude

    Long id;
    String name;
    @OneToMany(mappedBy = "organizer")
    List<Event> ownEvents;
}