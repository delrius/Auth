package ua.kiev.naukma.auth.server.db.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ua.kiev.naukma.auth.shared.model.user.User;

import java.util.List;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    private static final StandardServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(User.class);
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }

    public static SessionFactory get() {
        return sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> listAndCast(Query q) {
        return q.list();
    }
}