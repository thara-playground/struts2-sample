/*
* Copyright 2011 Tomochika Hara.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package org.tomochika1985.struts2_sample.chart;

import java.math.BigDecimal;

/**
 * @author t_hara
 *
 */
public class Quantity {

	private final BigDecimal value;
	
	/**
	 * @param value
	 */
	public Quantity(BigDecimal value) {
		super();
		if (value == null) throw new IllegalArgumentException("value is null.");
		this.value = value;
	}
	
	public Quantity add(Quantity other) {
		return new Quantity(value.add(other.value));
	}
	
	public Integer getInteger() {
		return value.intValue();
	}

	public String getStringValue() {
		return value.toString();
	}
	
	@Override
	public int hashCode() {
		return value.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Quantity)) return false;
		if (this == obj) return true;
		return this.value.equals(Quantity.class.cast(obj).value);
	}
	
	@Override
	public String toString() {
		return getStringValue();
	}
}
