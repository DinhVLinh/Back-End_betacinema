package com.example.beta.Services.Implements;

import com.example.beta.Model.Role;
import com.example.beta.Payload.Reponse.ReponseObject;
import com.example.beta.Repository.RoleRepository;
import com.example.beta.Services.Interfaces.IRoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
public class RoleServices implements IRoleServices {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ReponseObject roleReponse;

    @Override
    public ReponseObject<Role> addRole(Role role) {
        Role roleNew = Role.builder()
                .code(role.getCode())
                .roleName(role.getRoleName())
                .build();
        roleRepository.save(roleNew);
        return roleReponse.reponseSuccess("add role successfully",roleNew);
    }

    @Override
    public ReponseObject<Role> updateRole(Role role) {
        var roleUpdate = roleRepository.findById(role.getId());
        if(roleUpdate.isEmpty()){
            return roleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"role not found",null);
        }
        roleUpdate.get().setRoleName(role.getRoleName());
        roleUpdate.get().setCode(role.getCode());
        roleRepository.save(roleUpdate.get());
        return roleReponse.reponseSuccess("update role successfully",roleUpdate.get());
    }

    @Override
    public ReponseObject<Role> getRoleById(int id) {
        var role = roleRepository.findById(id);
        if(role.isEmpty()){
            return roleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"role not found",null);
        }

        return roleReponse.reponseSuccess("get role successfully",role.get());
    }

    @Override
    public ReponseObject<Role> deleteRole(int id) {
        var role = roleRepository.findById(id);
        if(role.isEmpty()){
            return roleReponse.reponseError(HttpURLConnection.HTTP_BAD_REQUEST,"role not found",null);
        }
        roleRepository.delete(role.get());
        return roleReponse.reponseSuccess("get role successfully",role.get());
    }
}
