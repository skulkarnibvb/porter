package com.acc.xlp.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Invoker {

	public static void main(String[] args) {
		
		Object obj = PortUtils.constructEntityFor(PortUtils.getConfigFor("employee.json"), null);
		System.out.println(obj);
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		session.save(obj);
	}
}
