package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.Role;
import com.leandrorego.elivro.models.Usuario;
@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
	public Role findById(long id);
}
