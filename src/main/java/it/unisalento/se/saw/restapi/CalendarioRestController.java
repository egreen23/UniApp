package it.unisalento.se.saw.restapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ICalendarioService;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CalendarioDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;
import it.unisalento.se.saw.dto.composite.CalendarioDTOComp;
import it.unisalento.se.saw.strategy.DateSortStrategy;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import net.minidev.json.JSONObject;


@RestController
@RequestMapping("/cal")
public class CalendarioRestController {

	
	
	@Autowired
	ICalendarioService calendarioService;
	
	
	
	public CalendarioRestController() {
		super();
	}
	
	public CalendarioRestController(ICalendarioService calendarioService) {
		super();
		this.calendarioService = calendarioService;
	}
	
	

	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CalendarioDTO>> getAll() throws Exception {
		try {
			
			List<Calendario> calList = calendarioService.getCalendari();
			Iterator<Calendario> calIterator = calList.iterator();
			List<CalendarioDTO> listCalDTO = new ArrayList<CalendarioDTO>();
			List<Date> datearray = new ArrayList<Date>();

			
			while(calIterator.hasNext()) {
				
				Calendario cal = calIterator.next();
				
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(cal.getDataInizio());
				datearray.add(date1);

			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			

			
			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  

				calIterator = calList.iterator();
				
				while(calIterator.hasNext()) {
					
					Calendario cal = calIterator.next();
					
					if (cal.getDataInizio().equals(strDate)) {
												
						CalendarioDTO calDTO = new CalendarioDTO();
						
						calDTO.setIdCalendario(cal.getIdCalendario());
						calDTO.setTipo(cal.getTipo());
						calDTO.setAnno(cal.getAnno());
						calDTO.setDataInizio(cal.getDataInizio());
						calDTO.setDataFine(cal.getDataFine());
						calDTO.setSemestre(cal.getSemestre());
						calDTO.setIdCds(cal.getCorsoDiStudio().getIdCorsoDiStudio());
						
						listCalDTO.add(calDTO);
						
						calList.remove(cal);
						
						break;
					}
				}
				
			}


			return new ResponseEntity<List<CalendarioDTO>>(listCalDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<CalendarioDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@GetMapping(value="/getCalendarioById/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> getCalendarioById(@PathVariable("idCalendario") int idCalendario) throws Exception {
		try {
			
			CalendarioComponent cal = calendarioService.getCalendarioById(idCalendario);
			JSONObject jsonSend = cal.toJson();
			return new ResponseEntity<JSONObject>(jsonSend, HttpStatus.OK);

			
		} catch (Exception e) {
			
			 return new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(value="/newCalendario", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Calendario> save(@RequestBody CalendarioDTO calendarioDTO) throws Exception {
		try { 
			
			Calendario newCalendario = new Calendario(); 
			CorsoDiStudio cds = new CorsoDiStudio();
			
			cds.setIdCorsoDiStudio(calendarioDTO.getIdCds());

			newCalendario.setTipo(calendarioDTO.getTipo());
			newCalendario.setAnno(calendarioDTO.getAnno());
			newCalendario.setDataInizio(calendarioDTO.getDataInizio());
			newCalendario.setDataFine(calendarioDTO.getDataFine());
			newCalendario.setSemestre(calendarioDTO.getSemestre());
			newCalendario.setCorsoDiStudio(cds);
			
			return new ResponseEntity<Calendario>(calendarioService.save(newCalendario), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Calendario>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	

	
	@RequestMapping(path="deleteCalendario/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCal(@PathVariable("id") int id) throws Exception {
    	try {
    		calendarioService.deleteCalendario(id);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.notFound().build();
    	 }
    }

	
	@RequestMapping(path="deleteCalendari", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCal(@RequestParam Map<String,String> allParams) throws Exception {
    	try {
    		
    		List<Calendario> list = new ArrayList<Calendario>();
    		for (int i = 0; i < allParams.size(); i++) {
    			String key = "id"+i;
    			int idCal = Integer.parseInt(allParams.get(key));
    			Calendario c = calendarioService.getOne(idCal);
    			list.add(c);
    			
    		}
    		calendarioService.deleteAll(list);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.notFound().build();
    	 }
    }
	
}
