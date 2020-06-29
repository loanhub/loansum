/**
 * Copyright (c) 2020 SyndLoanHub, LLC and contributors
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License from within this distribution and at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 */

package com.syndloanhub.loansum.product.facility;

/**
 * Types of Fee and Rate Options
 */
public enum FeeAndRateOptionType {
	/**
	 * Fixed rate option
	 */
	FixedRate,

	/**
	 * Floating rate option
	 */
	FloatingRate,

	/**
	 * LC rate option
	 */
	LC,

	/**
	 * PIK rate option
	 */
	AccruingPik,

	/**
	 * Fee rate option
	 */
	AccruingFee,
}
