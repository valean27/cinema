package com.webservice.app.webservice.repository;

import com.webservice.app.webservice.model.JwtUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<JwtUser, Integer> {
}
