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

import model.OfferManage;

@Path("/offer")
public class OfferManageService {
	OfferManage offer_obj = new OfferManage();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readOffer() {
		return offer_obj.readOffer();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createoffer(
			@FormParam("offerCode") String offerCode,
			@FormParam("offerName") String offerName,
			// changed
			@FormParam("offerPrice") String offerPrice,
			@FormParam("offerDesc") String offerDesc) {
		String output = offer_obj.createoffer(offerCode, offerName, offerPrice, offerDesc);
		return output;
	}

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateItem(String postData) {
		// Convert the input string to a JSON object
		JsonObject postObj = new JsonParser().parse(postData).getAsJsonObject();

		String ID = postObj.get("offID").getAsString();
		String offerCode = postObj.get("offerCode").getAsString();
		String offerName = postObj.get("offerName").getAsString();
		// change
		String offerPrice = postObj.get("offerPrice").getAsString();
		String offerDesc = postObj.get("offerDesc").getAsString();

		String output = offer_obj.updatePost(ID, offerCode, offerName, offerPrice, offerDesc);

		return output;
	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)

	public String deleteItem(String itemData) {
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

		String itemID = doc.select("itemID").text();

		String output = offer_obj.deleteOffer(itemID);

		return output;
	}
}
