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

import java.util.HashSet;
import java.util.Set;

/**
 * User :  Suresh
 * Date :  02/10/15
 * Version : v1
 */
public class CabBookingSystemImpl implements ICabBookingSystem {

    private Set<Cab> availableCabs = new HashSet<>();

    @Override
    public void addCab(Cab cab) {
        availableCabs.add(cab);
    }

    @Override
    public String requestForCab(CabRequest request) throws CabNotAvailableException {

        Cab selectedCab = null;
        double maxProfit = 0;
        for (Cab cab : availableCabs) {
            if (cab.gotTimeToServeThis(request)) {
                double profit = cab.getProfit(request);
                if (profit > 20 && profit > maxProfit) {
                    selectedCab = cab;
                    maxProfit = profit;
                }
            }
        }

        if (selectedCab != null) {
            selectedCab.addJourney(request);
            return selectedCab.getId();
        } else {
            throw new CabNotAvailableException("No Cab Available");
        }
    }

    @Override
    public int getNumofCabsRunning() {
        return availableCabs.size();
    }


}
