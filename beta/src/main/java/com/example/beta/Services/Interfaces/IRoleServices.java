package com.example.beta.Services.Interfaces;

import com.example.beta.Model.Role;
import com.example.beta.Payload.Reponse.ReponseObject;

public interface IRoleServices {

    ReponseObject<Role> addRole(Role role);
    ReponseObject<Role> updateRole(Role role);
    ReponseObject<Role> getRoleById(int id);
    ReponseObject<Role> deleteRole(int id);
}
