package com.edusupport.service;

import com.edusupport.dto.*;
import com.edusupport.entity.*;
import com.edusupport.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository tickets;
    private final UserRepository users;
    private final TicketCommentRepository comments;
    private final TicketActivityRepository activities;

    public TicketServiceImpl(
            TicketRepository tickets,
            UserRepository users,
            TicketCommentRepository comments,
            TicketActivityRepository activities) {

        this.tickets = tickets;
        this.users = users;
        this.comments = comments;
        this.activities = activities;
    }

    @Override
    public Ticket create(CreateTicketRequest r) {

        User student = users.findById(r.studentId()).orElseThrow();

        LocalDateTime now = LocalDateTime.now();

        Ticket t = Ticket.builder()
                .ticketNumber("EDU-" +
                        UUID.randomUUID()
                                .toString()
                                .substring(0, 8)
                                .toUpperCase())
                .student(student)
                .category(r.category())
                .subject(r.subject())
                .description(r.description())
                .priority(r.priority())
                .status(TicketStatus.OPEN)
                .createdAt(now)
                .updatedAt(now)
                .dueAt(now.plusHours(slaHours(r.priority())))
                .build();

        Ticket saved = tickets.save(t);

        activities.save(
                TicketActivity.builder()
                        .ticket(saved)
                        .actor(student)
                        .action("TICKET_CREATED")
                        .details("Ticket created")
                        .createdAt(now)
                        .build()
        );

        return saved;
    }

    @Override
    public List<Ticket> all() {
        return tickets.findAll();
    }

    @Override
    public Ticket getById(Long id) {
        return tickets.findById(id).orElseThrow();
    }

    @Override
    public List<Ticket> byStudent(Long id) {
        return tickets.findByStudentId(id);
    }

    @Override
    public Ticket update(Long id, UpdateTicketRequest r) {

        Ticket t = tickets.findById(id).orElseThrow();

        User actor = null;

        if (r.assignedToId() != null) {
            actor = users.findById(r.assignedToId()).orElseThrow();
            t.setAssignedTo(actor);
        }

        t.setStatus(r.status());
        t.setPriority(r.priority());
        t.setUpdatedAt(LocalDateTime.now());

        if (r.status() == TicketStatus.RESOLVED) {
            t.setResolvedAt(LocalDateTime.now());
        }

        if (r.status() == TicketStatus.CLOSED) {
            t.setClosedAt(LocalDateTime.now());
        }

        Ticket saved = tickets.save(t);

        activities.save(
                TicketActivity.builder()
                        .ticket(saved)
                        .actor(actor)
                        .action("TICKET_UPDATED")
                        .details(
                                "Status changed to " + r.status()
                                        + ", priority changed to " + r.priority()
                        )
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return saved;
    }

    @Override
    public Ticket addComment(Long id, AddCommentRequest r) {

        Ticket t = tickets.findById(id).orElseThrow();

        User author = users.findByEmail(r.authorEmail()).orElseThrow();

        comments.save(
                TicketComment.builder()
                        .ticket(t)
                        .author(author)
                        .message(r.message())
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        t.setUpdatedAt(LocalDateTime.now());

        Ticket saved = tickets.save(t);

        activities.save(
                TicketActivity.builder()
                        .ticket(saved)
                        .actor(author)
                        .action("COMMENT_ADDED")
                        .details("Comment added to ticket")
                        .createdAt(LocalDateTime.now())
                        .build()
        );

        return saved;
    }

    @Override
    public List<TicketActivity> getActivities(Long ticketId) {
        tickets.findById(ticketId).orElseThrow();
        return activities.findByTicketIdOrderByCreatedAtDesc(ticketId);
    }

    @Override
    public List<TicketComment> getComments(Long ticketId) {
        tickets.findById(ticketId).orElseThrow();
        return comments.findByTicketIdOrderByCreatedAtAsc(ticketId);
    }

    private long slaHours(Priority p) {

        return switch (p) {
            case CRITICAL -> 4;
            case HIGH -> 8;
            case MEDIUM -> 24;
            case LOW -> 72;
        };
    }
}