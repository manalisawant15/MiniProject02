/**
 * 
 */
package com.ms.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Sawant
 *
 */
@Entity
@Table(name="AIT_USER_DETAILS")
@Data
public class UserDetails {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private Long phoneNo;
	
	private String accStatus;
	
	@OneToMany(mappedBy ="user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<StudentEnquiry> enquiries;
}
