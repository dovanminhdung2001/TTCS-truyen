package com.javainuse.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeUserInforForm {
    private Long id;
    private String name;
    private String password;
}
