package com.Freelancer.getcitations_freelancer.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.model.ReviewsModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

import jakarta.annotation.PostConstruct;

//@Component
public class HibernateUtil {
    private static SessionFactory sessionFactory;
    
//    @Value("${db.host}")
//    private static String dbHost;
//
//    @Value("${db.port}")
//    private static String dbPort;
//
//    @Value("${db.database}")
//    private static String dbName;
//
//    @Value("${db.user}")
//    private static String dbUsername;
//
//    @Value("${db.password}")
//    private static String dbPassword;
//    
//    @PostConstruct
    public static SessionFactory getSessionFactory() {
    	
    	System.getenv().forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));
    	String dbHost = System.getenv("MYSQL_HOST") != null ? System.getenv("MYSQL_HOST") : "localhost";
        String dbPort = System.getenv("MYSQL_PORT") != null ? System.getenv("MYSQL_PORT") : "3306";
        String dbName = System.getenv("MYSQL_DB_NAME") != null ? System.getenv("MYSQL_DB_NAME") : "getcitation";
        String dbUsername = System.getenv("MYSQL_USER") != null ? System.getenv("MYSQL_USER") : "root";
        String dbPassword = System.getenv("MYSQL_PASSWORD") != null ? System.getenv("MYSQL_PASSWORD") : "Belapur5#";
        
        System.out.println("dbhost"+dbHost);
        System.out.println("dbport"+dbPort);
        System.out.println("dbname"+dbName);
        System.out.println("username"+dbUsername);
        System.out.println("dbpassword"+dbPassword);
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?useSSL=false");
                settings.put(Environment.USER, dbUsername);
                settings.put(Environment.PASS, dbPassword);

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");
                

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(UserModel.class);
                configuration.addAnnotatedClass(ReviewsModel.class);
                configuration.addAnnotatedClass(ReseachPaperBiddingModel.class);
                configuration.addAnnotatedClass(BidWinnerModel.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
            	System.out.println("error in creating sessionFactory"+e.getMessage());
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}