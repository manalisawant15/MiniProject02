 /**
 * 
 */
package com.ms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.binding.DashboardResponse;
import com.ms.binding.EnquiryForm;
import com.ms.binding.EnquirySearchCriteria;
import com.ms.entity.Course;
import com.ms.entity.EnquiryStatus;
import com.ms.entity.StudentEnquiry;
import com.ms.entity.UserDetails;
import com.ms.repo.CourseRepository;
import com.ms.repo.EnquiryStatusRepository;
import com.ms.repo.StudentEnquiryRepository;
import com.ms.repo.UserDetailsRepository;

import jakarta.servlet.http.HttpSession;

/**
 * @author Sawant
 *
 */
@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private CourseRepository courseRepo;
	
	@Autowired
	private EnquiryStatusRepository enqRepo;
	
	@Autowired
	private StudentEnquiryRepository studentEnqRepo;
	
	@Autowired
	private UserDetailsRepository userRepo;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<String> getCourseName() {
		List<Course> findAll = courseRepo.findAll();
		List<String> names = new ArrayList();
		for(Course entity : findAll) {
			names.add(entity.getCourseName());
		}
		return names;
	}

	@Override
	public List<String> getEnquiryStatus() {
		List<EnquiryStatus> findAll = enqRepo.findAll();
		List<String> names = new ArrayList<>();
		for(EnquiryStatus entity : findAll) {
			names.add(entity.getStatusName());
		}
		return names;
	}

	@Override
	public DashboardResponse getDashboardResponse(Integer userId) {
		DashboardResponse response =new DashboardResponse();
		
		Optional<UserDetails> findById = userRepo.findById(userId);
		
		if(findById.isPresent()) {
			UserDetails userEntity = findById.get();
			List<StudentEnquiry> enquiries = userEntity.getEnquiries();
			
			Integer total = enquiries.size();
			Integer enrolledCount = enquiries.stream()
				.filter(e->e.getEnquiryStatus().equals("Enrolled"))	
				.collect(Collectors.toList()).size();
			
			Integer lostCount = enquiries.stream()
			.filter(e->e.getEnquiryStatus().equals("Lost"))	
			.collect(Collectors.toList()).size();
			
			response.setTotalEnquiries(total);
			response.setEnrolledCount(enrolledCount);
			response.setLostCount(lostCount);
		}
		return response;
	}

	@Override
	public boolean addEnquiry(EnquiryForm form) 
	{	
		StudentEnquiry entity = new StudentEnquiry();
		BeanUtils.copyProperties(form, entity);
		Integer userId = (Integer) session.getAttribute("userId");
		UserDetails userEntity=userRepo.findById(userId).get();
		entity.setUser(userEntity);
		studentEnqRepo.save(entity);
		return true;
	}

	
	@Override
	public List<StudentEnquiry> getEnquiries() {
		Integer userId = (Integer) session.getAttribute("userId");
		
		Optional<UserDetails> findById = userRepo.findById(userId);
		if(findById.isPresent()) {
			UserDetails userDetailsEntity = findById.get();
			List<StudentEnquiry> enquiries = userDetailsEntity.getEnquiries();
			return enquiries;
		}
		return null;
	}

	@Override
	public List<StudentEnquiry> getFilteredEnquiries(EnquirySearchCriteria criteria, Integer userId) {
		// userId = (Integer) session.getAttribute("userId");
		
		Optional<UserDetails> findById = userRepo.findById(userId);
		if(findById.isPresent()) {
			UserDetails userDetailsEntity = findById.get();
			List<StudentEnquiry> enquiries = userDetailsEntity.getEnquiries();
			//filter logic
			
			if(null!= criteria.getCourseName() & !"".equals(criteria.getCourseName())) {
				enquiries = enquiries.stream()
				.filter(e-> e.getCourseName().equals(criteria.getCourseName()))
				.collect(Collectors.toList());
			}
			if(null!= criteria.getEnquiryStatus() && !"".equals(criteria.getEnquiryStatus())) {
				enquiries = enquiries.stream()
				.filter(e-> e.getEnquiryStatus().equals(criteria.getEnquiryStatus()))
				.collect(Collectors.toList()); 
			}
			if(null!= criteria.getClassMode() && !"".equals(criteria.getClassMode())) {
				enquiries = enquiries.stream()
				.filter(e-> e.getClassMode().equals(criteria.getClassMode()))
				.collect(Collectors.toList());
			}
			return enquiries;
		}
		return null;
	}



}
