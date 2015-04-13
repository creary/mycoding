package com.yf.util.hutool.db.handler;


import java.sql.ResultSet;
import java.sql.SQLException;
public interface RsHandler<T> {
	public T handle(ResultSet rs) throws SQLException;
}
