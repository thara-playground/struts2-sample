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

import java.util.List;

import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.action.SampleFacade;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilderImpl;
import org.tomochika1985.struts2_sample.chart.composite.CategoryComponent;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryLeafsVisitor;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

/**
 * @author t_hara
 *
 */
public class TimeChartBuilderTest {

	@Test
	public void test() {
		
		List<TimeChartSource> sources = new SampleFacade().find();
		TimeChartBuilder timeChartBuilder = new TimeChartBuilder(sources);
		TimeChart timeChart = timeChartBuilder.buildTimeChart(new CategoriesBuilderImpl());
		
		List<LocalMonth> targetMonths = timeChart.getTargetMonths();
		assertThat(targetMonths.size(), is(3));
		assertThat(targetMonths.get(0).toString("yyyyMM"), is("201201"));
		assertThat(targetMonths.get(1).toString("yyyyMM"), is("201202"));
		assertThat(targetMonths.get(2).toString("yyyyMM"), is("201203"));
		
		CategoryComponent categories = timeChart.getCategories();
		assertThat(categories.getChiildren().size(), is(2));
		assertThat(categories.accept(new CategoryLeafsVisitor()).getLeafsCount(), is(6));
		
		List<TimeChartLine> lines = timeChart.getLines();
		assertThat(lines.size(), is(6));

		TimeChartLine lineAA0A00 = lines.get(0);
		assertThat(lineAA0A00.getKey().getCategories().get(0).getCode(), is("A"));
		assertThat(lineAA0A00.getKey().getCategories().get(1).getCode(), is("A0"));
		assertThat(lineAA0A00.getKey().getCategories().get(2).getCode(), is("A00"));
		
		assertThat(lineAA0A00.getQuantityOf(new LocalMonth("201201", "yyyyMM")).getInteger(), is(1));
		assertThat(lineAA0A00.getQuantityOf(new LocalMonth("201202", "yyyyMM")).getInteger(), is(2));
		assertThat(lineAA0A00.getQuantityOf(new LocalMonth("201203", "yyyyMM")).getInteger(), is(3));
	
		TimeChartLine lineAA0A01 = lines.get(1);
		assertThat(lineAA0A01.getKey().getCategories().get(0).getCode(), is("A"));
		assertThat(lineAA0A01.getKey().getCategories().get(1).getCode(), is("A0"));
		assertThat(lineAA0A01.getKey().getCategories().get(2).getCode(), is("A01"));
		
		assertThat(lineAA0A01.getQuantityOf(new LocalMonth("201201", "yyyyMM")).getInteger(), is(4));
		assertThat(lineAA0A01.getQuantityOf(new LocalMonth("201202", "yyyyMM")).getInteger(), is(5));
		assertThat(lineAA0A01.getQuantityOf(new LocalMonth("201203", "yyyyMM")).getInteger(), is(6));
		
		TimeChartLine lineBA0A00 = lines.get(3);
		assertThat(lineBA0A00.getKey().getCategories().get(0).getCode(), is("B"));
		assertThat(lineBA0A00.getKey().getCategories().get(1).getCode(), is("A0"));
		assertThat(lineBA0A00.getKey().getCategories().get(2).getCode(), is("A00"));
		
		assertThat(lineBA0A00.getQuantityOf(new LocalMonth("201201", "yyyyMM")).getInteger(), is(11));
		assertThat(lineBA0A00.getQuantityOf(new LocalMonth("201202", "yyyyMM")).getInteger(), is(12));
		assertThat(lineBA0A00.getQuantityOf(new LocalMonth("201203", "yyyyMM")).getInteger(), is(13));		
	}

}
