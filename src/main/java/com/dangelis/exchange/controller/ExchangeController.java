package com.dangelis.exchange.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dangelis.entity.Appointment;
import com.dangelis.service.MeetRoomDisplayService;





@RestController
@RequestMapping("/exchange") 
public class ExchangeController {

	 @Autowired
	  private MeetRoomDisplayService service;
	 
	  @RequestMapping(value="appointment",method = RequestMethod.GET,produces="application/json")
	 public List<Appointment> getAllAppointmentsByEmail(@RequestParam("email") String email) {
		  try {
			  return service.getAllAppointmentsByEmail(email);
		  }catch(Exception e) {
			  //log
			  return new ArrayList<Appointment>();
		  }
		 
		}
	 
	
}
