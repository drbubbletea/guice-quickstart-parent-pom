package net.timeboxing.database.impl;

import net.timeboxing.database.InMemoryDS;
import net.timeboxing.database.TestDAL;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Inject;

public class DBTestDAL implements TestDAL {


    private final Jdbi jdbi;

    @Inject
    public DBTestDAL(@InMemoryDS Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public int test() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT 1")
                .mapTo(Integer.class)
                .first());
    }
}
