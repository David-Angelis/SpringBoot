package com.dangelis.exchange.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;





@RestController
@RequestMapping("/exchange") 
public class ExchangeController {

	 @Autowired
	  private com.dangelis.Service.MeetRoomDisplayService service;
	 
	  @RequestMapping(value="/{email}",method = RequestMethod.GET)
	 public String getAllAppointmentsByEmail(@PathVariable("email")String email) {
			return "OK";
		}
	 
	
}
