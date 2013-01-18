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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.tomochika1985.struts2_sample.chart.LocalMonth;
import org.tomochika1985.struts2_sample.chart.TimeChartSource;

/**
 * @author t_hara
 *
 */
public class SampleFacade {

	public List<TimeChartSource> find() {
		List<TimeChartSource> sources = new ArrayList<TimeChartSource>();
		
		/*
		 * (Top)
		 *   |--- A
		 *   |    |---A0
		 *   |    |   |---A00
		 *   |    |   |---A01
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
		
		LocalMonth month1 = new LocalMonth("201201", "yyyyMM");
		LocalMonth month2 = new LocalMonth("201202", "yyyyMM");
		LocalMonth month3 = new LocalMonth("201203", "yyyyMM");
		
		
		Src srcAA0A00 = new Src().set("A", "A0", "A00");
		sources.add(srcAA0A00.copy().set(month1, new BigDecimal(1)));
		sources.add(srcAA0A00.copy().set(month2, new BigDecimal(2)));
		sources.add(srcAA0A00.copy().set(month3, new BigDecimal(3)));
		
		Src srcAA0A01 = new Src().set("A", "A0", "A01");
		sources.add(srcAA0A01.copy().set(month1, new BigDecimal(4)));
		sources.add(srcAA0A01.copy().set(month2, new BigDecimal(5)));
		sources.add(srcAA0A01.copy().set(month3, new BigDecimal(6)));
		
		Src srcAA1A00 = new Src().set("A", "A1", "A00");
		sources.add(srcAA1A00.copy().set(month1, new BigDecimal(7)));
		sources.add(srcAA1A00.copy().set(month2, new BigDecimal(8)));
		sources.add(srcAA1A00.copy().set(month3, new BigDecimal(9)));
		
		Src srcBA0A00 = new Src().set("B", "A0", "A00");
		sources.add(srcBA0A00.copy().set(month1, new BigDecimal(11)));
		sources.add(srcBA0A00.copy().set(month2, new BigDecimal(12)));
		sources.add(srcBA0A00.copy().set(month3, new BigDecimal(13)));
		
		Src srcBA1A00 = new Src().set("B", "A1", "A00");
		sources.add(srcBA1A00.copy().set(month1, new BigDecimal(14)));
		sources.add(srcBA1A00.copy().set(month2, new BigDecimal(15)));
		sources.add(srcBA1A00.copy().set(month3, new BigDecimal(16)));
		
		Src srcBA1A01 = new Src().set("B", "A1", "A01");
		sources.add(srcBA1A01.copy().set(month1, new BigDecimal(17)));
		sources.add(srcBA1A01.copy().set(month2, new BigDecimal(18)));
		sources.add(srcBA1A01.copy().set(month3, new BigDecimal(19)));
		
		return sources;
	}
	
	static class Src implements TimeChartSource {
		
		Map<String, String> categories = new LinkedHashMap<String, String>();
		
		LocalMonth month;
		
		BigDecimal quantity;
		
		Src copy() {
			Src src = new Src();
			for (String k : categories.keySet()) {
				src.set(k);
			}
			src.month = month;
			src.quantity = quantity;
			return src;
		}

		Src set(String... keys) {
			for (String k : keys) {
				categories.put(k, k);
			}
			return this;
		}
		
		Src set(LocalMonth month, BigDecimal quantity) {
			this.month = month;
			this.quantity = quantity;
			return this;
		}
		
		@Override
		public Map<String, String> getCategories() {
			return categories;
		}
		
		@Override
		public LocalMonth getMonth() {
			return month;
		}
		
		@Override
		public BigDecimal getQuantity() {
			return quantity;
		}
	}
}
