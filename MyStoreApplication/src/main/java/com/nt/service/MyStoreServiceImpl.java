package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nt.bo.MyStoreBO;
import com.nt.dao.MyStoreDAO;
import com.nt.dto.MyStoreDTO;
@Service("strService")
public class MyStoreServiceImpl implements MyStoreService{
	
	@Autowired
	private MyStoreDAO dao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public String validate(MyStoreDTO dto) {
		int count=0;
		MyStoreBO bo=null;
		//convert dto to bo
		bo=new MyStoreBO();
		BeanUtils.copyProperties(dto, bo);
		//use dao
		count=dao.authenticate(bo);
		if(count!=0)
			return "Invalid Username and password";
		else
			return "Welcome to MyStoreApplication";
	}
	}
	
