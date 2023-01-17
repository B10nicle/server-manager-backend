package com.khilkoleg.servermanagerbackend.repository;

import com.khilkoleg.servermanagerbackend.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Oleg Khilko
 */

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByIpAddress(String ipAddress);
}
