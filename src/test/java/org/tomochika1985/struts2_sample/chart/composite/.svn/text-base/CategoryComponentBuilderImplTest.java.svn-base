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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;
import org.tomochika1985.struts2_sample.chart.Category;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryLeafsVisitor;

import static org.hamcrest.Matchers.*;

import static org.junit.Assert.*;

/**
 * @author t_hara
 *
 */
public class CategoryComponentBuilderImplTest {

	static class Src extends CategoriesSourceImpl{}
	
	@Test
	public void createHierarchy() {
	
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category("A", ""));
		categories.add(new Category("B", ""));
		categories.add(new Category("C", ""));
		
		CategoriesBuilderImpl target = new CategoriesBuilderImpl();
		List<List<Category>> hierarchy = target.createHierarchy(categories);
		
		assertThat(hierarchy.size(), is(3));
		
		assertThat(hierarchy.get(0).size(), is(1));
		assertThat(hierarchy.get(1).size(), is(2));
		assertThat(hierarchy.get(2).size(), is(3));
		
		assertThat(hierarchy.get(0).get(0).getCode(), is("A"));
		
		assertThat(hierarchy.get(1).get(0).getCode(), is("A"));
		assertThat(hierarchy.get(1).get(1).getCode(), is("B"));
		
		assertThat(hierarchy.get(2).get(0).getCode(), is("A"));
		assertThat(hierarchy.get(2).get(1).getCode(), is("B"));
		assertThat(hierarchy.get(2).get(2).getCode(), is("C"));
	}
	
	@Test
	public void createCategoryMap() {
		
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
		List<CategoriesSource> sources = new ArrayList<CategoriesSource>();
		
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A0", "")).add(new Category("A00", ""))
				);
		sources.add(new Src().
				add(new Category("A", "")).add(new Category("A0", "")).add(new Category("A01", ""))
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
		
		CategoriesBuilderImpl target = new CategoriesBuilderImpl();
		Map<CategoryCompositeKey, List<Category>> categoryMap = target.createCategoryMap(sources);
		
		List<Entry<CategoryCompositeKey, List<Category>>> entries = 
				new ArrayList<Entry<CategoryCompositeKey, List<Category>>>(categoryMap.entrySet());
		
		assertThat(entries.size(), is(12));
		
		StringBuilder expects = new StringBuilder();
		expects.append("A");
		expects.append("AA0");
		expects.append("AA0A00");
		expects.append("AA0A01");
		expects.append("AA1");
		expects.append("AA1A00");
		expects.append("B");
		expects.append("BA0");
		expects.append("BA0A00");
		expects.append("BA1");
		expects.append("BA1A01");
		expects.append("BA1A02");
		
		StringBuilder actual = new StringBuilder();
		for (Entry<CategoryCompositeKey, List<Category>> entry : entries) {
			actual.append(entry.getKey());
		}
		
		assertThat(actual.toString(), is(expects.toString()));
	}
	
	@Test
	public void buildComponent() {
		
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
		
		CategoryLeafsVisitor leafsVisitor = new CategoryLeafsVisitor();
		
		CategoriesBuilderImpl target = new CategoriesBuilderImpl();
		List<CategoryComponent> components = target.buildComponents(sources);
		assertThat(components.size(), is(2));
		
		CategoryComponent compositeA = components.get(0);
		assertComposite(compositeA, "A", 2);
		
		assertThat(compositeA.accept(leafsVisitor.clear()).getLeafsCount(), is(4));
		
		CategoryComponent compositeAA0 = compositeA.getChiildren().get(0);
		assertComposite(compositeAA0, "A0", 3);
		assertThat(compositeAA0.accept(leafsVisitor.clear()).getLeafsCount(), is(3));
		
		CategoryComponent compositeAA0A00 = compositeAA0.getChiildren().get(0);
		assertComposite(compositeAA0A00, "A00", 0);
		assertTrue(compositeAA0A00.isLeaf());
		assertThat(compositeAA0A00.accept(leafsVisitor.clear()).getLeafsCount(), is(1));
		
		CategoryComponent compositeAA0A01 = compositeAA0.getChiildren().get(1);
		assertComposite(compositeAA0A01, "A01", 0);
		assertTrue(compositeAA0A01.isLeaf());
		assertThat(compositeAA0A01.accept(leafsVisitor.clear()).getLeafsCount(), is(1));
		
		CategoryComponent compositeAA1 = compositeA.getChiildren().get(1);
		assertComposite(compositeAA1, "A1", 1);
		
		CategoryComponent compositeAA1A00 = compositeAA1.getChiildren().get(0);
		assertComposite(compositeAA1A00, "A00", 0);
		assertTrue(compositeAA1A00.isLeaf());
	}
	
	void assertComposite(CategoryComponent c, String code, int childrenSize) {
		assertThat(c.getCategory().getCode(), is(code));
		assertThat(c.getChiildren().size(), is(childrenSize));
	}
}
