package com.nt.springbootmailing;

import com.nt.service.I_purchase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nt")
public class SpringBootMailingApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext ctx=SpringApplication.run(SpringBootMailingApplication.class, args);
        //get service class object
        I_purchase order=ctx.getBean("purchaseService",I_purchase.class);
        //invoke business method
        try {
            String msg= order.purchase(new String[]{"shirt","trousers","watch"},
                                        new double[]{2500.00,3500.00,5000.00},new String[]{"dv9515615161@gmail.com"});
            System.out.println(msg);


        }catch (Exception e){
            e.printStackTrace();
        }
        //close ioc container
        ctx.close();
    }

}
