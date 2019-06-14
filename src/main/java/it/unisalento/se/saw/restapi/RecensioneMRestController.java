package it.unisalento.se.saw.restapi;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import it.unisalento.se.saw.domain.Recensionel;
import it.unisalento.se.saw.domain.Recensionem;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.dto.RecensioneLDTO;
import it.unisalento.se.saw.dto.RecensioneMDTO;
import it.unisalento.se.saw.strategy.DateSortStrategy;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;

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
	
	
	
	
	
	
	
	
	
	
	@PostMapping(value="/newRecMat", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Recensionem> save(@RequestBody RecensioneMDTO recensioneMDTO) throws Exception {
		try {
			
			Recensionem newRecMat = new Recensionem();
			Studente stud = new Studente();
			Materiale mat = new Materiale();
			
			stud.setIdStudente(recensioneMDTO.getIdStudente());
			mat.setIdMateriale(recensioneMDTO.getIdMateriale());
			
			newRecMat.setVoto(recensioneMDTO.getVoto());
			newRecMat.setTesto(recensioneMDTO.getTesto());
			newRecMat.setStudente(stud);
			newRecMat.setMateriale(mat);
			newRecMat.setData(recensioneMDTO.getData());
			
			recensioneMService.save(newRecMat);
			// System.out.println("ID "+newRecMat.getIdrecensionem());
			return new ResponseEntity<Recensionem>(newRecMat, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<Recensionem>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//nuovo metodo CH
		@GetMapping(value="/getByMatricolaStudIdInsegIdMaterial/{idMatricola}/{idInsegnamento}/{idMateriale}", produces=MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<RecensioneMDTO> getByMatricolaStudIdInsegnamento(@PathVariable("idMatricola") int idMatricola, @PathVariable("idInsegnamento") int idInsegnamento, @PathVariable("idMateriale") int idMateriale) throws Exception {
			try {
				
				Recensionem recMat = recensioneMService.getByMatricolaStudIdInsegIdMaterial(idMatricola, idInsegnamento, idMateriale);
				RecensioneMDTO recMatDTO = new RecensioneMDTO();			
				
				recMatDTO.setIdRecensioneM(recMat.getIdrecensionem());
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
				recMatDTO.setData(recMat.getData());
				
				return new ResponseEntity<RecensioneMDTO>(recMatDTO, HttpStatus.OK);
				
			} catch (Exception e) {
				
				return new ResponseEntity<RecensioneMDTO>(HttpStatus.NO_CONTENT);
			}
		}
		
		//nuovo metodo CH
			@GetMapping(value="/getRecByIdMateriale/{idMateriale}", produces=MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<List<RecensioneMDTO>> getRecByIdMateriale(@PathVariable("idMateriale") int idMateriale) throws Exception {
				try {
					
					List<Recensionem> recMatList = recensioneMService.getRecByIdMateriale(idMateriale);
					Iterator<Recensionem> recMatIterator = recMatList.iterator();
					List<Date> datearray = new ArrayList<Date>();

					List<RecensioneMDTO> listRecMatDTO = new ArrayList<RecensioneMDTO>();
							
					
					while(recMatIterator.hasNext())
					{
						Recensionem recMat = recMatIterator.next();
						Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(recMat.getData());
						datearray.add(date1);
						
					}
					SortStrategy<Date> datesort = new DateSortStrategy();
					SortContext dateorderer = new SortContext<Date>(datesort);
					dateorderer.setList(datearray);
					dateorderer.sort();
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
					
					for(Date d : datearray) {
						
						String strDate = dateFormat.format(d);  

						recMatIterator = recMatList.iterator();
						
						while(recMatIterator.hasNext()) {
							
							Recensionem recMat = recMatIterator.next();
							
							if (recMat.getData().equals(strDate)) {
														
								RecensioneMDTO recMatDTO = new RecensioneMDTO();			
								
								recMatDTO.setIdRecensioneM(recMat.getIdrecensionem());
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
								recMatDTO.setData(recMat.getData());
								
								listRecMatDTO.add(recMatDTO);
								
								
								recMatList.remove(recMat);
								
								break;
							}
						}
						
					}
					if (listRecMatDTO.isEmpty())
					{
						return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.NO_CONTENT);				
					}
					else
					{
						return new ResponseEntity<List<RecensioneMDTO>>(listRecMatDTO, HttpStatus.OK);
					}
				} catch (Exception e) {
					
					return new ResponseEntity<List<RecensioneMDTO>>(HttpStatus.BAD_REQUEST);
				}
			}
	
	
	

}
