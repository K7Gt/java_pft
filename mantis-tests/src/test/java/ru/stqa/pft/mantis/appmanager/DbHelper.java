package ru.stqa.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.mantis.model.Users;

import java.util.List;

/**
 * Created by Golem on 21.04.2017.
 */
public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper(){
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public List<Users> users(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Users> result = session.createQuery( "from Users").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }


    public Users userByUd(int id){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Users user = (Users) session.createQuery( "from Users where id = '" + id + "'").getSingleResult();
        session.getTransaction().commit();
        session.close();
        return user;
    }


}
