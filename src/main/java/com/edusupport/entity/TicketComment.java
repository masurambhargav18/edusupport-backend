package com.edusupport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name="ticket_comments")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TicketComment {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional=false) @JoinColumn(name="ticket_id") private Ticket ticket;
    @ManyToOne(optional=false) @JoinColumn(name="author_id") private User author;
    @Column(nullable=false, columnDefinition="TEXT") private String message;
    private LocalDateTime createdAt;
}
