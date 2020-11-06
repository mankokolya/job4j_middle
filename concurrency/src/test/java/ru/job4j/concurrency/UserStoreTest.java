package ru.job4j.concurrency;

import org.junit.Test;
import ru.job4j.concurrency.userstorage.User;
import ru.job4j.concurrency.userstorage.UserStore;

import static org.junit.Assert.*;

public class UserStoreTest {

    @Test
    public void whenStoreEmptyThenAddTrue() {
        UserStore store = new UserStore();
        assertTrue(store.add(new User(1, 200)));
    }

    @Test
    public void whenUserIsInStoreThenAddFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 200));
        assertFalse(store.add(new User(1, 200)));
    }

    @Test
    public void whenUserNotInStoreThenUpdateFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 200));
        assertFalse(store.update(new User(2, 100)));
    }

    @Test
    public void whenUserIsInStoreThenUpdateTrue() {
        UserStore store = new UserStore();
        store.add(new User(1, 200));
        assertTrue(store.update(new User(1, 100)));
    }

    @Test
    public void whenUserIsInStoreThenDeleteTrue() {
        UserStore store = new UserStore();
        store.add(new User(1, 200));
        assertTrue(store.delete(new User(1, 100)));
    }

    @Test
    public void whenUsersInStoreAndMoneyEnoughThenTransferTrue() {
        UserStore store = new UserStore();
        User nick = new User(1, 200);
        User ira = new User(2, 300);
        store.add(nick);
        store.add(ira);
        assertTrue(store.transfer(2, 1, 100));
        assertEquals(300, nick.getMoneyAmount());
        assertEquals(200, ira.getMoneyAmount());
    }

    @Test
    public void whenNotEnoughMoneyTransferFalse() {
        UserStore store = new UserStore();
        store.add(new User(1, 200));
        store.add(new User(2, 300));
        assertFalse(store.transfer(2, 1, 400));
    }
}