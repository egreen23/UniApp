package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IEsameService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.EsameDTO;
import it.unisalento.se.saw.dto.LezioneDTO;
import it.unisalento.se.saw.dto.composite.EsameDTOComp;
import it.unisalento.se.saw.dto.composite.LezioneDTOComp;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/esame")
public class EsameRestController {
	
	@Autowired
	IEsameService esameService;

	public EsameRestController() {
		super();
	}

	public EsameRestController(IEsameService esameService) {
		super();
		this.esameService = esameService;
	}
	
	
	
	
	
	
	
	@PostMapping(value="/newEsame", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Esame> save(@RequestBody EsameDTO esameDTO) throws Exception {
		try { 
			
			Esame newEsame = new Esame();			
						
			Calendario cal = new Calendario();
			Aula aula = new Aula();	
			Insegnamento ins = new Insegnamento();

			
			aula.setIdAula(esameDTO.getIdAula());
			cal.setIdCalendario(esameDTO.getIdCalendario());
			ins.setIdInsegnamento(esameDTO.getIdInsegnamento());
			
//			newLezione.setIdLezione(lezioneDTO.getIdLezione());  //SE PASSO UN ID UGUALE FA UN UPDATE 
			newEsame.setOrarioInizio(esameDTO.getOrarioInizio());
			newEsame.setOrarioFine(esameDTO.getOrarioFine());
			newEsame.setData(esameDTO.getData());
			newEsame.setCalendario(cal);
			newEsame.setAula(aula);
			newEsame.setInsegnamento(ins);
						
			return new ResponseEntity<Esame>(esameService.save(newEsame), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Esame>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping(value="/getEsameById/{idEsame}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EsameDTOComp> getEsameById(@PathVariable("idEsame") int idEsame) throws Exception {
		try { 
						
			Esame esame = esameService.getEsameById(idEsame);
			
			EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
					esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
					esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo(), esame.getInsegnamento().getIdInsegnamento(),
					esame.getCalendario().getIdCalendario(), esame.getAula().getIdAula());
			String prof = esame.getInsegnamento().getDocente().getUser().getNome() + " " + esame.getInsegnamento().getDocente().getUser().getCognome();
			esameDTO.setDocente(prof);
			
			return new ResponseEntity<EsameDTOComp>(esameDTO, HttpStatus.OK);

			
		} catch (Exception e) {
			
			return new ResponseEntity<EsameDTOComp>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getEsameByCalendario/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getEsameByCalendario(@PathVariable("idCalendario") int calendario_IdCalendario) throws Exception {
	try {
			List<Esame> esamelist = esameService.getEsameByIdCalendario(calendario_IdCalendario);
			Iterator<Esame> esameIterator = esamelist.iterator();

			List<JSONObject> listEsameDTO = new ArrayList<JSONObject>();
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo(), esame.getInsegnamento().getIdInsegnamento(),
						esame.getCalendario().getIdCalendario(), esame.getAula().getIdAula());
				String prof = esame.getInsegnamento().getDocente().getUser().getCognome() + " " + 
						esame.getInsegnamento().getDocente().getUser().getNome();
				esameDTO.setDocente(prof);
				
				listEsameDTO.add(esameDTO.toJson_2());

			}

			if (listEsameDTO.isEmpty())
			{
				return new ResponseEntity<List<JSONObject>>(listEsameDTO,HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<JSONObject>>(listEsameDTO,HttpStatus.OK);
			}
	    } catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
	 }
	}
	
	
	
	
	@RequestMapping(path="deleteEsame/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEsame(@PathVariable("id") int id) throws Exception {
    	try {
    		esameService.deleteEsame(id);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.badRequest().build();
    	 }
    }
    
    @PostMapping(value="/addEsami", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Esame>> addEsami(@RequestBody List<EsameDTO> exam) throws Exception {
    	try {
    			List<Esame> newEsame = new ArrayList<Esame>();			
			
    			Calendario cal = new Calendario();
    			Aula aula = new Aula();	
    			Insegnamento ins = new Insegnamento();

			
    			aula.setIdAula(exam.get(0).getIdAula());
    			cal.setIdCalendario(exam.get(0).getIdCalendario());
    			ins.setIdInsegnamento(exam.get(0).getIdInsegnamento());
    			
    			for (EsameDTO e : exam)
    			{
    				Esame nuova = new Esame();
    				
    				nuova.setOrarioInizio(e.getOrarioInizio());
    				nuova.setOrarioFine(e.getOrarioFine());
    				nuova.setData(e.getData());
    				nuova.setAula(aula);
    				nuova.setCalendario(cal);
    				nuova.setInsegnamento(ins);
    				
    				newEsame.add(nuova);
    			}
    			    			
    			return new ResponseEntity<List<Esame>>(esameService.saveAll(newEsame), HttpStatus.CREATED);
    			
    			
    			
    	} catch (Exception e) {
    		
    		return new ResponseEntity<List<Esame>>(HttpStatus.BAD_REQUEST);
    	}
    }
	
   
    @GetMapping(value="/getEsameByIdCalendario/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getEsameByIdCalendario(@PathVariable("idCalendario") int calendario_IdCalendario) throws Exception {
	try {
			List<Esame> esamelist = esameService.getEsameByIdCalendario(calendario_IdCalendario);
			Iterator<Esame> esameIterator = esamelist.iterator();

			List<JSONObject> listEsameDTO = new ArrayList<JSONObject>();
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo(), esame.getInsegnamento().getIdInsegnamento(), 
						esame.getCalendario().getIdCalendario(), esame.getAula().getIdAula());
				String prof = esame.getInsegnamento().getDocente().getUser().getNome() + " " + esame.getInsegnamento().getDocente().getUser().getCognome();
				esameDTO.setDocente(prof);
				listEsameDTO.add(esameDTO.toJson());

			}

			if (listEsameDTO.isEmpty())
			{
				return new ResponseEntity<List<JSONObject>>(listEsameDTO,HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<JSONObject>>(listEsameDTO,HttpStatus.OK);
			}
	    } catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
	 }
	}

    @PostMapping(value="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Esame> update(@RequestBody EsameDTO esameDTO) throws Exception {
		try { 
			
			// Lezione lezioneUpdate = lezioneService.getLezioneById(lezioneDTO.getIdLezione());
			Esame esameUpdate = new Esame();
			
			Aula aula = new Aula();
			Calendario cal = new Calendario();
			Insegnamento ins = new Insegnamento();
			
			aula.setIdAula(esameDTO.getIdAula());
			cal.setIdCalendario(esameDTO.getIdCalendario());
			ins.setIdInsegnamento(esameDTO.getIdInsegnamento());
			
			esameUpdate.setIdEsame(esameDTO.getIdEsame());
			esameUpdate.setOrarioInizio(esameDTO.getOrarioInizio());
			esameUpdate.setOrarioFine(esameDTO.getOrarioFine());
			esameUpdate.setData(esameDTO.getData());
			esameUpdate.setCalendario(cal);
			esameUpdate.setAula(aula);
			esameUpdate.setInsegnamento(ins);

			
			return new ResponseEntity<Esame>(esameService.save(esameUpdate), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Esame>(HttpStatus.BAD_REQUEST);

		}
	}
    

	

}
