package com.khilkoleg.servermanagerbackend.service;

import com.khilkoleg.servermanagerbackend.model.Server;
import com.khilkoleg.servermanagerbackend.repository.ServerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collection;
import java.util.Random;

import static com.khilkoleg.servermanagerbackend.enumeration.Status.SERVER_DOWN;
import static com.khilkoleg.servermanagerbackend.enumeration.Status.SERVER_UP;
import static org.springframework.data.domain.PageRequest.*;;

/**
 * @author Oleg Khilko
 */

@Slf4j
@Service
@Transactional
@AllArgsConstructor
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Override
    public Server create(Server server) {
        log.info("Saving new server: {}", server.getName());
        server.setImageUrl(setServerImageUrl());
        return serverRepository.save(server);
    }

    @Override
    public Server ping(String ipAddress) {
        log.info("Pinging server IP: {}", ipAddress);
        Server server = serverRepository.findByIpAddress(ipAddress);
        server.setStatus(isReachable(ipAddress) ? SERVER_UP : SERVER_DOWN);
        serverRepository.save(server);
        return server;
    }

    @Override
    public Collection<Server> list(int limit) {
        log.info("Fetching all servers");
        return serverRepository.findAll(of(0, limit)).toList();
    }

    @Override
    public Server get(Long id) {
        log.info("Fetching server by id: {}", id);
        return serverRepository.findById(id).get();
    }

    @Override
    public Server update(Server server) {
        log.info("Updating server: {}", server.getName());
        return serverRepository.save(server);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting server by id: {}", id);
        serverRepository.deleteById(id);
        return true;
    }

    private String setServerImageUrl() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/server/image/" + imageNames[new Random().nextInt(3)])
                .toUriString();
    }

    private static boolean isReachable(String ipAddress) {
        try (var socket = new Socket()) {
            socket.connect(new InetSocketAddress(ipAddress, 80), 5000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
