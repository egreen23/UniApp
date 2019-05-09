package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IRecensioneLService;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.InsegnamentoDTO;
import it.unisalento.se.saw.dto.RecensioneLDTO;

@RestController
@RequestMapping("/recL")
public class RecensioneLRestController {
	
	@Autowired
	IRecensioneLService recensioneLService;


	
	public RecensioneLRestController() {
		super();
	}
	
	public RecensioneLRestController(IRecensioneLService recensioneLService) {
		this.recensioneLService = recensioneLService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneLDTO>> findAll() throws Exception {
		try {
			
			List<Recensionel> recLezList = recensioneLService.findAll();
			Iterator<Recensionel> recLezIterator = recLezList.iterator();
			
			List<RecensioneLDTO> listRecLezDTO = new ArrayList<RecensioneLDTO>();
					
			
			while(recLezIterator.hasNext())
			{
				Recensionel recLez = recLezIterator.next();
				RecensioneLDTO recLezDTO = new RecensioneLDTO();			
				
				recLezDTO.setIdRecensioneL(recLez.getIdrecensionel());
				recLezDTO.setVoto(recLez.getVoto());
				recLezDTO.setTesto(recLez.getTesto());
				
				recLezDTO.setIdLezione(recLez.getLezione().getIdLezione());
				recLezDTO.setDataLezione(recLez.getLezione().getData());
				
				recLezDTO.setIdInsegnamento(recLez.getLezione().getInsegnamento().getIdInsegnamento());
				recLezDTO.setNomeInsegnamento(recLez.getLezione().getInsegnamento().getNome());
				
				recLezDTO.setIdDocente(recLez.getLezione().getInsegnamento().getDocente().getIdDocente());
				recLezDTO.setCognomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getCognome());
				recLezDTO.setNomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getNome());
				
				recLezDTO.setIdStudente(recLez.getStudente().getIdStudente());
				recLezDTO.setMatricolaStudente(recLez.getStudente().getUser().getIdMatricola());;
				recLezDTO.setCognomeStudente(recLez.getStudente().getUser().getCognome());;
				recLezDTO.setNomeStudente(recLez.getStudente().getUser().getNome());;
				
				recLezDTO.setIdcorsoDiStudio(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recLezDTO.setNomeCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getNome());
				recLezDTO.setTipoCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecLezDTO.add(recLezDTO);
				
			}
			return new ResponseEntity<List<RecensioneLDTO>>(listRecLezDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<RecensioneLDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getRecLByInsegnamento/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneLDTO>> getRecLByInsegnamento(@PathVariable("string") String string) throws Exception {
		try {
			
			List<Recensionel> recLezList = recensioneLService.getRecLByInsegnamento(string);
			Iterator<Recensionel> recLezIterator = recLezList.iterator();
			
			List<RecensioneLDTO> listRecLezDTO = new ArrayList<RecensioneLDTO>();
					
			
			while(recLezIterator.hasNext())
			{
				Recensionel recLez = recLezIterator.next();
				RecensioneLDTO recLezDTO = new RecensioneLDTO();			
				
				recLezDTO.setIdRecensioneL(recLez.getIdrecensionel());
				recLezDTO.setVoto(recLez.getVoto());
				recLezDTO.setTesto(recLez.getTesto());
				
				recLezDTO.setIdLezione(recLez.getLezione().getIdLezione());
				recLezDTO.setDataLezione(recLez.getLezione().getData());
				
				recLezDTO.setIdInsegnamento(recLez.getLezione().getInsegnamento().getIdInsegnamento());
				recLezDTO.setNomeInsegnamento(recLez.getLezione().getInsegnamento().getNome());
				
				recLezDTO.setIdDocente(recLez.getLezione().getInsegnamento().getDocente().getIdDocente());
				recLezDTO.setCognomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getCognome());
				recLezDTO.setNomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getNome());
				
				recLezDTO.setIdStudente(recLez.getStudente().getIdStudente());
				recLezDTO.setMatricolaStudente(recLez.getStudente().getUser().getIdMatricola());;
				recLezDTO.setCognomeStudente(recLez.getStudente().getUser().getCognome());;
				recLezDTO.setNomeStudente(recLez.getStudente().getUser().getNome());;
				
				recLezDTO.setIdcorsoDiStudio(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recLezDTO.setNomeCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getNome());
				recLezDTO.setTipoCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecLezDTO.add(recLezDTO);
				
			}
			if (listRecLezDTO.isEmpty())
			{
				return new ResponseEntity<List<RecensioneLDTO>>(listRecLezDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<RecensioneLDTO>>(listRecLezDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<RecensioneLDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getRecLByVoto/{voto}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneLDTO>> getRecLByInsegnamento(@PathVariable("voto") int voto) throws Exception {
		try {
			
			List<Recensionel> recLezList = recensioneLService.getRecLByVoto(voto);
			Iterator<Recensionel> recLezIterator = recLezList.iterator();
			
			List<RecensioneLDTO> listRecLezDTO = new ArrayList<RecensioneLDTO>();
					
			
			while(recLezIterator.hasNext())
			{
				Recensionel recLez = recLezIterator.next();
				RecensioneLDTO recLezDTO = new RecensioneLDTO();			
				
				recLezDTO.setIdRecensioneL(recLez.getIdrecensionel());
				recLezDTO.setVoto(recLez.getVoto());
				recLezDTO.setTesto(recLez.getTesto());
				
				recLezDTO.setIdLezione(recLez.getLezione().getIdLezione());
				recLezDTO.setDataLezione(recLez.getLezione().getData());
				
				recLezDTO.setIdInsegnamento(recLez.getLezione().getInsegnamento().getIdInsegnamento());
				recLezDTO.setNomeInsegnamento(recLez.getLezione().getInsegnamento().getNome());
				
				recLezDTO.setIdDocente(recLez.getLezione().getInsegnamento().getDocente().getIdDocente());
				recLezDTO.setCognomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getCognome());
				recLezDTO.setNomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getNome());
				
				recLezDTO.setIdStudente(recLez.getStudente().getIdStudente());
				recLezDTO.setMatricolaStudente(recLez.getStudente().getUser().getIdMatricola());;
				recLezDTO.setCognomeStudente(recLez.getStudente().getUser().getCognome());;
				recLezDTO.setNomeStudente(recLez.getStudente().getUser().getNome());;
				
				recLezDTO.setIdcorsoDiStudio(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recLezDTO.setNomeCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getNome());
				recLezDTO.setTipoCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecLezDTO.add(recLezDTO);
				
			}
			if (listRecLezDTO.isEmpty())
			{
				return new ResponseEntity<List<RecensioneLDTO>>(listRecLezDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<RecensioneLDTO>>(listRecLezDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<RecensioneLDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getById/{idRecensioneL}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecensioneLDTO> getById(@PathVariable("idRecensioneL") int idRecensioneL) throws Exception {
		try {
			
			Recensionel recLez = recensioneLService.getById(idRecensioneL);
			RecensioneLDTO recLezDTO = new RecensioneLDTO();			
			
			recLezDTO.setIdRecensioneL(recLez.getIdrecensionel());
			recLezDTO.setVoto(recLez.getVoto());
			recLezDTO.setTesto(recLez.getTesto());
			
			recLezDTO.setIdLezione(recLez.getLezione().getIdLezione());
			recLezDTO.setDataLezione(recLez.getLezione().getData());
			
			recLezDTO.setIdInsegnamento(recLez.getLezione().getInsegnamento().getIdInsegnamento());
			recLezDTO.setNomeInsegnamento(recLez.getLezione().getInsegnamento().getNome());
			
			recLezDTO.setIdDocente(recLez.getLezione().getInsegnamento().getDocente().getIdDocente());
			recLezDTO.setCognomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getCognome());
			recLezDTO.setNomeDocente(recLez.getLezione().getInsegnamento().getDocente().getUser().getNome());
			
			recLezDTO.setIdStudente(recLez.getStudente().getIdStudente());
			recLezDTO.setMatricolaStudente(recLez.getStudente().getUser().getIdMatricola());;
			recLezDTO.setCognomeStudente(recLez.getStudente().getUser().getCognome());;
			recLezDTO.setNomeStudente(recLez.getStudente().getUser().getNome());;
			
			recLezDTO.setIdcorsoDiStudio(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
			recLezDTO.setNomeCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getNome());
			recLezDTO.setTipoCorso(recLez.getLezione().getInsegnamento().getCorsoDiStudio().getTipo());

			return new ResponseEntity<RecensioneLDTO>(recLezDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<RecensioneLDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newRecLez", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recensionel> save(@RequestBody RecensioneLDTO recensioneLDTO) throws Exception {
		try {
			
			Recensionel newRecLez = new Recensionel();
			Studente stud = new Studente();
			Lezione lez = new Lezione();
			
			stud.setIdStudente(recensioneLDTO.getIdStudente());
			lez.setIdLezione(recensioneLDTO.getIdLezione());
			
			newRecLez.setVoto(recensioneLDTO.getVoto());
			newRecLez.setTesto(recensioneLDTO.getTesto());
			newRecLez.setStudente(stud);
			newRecLez.setLezione(lez);
			
			return new ResponseEntity<Recensionel>(recensioneLService.save(newRecLez), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<Recensionel>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/updateRecLezById/{idRecensioneL}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recensionel> updateRecLezById(@PathVariable("idRecensioneL") int idRecensioneL ,@RequestBody RecensioneLDTO recensioneLDTO) throws Exception {
		try {
			
			Recensionel updateRecLez = recensioneLService.updateRecLezById(idRecensioneL);
					
//			updateRecLez.setIdRecensioneL(recensioneLDTO.getIdRecensioneL());
			updateRecLez.setVoto(recensioneLDTO.getVoto());
			updateRecLez.setTesto(recensioneLDTO.getTesto());
			updateRecLez.getStudente().setIdStudente(recensioneLDTO.getIdStudente());
			updateRecLez.getLezione().setIdLezione(recensioneLDTO.getIdLezione());
			
			return new ResponseEntity<Recensionel>(recensioneLService.save(updateRecLez), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<Recensionel>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	
	
	

}