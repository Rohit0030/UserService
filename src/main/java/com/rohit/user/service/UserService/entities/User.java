package com.rohit.user.service.UserService.entities;

import jakarta.persistence.*;
import lombok.*;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "micro_users")
public class User {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email ID")
    private String email;

    @Column(name = "About")
    private String about;

    @Transient  // @Transient mean this rating entitie does not save in database yeh ignore krdega database me save nhi karega
    private List<Rating> ratings = new ArrayList<>();
}
