package ru.job4j.concurrency.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private final List<User> store;

    public UserStore() {
        store = new ArrayList<>();
    }

    public synchronized boolean add(User user) {
        boolean result = false;
        if (!store.contains(user)) {
            store.add(user);
            result = true;
        }
        return result;
    }

    public synchronized boolean update(User user) {
        boolean result = false;
        if (store.contains(user)) {
            store.set(store.indexOf(user), user);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(User user) {
        boolean result = false;
        if (store.contains(user)) {
            store.remove(user);
            result = true;
        }
        return result;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        Optional<User> fromUser = store.stream().filter(user -> user.getId() == fromId).findFirst();
        if (fromUser.isEmpty()) {
            return false;
        } else if (fromUser.get().getMoneyAmount() < amount) {
            return false;
        }
        Optional<User> toUser = store.stream().filter(user -> user.getId() == toId).findFirst();
        if (toUser.isEmpty()) {
            return false;
        }
        fromUser.get().setMoneyAmount(fromUser.get().getMoneyAmount() - amount);
        toUser.get().setMoneyAmount(toUser.get().getMoneyAmount() + amount);
        return true;
    }
}
