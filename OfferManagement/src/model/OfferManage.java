package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class OfferManage {

	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide Correct Database Details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}

	public String createoffer(String offerCode, String offerName, String offerPrice, String offerDesc) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error";
			}
			// LocalDate date= LocalDate.now();
			// LocalTime time= LocalTime.now();
			String query = " insert into offers(offerCode,offerName,offerPrice,offerDesc)"
					+
					" values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, offerCode);
			ps.setString(2, offerName);
			ps.setString(3, offerPrice);
			ps.setString(4, offerDesc);

			ps.execute();
			con.close();

			String newProudct = readOffer();

			output = "Insert Success";
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
		} catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the offer.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readOffer() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "123";
			}
			output = "<table boffer='1'><tr><th>offer ID</th><th>offer Code</th><th>offer Name</th><th>offer Price</th><th>offer Description</th><th>Update</th><th>Remove</th></tr>";
			// changed
			String query = "select * from offers";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String offer_id = Integer.toString(rs.getInt("id"));
				String offer_code = rs.getString("offerCode");
				String offer_name = rs.getString("offerName");
				// changed
				String offer_price = rs.getString("offerPrice");
				String offer_description = rs.getString("offerDesc");

				output += "<tr><td>" + offer_id + "</td>";
				output += "<td>" + offer_code + "</td>";
				output += "<td>" + offer_name + "</td>";
				// changed
				output += "<td>" + "Rs." + offer_price + "</td>";
				output += "<td>" + offer_description + "</td>";

				/*
				 * output += "<td><input name='btnUpdate' type='button' value='Update'</td>" +
				 * "<td><form method='post' action='items.jsp'>" +
				 * "<input name='btnRemove' id='btnRemove' type='submit' value='Remove'>" +
				 * "<input name='itemID' type='hidden' value='" + offer_id + "'>" +
				 * "</form></td></tr>";
				 */

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"
						+ offer_id + "' >Remove</button></td></tr>";

			}
			con.close();
			output += "</tabel>";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while reading the Posts";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// change
	public String updatePost(String ID, String offerCode, String offerName, String offerPrice,
			String offerDesc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			// LocalDate date= LocalDate.now();
			// LocalTime time= LocalTime.now();
			// change
			String query = "UPDATE offers SET offerCode=?,offerName=?,offerPrice=?,offerDesc=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, offerCode);
			preparedStmt.setString(2, offerName);
			// change
			preparedStmt.setString(3, offerPrice);
			preparedStmt.setString(4, offerDesc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			// output = "Updated successfully";

			String newProudct = readOffer();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the offer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteOffer(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			// change
			String query = "delete from offers where id = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// output = "Deleted successfully";
			String newProudct = readOffer();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			// output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the offer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
