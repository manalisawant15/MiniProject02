/**
 * 
 */
package com.ms.service;

import java.util.List;

import com.ms.binding.DashboardResponse;
import com.ms.binding.EnquiryForm;
import com.ms.binding.EnquirySearchCriteria;
import com.ms.entity.StudentEnquiry;

/**
 * @author Sawant
 *
 */
public interface EnquiryService {

	public List<String> getCourseName();
	
	public List<String> getEnquiryStatus();
	
	public DashboardResponse getDashboardResponse(Integer userId);
	
	public boolean addEnquiry(EnquiryForm form);
	
	public List<StudentEnquiry> getEnquiries();
	
	public List<StudentEnquiry> getFilteredEnquiries(EnquirySearchCriteria search,Integer userId);
	
}
