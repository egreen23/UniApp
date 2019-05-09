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
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EsameDTOComp>> findAll() throws Exception {
		try {
			
			List<Esame> esameList = esameService.findAll();
			Iterator<Esame> esameIterator = esameList.iterator();
			
			List<EsameDTOComp> listEsameDTO = new ArrayList<EsameDTOComp>();
			
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listEsameDTO.add(esameDTO);
				
			}
			return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<EsameDTOComp>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getEsamiByDocente/{cognome}/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EsameDTOComp>> getEsamiByDocente(@PathVariable("cognome") String cognome, @PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Esame> esameList = esameService.getEsamiByDocente(cognome, nome);
			Iterator<Esame> esameIterator = esameList.iterator();
			
			List<EsameDTOComp> listEsameDTO = new ArrayList<EsameDTOComp>();
			
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listEsameDTO.add(esameDTO);
				
			}
			if (listEsameDTO.isEmpty())
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.OK);				
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<EsameDTOComp>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping(value="/getEsamiByInsegnamento/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EsameDTOComp>> getEsamiByInsegnamento(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Esame> esameList = esameService.getEsamiByInsegnamento(nome);
			Iterator<Esame> esameIterator = esameList.iterator();
			
			List<EsameDTOComp> listEsameDTO = new ArrayList<EsameDTOComp>();
			
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listEsameDTO.add(esameDTO);
				
			}
			if (listEsameDTO.isEmpty())
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.OK);				
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<EsameDTOComp>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@GetMapping(value="/getEsamiByCorso/{nome}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EsameDTOComp>> getEsamiByCorso(@PathVariable("nome") String nome) throws Exception {
		try {
			
			List<Esame> esameList = esameService.getEsamiByCorso(nome);
			Iterator<Esame> esameIterator = esameList.iterator();
			
			List<EsameDTOComp> listEsameDTO = new ArrayList<EsameDTOComp>();
			
			while (esameIterator.hasNext()) {
				
				Esame esame = esameIterator.next();
				EsameDTOComp esameDTO = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
						esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listEsameDTO.add(esameDTO);
				
			}
			if (listEsameDTO.isEmpty())
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.NOT_FOUND);				
			}
			else 
			{
				return new ResponseEntity<List<EsameDTOComp>>(listEsameDTO,HttpStatus.OK);				
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<EsameDTOComp>>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	@PostMapping(value="/updateById/{idEsame}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Esame> updateById(@PathVariable("idEsame") int idEsame, @RequestBody EsameDTO esameDTO) throws Exception {
		try { 
			
			Esame esameUpdate = esameService.updateById(idEsame);
									
//			esameUpdate.setIdEsame(esameDTO.getIdEsame());
			esameUpdate.setData(esameDTO.getData());
			esameUpdate.setOrarioInizio(esameDTO.getOrarioInizio());
			esameUpdate.setOrarioFine(esameDTO.getOrarioFine());
			esameUpdate.getCalendario().setIdCalendario(esameDTO.getIdCalendario());
			esameUpdate.getAula().setIdAula(esameDTO.getIdAula());
			esameUpdate.getInsegnamento().setIdInsegnamento(esameDTO.getIdInsegnamento());

			
			return new ResponseEntity<Esame>(esameService.save(esameUpdate), HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<Esame>(HttpStatus.BAD_REQUEST);

		}
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
	public ResponseEntity<EsameDTO> getEsameById(@PathVariable("idEsame") int idEsame) throws Exception {
		try { 
						
			Esame esame = esameService.getEsameById(idEsame);
			
			EsameDTO esameDTO = new EsameDTO();

			esameDTO.setIdEsame(esame.getIdEsame());
			esameDTO.setData(esame.getData());
			esameDTO.setOrarioInizio(esame.getOrarioInizio());
			esameDTO.setOrarioFine(esame.getOrarioFine());
			esameDTO.setIdCalendario(esame.getCalendario().getIdCalendario());
			esameDTO.setIdAula(esame.getAula().getIdAula());
			esameDTO.setIdInsegnamento(esame.getInsegnamento().getIdInsegnamento());
			
			return new ResponseEntity<EsameDTO>(esameDTO, HttpStatus.OK);

			
		} catch (Exception e) {
			
			return new ResponseEntity<EsameDTO>(HttpStatus.BAD_REQUEST);
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
						esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
				
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
	
	
	
	
//	@DeleteMapping(value="/deleteEsameById/{idEsame}", consumes=MediaType.APPLICATION_JSON_VALUE)
//	public void deleteEsameById(@PathVariable("idEsame") int idEsame) {
//		esameService.deleteEsameById(idEsame);
//		}
	
	
	

	
	

}
