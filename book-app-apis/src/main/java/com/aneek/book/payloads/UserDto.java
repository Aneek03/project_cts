// 4th
package com.aneek.book.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
// this class is used for Data transfer. createUser function in the
//UserService interface will return the object of UserDto

// this data will directly exposed to Api's . we will not effect our entity class.

	private int id;
	
	@NotEmpty
	@Size(min=4, message="Username must be min of 4 characters !!")
	private String name;
	
	@Email(message="Email address is not valid !!")
	private String email;
	
	@NotEmpty
	@Size(min= 8, max=16, message="Password must be min of 8 characters and maximum 16 characters")
	//@Pattern(regexp="")
	//@Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[A-Za-z\\d\\W_]{8,}$")
	private String password;
	
	@NotEmpty
	private String about;

}