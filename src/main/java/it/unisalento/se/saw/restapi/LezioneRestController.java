package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.ILezioneService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.LezioneDTO;
import it.unisalento.se.saw.dto.composite.LezioneDTOComp;
import it.unisalento.se.saw.services.LezioneService;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/lez")
public class LezioneRestController {
	
	@Autowired
	ILezioneService lezioneService;
	
	
	
	public LezioneRestController() {
		super();
	}
	
	public LezioneRestController(ILezioneService lezioneService) {
		this.lezioneService = lezioneService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTOComp>> findAll() throws Exception {
		try {
			
			List<Lezione> lezList = lezioneService.findAll();
			Iterator<Lezione> lezIterator = lezList.iterator();
			
			List<LezioneDTOComp> listLezDTO = new ArrayList<LezioneDTOComp>();
			
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
						lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
						lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
						lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());
				
				listLezDTO.add(LezDTO);
				
			}
			return new ResponseEntity<List<LezioneDTOComp>>(listLezDTO,HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTOComp>>(HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping(value="/newLezione", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lezione> save(@RequestBody LezioneDTO lezioneDTO) throws Exception {
		try { 
			
			Lezione newLezione = new Lezione();			
						
			Calendario cal = new Calendario();
			Aula aula = new Aula();	
			Insegnamento ins = new Insegnamento();

			
			aula.setIdAula(lezioneDTO.getIdAula());
			cal.setIdCalendario(lezioneDTO.getIdCalendario());
			ins.setIdInsegnamento(lezioneDTO.getIdInsegnamento());
			
//			newLezione.setIdLezione(lezioneDTO.getIdLezione());  //SE PASSO UN ID UGUALE FA UN UPDATE 
			newLezione.setOrarioInizio(lezioneDTO.getOrarioInizio());
			newLezione.setOrarioFine(lezioneDTO.getOrarioFine());
			newLezione.setData(lezioneDTO.getData());
			newLezione.setCalendario(cal);
			newLezione.setAula(aula);
			newLezione.setInsegnamento(ins);
						
			return new ResponseEntity<Lezione>(lezioneService.save(newLezione), HttpStatus.CREATED);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Lezione>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getLezioneById/{idLezione}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LezioneDTOComp> getLezioneById(@PathVariable("idLezione") int idLezione) throws Exception {
		try { 
						
			Lezione lezione = lezioneService.getLezioneById(idLezione);
			
			LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
					lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
					lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
					lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());
			String prof = lezione.getInsegnamento().getDocente().getUser().getNome() + " " + lezione.getInsegnamento().getDocente().getUser().getCognome();
			LezDTO.setDocente(prof);

			return new ResponseEntity<LezioneDTOComp>(LezDTO, HttpStatus.OK);

			
		} catch (Exception e) {
			
			return new ResponseEntity<LezioneDTOComp>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value="/getLezioniByCalendario/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getLezioniByCalendario(@PathVariable("idCalendario") int calendario_IdCalendario) throws Exception {
	try {
			List<Lezione> lezlist = lezioneService.getLezioniByIdCalendario(calendario_IdCalendario);
			Iterator<Lezione> lezIterator = lezlist.iterator();

			List<JSONObject> listLezDTO = new ArrayList<JSONObject>();
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(),
					lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
					lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
					lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());
				String prof = lezione.getInsegnamento().getDocente().getUser().getCognome() + " " +
						lezione.getInsegnamento().getDocente().getUser().getNome();
				LezDTO.setDocente(prof);

				listLezDTO.add(LezDTO.toJson_2());


			}

			if (listLezDTO.isEmpty())
			{
				return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.OK);
			}
	    } catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
	 }
	}
	
	
    @RequestMapping(path="deleteLezione/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> deleteLez(@PathVariable("id") int id) throws Exception {
    	try {
    		lezioneService.deleteLezione(id);
    		return ResponseEntity.ok().build();
    	 } catch (Exception e) {
    	  return ResponseEntity.notFound().build();
    	 }
    }
    
    @PostMapping(value="/addLezioni", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Lezione>> addLezioni(@RequestBody List<LezioneDTO> lez) throws Exception {
    	try {
    			List<Lezione> newLezione = new ArrayList<Lezione>();			
			
    			Calendario cal = new Calendario();
    			Aula aula = new Aula();	
    			Insegnamento ins = new Insegnamento();
    			
			
    			aula.setIdAula(lez.get(0).getIdAula());
    			cal.setIdCalendario(lez.get(0).getIdCalendario());
    			ins.setIdInsegnamento(lez.get(0).getIdInsegnamento());
    			
    			for (LezioneDTO l : lez)
    			{
    				Lezione nuova = new Lezione();
    				
    				nuova.setOrarioInizio(l.getOrarioInizio());
    				nuova.setOrarioFine(l.getOrarioFine());
    				nuova.setData(l.getData());
    				nuova.setAula(aula);
    				nuova.setCalendario(cal);
    				nuova.setInsegnamento(ins);
    				
    				newLezione.add(nuova);
    			}
    			    			
    			return new ResponseEntity<List<Lezione>>(lezioneService.saveAll(newLezione), HttpStatus.CREATED);
    			
    			
    			
    	} catch (Exception e) {
    		
    		return new ResponseEntity<List<Lezione>>(HttpStatus.BAD_REQUEST);
    	}
    }
    
	@GetMapping(value="/getLezionibyIdAula/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LezioneDTO>> getLezionibyIdAula(@PathVariable("id") int id) throws Exception {
		try {
				List<Lezione> list = lezioneService.getLezionibyIdAula(id);
			
				Iterator<Lezione> lezIterator = list.iterator();
			
				List<LezioneDTO> listLezDTO = new ArrayList<LezioneDTO>();
			
				while (lezIterator.hasNext()) {
				
					Lezione lezione = lezIterator.next();
					LezioneDTO lezioneDTO = new LezioneDTO();
					lezioneDTO.setIdLezione(lezione.getIdLezione());
					lezioneDTO.setOrarioInizio(lezione.getOrarioInizio());
					lezioneDTO.setOrarioFine(lezione.getOrarioFine());
					lezioneDTO.setData(lezione.getData());
					lezioneDTO.setIdCalendario(lezione.getCalendario().getIdCalendario());
					lezioneDTO.setIdAula(lezione.getAula().getIdAula());
					lezioneDTO.setIdInsegnamento(lezione.getInsegnamento().getIdInsegnamento());
					listLezDTO.add(lezioneDTO);
				
				}
				if (listLezDTO.isEmpty())
				{
					return new ResponseEntity<List<LezioneDTO>>(listLezDTO,HttpStatus.NOT_FOUND);				
				}
			
				return new ResponseEntity<List<LezioneDTO>>(listLezDTO, HttpStatus.OK);
		} catch (Exception e) {
			
			return new ResponseEntity<List<LezioneDTO>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getLezioniByIdCalendario/{idCalendario}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<JSONObject>> getLezioniByIdCalendario(@PathVariable("idCalendario") int calendario_IdCalendario) throws Exception {
	try {
			List<Lezione> lezlist = lezioneService.getLezioniByIdCalendario(calendario_IdCalendario);
			Iterator<Lezione> lezIterator = lezlist.iterator();

			List<JSONObject> listLezDTO = new ArrayList<JSONObject>();
			while (lezIterator.hasNext()) {
				
				Lezione lezione = lezIterator.next();
				LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(),
					lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
					lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
					lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());
				
				String prof = lezione.getInsegnamento().getDocente().getUser().getNome() + " " + lezione.getInsegnamento().getDocente().getUser().getCognome();
				LezDTO.setDocente(prof);
				listLezDTO.add(LezDTO.toJson());


			}

			if (listLezDTO.isEmpty())
			{
				return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.NOT_FOUND);
			}
			else
			{
				return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.OK);
			}
	    } catch (Exception e) {
			return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
	 }
	}
	
	@PostMapping(value="/update", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Lezione> update(@RequestBody LezioneDTO lezioneDTO) throws Exception {
		try { 
			
			Lezione lezioneUpdate = new Lezione();
			
			Aula aula = new Aula();
			Calendario cal = new Calendario();
			Insegnamento ins = new Insegnamento();
			
			aula.setIdAula(lezioneDTO.getIdAula());
			cal.setIdCalendario(lezioneDTO.getIdCalendario());
			ins.setIdInsegnamento(lezioneDTO.getIdInsegnamento());
			
			lezioneUpdate.setIdLezione(lezioneDTO.getIdLezione());
			lezioneUpdate.setOrarioInizio(lezioneDTO.getOrarioInizio());
			lezioneUpdate.setOrarioFine(lezioneDTO.getOrarioFine());
			lezioneUpdate.setData(lezioneDTO.getData());
			lezioneUpdate.setCalendario(cal);
			lezioneUpdate.setAula(aula);
			lezioneUpdate.setInsegnamento(ins);

			
			return new ResponseEntity<Lezione>(lezioneService.save(lezioneUpdate), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Lezione>(HttpStatus.BAD_REQUEST);

		}
	}
	
	//nuovo metodo CH
		@GetMapping(value="/getLezioniByIdDocente/{idDocente}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<JSONObject>> getLezioniByIdDocente(@PathVariable("idDocente") int idDocente) throws Exception {
			try {
				
				List<Lezione> lezList = lezioneService.getLezioniByIdDocente(idDocente);
				Iterator<Lezione> lezIterator = lezList.iterator();
				
				List<JSONObject> listLezDTO = new ArrayList<JSONObject>();
				
				while (lezIterator.hasNext()) {
					
					Lezione lezione = lezIterator.next();
					LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(),
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
							lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());

//					listLezDTO.add(LezDTO);
					listLezDTO.add(LezDTO.toJson_2());

				}

				if (listLezDTO.isEmpty())
				{
					return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.NOT_FOUND);
				}
				else
				{
					return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.OK);
				}
		    } catch (Exception e) {
				return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		 }
		}
		
		//nuovo metodo CH
		@GetMapping(value="/getCalLezioniByCorso/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<JSONObject>> getCalLezioniByCorso(@PathVariable("nome") String nome) throws Exception {
			try {
				
				List<Lezione> lezList = lezioneService.getCalLezioniByCorso(nome);
				Iterator<Lezione> lezIterator = lezList.iterator();
				
				List<JSONObject> listLezDTO = new ArrayList<JSONObject>();
				
				while (lezIterator.hasNext()) {
					
					Lezione lezione = lezIterator.next();
					LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(),
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
							lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());

//					listLezDTO.add(LezDTO);
					listLezDTO.add(LezDTO.toJson_2());

				}

				if (listLezDTO.isEmpty())
				{
					return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.NOT_FOUND);
				}
				else
				{
					return new ResponseEntity<List<JSONObject>>(listLezDTO,HttpStatus.OK);
				}
		    } catch (Exception e) {
				return new ResponseEntity<List<JSONObject>>(HttpStatus.BAD_REQUEST);
		 }
		}
		
		//nuovo metodo CH
		@GetMapping(value="/getLezioniByIdInsegnamento/{idInsegnamento}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<List<LezioneDTOComp>> getLezioniByIdInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws Exception {
			try {
				
				List<Lezione> lezlist = lezioneService.getLezioniByIdInsegnamento(idInsegnamento);
				Iterator<Lezione> lezIterator = lezlist.iterator();
				
				List<LezioneDTOComp> listLezDTO = new ArrayList<LezioneDTOComp>();
				
				while (lezIterator.hasNext()) {
					
					Lezione lezione = lezIterator.next();
					LezioneDTOComp LezDTO = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(),
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(),  lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo(), lezione.getInsegnamento().getIdInsegnamento(),
							lezione.getCalendario().getIdCalendario(), lezione.getAula().getIdAula());

					listLezDTO.add(LezDTO);
					
				}
				if (listLezDTO.isEmpty())
				{
					return new ResponseEntity<List<LezioneDTOComp>>(listLezDTO,HttpStatus.NO_CONTENT);				
				}
				else 
				{
					return new ResponseEntity<List<LezioneDTOComp>>(listLezDTO,HttpStatus.OK);				
				}			
			} catch (Exception e) {
				
				return new ResponseEntity<List<LezioneDTOComp>>(HttpStatus.NO_CONTENT);

			}
		}

}
