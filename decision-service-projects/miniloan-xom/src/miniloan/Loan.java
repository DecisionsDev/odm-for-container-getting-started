/*
* Copyright IBM Corp. 1987, 2018
* 
* Licensed to the Apache Software Foundation (ASF) under one
* or more contributor license agreements.  See the NOTICE file
* distributed with this work for additional information
* regarding copyright ownership.  The ASF licenses this file
* to you under the Apache License, Version 2.0 (the
* "License"); you may not use this file except in compliance
* with the License.  You may obtain a copy of the License at
* 
* http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing,
* software distributed under the License is distributed on an
* "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
* KIND, either express or implied.  See the License for the
* specific language governing permissions and limitations
* under the License.
* 
**/

package miniloan;
//import to be able to use the annotations for the BOM
import ilog.rules.bom.annotations.*;

import java.util.ArrayList;
import java.util.Collection;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


/**
 * This class models a loan request.
 *
 * A loan is created with an amount, a duration, and a rate.
 * By default, a loan request is considered as approved.
 * Then when the loan status is evaluated, the loan approval status
 * may be set to false, and messages may be attached to the loan request
 * to explain the rejection.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Loan {
	@XmlElement	
    private int amount;
	@XmlElement	
    private int duration;
   private double yearlyInterestRate;
    private int yearlyRepayment;
    private boolean approved;
     private Collection<String> messages;

	/**
	 * Builds an empty loan request.
	 */
	public Loan() {
	   this.messages = new ArrayList<String>();
       this.approved = true;
	}

	/**
	 * Builds a loan request.
	 * The parameters names to be used in the BOM are given with the annotation BusinessName
	 * @param amount The requested amount of the loan.
	 * @param duration The requested duration (in months) of the loan.
	 * @param yearlyInterestRate The yearly base interest rate.
	 */
	// property to tell in the BOM that this is the constructor for DVS
	@CustomProperty(name = "dataio.default",
			value = "true")
	public Loan(@BusinessName("amount") int amount,@BusinessName("duration") int duration, 
			@BusinessName("yearlyInterestRate") double yearlyInterestRate) {
	    this();
		this.amount = amount;
		this.duration = duration;
		this.yearlyInterestRate = yearlyInterestRate;
	}

	/**
	 * @return The amount of the loan.
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * Sets the amount of the loan.
	 * @param a The amount to set.
	 */
	public void setAmount(int a) {
		amount = a;
	}
	/**
	 * @return The approval status of the loan.
	 */
	// property to tell in the BOM that this property should be ignored
	@CustomProperty(name = "factory.ignore",
			value = "true")
	public boolean isApproved() {
		return approved;
	}
	/**
	 * @return A string giving the approval status of the loan
	 */
	public String getApprovalStatus() {
		return String.valueOf(approved) + " " + messages.toString();
	}
	/**
	 * Sets the approval status of the loan.
	 * @param approved The approval status to set.
	 */
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	/**
	 * Rejects the loan by setting the approval status to false.
	 */
	public void reject() {
		this.approved = false;
	}
	/**
	 * @return The duration of the loan (in months).
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * Sets the duration of the loan.
	 * @param d The duration to set.
	 */	
	public void setDuration(int d) {
		duration = d;
	}
	/**
	 * @return The yearly interest rate.
	 */
	public double getYearlyInterestRate() {
		return yearlyInterestRate;
	}
	/**
	 * Sets the yearly interest rate of the loan.
	 * @param rate The rate to set.
	 */
	public void setYearlyInterestRate(double rate) {
		yearlyInterestRate = rate;
	}

	/**
	 * @return The messages attached to the loan.
	 */
	public Collection<String> getMessages() {
		return messages;
	}
	/**
	 * Removes a message.
	 * @param argument The message to remove.
	 */
	public void removeFromMessages (String argument) {
		messages.remove(argument);
	}
	/**
	 * Adds a message.
	 * @param argument The message to add.
	 */
	public void addToMessages (String argument) {
		messages.add(argument);
	}
	/**
	 * @return The yearly repayment.
	 * The value is computed and cached when the other data is consistent.
	 */
	public int getYearlyRepayment() {
	    if (yearlyRepayment == 0 && yearlyInterestRate != 0 && duration !=0 ) {
		   this.yearlyRepayment = computeYearlyRepayment();
		}
		return yearlyRepayment;
	}
	/**
	 * Computes the yearly repayment from the loan duration, amount, and rate.
	 * @return The computed yearly repayment.
	 */
	private int computeYearlyRepayment() {
		double i = yearlyInterestRate / 12;
		double p = i * amount / (1 - Math.pow(1 + i, -duration));

		return (int)(p * 12);
	}
}
