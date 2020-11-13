package ru.job4j.concurrency.nonblockingcache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class Base {


    private final int id;
    @GuardedBy("this")
    private final AtomicInteger version;

    public Base(int id, int version) {
        this.id = id;
        this.version = new AtomicInteger(version);
    }

    public void setVersion(int versionInCache) {
//        int temp;
//        temp = this.version.get();
        if (!this.version.compareAndSet(versionInCache,++versionInCache)) {
            throw new OptimisticException("You are trying to modify old value");
        }
    }

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                ", version=" + version +
                '}';
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id &&
                version.equals(base.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

    public int getVersion() {
        return version.get();
    }
}