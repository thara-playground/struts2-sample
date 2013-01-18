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
package org.tomochika1985.struts2_sample.chart.presenter.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.Category;
import org.tomochika1985.struts2_sample.chart.LocalMonth;
import org.tomochika1985.struts2_sample.chart.Quantity;
import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartQuentitiesPresenter;

/**
 * @author t_hara
 *
 */
public class TimeChartQuentitiesPresenterImpl implements TimeChartQuentitiesPresenter {

	final TimeChartLine line;
	
	final Map<String, String> keyValues = new HashMap<String, String>();
	
	/**
	 * @param line
	 * @param mediator
	 */
	public TimeChartQuentitiesPresenterImpl(TimeChartLine line) {
		super();
		this.line = line;
		
		CategoryCompositeKey key = line.getKey();
		List<Category> categories = key.getCategories();
		for (Category c : categories) {
			putKeyValue(c);
		}
	}

	@Override
	public Quantity getQuentiryOf(LocalMonth month) {
		return line.getQuantityOf(month);
	}
	
	/**
	 * @return the line
	 */
	public TimeChartLine getLine() {
		return line;
	}
	
	/**
	 * @param c
	 * @return
	 */
	protected void putKeyValue(Category c) {
		keyValues.put(c.getCode(), c.getName());
	}

	public Map<String, String> getKeyValues() {
		return keyValues;
	}
	
}
