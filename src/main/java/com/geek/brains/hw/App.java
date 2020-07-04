package com.geek.brains.hw;

import com.geek.brains.hw.PrepareDataApp;
import com.geek.brains.hw.goods.Customer;
import com.geek.brains.hw.goods.Good;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class App {
        public static void main(String[] args) {
            PrepareDataApp.forcePrepareData();
            SessionFactory factory = new Configuration()
                    .configure("configs/hibernate.cfg.xml")
                    .buildSessionFactory();

            Session session = null;

            try {
                session = factory.getCurrentSession();
                session.beginTransaction();

                //список товаров покупателя
                Customer customer = session.get(Customer.class,1L);
                System.out.println("List of goods " + customer.getName()+":");

                Double total = 0D;
                for (Good g:customer.getGoods()) {
                    System.out.println(g.toString2());
                    total+=g.getPrice();
                }
                System.out.println("Price in total: "+total);
                session.getTransaction().commit();

                //список покупок
                session = factory.getCurrentSession();
                session.beginTransaction();

                Good good = session.get(Good.class, 1L);
                System.out.println("Product: " + good.getName());

                for (Customer c:good.getCustomers()) {
                    System.out.println(c);
                }
                session.getTransaction().commit();

                //удаление
                session = factory.getCurrentSession();
                session.beginTransaction();

                session.createQuery("DELETE FROM Good g WHERE g.id = 1").executeUpdate();

                session.getTransaction().commit();
            } finally {
                factory.close();
                if (session != null) {
                    session.close();
                }
            }

        }
    }
