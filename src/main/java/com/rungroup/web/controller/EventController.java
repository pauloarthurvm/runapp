package com.rungroup.web.controller;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.dto.EventDto;
import com.rungroup.web.models.Event;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.security.SecurityUtil;
import com.rungroup.web.services.UserService;
import com.rungroup.web.services.impl.EventServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    private EventServiceImpl eventService;
    private UserService userService;

    @Autowired
    public EventController(EventServiceImpl eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        List<EventDto> eventDtoList = eventService.findAllEvents();
        UserEntity userEntity = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            userEntity = userService.findByUsername(username);
            model.addAttribute("userEntity", userEntity);
        }
        model.addAttribute("userEntity", userEntity);
        model.addAttribute("eventDtoList", eventDtoList);
        return "events-list";
    }

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}/new")
    public String createEvent(
            @PathVariable("clubId") Long clubId,
            @ModelAttribute("event") EventDto eventDto,
            Model model) {
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        UserEntity userEntity = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            userEntity = userService.findByUsername(username);
            model.addAttribute("userEntity", userEntity);
        }
        model.addAttribute("club", eventDto);
        model.addAttribute("userEntity", userEntity);
        model.addAttribute("eventDto", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("eventDto", eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateClub(@PathVariable("eventId") Long eventId,
                             @Valid @ModelAttribute("eventDto") EventDto eventDto,
                             BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute("eventDto", eventDto);
            return "events-edit";
        }
        EventDto originalEventDto = eventService.findByEventId(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(originalEventDto.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
