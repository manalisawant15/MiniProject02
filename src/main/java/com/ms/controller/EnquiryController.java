/**
 * 
 */
package com.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.binding.DashboardResponse;
import com.ms.binding.EnquiryForm;
import com.ms.binding.EnquirySearchCriteria;
import com.ms.entity.StudentEnquiry;
import com.ms.service.EnquiryService;

import jakarta.servlet.http.HttpSession;

/**
 * @author Sawant
 *
 */
@Controller 
public class EnquiryController {

	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	public void initForm(Model model) {
		//get courses for drop down
		List<String> courses = enqService.getCourseName();
		//get enquiry Status for drop down
		List<String> enqStatus = enqService.getEnquiryStatus();
		//create binding class obj
		EnquiryForm formObj =new EnquiryForm();
		//set data in model obj
		model.addAttribute("courses", courses);
		model.addAttribute("enqStatus", enqStatus); 
		model.addAttribute("formObj", formObj);
	}
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		
		Integer userId = (Integer) session.getAttribute("userId");
		
		DashboardResponse dashboardData = enqService.getDashboardResponse(userId);
		
		model.addAttribute("dashboardData", dashboardData);
		return "dashboard";
	}
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		//get courses for drop down
		List<String> courses = enqService.getCourseName();
		//get enquiry Status for drop down
		List<String> enqStatus = enqService.getEnquiryStatus();
		//create binding class obj
		EnquiryForm formObj =new EnquiryForm();
		model.addAttribute("courses", courses);
		model.addAttribute("enqStatus", enqStatus); 
		model.addAttribute("formObj", formObj);
		
		return "addenquiry";
	}
	
	@PostMapping("/addenquiry")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj,Model model) {
		
		System.out.println(formObj);
		boolean status = enqService.addEnquiry(formObj);
		
		if(status) {
			model.addAttribute("succMsg", "Enquiry Added");
		}
		else {
		model.addAttribute("errMsg", "PRoblem Occured");
		}
		return "addenquiry";
	}
	@GetMapping("/enquiries")
	public String viewEnquiryPage(Model model) {
		initForm(model);
		List<StudentEnquiry> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		
		return "viewenquiries";
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs(@RequestParam String cname,@RequestParam String status,@RequestParam String mode, Model model) {
		
	
		EnquirySearchCriteria criteria =new EnquirySearchCriteria();
		criteria.setCourseName(cname);
		criteria.setEnquiryStatus(status);
		criteria.setClassMode(mode);
		System.out.println(criteria);
		
		Integer userId = (Integer) session.getAttribute("userId");
		List<StudentEnquiry> filteredEnqs	=enqService.getFilteredEnquiries(criteria, userId);
		//System.out.println(model.addAttribute("enquiries", filteredEnqs));
		model.addAttribute("enquiries", filteredEnqs);
		return "filteredenquiries";
	}
	
}
