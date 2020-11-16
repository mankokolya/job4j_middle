package ru.job4j.concurrency.emailnotification;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private final ExecutorService pool;

    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void emailTo(User user) {
        pool.submit(
                () -> {
                    String username = user.getUsername();
                    String email = user.getEmail();
                    String subject = String.format("Notification %s to email %s.", username, email);
                    String body = String.format("Add a new event to %s", username);
                    send(subject, body, email);
                }
        );
    }

    public void close() {
        pool.shutdown();
    }

    public void send(String subject, String body, String email) {
        System.out.println(subject + System.lineSeparator() + body + System.lineSeparator() + email
                + System.lineSeparator() + System.lineSeparator());
    }

    public static void main(String[] args) {
        List<User> users = new ArrayList<>(List.of(new User("Nick", "mankokolya@gmail.com"),
                new User("Ira", "ira@gmail.com"),
                new User("Bill", "bill@gmail.com")
        ));
        EmailNotification notification = new EmailNotification();
        for (User user : users) {
            notification.emailTo(user);
        }
        notification.close();
    }
}
