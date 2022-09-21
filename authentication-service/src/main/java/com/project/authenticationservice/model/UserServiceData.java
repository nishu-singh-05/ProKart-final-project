package com.project.authenticationservice.model;


import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "user_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceData {

    @Id
    private String userName;
    private String password;
    private String role;
}
