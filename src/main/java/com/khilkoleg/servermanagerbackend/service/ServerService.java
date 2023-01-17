package com.khilkoleg.servermanagerbackend.service;

import com.khilkoleg.servermanagerbackend.model.Server;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Oleg Khilko
 */

public interface ServerService {

    Server create(Server server);

    Server ping(String ipAddress) throws IOException;

    Collection<Server> list(int limit);

    Server get(Long id);

    Server update(Server server);

    Boolean delete(Long id);
}
