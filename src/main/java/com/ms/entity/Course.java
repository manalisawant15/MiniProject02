/**
 * 
 */
package com.ms.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Sawant
 *
 */
@Data
@Entity
@Table(name="AIT_COURSES")
public class Course {
		
		@Id
		@GeneratedValue
		private Integer courseId;
		
		private String courseName;
}
