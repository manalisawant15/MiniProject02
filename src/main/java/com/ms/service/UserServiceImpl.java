/**
 * 
 */
package com.ms.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ms.binding.LoginForm;
import com.ms.binding.SignUpForm;
import com.ms.binding.UnlockForm;
import com.ms.entity.UserDetails;
import com.ms.repo.UserDetailsRepository;
import com.ms.util.EmailUtil;
import com.ms.util.PwdUtil;

import jakarta.servlet.http.HttpSession;

/**
 * @author Sawant
 *
 */
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDetailsRepository userDetailsrepo;
	
	@Autowired 
	private EmailUtil emailUtils;
	
	@Autowired
	private HttpSession session;
	
	@Override
	public String login(LoginForm login)
	{
		UserDetails entity =
				userDetailsrepo.findByEmailAndPwd(login.getEmail(), login.getPwd());
		
		if(entity==null) 
		{
			return "Invalid Credentials";
		}
		
		if(entity.getAccStatus().equals("LOCKED"))
		{
			return "Your Account is Locked";
		} 
		//create session and store user data in session
		session.setAttribute("userId", entity.getUserId());
		return "success ";
		
	}

	@Override
	public boolean signUp(SignUpForm signup) {
		
		UserDetails user = userDetailsrepo.findByEmail(signup.getEmail());
 		if(user != null) {
 			return false;
 		}
		//TODO: copy data from binding obj to entity obj
		UserDetails entity = new UserDetails();
		BeanUtils.copyProperties(signup, entity);
		// TODO generate pwd
		String tempPwd = PwdUtil.generateRandomPwd();
		entity.setPwd(tempPwd);
		//TODO: set account status as locked
		entity.setAccStatus("LOCKED");
		
		//TODO: save records
		userDetailsrepo.save(entity);
		
		//TODO: send email to unlock account
		String to = signup.getEmail();
		String subject = "Unlock your Account !";

		StringBuffer body = new StringBuffer("");
		body.append("<h1>Use below Temporary password to unlock your account</h1>");
		
		body.append("Temporary Password :" + tempPwd);
		body.append("<br>");
		body.append("<a href =\"http://localhost:9991/unlock?email="+to+"\"> click here to unlock account");
		emailUtils.sendEmail(to, subject, body.toString());
		return false;
	}

	@Override 
	public boolean unlockAcc(UnlockForm unlock) {
		// TODO Auto-generated method stub
	  UserDetails entity = userDetailsrepo.findByEmail(unlock.getEmail());
		if(entity.getPwd().equals(unlock.getTempPwd())) {
			entity.setPwd(unlock.getNewPwd());
			entity.setAccStatus("UNLOCKED");
			userDetailsrepo.save(entity);
			return true;
		}
		else {
	    return false;
	    }
	}

	@Override
	public boolean forgotPwd(String email) {
	//TODO: check email is available or not
		UserDetails entity = userDetailsrepo.findByEmail(email);
		if(entity == null) {
			return false;
		}
		if(entity.getAccStatus().equals("LOCKED")) {
			return false;
		}
		
			//String to = entity.getEmail();
			String subject = "Recover Password";

			StringBuffer body = new StringBuffer("");
			body.append("<h1>Use below password to login your account</h1>");
			
			body.append("Temporary Password :" + entity.getPwd());
			//body.append("<br>");
			//body.append("<a href =\"http://localhost:9991/login?email="+email+"\"> click here to Login account");
			emailUtils.sendEmail(email, subject, body.toString());
		
		return true;
	}

}
