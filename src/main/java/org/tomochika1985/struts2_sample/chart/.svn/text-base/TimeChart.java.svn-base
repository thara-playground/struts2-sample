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

import java.util.ArrayList;
import java.util.List;

import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;

/**
 * @author t_hara
 *
 */
public class TimeChart {

	final List<LocalMonth> targetMonths = new ArrayList<LocalMonth>();
	
	final CategoryComponent categoryComponent;
	
	final List<TimeChartLine> lines = new ArrayList<TimeChartLine>();
	
	/**
	 * @param targetMonths
	 * @param categoryComponent
	 * @param lines
	 */
	public TimeChart(List<LocalMonth> targetMonths,
			CategoryComponent categoryComponent, List<TimeChartLine> lines) {
		super();
		this.targetMonths.addAll(targetMonths);
		this.categoryComponent = categoryComponent;
		this.lines.addAll(lines);
	}

	public List<LocalMonth> getTargetMonths() {
		return new ArrayList<LocalMonth>(targetMonths);
	}
	
	public CategoryComponent getCategories() {
		return categoryComponent;
	}
	
	public List<TimeChartLine> getLines(){
		return new ArrayList<TimeChartLine>(lines);
	}
}
