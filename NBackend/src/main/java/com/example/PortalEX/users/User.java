package com.example.PortalEX.users;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    private String username;
    @NonNull
    private String password;

}
