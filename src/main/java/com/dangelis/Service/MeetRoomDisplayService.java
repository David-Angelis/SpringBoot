package com.dangelis.Service;

import com.dangelis.Entity.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangelis.exchangeservice.ExchangeServiceImpl;

import java.util.List;


@Service
public class MeetRoomDisplayService  {

	  @Autowired
	    ExchangeServiceImpl exchangeService;
	  
	  public List<Appointment> getAllAppointmentsByEmail(String email) {
		 return exchangeService.getAllAppointmentsByEmailByDay(email);
			
		}
}
