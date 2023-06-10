/**
 * 
 */
package com.ms.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Sawant
 *
 */
@Entity
@Table(name="AIT_STUDENT_ENQUIRIES")
@Data
public class StudentEnquiry {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer enqId;
	
	private String studentName;
	
	private Long phoneNo;
	
	private String classMode;
	
	private String courseName;
	
	private String enquiryStatus;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private UserDetails user;
}
