/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.hibernateconfig;

import java.io.File;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author rohat
 */
public class Configure {

    public static SessionFactory configs() {
        String hibernatePropsFilePath = "your hibernate.cfg.xml path";
        File hibernatePropsFile = new File(hibernatePropsFilePath);
        Configuration configuration = new Configuration();
        configuration.configure(hibernatePropsFile);
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        SessionFactory sessionfactory = configuration.buildSessionFactory(serviceRegistry);

        return sessionfactory;
    }

}
