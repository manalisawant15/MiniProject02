/**
 * 
 */
package com.ms.service;

import com.ms.binding.LoginForm;
import com.ms.binding.SignUpForm;
import com.ms.binding.UnlockForm;

/**
 * @author Sawant
 *
 */
public interface UserService {
	
	public String login(LoginForm login);
	
	public boolean signUp(SignUpForm signup);
	
	public boolean unlockAcc(UnlockForm unlock);
	
	public boolean forgotPwd(String email);
	
	

}
