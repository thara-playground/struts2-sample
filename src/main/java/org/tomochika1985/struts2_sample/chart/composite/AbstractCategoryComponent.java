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

import org.tomochika1985.struts2_sample.chart.Category;
import org.tomochika1985.struts2_sample.chart.composite.visitor.CategoryComponentVisitor;

/**
 * @author t_hara
 *
 */
public abstract class AbstractCategoryComponent implements CategoryComponent {

	final Category category;
	
	final CategoryCompositeKey key;
	
	final List<CategoryComponent> children = new ArrayList<CategoryComponent>();
	
	/**
	 * @param category
	 * @param children
	 */
	public AbstractCategoryComponent(Category category, CategoryCompositeKey key) {
		super();
		if (category == null) throw new IllegalArgumentException("category is null.");
		if (key == null) throw new IllegalArgumentException("key is null.");
		this.category = category;
		this.key = key;
	}

	@Override
	public Category getCategory() {
		return category;
	}
	
	@Override
	public CategoryCompositeKey getKey() {
		return key;
	}

	@Override
	public boolean isLeaf() {
		return false;
	}
	
	@Override
	public List<CategoryComponent> getChiildren() {
		return new ArrayList<CategoryComponent>(children);
	}
	
	@Override
	public <T extends CategoryComponentVisitor> T accept(T visitor) {
		boolean chain = visitor.visit(this);
		if (chain) {
			for (CategoryComponent c : this.children) {
				c.accept(visitor);
			}
		}
		return visitor;
	}
	
	void addChild(CategoryComponent c) {
		if (c == null) throw new IllegalArgumentException("component is null.");
		if (isLeaf()) throw new IllegalStateException("The leaf can not have any children.");
		this.children.add(c);
	}
	
	protected void setChildren(List<CategoryComponent> children) {
		this.children.addAll(children);
	}
	
	@Override
	public String toString() {
		return getCategory().getCode();
	}
}
