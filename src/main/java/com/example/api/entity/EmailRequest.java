package com.example.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
}