package com.statestreet;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * @author krishna
 *
 */
public class MapsConsumer {
	
	public static void main(String[] args) {
		
		Client client = Client.create();

		WebResource webResource = client
		   .resource("https://nominatim.openstreetmap.org/?format=json&addressdetails=1&q=bakery+in+berlin+wedding&format=json&limit=1");

		ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

		if (response.getStatus() != 200) {
		   throw new RuntimeException("Failed : HTTP error code : "
			+ response.getStatus());
		}

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		
	}

}
