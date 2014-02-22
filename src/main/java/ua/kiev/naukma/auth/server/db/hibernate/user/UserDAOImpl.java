package ua.kiev.naukma.auth.server.db.hibernate.user;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ua.kiev.naukma.auth.server.db.hibernate.HibernateUtil;
import ua.kiev.naukma.auth.shared.model.user.User;

import java.util.List;

public class UserDAOImpl {
    private static SessionFactory factory = HibernateUtil.get();

    public User findById(Long id) {
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public Long save(User user) {
        Session session = factory.openSession();
        Transaction tx = null;
        Long employeeID = null;
        try {
            tx = session.beginTransaction();
            employeeID = (Long) session.save(user);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employeeID;
    }

    /* Method to  READ all the employees */
    public List<User> getAll() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try {
            tx = session.beginTransaction();
            users = HibernateUtil.listAndCast(session.createQuery("FROM User"));
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    /* Method to DELETE an employee from the records */
    @SuppressWarnings("unused")
    public void deleteEmployee(Integer EmployeeID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User employee =
                    (User) session.get(User.class, EmployeeID);
            session.delete(employee);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}