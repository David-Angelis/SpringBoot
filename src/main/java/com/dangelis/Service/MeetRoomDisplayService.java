package com.dangelis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangelis.exchangeservice.ExchangeServiceImpl;



@Service
public class MeetRoomDisplayService  {

	  @Autowired
	    ExchangeServiceImpl exchangeService;
	  
	  public void getAllAppointmentsByEmail(String email) {
		  exchangeService.getAllAppointmentsByEmailByDay(email);
			
		}
}
