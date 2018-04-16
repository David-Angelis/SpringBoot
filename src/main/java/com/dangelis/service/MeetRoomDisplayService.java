package com.dangelis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangelis.entity.Appointment;
import com.dangelis.entity.Room;
import com.dangelis.exchangeservice.ExchangeServiceImpl;
import com.dangelis.exchangeservice.exception.AppointmentException;

import java.util.List;


@Service
public class MeetRoomDisplayService  {

	  @Autowired
	    ExchangeServiceImpl exchangeService;
	  
	  public Room getAllAppointmentsByEmail(String email,String dateIni,String dateFinal) throws AppointmentException {
		 Room room=new Room(email.replace("@altran.com", ""),email,exchangeService.getAllAppointmentsByEmailByDay(email,dateIni,dateFinal));
		  return room;
			
		}
}
