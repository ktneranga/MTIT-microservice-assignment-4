package com;

import javax.ws.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.*;

import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.CustomerManage;

@Path("/customer")
public class CustomerManageService {
	CustomerManage customer_obj = new CustomerManage();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return customer_obj.readCustomer();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createCustomer(
			@FormParam("customerCode") String customerCode,
			@FormParam("customerName") String customerName,
			// changed
			@FormParam("customerPrice") String customerPrice,
			@FormParam("customerDesc") String customerDesc) {
		String output = customer_obj.createCustomer(customerCode, customerName, customerPrice, customerDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData) {
		// Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();

		String ID = postObj.get("cID").getAsString();
		String customerCode = postObj.get("customerCode").getAsString();
		String customerName = postObj.get("customerName").getAsString();
		// change
		String customerPrice = postObj.get("customerPrice").getAsString();
		String customerDesc = postObj.get("customerDesc").getAsString();

		String output = customer_obj.updatePost(ID, customerCode, customerName, customerPrice, customerDesc);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData) {
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String itemID = doc.select("itemID").text();

		String output = customer_obj.deleteCustomer(itemID);

		return output;
	}
}
