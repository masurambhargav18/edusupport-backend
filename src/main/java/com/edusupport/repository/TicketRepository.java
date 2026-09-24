package com.edusupport.repository;
import com.edusupport.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findByStudentId(Long studentId);
    List<Ticket> findByAssignedToId(Long staffId);
    List<Ticket> findByStatus(TicketStatus status);
}
