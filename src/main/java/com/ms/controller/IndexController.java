/**
 * 
 */
package com.ms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Sawant
 *
 */
@Controller
public class IndexController {

	@GetMapping
	public String welcome() {
		
		return "index";
	}
}
