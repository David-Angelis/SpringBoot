package com.dangelis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Created by Altran on 18/04/13.
 */
public class Appointment implements Comparable<Appointment> {

    private String _subject;
    private String _body;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime _dateStart;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime _dateEnd;
    private String _location;


    public String getSubject() {
        return _subject;
    }

    public void setSubject(String _subject) {
        this._subject = _subject;
    }

    public String getBody() {
        return _body;
    }

    public void setBody(String _body) {
        this._body = _body;
    }

    public LocalDateTime getDateStart() {
        return _dateStart;
    }

    public void setDateStart(LocalDateTime _dateStart) {
        this._dateStart = _dateStart;
    }

    public LocalDateTime getDateEnd() {
        return _dateEnd;
    }

    public void setDateEnd(LocalDateTime _dateEnd) {
        this._dateEnd = _dateEnd;
    }

    public String getLocation() {
        return _location;
    }

    public void setLocation(String _location) {
        this._location = _location;
    }

    public Appointment(microsoft.exchange.webservices.data.core.service.item.Appointment appointment) throws ServiceLocalException {
        this._subject=appointment.getSubject();
        this._body=appointment.getBody().toString();
        this._dateStart=appointment.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        this._dateEnd=appointment.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

	

	@Override
	public int compareTo(Appointment o) {
		
		
		if(this._dateStart.isBefore(o._dateStart)) {
			return -1;
		}
		if(this._dateStart.isAfter(o._dateStart)) {
			return 1;
		}else {
			return 0;
		}
	}
}
