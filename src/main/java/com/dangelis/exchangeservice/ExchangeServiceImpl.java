package com.dangelis.exchangeservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import microsoft.exchange.webservices.data.core.PropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BasePropertySet;
import microsoft.exchange.webservices.data.core.enumeration.property.BodyType;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.FolderId;
import microsoft.exchange.webservices.data.property.complex.Mailbox;
import microsoft.exchange.webservices.data.search.CalendarView;
import microsoft.exchange.webservices.data.search.FindItemsResults;

import org.springframework.stereotype.Repository;

import com.dangelis.entity.Appointment;
import com.dangelis.exchangeservice.exception.AppointmentException;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
@Repository
public class ExchangeServiceImpl implements ExchangeServices {
	
	private static ExchangeServiceImpl exchangeImpl;
	private ExchangeService service;
	 
	 public static ExchangeServiceImpl getInstance() {
		 return exchangeImpl;
	 }
	 
	 /**
	     * Initialize the Exchange Credentials.
	     * Don't forget to replace the "USRNAME","PWD","DOMAIN_NAME" variables.
	 * @throws URISyntaxException 
	     */
	    public ExchangeServiceImpl(){
	    	service = new ExchangeService(ExchangeVersion.Exchange2007_SP1);
            try {
				service.setUrl(new URI("https://mail.altran.com/EWS/"));
			} catch (URISyntaxException e) {
				
				e.printStackTrace();
			}
	        ExchangeCredentials credentials = new WebCredentials("", "", "europe");
	        service.setCredentials(credentials);
	       
	    }	

	public List<Appointment> getAllAppointmentsByEmailByDay(String email) throws AppointmentException {
		List<Appointment>list=new ArrayList<Appointment>();
		EmailAddress emAddr = new EmailAddress(email);
		Date d1 = new Date();
		Date d2=new Date();		
		d1=atStartOfDay(d1);
		d2 = atEndOfDay(d2);
		CalendarView cView = new CalendarView(d1,d2);
		PropertySet prop = new PropertySet();
		cView.setPropertySet(prop);
		FolderId folderId = new FolderId(WellKnownFolderName.Calendar, new Mailbox(emAddr.getAddress()));
		FindItemsResults<microsoft.exchange.webservices.data.core.service.item.Appointment> findResults;
		try {
			findResults = service.findAppointments(folderId, cView);
			ArrayList<microsoft.exchange.webservices.data.core.service.item.Appointment> calItem = findResults.getItems();
			PropertySet itemPropertySet = new PropertySet(BasePropertySet.FirstClassProperties);
			itemPropertySet.setRequestedBodyType(BodyType.Text);
			int numItems = findResults.getTotalCount();
			for (int i=0;i<numItems;i++) {
				microsoft.exchange.webservices.data.core.service.item.Appointment Details = microsoft.exchange.webservices.data.core.service.item.Appointment.bind(service, calItem.get(i).getId(),itemPropertySet);
				Appointment appointment=new Appointment(Details);
				list.add(appointment);
			}
		} catch (Exception e) {
			throw new AppointmentException();
		}
		
		return list;
		
	}
	
	public static Date atStartOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
	    return localDateTimeToDate(startOfDay);
	}

	public static Date atEndOfDay(Date date) {
	    LocalDateTime localDateTime = dateToLocalDateTime(date);
	    LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
	    return localDateTimeToDate(endOfDay);
	}

	private static LocalDateTime dateToLocalDateTime(Date date) {
	    return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	private static Date localDateTimeToDate(LocalDateTime localDateTime) {
	    return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
