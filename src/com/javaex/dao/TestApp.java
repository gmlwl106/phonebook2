package com.javaex.dao;

import com.javaex.vo.PersonVo;

public class TestApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		PersonVo person = phoneDao.getPerson(1);
		System.out.println(person.getName());
	}
}
