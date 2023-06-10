/**
 * 
 */
package com.ms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ms.entity.StudentEnquiry;

/**
 * @author Sawant
 *
 */
public interface StudentEnquiryRepository extends JpaRepository<StudentEnquiry, Integer>{
	
	@Query("select distict (statusName) from EnquiryStatus")
	public List<String> getStatusName();
	
	@Query("select distict (courseName) from Course")
	public List<String> getCourseName();
	
}
