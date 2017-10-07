/*
 *  ref: 
 *  1. https://spring.io/guides/gs/scheduling-tasks/
 *  2. https://www.mkyong.com/java/apache-httpclient-examples/
 *  
 */
package com.bluedream.test.quartzJob;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;


@Component
public class ScheduledTasks_1 {
	
	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks_1.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final String USER_AGENT = "Mozilla/5.0";

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("The time is now {}", dateFormat.format(new Date()));
    }
       
    @Scheduled(cron = "*/5 * * * * *")
    // public void Cron() throws InterruptedException {
    public void Cron_HttpCallProc_1() {
        System.out.println("Cron_HttpCallProc_1: " + dateFormat.format(new Date()));
        
        // String url = "http://www.google.com/search?q=httpClient";
        String url = "http://jbossews-bluedream.rhcloud.com/CustomersJson/103";

        try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);

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
