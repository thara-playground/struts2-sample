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

import java.util.HashMap;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;

/**
 * @author t_hara
 *
 */
public class TimeChartLine {

	CategoryCompositeKey key;
	
	final Map<LocalMonth, Quantity> quantities = new HashMap<LocalMonth, Quantity>();
	
	/**
	 * @param key
	 */
	public TimeChartLine(CategoryCompositeKey key) {
		super();
		this.key = key;
	}

	public CategoryCompositeKey getKey() {
		return key;
	}
	
	void putQuantity(LocalMonth month, Quantity quantity) {
		quantities.put(month, quantity);
	}
	
	public Quantity getQuantityOf(LocalMonth month) {
		return quantities.get(month);
	}
}
