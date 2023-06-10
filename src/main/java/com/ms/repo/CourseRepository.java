/**
 * 
 */
package com.ms.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.entity.Course;

/**
 * @author Sawant
 *
 */
public interface CourseRepository extends JpaRepository<Course,Integer> {

}
