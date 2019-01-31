//package it.unisalento.se.saw.controllers;
//
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import it.unisalento.se.saw.IService.IAulaService;
//import it.unisalento.se.saw.IService.IUserService;
//import it.unisalento.se.saw.domain.User;
//import it.unisalento.se.saw.exceptions.UserNotFoundException;
//import it.unisalento.se.saw.services.StudentService;
//
//@Controller
//public class HomeController {
//	
//	@Autowired
//	StudentService studentService;
//	
//	@Autowired
//	IUserService userService;
//	
//	@Autowired
//	IAulaService aulaService;
//	
//	@Autowired
//	public HomeController(IUserService userService) {
//		this.userService=userService;
//	}
//
//
//	@RequestMapping(value="/home/users", method = RequestMethod.GET)
//	public String users(ModelMap modelMap) throws UserNotFoundException {
//		
//		ArrayList<User> users = (ArrayList<User>)userService.findAll();
//		modelMap.addAttribute("users",users);
//		int i;
//		for(i=0; i<users.size();i++)
//		{	
//			User u = users.get(i);
//			System.out.println("matricola i : " +u.getIdMatricola()+" nome : "+u.getNome()+" ");
//		}
//		return "users";
//	}
//	
//	
//	@RequestMapping(value="/home", method=RequestMethod.GET)
//	public String home(ModelMap modelMap) {
//		
//		/*User user = new User();
//		user.setEmail("osvaldo@email.it");
//		user.setNome("Osvaldo");
//		user.setCognome("Rossi");
//		userService.save(user);*/
//		
//				
//		/*studentService.getStudentById(7);*/
//		modelMap.addAttribute("param1","param2");
//		System.out.println("HOMECONTROLLER");
//		return "home";
//	}
//
//}
