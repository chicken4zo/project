package kr.or.bit.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDao {

    DataSource ds = null;

    public ProductDao() {
        try {
            Context context = new InitialContext();
            ds = (DataSource) context.lookup("java:comp/env/jdbc/mysql");
        } catch (NamingException e) {
            System.out.println(e.getMessage());
        }
    }
}
