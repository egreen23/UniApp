package it.unisalento.se.saw.strategy;

import java.util.Date;
import java.util.List;

public class StringSortStrategy implements SortStrategy<String> {
	
	@Override
    public List<String> sortList(List<String> alist) {
		
		
		int i,j;
	    

	    for (i = 1; i < alist.size(); i++) {
	    	String key = new String();
	        key = alist.get(i);
	        j = i;
	        while((j > 0) && (alist.get(j - 1).compareTo(key) > 0)) {
	            alist.set(j,alist.get(j - 1));
	            j--;
	        }
	        alist.set(j,key);
	    }

			
      return alist;
   }

}
