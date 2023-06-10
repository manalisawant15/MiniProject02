/**
 * 
 */
package com.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.UserDetails;

/**
 * @author Sawant
 *
 */
public interface UserDetailsRepository extends JpaRepository<UserDetails, Integer>{

	public UserDetails findByEmail(String email);
	
	public UserDetails findByEmailAndPwd(String email, String pwd);
}
