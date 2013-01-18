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
package org.tomochika1985.struts2_sample.chart.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.tomochika1985.struts2_sample.chart.TimeChart;
import org.tomochika1985.struts2_sample.chart.TimeChartBuilder;
import org.tomochika1985.struts2_sample.chart.TimeChartSource;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilder;
import org.tomochika1985.struts2_sample.chart.composite.CategoriesBuilderImpl;

import com.opensymphony.xwork2.ActionSupport;

/**
 * @author t_hara
 *
 */
@Namespace("/chart")
public class SampleAction extends ActionSupport {

	private static final long serialVersionUID = 784101236208725958L;

	@Action(value = "index", results = {
			@Result(location = "index.jsp")
		})
	@Override
	public String execute() throws Exception {
		
		List<TimeChartSource> sources = new SampleFacade().find();
		TimeChartBuilder timeChartBuilder = new TimeChartBuilder(sources);
		CategoriesBuilder categoriesBuilder = new CategoriesBuilderImpl();
		TimeChart timeChart = timeChartBuilder.buildTimeChart(categoriesBuilder);
		
		return SUCCESS;
	}
	
	public String getName() {
		return "AAB";
	}
}
