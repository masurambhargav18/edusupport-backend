package com.edusupport.service;

import com.edusupport.dto.*;
import com.edusupport.entity.Ticket;
import com.edusupport.entity.TicketActivity;
import com.edusupport.entity.TicketComment;

import java.util.List;

public interface TicketService {

    Ticket create(CreateTicketRequest request);

    List<Ticket> all();

    Ticket getById(Long id);

    List<Ticket> byStudent(Long id);

    Ticket update(Long id, UpdateTicketRequest request);

    Ticket addComment(Long id, AddCommentRequest request);

    List<TicketActivity> getActivities(Long ticketId);

    List<TicketComment> getComments(Long ticketId);
}