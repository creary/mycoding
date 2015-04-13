package com.yf.test.rmi.services;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IPersonService  extends Remote{
	
 public List<PersonEntity>	getList() throws RemoteException;
	
}
