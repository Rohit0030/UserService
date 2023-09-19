package com.rohit.user.service.UserService.payLoad;

import lombok.*;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse {

    private String messsage;
    private Boolean success;
    private HttpStatus status;

}
