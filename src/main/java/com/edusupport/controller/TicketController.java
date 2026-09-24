package com.edusupport.controller;

import com.edusupport.dto.*;
import com.edusupport.entity.Ticket;
import com.edusupport.entity.TicketActivity;
import com.edusupport.entity.TicketComment;
import com.edusupport.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:5173")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping
    public Ticket create(@Valid @RequestBody CreateTicketRequest request) {
        return service.create(request);
    }

    @GetMapping
    public List<Ticket> all() {
        return service.all();
    }

    @GetMapping("/{id}")
    public Ticket getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/student/{id}")
    public List<Ticket> byStudent(@PathVariable Long id) {
        return service.byStudent(id);
    }

    @GetMapping("/{id}/activities")
    public List<TicketActivity> activities(@PathVariable Long id) {
        return service.getActivities(id);
    }

    @GetMapping("/{id}/comments")
    public List<TicketComment> comments(@PathVariable Long id) {
        return service.getComments(id);
    }

    @PutMapping("/{id}")
    public Ticket update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTicketRequest request) {
        return service.update(id, request);
    }

    @PostMapping("/{id}/comments")
    public Ticket comment(
            @PathVariable Long id,
            @Valid @RequestBody AddCommentRequest request) {
        return service.addComment(id, request);
    }
}