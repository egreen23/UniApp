package it.unisalento.se.saw.strategy;

import java.util.ArrayList;
import java.util.List;

public interface SortStrategy<T> {
	
	List<T> sortList(List<T> alist);

}
