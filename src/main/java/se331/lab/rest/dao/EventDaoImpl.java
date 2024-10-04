package se331.lab.rest.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.rest.entity.Event;

public class EventDaoImpl implements EventDao {


    @Override
    public Integer getEventSize() {
        return 0;
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page) {
        return null;
    }

    @Override
    public Event getEventById(Long id) {
        return null;
    }

    @Override
    public Event saveEvent(Event event) {
        return null;
    }

    @Override
    public Page<Event> getEvents(String title, Pageable page) {
        return null;
    }
}
