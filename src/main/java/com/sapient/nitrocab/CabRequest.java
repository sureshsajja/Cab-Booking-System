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

import java.util.Date;

/**
 * User :  Suresh
 * Date :  02/10/15
 * Version : v1
 */
public class CabRequest {

    private final String bookingId;
    private final int pickupCode;
    private final int dropCode;
    private final Date pickupTime;


    public CabRequest(String bookingId, int pickupCode, int dropCode, Date pickupTime) {
        this.bookingId = bookingId;
        this.pickupCode = pickupCode;
        this.dropCode = dropCode;
        this.pickupTime = pickupTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public int getPickupCode() {
        return pickupCode;
    }

    public int getDropCode() {
        return dropCode;
    }

    public Date getPickupTime() {
        return (Date) pickupTime.clone();
    }
}
