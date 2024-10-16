package com.Freelancer.getcitations_freelancer.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.model.ReviewsModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/GetCitation?useSSL=false");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "Belapur5#");

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
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}