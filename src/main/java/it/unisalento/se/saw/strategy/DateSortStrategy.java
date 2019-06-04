package it.unisalento.se.saw.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class DateSortStrategy implements SortStrategy<Date> {
	
	
	@Override
    public List<Date> sortList(List<Date> alist) {
		
		
		int i,j;
	    

	    for (i = 1; i < alist.size(); i++) {
	    	Date key = new Date();
	        key = alist.get(i);
	        j = i;
	        while((j > 0) && (alist.get(j - 1).before(key))) {
	            alist.set(j,alist.get(j - 1));
	            j--;
	        }
	        alist.set(j,key);
	    }

			
      return alist;
   }
	

}
