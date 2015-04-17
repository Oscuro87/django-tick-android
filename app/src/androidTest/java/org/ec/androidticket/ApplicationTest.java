package org.ec.androidticket;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.squareup.otto.Bus;

import org.ec.androidticket.backend.Async.BusDepot;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private Bus bus;

    public ApplicationTest() {
        super(Application.class);
        bus = BusDepot.get().getBus(BusDepot.BusType.GENERAL);
        bus.register(this);
    }

    public void testWrongLogin()
    {

    }
}