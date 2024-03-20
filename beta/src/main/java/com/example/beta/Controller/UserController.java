package com.example.beta.Controller;

import com.example.beta.Payload.DTO.UserDto;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Payload.Request.UpdateInforUserRequest;
import com.example.beta.Services.Implements.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserServices  _services;

    @GetMapping("/findall")
    private Page<UserDto> findAllUser(@RequestParam  Integer page , Integer size){
        if(page == null){
            page = 0;
        }
        if(size == null){
            size = 1;
        }
        return  _services.findAllUser(page,size);
    }

    @GetMapping("/finduserbyid")
    private ReponseObject<UserDto> findAllUser(@RequestParam  Integer id){
        if(id == null) {
            id = 0;
        }
        return  _services.findUserById(id);
    }

    @DeleteMapping("/deleteuserbyid")
    private ReponseObject<UserDto> deleteUserById(@RequestParam  Integer id){

        return  _services.deleteUserById(id);
    }

    @PatchMapping("/updateinforuser")
    private ReponseObject<UserDto> updateInforUser(@RequestBody UpdateInforUserRequest request){
        return _services.updateInforUser(request);
    }
}
