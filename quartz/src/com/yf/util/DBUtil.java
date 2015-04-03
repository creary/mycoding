package com.yf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBUtil {
 
    private static final Logger logger = LoggerFactory.getLogger(DBUtil.class);
 
    // 打开数据库连接（type: MySQL，Oracle，SQLServer）
    /**
     * 
     * @param type   MySQL，Oracle，SQLServer  其中的type类型是指 数据库的连接类型(需要注明)
     * @param host 本地活这远程IP地址
     * @param port 数据库使用端口号
     * @param name 数据库名
     * @param username 数据库连接用户名
     * @param password 数据库连接密码
     * @return 得到连接(否者返回异常)
     */
    public static Connection openConnection(String driver,String url,String username,String password) {
    	Connection conn = null;
        try {
/*            if (type.equalsIgnoreCase("MySQL")) {
                driver = "com.mysql.jdbc.Driver";
                url = "jdbc:mysql://" + host + ":" + port + "/" + name;
            } else if (type.equalsIgnoreCase("Oracle")) {
                driver = "oracle.jdbc.driver.OracleDriver";
                url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + name;
            } else if (type.equalsIgnoreCase("SQLServer")) {
                driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
                url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + name;
            } else {
                throw new RuntimeException();
            }*/
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return conn;
    }
    
    // 关闭数据库连接
    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
 
    // 查询（返回 Array）
    public static Object[] queryArray(QueryRunner runner, String sql, Object... params) {
        Object[] result = null;
        try {
            result = runner.query(sql, new ArrayHandler(), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 查询（返回 ArrayList）
    public static List<Object[]> queryArrayList(QueryRunner runner, String sql, Object... params) {
        List<Object[]> result = null;
        try {
            result = runner.query(sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 查询（返回 Map）
    public static Map<String, Object> queryMap(QueryRunner runner, String sql, Object... params) {
        Map<String, Object> result = null;
        try {
            result = runner.query(sql, new MapHandler(), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 查询（返回 MapList）
    public static List<Map<String, Object>> queryMapList(QueryRunner runner, String sql, Object... params) {
        List<Map<String, Object>> result = null;
        try {
            result = runner.query(sql, new MapListHandler(), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
/*    // 查询（返回 Bean）
    public static <T> T queryBean(QueryRunner runner, Class<T> cls, Map<String, String> map, String sql, Object... params) {
        T result = null;
        try {
            if (MapUtil.isNotEmpty(map)) {
                result = runner.query(sql, new BeanHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
            } else {
                result = runner.query(sql, new BeanHandler<T>(cls), params);
            }
            printSQL(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }*/
 
    
/*    // 查询（返回 BeanList）
    public static <T> List<T> queryBeanList(QueryRunner runner, Class<T> cls, Map<String, String> map, String sql, Object... params) {
        List<T> result = null;
        try {
            if (MapUtil.isNotEmpty(map)) {
                result = runner.query(sql, new BeanListHandler<T>(cls, new BasicRowProcessor(new BeanProcessor(map))), params);
            } else {
                result = runner.query(sql, new BeanListHandler<T>(cls), params);
            }
            printSQL(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
 */
    // 查询指定列名的值（单条数据）
    public static Object queryColumn(QueryRunner runner, String column, String sql, Object... params) {
        Object result = null;
        try {
            result = runner.query(sql, new ScalarHandler<Object>(column), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 查询指定列名的值（多条数据）
    public static <T> List<T> queryColumnList(QueryRunner runner, String column, String sql, Object... params) {
        List<T> result = null;
        try {
            result = runner.query(sql, new ColumnListHandler<T>(column), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 查询指定列名对应的记录映射
    public static <T> Map<T, Map<String, Object>> queryKeyMap(QueryRunner runner, String column, String sql, Object... params) {
        Map<T, Map<String, Object>> result = null;
        try {
            result = runner.query(sql, new KeyedHandler<T>(column), params);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        printSQL(sql);
        return result;
    }
 
    // 更新（包括 UPDATE、INSERT、DELETE，返回受影响的行数）
    public static int update(QueryRunner runner, Connection conn, String sql, Object... params) {
        int result = 0;
        try {
            if (conn != null) {
                result = runner.update(conn, sql, params);
            } else {
                result = runner.update(sql, params);
            }
            printSQL(sql);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return result;
    }
 
    private static void printSQL(String sql) {
        if (logger.isDebugEnabled()) {
            logger.debug("SQL: " + sql);
        }
    }
}