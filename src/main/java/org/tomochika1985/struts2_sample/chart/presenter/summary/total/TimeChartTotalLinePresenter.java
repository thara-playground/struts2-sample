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
package org.tomochika1985.struts2_sample.chart.presenter.summary.total;

import java.util.List;

import org.tomochika1985.struts2_sample.chart.TimeChartLine;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartCategoryPresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartLinePresenter;
import org.tomochika1985.struts2_sample.chart.presenter.TimeChartQuentitiesPresenter;

/**
 * @author t_hara
 *
 */
public class TimeChartTotalLinePresenter implements TimeChartLinePresenter {

	final List<TimeChartCategoryPresenter> categoryPresenters;
	
	final TotalQuentitiesPresenter quentitiesPresenter;
	
	/**
	 * @param categoryPresenters
	 * @param quentitiesPresenter
	 */
	public TimeChartTotalLinePresenter(
			List<TimeChartCategoryPresenter> categoryPresenters,
			TotalQuentitiesPresenter quentitiesPresenter) {
		super();
//		this.categoryPresenters = Arrays.asList(new TimeChartCategoryPresenter[categoryPresenters.size()]);
		this.categoryPresenters = categoryPresenters;
		this.quentitiesPresenter = quentitiesPresenter;
	}

	@Override
	public TimeChartLine getLine() {
		// not supported
		return null;
	}

	@Override
	public List<TimeChartCategoryPresenter> getCategoryPresenters() {
		return categoryPresenters;
	}

	@Override
	public TimeChartQuentitiesPresenter getQuentitiesPresenter() {
		return quentitiesPresenter;
	}

}
