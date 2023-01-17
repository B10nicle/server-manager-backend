package com.khilkoleg.servermanagerbackend.model;

import com.khilkoleg.servermanagerbackend.enumeration.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.Hibernate;
import lombok.*;

import java.util.Objects;

/**
 * @author Oleg Khilko
 */


@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "server", uniqueConstraints = @UniqueConstraint(columnNames = "ip_address"))
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ip_address")
    @NotEmpty(message = "IP Address cannot be empty or null")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Server server = (Server) o;
        return id != null && Objects.equals(id, server.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", ipAddress='" + ipAddress + '\'' +
                ", name='" + name + '\'' +
                ", memory='" + memory + '\'' +
                ", type='" + type + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
