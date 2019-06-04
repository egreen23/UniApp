package it.unisalento.se.saw.dto.composite;

import net.minidev.json.JSONObject;

public abstract class CalendarioComponent {
	
	
	
	public void add(CalendarioComponent Component){
        throw new UnsupportedOperationException("Cannot add item to catalog.");
    }
 
    public void remove(CalendarioComponent Component){
        throw new UnsupportedOperationException("Cannot remove item from catalog.");
    }
 
    public String getData(){
    throw new UnsupportedOperationException("Cannot return data.");
    }
    
    public JSONObject toJson() {
        throw new UnsupportedOperationException("Cannot showJson.");
    }
    
//    
//    // non serve 
//    public void print(){
//        throw new UnsupportedOperationException("Cannot print.");
//    }



}
