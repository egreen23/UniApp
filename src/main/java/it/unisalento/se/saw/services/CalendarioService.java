package it.unisalento.se.saw.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.IService.ICalendarioService;
import it.unisalento.se.saw.domain.Calendario;
import it.unisalento.se.saw.domain.Esame;
import it.unisalento.se.saw.domain.Lezione;
import it.unisalento.se.saw.dto.composite.CalendarioComponent;
import it.unisalento.se.saw.dto.composite.CalendarioDTOComp;
import it.unisalento.se.saw.dto.composite.EsameDTOComp;
import it.unisalento.se.saw.dto.composite.LezioneDTOComp;
import it.unisalento.se.saw.repositories.CalendarioRepository;
import it.unisalento.se.saw.repositories.EsameRepository;
import it.unisalento.se.saw.repositories.LezioneRepository;
import it.unisalento.se.saw.repositories.UserRepository;

//eliminare ridondanze nei metodi dove prendo i calendari (vedi calendaribyCds)
@Service
public class CalendarioService implements ICalendarioService {
	
	@Autowired
	CalendarioRepository calendarioRepository;

	@Autowired
	LezioneRepository lezioneRepository;
	
	@Autowired
	EsameRepository esameRepository;
	
	
	
	
	@Transactional
	public List<CalendarioComponent> findAll(){
		
		List<Calendario> listCalendario = calendarioRepository.findAll();
		List<CalendarioComponent> listCalendarioComp = new ArrayList<CalendarioComponent>();
		
		for (Calendario calendario : listCalendario) 
		{
			
			if (calendario.getTipo().equals("Lezione")) 
			{
				
				CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
						calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine() ,calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
				
				List<Lezione> listLezioni = lezioneRepository.getLezioniByIdCalendario(calendario.getIdCalendario());
				
				for (Lezione lezione : listLezioni) 
				{
					
					CalendarioComponent lezioneComponent = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(), lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(lezioneComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			else 
			{
				CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
						calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine(), calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
				
				List<Esame> listEsami = esameRepository.getEsameByIdCalendario(calendario.getIdCalendario());
				
				for (Esame esame : listEsami) 
				{
					
					CalendarioComponent esameComponent = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
							esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
							esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(esameComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			
		}
		return listCalendarioComp;
	}
	
	
	
	
	@Transactional
	public List<CalendarioComponent> getAll(){
		
		List<Calendario> listCalendario = calendarioRepository.getAll();
	
		List<CalendarioComponent> listCalendarioComp = new ArrayList<CalendarioComponent>();
		
		for (Calendario calendario : listCalendario) 
		{
			
			if (calendario.getTipo().equals("Lezione")) 
			{
				
				CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
						calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine() ,calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
				
				List<Lezione> listLezioni = lezioneRepository.getLezioniByIdCalendario(calendario.getIdCalendario());
				
				for (Lezione lezione : listLezioni) 
				{
					
					CalendarioComponent lezioneComponent = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(), lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(lezioneComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			else 
			{
				CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
						calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine(), calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
				
				List<Esame> listEsami = esameRepository.getEsameByIdCalendario(calendario.getIdCalendario());
				
				for (Esame esame : listEsami) 
				{
					
					CalendarioComponent esameComponent = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
							esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
							esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(esameComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			
		}
		return listCalendarioComp;
	}
	
	
	@Transactional
	public CalendarioComponent getCalendarioById(int idCalendario){
		
		Calendario calendario = calendarioRepository.getOne(idCalendario);
		
		CalendarioComponent cal = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
				calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine() ,calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
		
		
			if (calendario.getTipo().equals("Lezione")) 
			{
							
				List<Lezione> listLezioni = lezioneRepository.getLezioniByIdCalendario(calendario.getIdCalendario());
				
				for (Lezione lezione : listLezioni) 
				{
					
					CalendarioComponent lezioneComponent = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(), lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
					
					cal.add(lezioneComponent);
				} 
				
			}
			else
			{
				List<Esame> listEsami = esameRepository.getEsameByIdCalendario(calendario.getIdCalendario());
				
				for (Esame esame : listEsami) 
				{
					
					CalendarioComponent esameComponent = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
							esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
							esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
					
					cal.add(esameComponent);
				} 
				
			}
			
		return cal;
	}
	
	@Transactional
	public Calendario save(Calendario calendario) {
		return calendarioRepository.save(calendario);
	}
	
	@Transactional
	public Calendario updateCalendarioById(int idCalendario) {
		return calendarioRepository.getOne(idCalendario);
	}
	
	@Transactional
	public List<CalendarioComponent> getCalendaribyCds(int idCds) {
		
		List<Calendario> listCalendario = calendarioRepository.getCalendaribyCds(idCds);
		
		List<CalendarioComponent> listCalendarioComp = new ArrayList<CalendarioComponent>();
		
		
		
		for (Calendario calendario : listCalendario) 
		{
			CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
					calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine() ,calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio(), calendario.getCorsoDiStudio().getNome());
			
			if (calendario.getTipo().equals("Lezione")) 
			{
				
				
				
				List<Lezione> listLezioni = lezioneRepository.getLezioniByIdCalendario(calendario.getIdCalendario());
				
				for (Lezione lezione : listLezioni) 
				{
					
					CalendarioComponent lezioneComponent = new LezioneDTOComp(lezione.getIdLezione(), lezione.getOrarioInizio(), lezione.getOrarioFine(), lezione.getData(), 
							lezione.getAula().getNome(), lezione.getInsegnamento().getNome(), lezione.getInsegnamento().getDocente().getUser().getNome(), lezione.getInsegnamento().getDocente().getUser().getCognome(),
							lezione.getInsegnamento().getCrediti(), lezione.getInsegnamento().getCorsoDiStudio().getNome(), lezione.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(lezioneComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			else 
			{
				/*CalendarioComponent calendarioComponent = new CalendarioDTOComp(calendario.getIdCalendario(), calendario.getTipo(), 
						calendario.getAnno(), calendario.getDataInizio(), calendario.getDataFine(), calendario.getSemestre(), calendario.getCorsoDiStudio().getIdCorsoDiStudio());*/
				
				List<Esame> listEsami = esameRepository.getEsameByIdCalendario(calendario.getIdCalendario());
				
				for (Esame esame : listEsami) 
				{
					
					CalendarioComponent esameComponent = new EsameDTOComp(esame.getIdEsame(), esame.getData(), esame.getOrarioInizio(), esame.getOrarioFine(), 
							esame.getAula().getNome(), esame.getInsegnamento().getNome(), esame.getInsegnamento().getDocente().getUser().getNome(), esame.getInsegnamento().getDocente().getUser().getCognome(),
							esame.getInsegnamento().getCrediti(), esame.getInsegnamento().getCorsoDiStudio().getNome(), esame.getInsegnamento().getCorsoDiStudio().getTipo());
					
					calendarioComponent.add(esameComponent);
				} 
				
				listCalendarioComp.add(calendarioComponent);
			}
			
		}
		return listCalendarioComp;
		
	}

	
	
	


}
