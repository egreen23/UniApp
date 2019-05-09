package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ICalendarioService;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.CorsoDiStudio;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.dto.CalendarioDTO;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.UserDTO;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;
import it.unisalento.se.saw.dto.composite.CalendarioDTOComp;
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
	
	
	
	@RequestMapping(value="/findAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> findAll() throws Exception {
		try {
				
			List<CalendarioComponent> calList = calendarioService.findAll();
			
			List<JSONObject> jsonList = new ArrayList<JSONObject>();	
			Iterator<CalendarioComponent> calCompIterator = calList.iterator();
			
			while(calCompIterator.hasNext()) {
				
				CalendarioComponent calendarioComponent = calCompIterator.next();
				
				jsonList.add(calendarioComponent.toJson());
			}

			return new ResponseEntity<List<JSONObject>>(jsonList, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		}
		
	}	
	
	
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getAll() throws Exception {
		try {
				
			List<CalendarioComponent> calList = calendarioService.getAll();
			
			List<JSONObject> jsonList = new ArrayList<JSONObject>();	
			Iterator<CalendarioComponent> calCompIterator = calList.iterator();
			
			while(calCompIterator.hasNext()) {
				
				CalendarioComponent calendarioComponent = calCompIterator.next();
				
				jsonList.add(calendarioComponent.toJson());
			}

			return new ResponseEntity<List<JSONObject>>(jsonList, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	
	@GetMapping(value="/getCalendarioById/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JSONObject> getCalendarioById(@PathVariable("idCalendario") int idCalendario) throws Exception {
		try {
				
			CalendarioComponent cal = calendarioService.getCalendarioById(idCalendario);
			
			JSONObject jsonSend = new JSONObject();	
			
			jsonSend.put("calendario", cal.toJson());

			return new ResponseEntity<JSONObject>(jsonSend, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@PostMapping(value="/newCalendario", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Calendario> save(@RequestBody CalendarioDTO calendarioDTO) throws Exception {
		try { 
			
			Calendario newCalendario = new Calendario(); 

			newCalendario.setTipo(calendarioDTO.getTipo());
			newCalendario.setAnno(calendarioDTO.getAnno());
			newCalendario.setDataInizio(calendarioDTO.getDataInizio());
			newCalendario.setDataFine(calendarioDTO.getDataFine());
			newCalendario.setSemestre(calendarioDTO.getSemestre());
			
			return new ResponseEntity<Calendario>(calendarioService.save(newCalendario), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Calendario>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@PostMapping(value="/updateCalendarioById/{idCalendario}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Calendario> updateCalendarioById(@PathVariable("idCalendario") int idCalendario, @RequestBody CalendarioDTO calendarioDTO) throws Exception {
		try { 
			
			Calendario updateCalendario = calendarioService.updateCalendarioById(idCalendario); 

//			updateCalendario.setIdCalendario(calendarioDTO.getIdCalendario());
			updateCalendario.setTipo(calendarioDTO.getTipo());
			updateCalendario.setAnno(calendarioDTO.getAnno());
			updateCalendario.setDataInizio(calendarioDTO.getDataInizio());
			updateCalendario.setDataFine(calendarioDTO.getDataFine());
			updateCalendario.setSemestre(calendarioDTO.getSemestre());
			
			return new ResponseEntity<Calendario>(calendarioService.save(updateCalendario), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Calendario>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getCalendaribyCds/{idCds}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getCalendaribyCds(@PathVariable("idCds") int idCds) throws Exception {
	
			
			try {
				
				List<CalendarioComponent> calList = calendarioService.getCalendaribyCds(idCds);
				
				List<JSONObject> jsonList = new ArrayList<JSONObject>();	
				Iterator<CalendarioComponent> calCompIterator = calList.iterator();
				
				while(calCompIterator.hasNext()) {
					
					CalendarioComponent calendarioComponent = calCompIterator.next();
					
					jsonList.add(calendarioComponent.toJson());
				}

				return new ResponseEntity<List<JSONObject>>(jsonList, HttpStatus.OK);
				
			} catch (Exception e) {
				
				return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
			}
			

		
	}

	
}
