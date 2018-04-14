package com.dangelis.exchange.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dangelis.Entity.Appointment;





@RestController
@RequestMapping("/exchange") 
public class ExchangeController {

	 @Autowired
	  private com.dangelis.Service.MeetRoomDisplayService service;
	 
	  @RequestMapping(value="/{email}",method = RequestMethod.GET,produces="application/json")
	 public List<Appointment> getAllAppointmentsByEmail(@PathVariable("email")String email) throws Exception {
		  return service.getAllAppointmentsByEmail(email);
		}
	 
	
}
