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

import it.unisalento.se.saw.IService.IMaterialeService;
import it.unisalento.se.saw.domain.Aula;
import it.unisalento.se.saw.domain.Insegnamento;
import it.unisalento.se.saw.domain.Materiale;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.domain.Tool;
import it.unisalento.se.saw.dto.AulaDTO;
import it.unisalento.se.saw.dto.MaterialeDTO;
import it.unisalento.se.saw.dto.StudenteDTO;
import it.unisalento.se.saw.dto.ToolDTO;

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
			Iterator<Materiale> materialeIterator = materialeList.iterator();
			
			List<MaterialeDTO> ListMaterialeDTO = new ArrayList<MaterialeDTO>();
			
			while(materialeIterator.hasNext())
			{
				Materiale materiale = materialeIterator.next();
				MaterialeDTO materialeDTO = new MaterialeDTO();
				
				materialeDTO.setIdMateriale(materiale.getIdMateriale());
				materialeDTO.setNome(materiale.getNome());
				materialeDTO.setUrl(materiale.getUrl());
				materialeDTO.setIdInsegnamento(materiale.getInsegnamento().getIdInsegnamento());
				materialeDTO.setNomeInsegnamento(materiale.getInsegnamento().getNome());
				materialeDTO.setNomeCorsoDiStudio(materiale.getInsegnamento().getCorsoDiStudio().getNome());
				materialeDTO.setTipo(materiale.getInsegnamento().getCorsoDiStudio().getTipo());
				
				ListMaterialeDTO.add(materialeDTO);
				
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
			
			return new ResponseEntity<MaterialeDTO>(materialeDTO, HttpStatus.OK);
			
		} catch (Exception e) {
		
			return new ResponseEntity<MaterialeDTO>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PostMapping(value="/newMat", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materiale> save(@RequestBody MaterialeDTO materialeDTO) throws Exception {
		try {
			
			Materiale materiale = new Materiale();
			Insegnamento insegn = new Insegnamento();
			
			insegn.setIdInsegnamento(materialeDTO.getIdInsegnamento());
			
			materiale.setNome(materialeDTO.getNome());
			materiale.setUrl(materialeDTO.getUrl());
			materiale.setInsegnamento(insegn);
			
			return new ResponseEntity<Materiale>(materialeService.save(materiale), HttpStatus.CREATED);
		
			
		} catch (Exception e) {
			
			return new ResponseEntity<Materiale>(HttpStatus.BAD_REQUEST);

		}
	}	
	
	
	@PostMapping(value="/updateById/{idMateriale}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Materiale> updateById(@PathVariable("idMateriale") int idMateriale, @RequestBody MaterialeDTO materialeDTO) throws Exception {
		try {
			
			Materiale matUpdate = materialeService.updateById(idMateriale);
			Insegnamento insegn = new Insegnamento();
			
			insegn.setIdInsegnamento(materialeDTO.getIdInsegnamento());
			
			matUpdate.setNome(materialeDTO.getNome());
			matUpdate.setUrl(materialeDTO.getUrl());
			matUpdate.setInsegnamento(insegn);

			return new ResponseEntity<Materiale>(materialeService.save(matUpdate), HttpStatus.OK);
			
			
		} catch (Exception e) {
			return new ResponseEntity<Materiale>(HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value="/getByName/{string}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<MaterialeDTO>> getByName(@PathVariable("string") String string) throws Exception {
		try {
			
			List<Materiale> materialeList = materialeService.getByName(string);
			Iterator<Materiale> materialeIterator = materialeList.iterator();
			
			List<MaterialeDTO> listMaterialeDTO = new ArrayList<MaterialeDTO>();
		
			while(materialeIterator.hasNext())
			{
				Materiale materiale = materialeIterator.next();
				MaterialeDTO materialeDTO = new MaterialeDTO();
				
				materialeDTO.setIdMateriale(materiale.getIdMateriale());
				materialeDTO.setNome(materiale.getNome());
				materialeDTO.setUrl(materiale.getUrl());
				materialeDTO.setIdInsegnamento(materiale.getInsegnamento().getIdInsegnamento());
				materialeDTO.setNomeInsegnamento(materiale.getInsegnamento().getNome());
				materialeDTO.setNomeCorsoDiStudio(materiale.getInsegnamento().getCorsoDiStudio().getNome());
				materialeDTO.setTipo(materiale.getInsegnamento().getCorsoDiStudio().getTipo());
				
				listMaterialeDTO.add(materialeDTO);
			}			
			return new ResponseEntity<List<MaterialeDTO>>(listMaterialeDTO, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<List<MaterialeDTO>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	

}
