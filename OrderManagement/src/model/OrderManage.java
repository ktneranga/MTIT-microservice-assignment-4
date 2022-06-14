package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class OrderManage {

	private Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide Correct Database Details
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test?useTimezone=true&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}

	public String createOrder(String orderCode, String orderName, String orderPrice, String orderDesc) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error";
			}
			// LocalDate date= LocalDate.now();
			// LocalTime time= LocalTime.now();
			String query = " insert into orders(orderCode,orderName,orderPrice,orderDesc)"
					+
					" values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, orderCode);
			ps.setString(2, orderName);
			ps.setString(3, orderPrice);
			ps.setString(4, orderDesc);

			ps.execute();
			con.close();

			String newProudct = readOrders();

			output = "Insert Success";
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
		} catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readOrders() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "123";
			}
			output = "<table border='1'><tr><th>order ID</th><th>order Code</th><th>order Name</th><th>order Price</th><th>order Description</th><th>Update</th><th>Remove</th></tr>";
			// changed
			String query = "select * from orders";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String order_id = Integer.toString(rs.getInt("id"));
				String order_code = rs.getString("orderCode");
				String order_name = rs.getString("orderName");
				// changed
				String order_price = rs.getString("orderPrice");
				String order_description = rs.getString("orderDesc");

				output += "<tr><td>" + order_id + "</td>";
				output += "<td>" + order_code + "</td>";
				output += "<td>" + order_name + "</td>";
				// changed
				output += "<td>" + "Rs." + order_price + "</td>";
				output += "<td>" + order_description + "</td>";

				/*
				 * output += "<td><input name='btnUpdate' type='button' value='Update'</td>" +
				 * "<td><form method='post' action='items.jsp'>" +
				 * "<input name='btnRemove' id='btnRemove' type='submit' value='Remove'>" +
				 * "<input name='itemID' type='hidden' value='" + order_id + "'>" +
				 * "</form></td></tr>";
				 */

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"
						+ order_id + "' >Remove</button></td></tr>";

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
	public String updatePost(String ID, String orderCode, String orderName, String orderPrice,
			String orderDesc) {
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
			String query = "UPDATE orders SET orderCode=?,orderName=?,orderPrice=?,orderDesc=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, orderCode);
			preparedStmt.setString(2, orderName);
			// change
			preparedStmt.setString(3, orderPrice);
			preparedStmt.setString(4, orderDesc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			// output = "Updated successfully";

			String newProudct = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteOrder(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			// change
			String query = "delete from orders where id = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// output = "Deleted successfully";
			String newProudct = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			// output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
