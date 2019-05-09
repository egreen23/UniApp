package it.unisalento.se.saw.restapi;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unisalento.se.saw.IService.IRecensioneMService;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.RecensioneL;
import it.unisalento.se.saw.domain.RecensioneM;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.RecensioneLDTO;
import it.unisalento.se.saw.dto.RecensioneMDTO;

@RestController
@RequestMapping("recM/")
public class RecensioneMRestController {
	
	@Autowired
	IRecensioneMService recensioneMService;
	
	
	public RecensioneMRestController(IRecensioneMService recensioneMService) {
		this.recensioneMService = recensioneMService;
	}

	public RecensioneMRestController() {
		super();
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneMDTO>> findAll() throws Exception {
		try {
			
			List<RecensioneM> recMatList = recensioneMService.findAll();
			Iterator<RecensioneM> recMatIterator = recMatList.iterator();
			
			List<RecensioneMDTO> listRecMatDTO = new ArrayList<RecensioneMDTO>();
					
			
			while(recMatIterator.hasNext())
			{
				RecensioneM recMat = recMatIterator.next();
				RecensioneMDTO recMatDTO = new RecensioneMDTO();			
				
				recMatDTO.setIdRecensioneM(recMat.getIdRecensioneM());
				recMatDTO.setVoto(recMat.getVoto());
				recMatDTO.setTesto(recMat.getTesto());
				
				recMatDTO.setIdMateriale(recMat.getMateriale().getIdMateriale());
				recMatDTO.setNomeMateriale(recMat.getMateriale().getNome());
				recMatDTO.setUriMateriale(recMat.getMateriale().getUrl());

				recMatDTO.setIdInsegnamento(recMat.getMateriale().getInsegnamento().getIdInsegnamento());
				recMatDTO.setNomeInsegnamento(recMat.getMateriale().getInsegnamento().getNome());
				
				recMatDTO.setIdDocente(recMat.getMateriale().getInsegnamento().getDocente().getIdDocente());
				recMatDTO.setCognomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getCognome());
				recMatDTO.setNomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getNome());
				
				recMatDTO.setIdStudente(recMat.getStudente().getIdStudente());
				recMatDTO.setMatricolaStudente(recMat.getStudente().getUser().getIdMatricola());;
				recMatDTO.setCognomeStudente(recMat.getStudente().getUser().getCognome());;
				recMatDTO.setNomeStudente(recMat.getStudente().getUser().getNome());;
				
				recMatDTO.setIdcorsoDiStudio(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recMatDTO.setNomeCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getNome());
				recMatDTO.setTipoCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecMatDTO.add(recMatDTO);
				
			}
			return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.OK);
			

		} catch (Exception e) {
			return new ResponseEntity<List<RecensioneMDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getRecMatByInsegnamento/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneMDTO>> getRecMatByInsegnamento(@PathVariable("string") String string) throws Exception {
		try {
			
			List<RecensioneM> recMatList = recensioneMService.getRecMatByInsegnamento(string);
			Iterator<RecensioneM> recMatIterator = recMatList.iterator();
			
			List<RecensioneMDTO> listRecMatDTO = new ArrayList<RecensioneMDTO>();
					
			
			while(recMatIterator.hasNext())
			{
				RecensioneM recMat = recMatIterator.next();
				RecensioneMDTO recMatDTO = new RecensioneMDTO();			
				
				recMatDTO.setIdRecensioneM(recMat.getIdRecensioneM());
				recMatDTO.setVoto(recMat.getVoto());
				recMatDTO.setTesto(recMat.getTesto());
				
				recMatDTO.setIdMateriale(recMat.getMateriale().getIdMateriale());
				recMatDTO.setNomeMateriale(recMat.getMateriale().getNome());
				recMatDTO.setUriMateriale(recMat.getMateriale().getUrl());

				recMatDTO.setIdInsegnamento(recMat.getMateriale().getInsegnamento().getIdInsegnamento());
				recMatDTO.setNomeInsegnamento(recMat.getMateriale().getInsegnamento().getNome());
				
				recMatDTO.setIdDocente(recMat.getMateriale().getInsegnamento().getDocente().getIdDocente());
				recMatDTO.setCognomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getCognome());
				recMatDTO.setNomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getNome());
				
				recMatDTO.setIdStudente(recMat.getStudente().getIdStudente());
				recMatDTO.setMatricolaStudente(recMat.getStudente().getUser().getIdMatricola());;
				recMatDTO.setCognomeStudente(recMat.getStudente().getUser().getCognome());;
				recMatDTO.setNomeStudente(recMat.getStudente().getUser().getNome());;
				
				recMatDTO.setIdcorsoDiStudio(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recMatDTO.setNomeCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getNome());
				recMatDTO.setTipoCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecMatDTO.add(recMatDTO);
				
			}	
			if (listRecMatDTO.isEmpty())
			{
				return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<RecensioneMDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@GetMapping(value="/getRecMatByVoto/{voto}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecensioneMDTO>> getRecMatByVoto(@PathVariable("voto") int voto) throws Exception {
		try {
			
			List<RecensioneM> recMatList = recensioneMService.getRecMatByVoto(voto);
			Iterator<RecensioneM> recMatIterator = recMatList.iterator();
			
			List<RecensioneMDTO> listRecMatDTO = new ArrayList<RecensioneMDTO>();
					
			
			while(recMatIterator.hasNext())
			{
				RecensioneM recMat = recMatIterator.next();
				RecensioneMDTO recMatDTO = new RecensioneMDTO();			
				
				recMatDTO.setIdRecensioneM(recMat.getIdRecensioneM());
				recMatDTO.setVoto(recMat.getVoto());
				recMatDTO.setTesto(recMat.getTesto());
				
				recMatDTO.setIdMateriale(recMat.getMateriale().getIdMateriale());
				recMatDTO.setNomeMateriale(recMat.getMateriale().getNome());
				recMatDTO.setUriMateriale(recMat.getMateriale().getUrl());

				recMatDTO.setIdInsegnamento(recMat.getMateriale().getInsegnamento().getIdInsegnamento());
				recMatDTO.setNomeInsegnamento(recMat.getMateriale().getInsegnamento().getNome());
				
				recMatDTO.setIdDocente(recMat.getMateriale().getInsegnamento().getDocente().getIdDocente());
				recMatDTO.setCognomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getCognome());
				recMatDTO.setNomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getNome());
				
				recMatDTO.setIdStudente(recMat.getStudente().getIdStudente());
				recMatDTO.setMatricolaStudente(recMat.getStudente().getUser().getIdMatricola());;
				recMatDTO.setCognomeStudente(recMat.getStudente().getUser().getCognome());;
				recMatDTO.setNomeStudente(recMat.getStudente().getUser().getNome());;
				
				recMatDTO.setIdcorsoDiStudio(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
				recMatDTO.setNomeCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getNome());
				recMatDTO.setTipoCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getTipo());

				
				listRecMatDTO.add(recMatDTO);
				
			}	
			if (listRecMatDTO.isEmpty())
			{
				return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.NOT_FOUND);				
			}
			else
			{
				return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.OK);
			}
		} catch (Exception e) {
			
			return new ResponseEntity<List<RecensioneMDTO>>(HttpStatus.BAD_REQUEST);
		}
	}	
	
	
	@GetMapping(value="/getById/{idRecensioneM}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecensioneMDTO> getById(@PathVariable("idRecensioneM") int idRecensioneM) throws Exception {
		try {
			
			RecensioneM recMat = recensioneMService.getById(idRecensioneM);
			RecensioneMDTO recMatDTO = new RecensioneMDTO();			
			
			recMatDTO.setIdRecensioneM(recMat.getIdRecensioneM());
			recMatDTO.setVoto(recMat.getVoto());
			recMatDTO.setTesto(recMat.getTesto());
			
			recMatDTO.setIdMateriale(recMat.getMateriale().getIdMateriale());
			recMatDTO.setNomeMateriale(recMat.getMateriale().getNome());
			recMatDTO.setUriMateriale(recMat.getMateriale().getUrl());

			recMatDTO.setIdInsegnamento(recMat.getMateriale().getInsegnamento().getIdInsegnamento());
			recMatDTO.setNomeInsegnamento(recMat.getMateriale().getInsegnamento().getNome());
			
			recMatDTO.setIdDocente(recMat.getMateriale().getInsegnamento().getDocente().getIdDocente());
			recMatDTO.setCognomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getCognome());
			recMatDTO.setNomeDocente(recMat.getMateriale().getInsegnamento().getDocente().getUser().getNome());
			
			recMatDTO.setIdStudente(recMat.getStudente().getIdStudente());
			recMatDTO.setMatricolaStudente(recMat.getStudente().getUser().getIdMatricola());;
			recMatDTO.setCognomeStudente(recMat.getStudente().getUser().getCognome());;
			recMatDTO.setNomeStudente(recMat.getStudente().getUser().getNome());;
			
			recMatDTO.setIdcorsoDiStudio(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getIdCorsoDiStudio());
			recMatDTO.setNomeCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getNome());
			recMatDTO.setTipoCorso(recMat.getMateriale().getInsegnamento().getCorsoDiStudio().getTipo());
			
			return new ResponseEntity<RecensioneMDTO>(recMatDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			
			return new ResponseEntity<RecensioneMDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newRecMat", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecensioneM> save(@RequestBody RecensioneMDTO recensioneMDTO) throws Exception {
		try {
			
			RecensioneM newRecMat = new RecensioneM();
			Studente stud = new Studente();
			Materiale mat = new Materiale();
			
			stud.setIdStudente(recensioneMDTO.getIdStudente());
			mat.setIdMateriale(recensioneMDTO.getIdMateriale());
			
			newRecMat.setVoto(recensioneMDTO.getVoto());
			newRecMat.setTesto(recensioneMDTO.getTesto());
			newRecMat.setStudente(stud);
			newRecMat.setMateriale(mat);
			
			recensioneMService.save(newRecMat);
			System.out.println("ID "+newRecMat.getIdRecensioneM());
			return new ResponseEntity<RecensioneM>(newRecMat, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<RecensioneM>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/updateRecMatById/{idRecensioneM}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RecensioneM> updateRecMatById(@PathVariable("idRecensioneM") int idRecensioneM ,@RequestBody RecensioneMDTO recensioneMDTO) throws Exception {
		try {
			
			RecensioneM updateRecMat = recensioneMService.updateRecMatById(idRecensioneM);
					
//			updateRecLez.setIdRecensioneL(recensioneLDTO.getIdRecensioneL());
			updateRecMat.setVoto(recensioneMDTO.getVoto());
			updateRecMat.setTesto(recensioneMDTO.getTesto());
			updateRecMat.getStudente().setIdStudente(recensioneMDTO.getIdStudente());
			updateRecMat.getMateriale().setIdMateriale(recensioneMDTO.getIdMateriale());
			
			return new ResponseEntity<RecensioneM>(recensioneMService.save(updateRecMat), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<RecensioneM>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	

}
