package com.dangelis.exchangeservice;

import java.util.List;

import com.dangelis.entity.Appointment;
import com.dangelis.exchangeservice.exception.AppointmentException;

public interface ExchangeServices {

	public List<Appointment> getAllAppointmentsByEmailByDay(String email) throws AppointmentException;
}
