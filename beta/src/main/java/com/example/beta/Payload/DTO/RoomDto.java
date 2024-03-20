package com.example.beta.Payload.DTO;

import lombok.Data;

@Data
public class RoomDto {
        private int capacity;
        private int type;
        private String description;
        private String code;
        private String name;
        private Boolean isActive;


}
