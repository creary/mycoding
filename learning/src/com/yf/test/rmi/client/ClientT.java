package com.yf.test.rmi.client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import com.yf.test.rmi.services.IPersonService;
import com.yf.test.rmi.services.PersonEntity;

public class ClientT {
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
	IPersonService personService=	(IPersonService) Naming.lookup("rmi://127.0.0.1:8888/IPersonService");
	List<PersonEntity> personEntities= personService.getList();
	for(PersonEntity p : personEntities){
		System.out.println(p.getAge()+" : "+p.getName());
		}
	
	}
}
