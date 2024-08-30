package se331.lab.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;
import jakarta.annotation.PostConstruct;
import se331.lab.rest.service.EventService;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Controller
public class EventController {
    final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getEventLists(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {

//        perPage = (perPage == null) ? eventList.size() : perPage;
//        page = (page == null) ? 1 : page;
//        Integer firstIndex = (page - 1) * perPage;
//        List<Event> output = new ArrayList<>();
        List<Event> output = null;
        Integer eventSize = eventService.getEventSize();
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(eventSize));
        try {
    output = eventService.getEvents(perPage, page);
return new ResponseEntity<>(output,responseHeader, HttpStatus.OK);
        } catch (IndexOutOfBoundsException ex) {
            return ResponseEntity.ok(output);
        }
//        return ResponseEntity.ok(output);
    }
    @GetMapping("events/{id}")
    public ResponseEntity<?> getEventById(@PathVariable("id") Long id)  {
        Event output = eventService.getEvent(id);
        if(output != null) {
            return ResponseEntity.ok(output);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }




}
