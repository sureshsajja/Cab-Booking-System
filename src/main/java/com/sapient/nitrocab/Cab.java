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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User :  Suresh
 * Date :  02/10/15
 * Version : v1
 */
public class Cab {

    private final String id;
    private int location;
    private Date availableFrom;
    private final List<CabRequest> requests;


    public Cab(String num, int location) {
        this.id = num;
        this.location = location;
        Calendar cal = Calendar.getInstance();
        this.availableFrom = cal.getTime();
        this.requests = new ArrayList<>();
    }

    public int getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public List<CabRequest> getRequestsServed() {
        return requests;
    }


    public int hashCode() {
        return id.hashCode();
    }

    public boolean equals(Object that) {
        if (that instanceof Cab) {
            Cab _that = (Cab) that;
            return this.id.equals(_that.id);
        } else {
            return false;
        }
    }


    public Date getAvailableFrom() {
        return (Date) availableFrom.clone();
    }

    private void addJourneyMinutes(Date pickUpTime, int minutes) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        long curTimeInMs = pickUpTime.getTime();
        this.availableFrom = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
    }

    public boolean gotTimeToServeThis(CabRequest request) {
        int timeToReach = getTimeToReach(request);
        Date currentTime = addMinutesToDate(timeToReach, this.getAvailableFrom());
        Date pickUpTime = request.getPickupTime();
        int spareTime = (int) (pickUpTime.getTime() - currentTime.getTime()) / (1000 * 60);
        return spareTime >= 15;
    }

    private int getTimeToReach(CabRequest request) {
        int distance = Math.abs(this.getLocation() - request.getPickupCode()) * CabSystemConstants.DISTANCE_BETWEEN_LOCATIONS;
        return distance * CabSystemConstants.TIME_FOR_KM;
    }

    private int getTimeForDrive(CabRequest request) {
        int distance = Math.abs(request.getPickupCode() - request.getDropCode()) * CabSystemConstants.DISTANCE_BETWEEN_LOCATIONS;
        return distance * CabSystemConstants.TIME_FOR_KM;
    }

    private static Date addMinutesToDate(int minutes, Date beforeTime) {
        final long ONE_MINUTE_IN_MILLIS = 60000;
        long curTimeInMs = beforeTime.getTime();
        return new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
    }

    public double getProfit(CabRequest request) {
        int distance = Math.abs(this.getLocation() - request.getPickupCode()) * CabSystemConstants.DISTANCE_BETWEEN_LOCATIONS;
        double costInCurred = distance * CabSystemConstants.COMPANY_COST;
        int travelDistance = Math.abs(request.getPickupCode() - request.getDropCode());
        costInCurred += travelDistance * CabSystemConstants.COMPANY_COST;
        double costCollected = travelDistance * CabSystemConstants.FARE;
        return ((costCollected - costInCurred) / costInCurred) * 100;
    }


    public void addJourney(CabRequest request) {
        addJourneyMinutes(request.getPickupTime(), getTimeForDrive(request));
        this.location = request.getDropCode();
        requests.add(request);

    }
}
