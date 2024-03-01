package com.image.extract.vitraya.vitrayaimageextractor.authentication.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@Table(name = "auth_user")
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;
    
    @Column(name = "user_name", unique = true, nullable = false)
    private String userName;

    @Column(name = "password")
    private String password;
    
    @Column(name = "user_type")
    private String userType;
    
    @Builder.Default
    @Column(name = "login_id_status")
    private String loginIdStatus = "ACTIVE";
    
    @Column(name = "role")
    private String role;

}
