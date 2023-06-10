/**
 * 
 */
package com.ms.binding;

import lombok.Data;

/**
 * @author Sawant
 *
 */
@Data
public class UnlockForm {

	
	private String email;
	
	private String tempPwd;
	
	private String newPwd;
	
	private String confirmPwd;
}
