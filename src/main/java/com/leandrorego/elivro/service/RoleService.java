package com.leandrorego.elivro.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Role;
import com.leandrorego.elivro.repository.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository crud;
	
    public List<Role> findAll(){
    	 return crud.findAll();
    }
 	
    public Role findById(long id) {
		return crud.findById(id);
    }
}
