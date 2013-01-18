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
package org.tomochika1985.struts2_sample.chart.renderer;

import org.tomochika1985.struts2_sample.chart.Quantity;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;

/**
 * @author t_hara
 *
 */
public class TimeChartQuantityRenderer {

	final CategoryCompositeKey key;
	
	final Quantity quantity;
	
	/**
	 * @param key
	 * @param quantity
	 */
	public TimeChartQuantityRenderer(CategoryCompositeKey key,
			Quantity quantity) {
		super();
		if (key == null) throw new IllegalArgumentException("key is null.");
		if (quantity == null) throw new IllegalArgumentException("quantity is null.");
		this.key = key;
		this.quantity = quantity;
	}

	public CategoryCompositeKey getKey() {
		return this.key;
	}
	
	public Quantity getQuantity() {
		return quantity;
	}
}
