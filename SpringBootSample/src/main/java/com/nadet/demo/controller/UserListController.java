package com.nadet.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nadet.demo.domain.user.model.MUser;
import com.nadet.demo.domain.user.service.UserService;
import com.nadet.demo.form.UserListForm;

@Controller
@RequestMapping("/user")
public class UserListController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/** display user list screen **/
	@GetMapping("/list")
	public String getUserList(@ModelAttribute UserListForm form, Model model) {

		// Convert from MUser class
		MUser user = modelMapper.map(form, MUser.class);

		// Get users list
		List<MUser> userList = userService.getUsers(user);

		// Registered in Model
		model.addAttribute("userList", userList);

		return "user/list";
	}

	/** User search process **/
	@PostMapping("/list")
	public String postUserList(@ModelAttribute UserListForm form, Model model) {

		// Convert from MUser class
		MUser user = modelMapper.map(form, MUser.class);

		// Get users list
		List<MUser> userList = userService.getUsers(user);

		// Registered in Model
		model.addAttribute("userList", userList);

		return "user/list";
	}

}
