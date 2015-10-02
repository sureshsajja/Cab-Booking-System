/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 CodeRevisited.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.sapient.nitrocab;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

/**
 * User :  Suresh
 * Date :  02/10/15
 * Version : v1
 */
public class CabBookingTest {


    @Test
    public void addCab() {

        Cab one = new Cab("DL01HB001", 100020);
        ICabBookingSystem system = new CabBookingSystemImpl();
        system.addCab(one);
        system.addCab(one);
        Assert.assertEquals(system.getNumofCabsRunning(), 1);
    }

    @Test
    public void checkBooking() throws CabNotAvailableException {
        Cab one = new Cab("DL01HB001", 100020);
        Cab two = new Cab("DL01HB002", 100040);
        Cab three = new Cab("DL01HB003", 100060);
        Cab four = new Cab("DL01HB004", 100080);


        ICabBookingSystem system = new CabBookingSystemImpl();
        system.addCab(one);
        system.addCab(two);
        system.addCab(three);
        system.addCab(four);


        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 10);
        String cab = system.requestForCab(new CabRequest("BR001", 100025, 100056, cal.getTime()));
        Assert.assertEquals(cab, "DL01HB001");


        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 11);
        cab = system.requestForCab(new CabRequest("BR002", 100056, 100022, cal.getTime()));
        Assert.assertEquals(cab, "DL01HB003");


        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 15);
        cab = system.requestForCab(new CabRequest("BR003", 100020, 100075, cal.getTime()));
        Assert.assertEquals(cab, "DL01HB003");

        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 15);
        cab = system.requestForCab(new CabRequest("BR004", 100040, 100056, cal.getTime()));
        Assert.assertEquals(cab, "DL01HB002");


    }


}
