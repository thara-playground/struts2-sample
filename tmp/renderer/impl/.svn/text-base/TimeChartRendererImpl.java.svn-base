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
package org.tomochika1985.struts2_sample.chart.renderer.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.tomochika1985.struts2_sample.chart.Category;
import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryLeafsVisitor;
import org.tomochika1985.struts2_sample.chart.renderer.TimeChartCategoryRenderer;
import org.tomochika1985.struts2_sample.chart.renderer.TimeChartLineRenderer;
import org.tomochika1985.struts2_sample.chart.renderer.TimeChartQuantityRenderer;
import org.tomochika1985.struts2_sample.chart.renderer.TimeChartRenderer;
import org.tomochika1985.struts2_sample.chart.renderer.TimeChartTotalLineRenderer;

/**
 * @author t_hara
 *
 */
public class TimeChartRendererImpl implements TimeChartRenderer {

	private TimeChart timeChart;
	
	Set<Category> alreadyRenderedCategorys = new HashSet<Category>();
	
	private Map<Category, Integer> lineSizeMap = new HashMap<Category, Integer>();
	
	/**
	 * @param timeChart
	 */
	public TimeChartRendererImpl(TimeChart timeChart) {
		super();
		this.timeChart = timeChart;
		
		List<CategoryComponent> categories = timeChart.getCategories();
		for (CategoryComponent categoryComponent : categories) {
			CategoryLeafsVisitor visitor = categoryComponent.accept(new CategoryLeafsVisitor());
			
		}
	}
	
	@Override
	public List<TimeChartLineRenderer> getLineRenderers() {
		return null;
	}
	
	class TimeChartLineRendererImpl implements TimeChartLineRenderer {

		List<Category> categories = new ArrayList<Category>();
		
		@Override
		public List<TimeChartCategoryRenderer> getCategoryRenderers() {
			
			List<TimeChartCategoryRenderer> renderes = new ArrayList<TimeChartCategoryRenderer>();
			for (Category c : categories) {
				boolean alreadyRendered = alreadyRenderedCategorys.contains(c);
				Integer lineSize = lineSizeMap.get(c);
				TimeChartCategoryRenderer renderer = new TimeChartCategoryRenderer(c, alreadyRendered, lineSize);
				alreadyRenderedCategorys.add(c);
			}
			return renderes;
		}

		@Override
		public List<TimeChartQuantityRenderer> getQuantityRenderers() {
//			TimeChartQuantityRenderer renderer = new TimeChartQuantityRenderer(key, quantity);
			return null;
		}

		
		//TODO
		@Override
		public boolean isTotalizeOfNextLine() {
			return false;
		}

		//TODO
		@Override
		public TimeChartTotalLineRenderer getTotalLineRenderer() {
			return null;
		}
	}
}
