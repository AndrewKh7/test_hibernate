package org.andrey.testhibernate;

import org.andrey.testhibernate.repo.Bid;
import org.andrey.testhibernate.repo.Item;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.jar.JarOutputStream;

public class Application {

    static Random random = new Random();
    SessionFactory sessionFactory;
    public static void main(String[] args){
        System.out.println("Hello world!");
        Application app = new Application();
        app.sessionFactory = new Configuration().configure().buildSessionFactory();
//        app.sessionFactory = new Configuration().setProperties(new Properties);

        List<Item> items = new ArrayList<>();
        items.add(app.addItem());
        items.add(app.addItem());
        items.add(app.addItem());

        app.addBid(items.get(0));
        app.addBid(items.get(0));
        app.addBid(items.get(1));
        app.addBid(items.get(1));
        app.addBid(items.get(1));
        app.addBid(items.get(2));
        app.addBid(items.get(2));
        app.addBid(items.get(2));

        System.out.println(app.getItems());
        System.out.println("end");
    }

    public Item addItem(){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        Integer id = null;

        transaction = session.beginTransaction();
        Item item = new Item();
        session.save(item);
        transaction.commit();
        session.close();
        System.out.println("---------------create item with id = " + item.getId());

        return item;
    }

    public Bid addBid(Item item){
        Session session = this.sessionFactory.openSession();
        Transaction transaction= null;
        Bid bid = null;

        transaction = session.beginTransaction();
        bid = new Bid("Bid"+random.nextInt(),item);
        session.save(bid);
        transaction.commit();
        session.close();
        System.out.println("---------------create bid with id = "+ bid.getId());
        return bid;
    }

    public Item loadItem(Integer id){
        Session session = this.sessionFactory.openSession();
        Transaction transaction = null;
        Item item = null;

        transaction = session.beginTransaction();
        item = (Item) session.get(Item.class,id);

        transaction.commit();
        session.close();
        return item;
    }

    public List<Item> getItems(){
        Session session= this.sessionFactory.openSession();
        Transaction transaction = null;
        List<Item> items = null;

        transaction = session.beginTransaction();
        items = (List<Item>) session.createQuery("FROM Item").list();
        transaction.commit();
        session.close();
        return items;
    }
}
