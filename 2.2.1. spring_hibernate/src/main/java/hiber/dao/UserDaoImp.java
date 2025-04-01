package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User findUserByCarInfo(String model, int series) {

        try {
            Query<Car> query = sessionFactory.getCurrentSession().createQuery("select c from Car c where c.model like :model AND c.series = :series");
            query.setParameter("model", model);
            query.setParameter("series", series);
            Long id = query.getSingleResult().getId();
            Query<User> query1 = sessionFactory.getCurrentSession().createQuery("select u from User u where u.car.id = :id");
            query1.setParameter("id", id);
            return query1.getSingleResult();
        } catch (NoResultException e) {
            throw new RuntimeException("Такого пользователя или такой машины не найдено");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

}
