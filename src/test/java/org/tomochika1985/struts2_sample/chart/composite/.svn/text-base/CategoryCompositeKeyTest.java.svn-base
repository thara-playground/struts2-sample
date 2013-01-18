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
package org.tomochika1985.struts2_sample.chart.composite;

import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.Category;

import static org.junit.Assert.*;

/**
 * @author t_hara
 *
 */
public class CategoryCompositeKeyTest {

	@Test
	public void test() {
		/*
		 * A 
		 * | -- B
		 *      |-- C 
		 */
		CategoryCompositeKey key1 = new CategoryCompositeKey(
				new Category("A", ""), new Category("B", ""), new Category("B", ""));

		/*
		 * A 
		 * | -- B
		 */
		CategoryCompositeKey key2 = new CategoryCompositeKey(
				new Category("A", ""), new Category("B", ""));
		
		assertTrue(key1.isChildOf(key2));
		assertTrue(key2.containsParentsOf(key1));
	}

}
