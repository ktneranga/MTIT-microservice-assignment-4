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

import model.OrderManage;

@Path("/Order")
public class OrderManageService {
	OrderManage order_obj = new OrderManage();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOrders() {
		return order_obj.readOrders();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createOrder(
			@FormParam("orderCode") String orderCode,
			@FormParam("orderName") String orderName,
			// changed
			@FormParam("orderPrice") String orderPrice,
			@FormParam("orderDesc") String orderDesc) {
		String output = order_obj.createOrder(orderCode, orderName, orderPrice, orderDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData) {
		// Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();

		String ID = postObj.get("oID").getAsString();
		String orderCode = postObj.get("orderCode").getAsString();
		String orderName = postObj.get("orderName").getAsString();
		// change
		String orderPrice = postObj.get("orderPrice").getAsString();
		String orderDesc = postObj.get("orderDesc").getAsString();

		String output = order_obj.updatePost(ID, orderCode, orderName, orderPrice, orderDesc);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData) {
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String itemID = doc.select("itemID").text();

		String output = order_obj.deleteOrder(itemID);

		return output;
	}
}
