package za.ac.cput.novacinemaapp.repository;

// Entity for Ticket
// Author Amaan Allie
// 17 May 2024

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.novacinemaapp.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, String> {

    // Method to find tickets by ticketID (now String)
    List<Ticket> findTicketByTicketID(String ticketId);

    // Method to find tickets by userID (now String)
    List<Ticket> findTicketsByUserID(String userID);
}


