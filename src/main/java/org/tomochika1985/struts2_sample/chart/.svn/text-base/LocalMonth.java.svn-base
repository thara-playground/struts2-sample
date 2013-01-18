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

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

/**
 * @author t_hara
 *
 */
public class LocalMonth implements Comparable<LocalMonth>{

	private final LocalDate date;

	/**
	 * 
	 */
	public LocalMonth() {
		this(new LocalDate());
	}
	
	public LocalMonth(String dateStr, String pattern) {
		this(DateTimeFormat.forPattern(pattern).parseLocalDate(dateStr));
	}
	
	/**
	 * @param date
	 */
	public LocalMonth(LocalDate date) {
		super();
		if (date == null) throw new IllegalArgumentException("date is null.");
		this.date = date.withDayOfMonth(1);
	}
	
	public String toString(String pattern) {
		return date.toString(pattern);
	}

	@Override
	public int compareTo(LocalMonth o) {
		return date.compareTo(o.date);
	}
	
	@Override
	public int hashCode() {
		return date.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof LocalMonth)) return false;
		if (this == obj) return true;
		
		LocalMonth other = (LocalMonth) obj;
		return this.date.equals(other.date);
	}
	
	@Override
	public String toString() {
		return date.toString("yyyyMM");
	}
}
