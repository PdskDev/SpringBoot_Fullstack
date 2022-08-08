package com.nadet.demo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
	
	@Autowired
	private HelloService service;
	
	@GetMapping("/hello")
	public String getHello() {
		
		return "hello";
	}
	
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1") String str, Model model) {
		
		model.addAttribute("sample", str);
		
		return "hello/response";
	}
	
	@PostMapping("/hello/db")
	public String postDbRequest(@RequestParam("text2") String id, Model model) {
		//Search one employee
		Employee employee = service.getEmployee(id);
		
		//Save search result to model
		model.addAttribute("employee", employee);
		
		return "hello/db";
	}

}
