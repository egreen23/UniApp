package it.unisalento.se.saw.strategy;

import java.util.ArrayList;
import java.util.List;

public class SortContext<T> {
	
	private SortStrategy<T> strategy;
	
	private List<T> list;
	
	public SortContext(SortStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void sort() {
		this.list = strategy.sortList(list);
	}
	
	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


}
