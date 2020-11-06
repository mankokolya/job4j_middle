package ru.job4j.concurrency.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
@ThreadSafe
public class User {
    private final int id;
    @GuardedBy("this") private int moneyAmount;

    public User(int id, int moneyAmount) {
        this.moneyAmount = moneyAmount;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public synchronized int getMoneyAmount() {
        return moneyAmount;
    }

    public synchronized void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
