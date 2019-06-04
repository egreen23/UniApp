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

import it.unisalento.se.saw.IService.IMaterialeService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.dto.AulaDTO;
import it.unisalento.se.saw.dto.DocenteDTO;
import it.unisalento.se.saw.dto.MaterialeDTO;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.dto.ToolDTO;
import it.unisalento.se.saw.strategy.DateSortStrategy;
import it.unisalento.se.saw.strategy.SortContext;
import it.unisalento.se.saw.strategy.SortStrategy;
import it.unisalento.se.saw.strategy.StringSortStrategy;

@RestController
@RequestMapping("/materiale")
public class MaterialeRestController {
	
	@Autowired
	IMaterialeService materialeService;
	
	public MaterialeRestController() {
		super();
	}
	
	public MaterialeRestController(IMaterialeService materialeService) {
		this.materialeService = materialeService;
	}
	
	
	@GetMapping(value="/findAll", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialeDTO>> findAll() throws Exception {
		try {
			
			List<Materiale> materialeList = materialeService.findAll();
			List<Date> datearray = new ArrayList<Date>();
			Iterator<Materiale> materialeIterator = materialeList.iterator();
			
			List<MaterialeDTO> ListMaterialeDTO = new ArrayList<MaterialeDTO>();
			
			while(materialeIterator.hasNext())
			{
				Materiale materiale = materialeIterator.next();
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(materiale.getData());
				datearray.add(date1);
				
			}
			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			
			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  
				
				materialeIterator = materialeList.iterator();
				
				while(materialeIterator.hasNext()) {
					
					Materiale materiale = materialeIterator.next();
										
					if (materiale.getData().equals(strDate)) {
						
						MaterialeDTO materialeDTO = new MaterialeDTO();
						
						materialeDTO.setIdMateriale(materiale.getIdMateriale());
						materialeDTO.setNome(materiale.getNome());
						materialeDTO.setUrl(materiale.getUrl());
						materialeDTO.setIdInsegnamento(materiale.getInsegnamento().getIdInsegnamento());
						materialeDTO.setNomeInsegnamento(materiale.getInsegnamento().getNome());
						materialeDTO.setNomeCorsoDiStudio(materiale.getInsegnamento().getCorsoDiStudio().getNome());
						materialeDTO.setTipo(materiale.getInsegnamento().getCorsoDiStudio().getTipo());
						materialeDTO.setData(materiale.getData());

						
						ListMaterialeDTO.add(materialeDTO);
						
						
						materialeList.remove(materiale);
						
						break;
					}
				}
			}
			return new ResponseEntity<List<MaterialeDTO>>(ListMaterialeDTO, HttpStatus.OK);
			
			
		} catch (Exception e) {
			
			return new ResponseEntity<List<MaterialeDTO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	
	@GetMapping(value="/getById/{idMateriale}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MaterialeDTO> getById(@PathVariable("idMateriale") int idMateriale) throws Exception {
		try {
			
			Materiale materiale = materialeService.getById(idMateriale);
			MaterialeDTO materialeDTO = new MaterialeDTO();

			materialeDTO.setIdMateriale(materiale.getIdMateriale());
			materialeDTO.setNome(materiale.getNome());
			materialeDTO.setUrl(materiale.getUrl());
			materialeDTO.setIdInsegnamento(materiale.getInsegnamento().getIdInsegnamento());
			materialeDTO.setNomeInsegnamento(materiale.getInsegnamento().getNome());
			materialeDTO.setNomeCorsoDiStudio(materiale.getInsegnamento().getCorsoDiStudio().getNome());
			materialeDTO.setTipo(materiale.getInsegnamento().getCorsoDiStudio().getTipo());
			materialeDTO.setData(materiale.getData());

			
			return new ResponseEntity<MaterialeDTO>(materialeDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<MaterialeDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newMat", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materiale> save(@RequestBody MaterialeDTO materialeDTO) throws Exception {
		try {
			
			Materiale newMateriale = new Materiale();
			Insegnamento insegn = new Insegnamento();
			
			insegn.setIdInsegnamento(materialeDTO.getIdInsegnamento());
			
			newMateriale.setNome(materialeDTO.getNome());
			newMateriale.setUrl(materialeDTO.getUrl());
			newMateriale.setData(materialeDTO.getData());

			newMateriale.setInsegnamento(insegn);
			
			return new ResponseEntity<Materiale>(materialeService.save(newMateriale), HttpStatus.CREATED);
		
			
		} catch (Exception e) {
			
			return new ResponseEntity<Materiale>(HttpStatus.BAD_REQUEST);

		}
	}	
	
	
//	@PostMapping(value="/updateById/{idMateriale}", consumes=MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<Materiale> updateById(@PathVariable("idMateriale") int idMateriale, @RequestBody MaterialeDTO materialeDTO) throws Exception {
//		try {
//			
//			Materiale matUpdate = materialeService.getById(idMateriale);
//			Insegnamento insegn = new Insegnamento();
//			
//			insegn.setIdInsegnamento(materialeDTO.getIdInsegnamento());
//			
//			matUpdate.setNome(materialeDTO.getNome());
//			matUpdate.setUrl(materialeDTO.getUrl());
//			matUpdate.setData(materialeDTO.getData());
//
//			matUpdate.setInsegnamento(insegn);
//
//			return new ResponseEntity<Materiale>(materialeService.save(matUpdate), HttpStatus.OK);
//			
//			
//		} catch (Exception e) {
//			return new ResponseEntity<Materiale>(HttpStatus.OK);
//		}
//	}
	
	
	@GetMapping(value="/getMatByIdInsegnamento/{idInsegnamento}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialeDTO>> getbyIdInsegnamento(@PathVariable("idInsegnamento") int idInsegnamento) throws Exception {
		try {
			
			List<Materiale> materialeList = materialeService.getMatByIdInsegnamento(idInsegnamento);
			Iterator<Materiale> materialeIterator = materialeList.iterator();
			List<Date> datearray = new ArrayList<Date>();

			List<MaterialeDTO> listMaterialeDTO = new ArrayList<MaterialeDTO>();
		
			while(materialeIterator.hasNext())
			{
				Materiale materiale = materialeIterator.next();
				Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(materiale.getData());
				datearray.add(date1);
			}			
			SortStrategy<Date> datesort = new DateSortStrategy();
			SortContext dateorderer = new SortContext<Date>(datesort);
			dateorderer.setList(datearray);
			dateorderer.sort();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			
			for(Date d : datearray) {
				
				String strDate = dateFormat.format(d);  
				
				materialeIterator = materialeList.iterator();
				
				while(materialeIterator.hasNext()) {
					
					Materiale materiale = materialeIterator.next();
										
					if (materiale.getData().equals(strDate)) {
						
						MaterialeDTO materialeDTO = new MaterialeDTO();
						
						materialeDTO.setIdMateriale(materiale.getIdMateriale());
						materialeDTO.setNome(materiale.getNome());
						materialeDTO.setUrl(materiale.getUrl());
						materialeDTO.setIdInsegnamento(materiale.getInsegnamento().getIdInsegnamento());
						materialeDTO.setNomeInsegnamento(materiale.getInsegnamento().getNome());
						materialeDTO.setNomeCorsoDiStudio(materiale.getInsegnamento().getCorsoDiStudio().getNome());
						materialeDTO.setTipo(materiale.getInsegnamento().getCorsoDiStudio().getTipo());
						materialeDTO.setData(materiale.getData());

						
						listMaterialeDTO.add(materialeDTO);
						
						
						materialeList.remove(materiale);
						
						break;
					}
				}
			}
			return new ResponseEntity<List<MaterialeDTO>>(listMaterialeDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<MaterialeDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
