package com.edusupport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="tickets")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Ticket {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, unique=true) private String ticketNumber;
    @ManyToOne @JoinColumn(name="student_id", nullable=false) private User student;
    @ManyToOne @JoinColumn(name="assigned_to") private User assignedTo;
    @Column(nullable=false) private String category;
    @Column(nullable=false) private String subject;
    @Column(nullable=false, columnDefinition="TEXT") private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private Priority priority;
    @Enumerated(EnumType.STRING) @Column(nullable=false) private TicketStatus status;
    private LocalDateTime createdAt, updatedAt, dueAt, resolvedAt, closedAt;
}
