package com.dangelis.exchangeservice;

import com.dangelis.Entity.Appointment;

import java.util.List;

public interface ExchangeServices {

	public List<Appointment> getAllAppointmentsByEmailByDay(String email);
}
