package com.javainuse.model.req;

import lombok.Data;

@Data
public class ChangePasswordForm {
    private Long userId;
    private String oldPass;
    private String newPass;
    private String repeat;
}
