package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        User user1 = new User("Иван", "Иванов", "ivanov@example.com");
        User user2 = new User("Петр", "Петров", "petrov@example.com");
        User user3 = new User("Анна", "Сидорова", "sidorova@example.com");
        User user4 = new User("Елена", "Смирнова", "smirnova@example.com");
        User user5 = new User("Алексей", "Кузнецов", "kuznetsov@example.com");

        Car car1 = new Car("Toyota", 70);
        Car car2 = new Car("BMW", 530);
        Car car3 = new Car("Mercedes-Benz", 21);
        Car car4 = new Car("ВАЗ", 2101);
        Car car5 = new Car("Lexus", 4);

        user1.setCar(car1);
        user2.setCar(car2);
        user3.setCar(car3);
        user4.setCar(car4);
        user5.setCar(car5);

        userService.addCar(car1);
        userService.addCar(car2);
        userService.addCar(car3);
        userService.addCar(car4);
        userService.addCar(car5);

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);
        userService.add(user5);


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println(user.getCar());
        }

        System.out.println("User которого мы ищем - " + userService.findUserByCarInfo("ВАЗ", 2101).toString());
        context.close();
    }
}
