package com.persistentbit.sql.old;

import com.persistentbit.glasgolia.db.connections.pool.PooledConnectionSupplier;
import com.persistentbit.glasgolia.db.connections.SimpleConnectionSupplier;

/**
 * User: petermuys
 * Date: 13/07/16
 * Time: 18:55
 */
public class InMemConnectionSupplier extends PooledConnectionSupplier{


    public InMemConnectionSupplier() {
        super(new SimpleConnectionSupplier(
               "org.apache.derby.jdbc.EmbeddedDriver",
                "jdbc:derby:memory:junittests;create=true"
        ),10);
    }


}
