package com.project.usermodule.kafka;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {
    private String userName;
    private String password;
    private String role;
}
