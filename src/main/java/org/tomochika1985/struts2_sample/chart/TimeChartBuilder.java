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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilder;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesSource;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesSourceImpl;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryLeafsVisitor;

/**
 * @author t_hara
 *
 */
public class TimeChartBuilder {

	private List<TimeChartSource> sources;
	
	private List<LocalMonth> targetMonths = new ArrayList<LocalMonth>();
	
	private CategoryComponent categoryComponents;

	/**
	 * @param sources
	 */
	public TimeChartBuilder(List<TimeChartSource> sources) {
		super();
		if (sources == null) throw new IllegalArgumentException("sources is null.");
		if (sources.isEmpty()) throw new IllegalArgumentException("sources is empty.");
		this.sources = sources;

		Set<LocalMonth> targetMonths = new HashSet<LocalMonth>();
		for (TimeChartSource src : sources) {
			targetMonths.add(src.getMonth());
		}
		this.targetMonths.addAll(targetMonths);
		Collections.sort(this.targetMonths);
	}
	
	public TimeChart buildTimeChart(CategoriesBuilder categoriesBuilder) {
		
		buildCategoryComponent(categoriesBuilder);
		
		Map<CategoryCompositeKey, TimeChartLine> lineMap = getLineMap();
		List<CategoryComponent> leafsAll = getLeafsAll();
		
		List<TimeChartLine> lines = new ArrayList<TimeChartLine>();
		for (CategoryComponent leaf : leafsAll) {
			lines.add(lineMap.get(leaf.getKey()));
		}
		
		return new TimeChart(new ArrayList<LocalMonth>(targetMonths), categoryComponents, lines);
	}

	/**
	 * @return
	 */
	Map<CategoryCompositeKey, TimeChartLine> getLineMap() {
		Map<CategoryCompositeKey, TimeChartLine> keyLineMap = new HashMap<CategoryCompositeKey, TimeChartLine>();
		
		for (TimeChartSource src : sources) {
			CategoriesSource cateSrc = createSource(src);
			CategoryCompositeKey key = new CategoryCompositeKey(cateSrc.getCategories());
			TimeChartLine line = keyLineMap.get(key);
			if (line == null) {
				line = new TimeChartLine(key);
				keyLineMap.put(key, line);
			}
			LocalMonth month = src.getMonth();
			Quantity quantity = new Quantity(src.getQuantity());
			line.putQuantity(month, quantity);
		}
		return keyLineMap;
	}

	/**
	 * @return
	 */
	List<CategoryComponent> getLeafsAll() {
		return categoryComponents.accept(new CategoryLeafsVisitor()).getLeafAll();
	}

	void buildCategoryComponent(CategoriesBuilder builder) {
		List<CategoriesSource> categoriesSources = new ArrayList<CategoriesSource>();

		for (TimeChartSource src : sources) {
			CategoriesSource cateSrc = createSource(src);
			categoriesSources.add(cateSrc);
		}
		
		categoryComponents = builder.buildComponent(categoriesSources);
	}

	/**
	 * @param src
	 * @return
	 */
	CategoriesSource createSource(TimeChartSource src) {
		CategoriesSourceImpl cateSrc = new CategoriesSourceImpl();
		for (Entry<String, String> entry : src.getCategories().entrySet()) {
			Category c = new Category(entry.getKey(), entry.getValue());
			cateSrc.add(c);
		}
		return cateSrc;
	}
}
