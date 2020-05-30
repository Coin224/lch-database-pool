package com.lch.dbpool.pool;

import com.lch.dbpool.exception.NoConnExeception;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 这是一个连接池
 * 为了项目启动的时候就创建多个连接
 * 这样就能同时使用多个连接
 * 提升效率
 */
public class DbPool {



    //这个属性是已使用连接数
    private static int count = 0;

    /*需要一个集合来存放这些连接*/
    private static List<MyConn> conns = new ArrayList<MyConn>();

    /*在启动项目的时候就创建多个连接*/
    static {
        try {
            Properties prop = new Properties();
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));
            String url = (String) prop.get("url");
            String driver = (String) prop.get("driver");
            String username = (String) prop.get("username");
            String password = (String) prop.get("password");

            // 加载驱动
            Class.forName(driver);

            //做一个循环 每一次都创建一个MyConn对象，给conn属性赋值
            for (int i = 0 ; i < 10 ; i++) {
                MyConn conn = new MyConn();
                //获取连接
                Connection connection = DriverManager.getConnection(url, username, password);
                //把connection赋值到MyConn对象中
                conn.setConn(connection);
                //把MyConn对象添加到集合中
                conns.add(conn);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //这个方法从集合中得到MyConn对象
    public static Connection getConn(){
        if (count >8) throw new NoConnExeception("连接数不够用啦");
        //获取一个count count++
        count++;
        MyConn conn = conns.get(count);
        //设置MyConn的状态为繁忙
        conn.setBusy(true);
        //把Connection对象返回
        return conn;
    }



    //这个方法是获取连接的数量
    public static int getCount() {
        return count;
    }

    //这个方法用来给MyConn调用close方法的时候改变状态时
    //count--
    public static void setCount(int count) {
        DbPool.count = count;
    }


}
