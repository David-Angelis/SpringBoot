package com.dangelis.exchangeservice;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dangelis.Entity.Appointment;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        ExchangeCredentials credentials = new WebCredentials("", "", "europe");
	        service.setCredentials(credentials);
	       
	    }	

	public List<Appointment> getAllAppointmentsByEmailByDay(String email) {
		EmailAddress emAddr = new EmailAddress(email);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String td1 = "2018-04-09 00:00:00";
		String td2 = "2018-04-09 23:59:00";
		Date d1 = format.parse(td1);
		Date d2 = format.parse(td2);
		CalendarView cView = new CalendarView(d1,d2);
		PropertySet prop = new PropertySet();
		cView.setPropertySet(prop);
		FolderId folderId = new FolderId(WellKnownFolderName.Calendar, new Mailbox(emAddr.getAddress()));
		FindItemsResults<microsoft.exchange.webservices.data.core.service.item.Appointment> findResults = msees.service.findAppointments(folderId, cView);
		ArrayList<microsoft.exchange.webservices.data.core.service.item.Appointment> calItem = findResults.getItems();
		PropertySet itemPropertySet = new PropertySet(BasePropertySet.FirstClassProperties);
		itemPropertySet.setRequestedBodyType(BodyType.Text);
		int numItems = findResults.getTotalCount();
		Gson gson =new Gson();
		for (int i=0;i<numItems;i++) {
			microsoft.exchange.webservices.data.core.service.item.Appointment Details = microsoft.exchange.webservices.data.core.service.item.Appointment.bind(msees.service, calItem.get(i).getId(),itemPropertySet);
			System.out.println(gson.toJson(calItem.get(i).));
            /*
            System.out.println(calItem.get(i).getOrganizer().getName());
            System.out.println(calItem.get(i).getStart());
            System.out.println(calItem.get(i).getEnd());
            System.out.println(calItem.get(i).getSubject());
            System.out.println(calItem.get(i).getDisplayTo());
            System.out.println(calItem.get(i).getLocation());
            System.out.println(Details.getBody());
            */
		}
		
	}

}
