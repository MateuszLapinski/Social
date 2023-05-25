package com.example.PortalEX.posts;

import com.example.PortalEX.users.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="posts")
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NonNull
    @ManyToOne
    private User user;
    @NonNull
    private String body;


}
