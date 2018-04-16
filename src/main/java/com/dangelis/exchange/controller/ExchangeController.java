package com.dangelis.exchange.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangelis.entity.Appointment;
import com.dangelis.entity.Room;
import com.dangelis.service.MeetRoomDisplayService;





@RestController
@RequestMapping("/exchange") 
public class ExchangeController {

	 @Autowired
	  private MeetRoomDisplayService service;
	 
	  @RequestMapping(value="appointment",method = RequestMethod.GET,produces="application/json")
	 public Room getAllAppointmentsByEmail(@RequestParam("email") String email,@RequestParam("dateIni") String dateIni,@RequestParam("dateFinal") String dateFinal) {
		  try {
			  return service.getAllAppointmentsByEmail(email,dateIni,dateFinal);
		  }catch(Exception e) {
			  //log
			  return null;
		  }
		 
		}
	 
	
}
