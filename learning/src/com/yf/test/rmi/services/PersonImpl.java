package com.yf.test.rmi.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;

public class PersonImpl extends UnicastRemoteObject implements IPersonService{


	protected PersonImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<PersonEntity> getList() {
		System.out.println("getPersonList Start.........");
		List<PersonEntity> list=new LinkedList<PersonEntity>();
		PersonEntity pEntity=new PersonEntity();
		pEntity.setAge(11);
		pEntity.setName("李听");
		list.add(pEntity);
		return list;
	}

}
