package com.example.beta.Payload.Request;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {
    private int id;
    private int capacity;
    private int type;
    private String description;
    private String code;
    private String name;
    private Boolean isActive;
    private int cinemaId;
}
