package com.nt.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nt.bo.MyStoreBO;

@Repository("strDao")
public class MyStoreImpl implements MyStoreDAO {
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM MYSTOREDATA WHERE USERNAME=? AND PASSWORD=?";
	@Autowired
	 private JdbcTemplate jt;

	@Override
	public int authenticate(MyStoreBO bo) {
     int count=0;
     count=jt.queryForObject(AUTH_QUERY,Integer.class,bo.getUsername(),bo.getPassword());
		return count;
	}

}
