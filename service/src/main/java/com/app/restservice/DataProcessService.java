/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.restservice;

import com.app.databaseModel.Price;
import com.app.databaseModel.Product;
import com.app.hibernateconfig.Configure;
import com.app.xmlModel.Rows;
import com.app.xmlModel.XmlDataDomain;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import javax.xml.bind.JAXB;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rohat
 */
@RestController
public class DataProcessService {

    @RequestMapping(value = "/rest/xmlUrl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity saveXmlData(@RequestBody XmlDataDomain data) {

        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        dt.setTimeZone(TimeZone.getTimeZone("GMT-2"));

        Session session = Configure.configs().openSession();
        try {
            String s = data.getDomain();
            URL url = new URL(s);
            // marshal object to file input stream
            Rows st = JAXB.unmarshal(url, Rows.class);

            for (int i = 0; i < st.getRow().size(); i++) {

                Product product = new Product();

                String price[] = st.getRow().get(i).getPrices().split(",");
                String date[] = st.getRow().get(i).getDates().split(",");
                Set<Price> allprice = new HashSet<Price>();
                for (int k = 0; k < date.length; k++) {
                    Price prices = new Price();
                    prices.setPrice(Float.parseFloat(price[k]));
                    long longdates = Long.parseLong(date[k]);
                    Date d = new Date(longdates);
                    prices.setDate(dt.format(d));
                    allprice.add(prices);

                }
                product.setPrice(allprice);

                product.setId(st.getRow().get(i).getId());

                product.setTitle(st.getRow().get(i).getTitle());

                product.setBrand(st.getRow().get(i).getBrand());

                product.setCategory(st.getRow().get(i).getCategory());

                product.setUrl(st.getRow().get(i).getUrl());

                session.beginTransaction();
                session.saveOrUpdate(product);
                session.getTransaction().commit();

            }
            session.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity(HttpStatus.FAILED_DEPENDENCY);
        }

        return new ResponseEntity(HttpStatus.OK);

    }

}
