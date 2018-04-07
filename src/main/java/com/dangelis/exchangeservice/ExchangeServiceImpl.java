package com.dangelis.exchangeservice;

import java.net.URI;
import java.net.URISyntaxException;

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
	        ExchangeCredentials credentials = new WebCredentials("dangelis", "02Set?06", "europe");
	        service.setCredentials(credentials);
	       
	    }	

	public void getAllAppointmentsByEmailByDay(String email) {
		
		
	}

}
