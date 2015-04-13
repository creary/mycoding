package com.yf.test.rmi.services;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class TestServerProger {
	public static void main(String[] args) throws Exception {
		
		IPersonService ps=new PersonImpl();
		LocateRegistry.createRegistry(8888);
		Naming.rebind("rmi://127.0.0.1:8888/IPersonService", ps);
		System.out.println("RMI　server start ........");
		
		
	}

}
