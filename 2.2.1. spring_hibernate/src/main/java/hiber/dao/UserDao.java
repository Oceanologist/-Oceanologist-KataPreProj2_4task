package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {

    void add(User user);

    void addCar(Car car);

    User findUserByCarInfo(String model, int series);

    List<User> listUsers();
}
