package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.IStudenteService;
import it.unisalento.se.saw.domain.Studente;
import it.unisalento.se.saw.repositories.StudenteRepository;

@Service
public class StudentService implements IStudenteService{
	

	@Autowired
	StudenteRepository studenteRepository;
	
	
	@Transactional
	public Studente logStudent(int idMatricola) {
		return studenteRepository.logStudent(idMatricola);
	}

}
