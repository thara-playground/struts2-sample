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
package org.tomochika1985.struts2_sample.chart.presenter.summary.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.Category;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilder;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilderImpl;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesSource;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesSourceImpl;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.CategoryCompositeKey;
import org.tomochika1985.struts2_sample.chart.presenter.summary.ControlBreakResolver;
import org.tomochika1985.struts2_sample.chart.presenter.summary.TimeChartSummarizer;

import static org.junit.Assert.*;

/**
 * @author t_hara
 *
 */
public class ControlBraekResolverImplTest {

	static class Src extends CategoriesSourceImpl{}
	
	@Test
	public void test() {
		
		/*
		 * (Top)
		 *   |--- A
		 *   |    |---A0
		 *   |    |   |---A00
		 *   |    |   |---A01
		 *   |    |   |---A02
		 *   |    |
		 *   |    |---A1
		 *   |    |   |---A00
		 *   |
		 *   |--- B
		 *   |    |---A0
		 *   |    |   |---A00
		 *   |    |
		 *   |    |---A1
		 *   |    |   |---A01
		 *   |    |   |---A02
		 * 
		 */
		List<CategoriesSource> sources = new ArrayList<CategoriesSource>();
		
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A0", "")).add(new Category("A00", ""))
				);
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A0", "")).add(new Category("A01", ""))
				);
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A0", "")).add(new Category("A02", ""))
				);
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A1", "")).add(new Category("A00", ""))
				);
		sources.add(new Src().
				add(new Category("B", "")).add(new Category("A0", "")).add(new Category("A00", ""))
				);
		sources.add(new Src().
				add(new Category("B", "")).add(new Category("A1", "")).add(new Category("A01", ""))
				);
		sources.add(new Src().
				add(new Category("B", "")).add(new Category("A1", "")).add(new Category("A02", ""))
				);
		
		CategoriesBuilder categoriesBuilder = new CategoriesBuilderImpl();
		CategoryComponent component = categoriesBuilder.buildComponent(sources);
		
		TimeChartSummarizer summarizer = new TimeChartSummarizer();
		summarizer.addSummarizeKey(CategoryCompositeKey.create("A", "A0"));
		summarizer.addSummarizeKey(CategoryCompositeKey.create("B", "A1"));
		ControlBreakResolver target = new ControlBraekResolverImpl(component, summarizer);
		
		assertFalse(target.isControlBreak(CategoryCompositeKey.create("A", "A0", "A00")));
		assertFalse(target.isControlBreak(CategoryCompositeKey.create("A", "A0", "A01")));
		assertTrue(target.isControlBreak(CategoryCompositeKey.create("A", "A0", "A02")));
		assertFalse(target.isControlBreak(CategoryCompositeKey.create("A", "A1", "A00")));
		
		assertFalse(target.isControlBreak(CategoryCompositeKey.create("B", "A0", "A00")));
		assertFalse(target.isControlBreak(CategoryCompositeKey.create("B", "A1", "A01")));
		assertTrue(target.isControlBreak(CategoryCompositeKey.create("B", "A1", "A02")));
		
	}

}
