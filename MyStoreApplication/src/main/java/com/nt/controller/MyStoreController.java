package com.nt.controller;

import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nt.command.MyStoreCommand;
import com.nt.dto.MyStoreDTO;
import com.nt.service.MyStoreService;

@Controller
public class MyStoreController {
	@Autowired
	private MyStoreService service;

	@GetMapping(value="/welcome.htm")
	public String showHomePage(@ModelAttribute("strCmd") MyStoreCommand cmd) {
		return "welcome_page";
	}
	@GetMapping(value="/login_page.htm")
	public String showLoginPage(@ModelAttribute("strCmd") MyStoreCommand cmd) {
		return "login_page";
	}
	@PostMapping(value="/login_page.htm")
	public  String  processForm(Map<String,Object> map,
			                                           @ModelAttribute("strCmd")MyStoreCommand cmd,BindingResult errors){
		MyStoreDTO dto=null;
		String result=null;
		//perform validations using Validator 
		/*if(validator.supports(MyStoreCommand.class)){
			validator.validate(cmd, errors);
			if(errors.hasErrors()){
				return "login_page";
			}
		}
		else{
			return "login_page";
		}*/
		//Convert command to dTO
		dto=new MyStoreDTO();
		BeanUtils.copyProperties(cmd,dto);
		//use service
		result=service.validate(dto);
		map.put("result", result);
		return "main_page";
	}

}
