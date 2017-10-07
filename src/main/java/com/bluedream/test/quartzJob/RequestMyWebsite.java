package com.bluedream.test.quartzJob;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestMyWebsite {
	private static final Logger log = LoggerFactory.getLogger(RequestMyWebsite.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final String USER_AGENT = "Mozilla/5.0";
	
	
	public void doTask() {
		// printing current system time
		System.out.println("Current Running : doTask_1");
		String url = "";
		// REquest 1
		url = "http://classicmodel-augjs1-mytestprj1.193b.starter-ca-central-1.openshiftapps.com/classicmodel-ap1/CustomersJson/103";
		Cron_HttpCallProc_1(url);
		// REquest 2
		//url = "http://classicmodel-augjs1-mytestprj1.193b.starter-ca-central-1.openshiftapps.com/classicmodel-ap1/OrdersJson/10103";
		//Cron_HttpCallProc_1(url);
		// REquest 3
		url = "http://classicmodel-web1-my007testprj1.193b.starter-ca-central-1.openshiftapps.com/#/view1";
		Cron_HttpCallProc_1(url);
	}
	
	public void Cron_HttpCallProc_1(String pStrURL) {
        System.out.println("Cron_HttpCallProc_1: " + dateFormat.format(new Date()));
        
        // String url = "http://www.google.com/search?q=httpClient";
        // String url = "http://jbossews-bluedream.rhcloud.com/CustomersJson/103";        
        // String url = "http://classicmodel-augjs1-mytestprj1.193b.starter-ca-central-1.openshiftapps.com/classicmodel-ap1/CustomersJson/103";
        System.out.println("Request: " + pStrURL);

        try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(pStrURL);

			// add request header
			request.addHeader("User-Agent", USER_AGENT);
			HttpResponse response = client.execute(request);

			System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
            }
			System.out.println("Response Content : " + result.toString());
        } catch (Exception e) {
        	System.out.println("Error: " + e.getMessage());        	
        }
        
    }

}
