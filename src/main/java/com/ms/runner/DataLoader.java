/**
 * 
 */
package com.ms.runner;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ms.entity.Course;
import com.ms.entity.EnquiryStatus;
import com.ms.repo.CourseRepository;
import com.ms.repo.EnquiryStatusRepository;

/**
 * @author Sawant
 *
 */
@Component
public class DataLoader implements ApplicationRunner{

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private EnquiryStatusRepository enqRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		courseRepo.deleteAll();
		
		Course c1 =new Course();
		c1.setCourseName("Java Fullstack");
		
		Course c2 =new Course();
		c2.setCourseName("Devops");
		
		Course c3 =new Course();
		c3.setCourseName("AWS");
		
		courseRepo.saveAll(Arrays.asList(c1,c2,c3));
		
		
		enqRepo.deleteAll(); 
		EnquiryStatus e1 = new EnquiryStatus();
		e1.setStatusName("New");
		
		EnquiryStatus e2 = new EnquiryStatus();
		e2.setStatusName("Enrolled");
		
		EnquiryStatus e3 = new EnquiryStatus();
		e3.setStatusName("Lost");

		enqRepo.saveAll(Arrays.asList(e1,e2,e3));
		
		
	}
	
	

}
