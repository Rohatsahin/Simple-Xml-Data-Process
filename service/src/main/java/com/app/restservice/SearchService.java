/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.restservice;

import com.app.databaseModel.Product;
import com.app.hibernateconfig.Configure;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rohat
 */
@RestController
public class SearchService {

    @RequestMapping(value = "/rest/product/{title}", method = RequestMethod.GET)
    public ResponseEntity getDataById(@PathVariable String title) {

        Session session = Configure.configs().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from Product where title = :titlename ");
        query.setParameter("titlename", title);
        List<Product> product = query.list();
        session.getTransaction().commit();
        session.close();
        return new ResponseEntity(product, HttpStatus.OK);
    }

}
