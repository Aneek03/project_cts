package com.aneek.book;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.password.PasswordEncoder;

import com.aneek.book.config.AppConstants;
import com.aneek.book.entities.Role;
import com.aneek.book.repositories.RoleRepo;

@SpringBootApplication // configuration+componentscan + enableautoconfig
public class BookAppApisApplication implements CommandLineRunner{
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BookAppApisApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() { // to convert one object  to another
		
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//System.out.println(this.passwordEncoder.encode("0123456789abc"));
		
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");
			
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			List<Role> roles = List.of(role,role1);
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(r->{
				System.out.println(r.getName());
			});
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

}
