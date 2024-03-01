package com.image.extract.vitraya.vitrayaimageextractor.authentication.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.image.extract.vitraya.vitrayaimageextractor.authentication.entity.AuthUser;

public interface AuthUserRepo extends JpaRepository<AuthUser, Long> {

	Optional<AuthUser> findByUserName(String userName);

	@Query(value = "{call user_login(:username, :password)}", nativeQuery = true)
	Optional<Map<String, Object>> userLogin(@Param("username") String username, @Param("password") String password);
	
	AuthUser findByUserNameAndPassword(String userName, String password);
	
	boolean existsByUserName(String userName);

}
