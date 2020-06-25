/**
 * Copyright (c) 2018 SyndLoanHub, LLC and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License from within this distribution and at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.syndloanhub.loansum.product.facility.prorated;

import java.time.LocalDate;
import java.util.Optional;

import com.opengamma.strata.basics.currency.CurrencyAmount;
import com.opengamma.strata.basics.date.DayCount;
import com.opengamma.strata.basics.schedule.Frequency;
import com.syndloanhub.loansum.product.facility.AccrualType;

/**
 * Interface that all accrual (fixed and floating) classes implement to support
 * pricing.
 */
public interface ProratedAccrual {
	/**
	 * @return number of days in accrual period
	 */
	public abstract int getDays();
	
	/**
	 * @return accrual type
	 */
	public abstract AccrualType getAccrualType();

	/**
	 * @return accrual period start date
	 */
	public abstract LocalDate getStartDate();

	/**
	 * @return accrual period end date which may or may not coincide with payment
	 *         date
	 */
	public abstract LocalDate getEndDate();

	/**
	 * @return optional payment date, for implementing interest-on-paydown
	 *         sub-accrual exception
	 */
	public abstract Optional<LocalDate> getPaymentDate();

	/**
	 * @return total annual cash interest rate, this DOES NOT include the PIK spread
	 */
	public abstract double getAllInRate();

	/**
	 * @return total annual pay-in-kind interest rate
	 */
	public abstract double getPikSpread();

	/**
	 * @return accrual amount in specified currency
	 */
	public abstract CurrencyAmount getAccrualAmount();

	/**
	 * @return day count basis to be used for calculations
	 */
	public abstract DayCount getDayCount();

	/**
	 * @return accrual payment frequency
	 */
	public abstract Frequency getPaymentFrequency();

	/**
	 * @return calculated projected cash payment amount
	 */
	public abstract CurrencyAmount getPaymentProjection();

	/**
	 * @return calculated projected PIK capitalization amount
	 */
	public abstract CurrencyAmount getPikProjection();

}
