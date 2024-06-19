package com.acciojob.BookMyShowMAY.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private Integer age;
    private String emailId;
    private String mobileNo;
}
