package com.edusupport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ticket_activity")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TicketActivity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="ticket_id") private Ticket ticket;
    @ManyToOne(optional=false) @JoinColumn(name="actor_id") private User actor;
    @Column(nullable=false) private String action;
    @Column(columnDefinition="TEXT") private String details;
    private LocalDateTime createdAt;
}
