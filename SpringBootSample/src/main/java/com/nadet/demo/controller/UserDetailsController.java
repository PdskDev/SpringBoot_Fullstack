package com.nadet.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nadet.demo.domain.user.model.MUser;
import com.nadet.demo.domain.user.service.UserService;
import com.nadet.demo.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserDetailsController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/** Display user details screen **/
	@GetMapping("/detail/{userId:.+}")
	public String gerUser(UserDetailForm form, Model model, @PathVariable("userId") String userId )
	{
		//Get user
		MUser user = userService.getUserOne(userId);
		user.setPassword(null);
		
		//Get MUser to form
		form = modelMapper.map(user, UserDetailForm.class);
		form.setSalaryList(user.getSalaryList());
		
		//Registred in Model
		model.addAttribute("userDetailForm", form);
		
		//Display user details screen
		return "user/detail";
	}
	
	/** User update process **/
	@PostMapping(value="/detail", params = "update")
	public String updateUser(UserDetailForm form, Model model) {
		
		try {
			//Update user
			userService.updateUserOne(
					form.getUserId(), 
					form.getPassword(), 
					form.getUserName()
					);
			
		} catch (Exception e) {
			log.error("Error in user update", e);
		}
		
		//Redirect to user list screen
		return "redirect:/user/list";
	}
	
	/** Delete user process**/
	@PostMapping(value="/detail", params="delete")
	public String deleteUser(UserDetailForm form, Model model) {
		
		//Delete user
		userService.deleteUserOne(form.getUserId());
		
		return "redirect:/user/list";
	}

}
