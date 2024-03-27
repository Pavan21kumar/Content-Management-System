package com.example.cms.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

	@NotNull(message = "userName should not be null")
	private String userName;
	@NotBlank(message = "email should be not null")
	@NotNull(message = "userEmail should not be null")
	@Column(unique = true)
	@Email(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+(com|org|in|co|net|com\\.au|com\\.com)", message = "email is not valid please enter correct email")
	// ^[a-z0-9\\.\\_\\-\\+a-z0-9]*\\@[a-z0-9]*\\.(com|org|in|co|net|com\\.au|com\\.com)
	private String email;

	@NotNull(message = "userPassword should not be null")
	@Size(min = 8, max = 16, message = "password should be length is 8>= &&<=16")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$", message = "give propere password")
	// ^(?=.*[A-Z])(?=.*[0-9])(?=.*\\W)[a-zA-Z0-9\\#]{8,}$"
	// "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$jffdf).{8,20}$
	private String password;

	
	//"^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
}
