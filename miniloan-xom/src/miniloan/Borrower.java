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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


// import to be able to use the annotations for the BOM
import ilog.rules.bom.annotations.*;

/**
 * This class models a borrower.
 * A borrower is created with a name, a credit score, and a yearly income.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Borrower {
	@XmlElement	
	private String name;
	@XmlElement	
    private int creditScore;
	@XmlElement	
    private int yearlyIncome;

	public Borrower() {
	}

	/**
	 * Builds a borrower.
	 * The parameters names to be used in the BOM are given with the annotation BusinessName
	 * @param name The name of the borrower.
	 * @param creditScore The credit score of the borrower.
	 * @param yearlyIncome The yearly income of the borrower.
	 */
	public Borrower(@BusinessName("name") String name,@BusinessName("creditScore") int creditScore, 
			@BusinessName("yearlyIncome") int yearlyIncome) {
	    this();
		this.name = name;
		this.creditScore = creditScore;
		this.yearlyIncome = yearlyIncome;
	}

	/**
	 * @return The name of the borrower.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the name of the borrower.
	 * @param name The name to set.
	 */
	public void setName(String n) {
		name = n;
	}
	/**
	 * @return The credit score of the borrower.
	 */
	public int getCreditScore() {
		return creditScore;
	}
	/**
	 * Sets the credit score of the borrower.
	 * @param creditScore The credit score to set.
	 */
	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}
	/**
	 * @return The yearly income of the borrower.
	 */
	public int getYearlyIncome() {
		return yearlyIncome;
	}
	/**
	 * Sets the yearly income of the borrower.
	 * @param yearlyIncome The yearly income to set.
	 */
	public void setYearlyIncome(int yearlyIncome) {
		this.yearlyIncome = yearlyIncome;
	}
}
