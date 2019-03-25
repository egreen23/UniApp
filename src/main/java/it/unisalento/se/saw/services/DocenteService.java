package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IDocenteService;
import it.unisalento.se.saw.domain.Docente;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.repositories.DocenteRepository;
import it.unisalento.se.saw.repositories.StudenteRepository;

@Service
public class DocenteService implements IDocenteService{
	
	@Autowired
	DocenteRepository docenteRepository;
	
	@Transactional
	public Docente logDocente(int idMatricola){
		return docenteRepository.logDocente(idMatricola);
	}
	
	@Transactional
	public List<Docente> findAll() {
		return docenteRepository.findAll();
	}
	
	@Transactional
	public Docente getByMatricola(int idMatricola) {
		return docenteRepository.logDocente(idMatricola);
	}
	
	@Transactional
	public Docente save(Docente docente) {
		return docenteRepository.save(docente);
	}
	
	@Transactional
	public Docente updateDocByMatricola(int matricola) {
		return docenteRepository.logDocente(matricola);
	}

}
