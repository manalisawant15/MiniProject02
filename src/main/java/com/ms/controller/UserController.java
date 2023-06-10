/**
 * 
 */
package com.ms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ms.binding.LoginForm;
import com.ms.binding.SignUpForm;
import com.ms.binding.UnlockForm;
//import com.ms.entity.UserDetails;
import com.ms.service.UserService;

/**
 * @author Sawant
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute("loginForm") LoginForm form, Model model)
	{
		System.out.println(form);
		String login =userService.login(form);
		if(login.contains("success")) {
			return "redirect:/dashboard";
		}
		
		model.addAttribute("errMsg", login);
		return "login";
	}
	
	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}
	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form,Model model) {
	 boolean status = userService.signUp(form);
		if(status) 
		{
			model.addAttribute("succMsg", "Account Created, Please Check your Email!");
		}else
		{
			model.addAttribute("errMsg", "Choose Unique Email");
		}
		return "signup";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {
		
		UnlockForm unlock = new UnlockForm();
		unlock.setEmail(email);
		model.addAttribute("unlock", unlock);
		return "unlock";
	}
	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute("unlock") UnlockForm unlock, Model model) {
		System.out.println(unlock);
		if(unlock.getNewPwd().equals(unlock.getConfirmPwd())) {
			boolean status = userService.unlockAcc(unlock);
			if(status) {
				model.addAttribute("succMsg", "Your account unlocked successfully");
			}
			else {
				model.addAttribute("errMsg", "Given temporary password is incorrect, ckeck your email");
			}
		}
		else {
			model.addAttribute("errMsg", "New password and confirm password should be same");
		}
		return "unlock";
	}
	
	@GetMapping("/forgot")
	public String forgatePwdPage(Model model) {
		
		return "forgotpwd";
	}
	@PostMapping("/forgotpwd")
	public String forgatePwd(@RequestParam("email") String email,Model model) {
		System.out.println(email);
	  boolean status = 	userService.forgotPwd(email);
	 if(status) {
		 model.addAttribute("succMsg", "Password send to your email");
	 }
	 else {
		 model.addAttribute("errMsg","Invalid Email Id");
	 }
		return "forgotpwd";
	}
}
