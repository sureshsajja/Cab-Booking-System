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
import java.util.List;

/**
 * User :  Suresh
 * Date :  02/10/15
 * Version : v1
 */
public class CabTest {

    @Test
    public void testCabConstruction() {
        Cab cab = new Cab("DL01HB001", 100020);
        Assert.assertEquals(cab.getId(), "DL01HB001");
        Assert.assertEquals(cab.getLocation(), 100020);
    }


    @Test
    public void addCabRequest() {

        Cab cab = new Cab("DL01HB001", 100020);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.HOUR_OF_DAY, 1);
        CabRequest request = new CabRequest("BK1010", 100025, 100023, cal.getTime());
        cab.addJourney(request);

        Calendar afterRequest = Calendar.getInstance();
        afterRequest.setTime(cal.getTime());
        afterRequest.add(Calendar.MINUTE, 8);
        Assert.assertEquals(cab.getLocation(), 100023);
        Assert.assertEquals(cab.getAvailableFrom(), afterRequest.getTime());
        List<CabRequest> req = cab.getRequestsServed();
        Assert.assertEquals(req.get(0).getBookingId(), "BK1010");
    }
}
