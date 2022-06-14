package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerManage {

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

	public String createCustomer(String customerCode, String customerName, String customerPrice, String customerDesc) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error";
			}
			// LocalDate date= LocalDate.now();
			// LocalTime time= LocalTime.now();
			String query = " insert into customers(customerCode,customerName,customerPrice,customerDesc)"
					+
					" values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, customerCode);
			ps.setString(2, customerName);
			ps.setString(3, customerPrice);
			ps.setString(4, customerDesc);

			ps.execute();
			con.close();

			String newProudct = readCustomer();

			output = "Insert Success";
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
		} catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String readCustomer() {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "123";
			}
			output = "<table bcustomer='1'><tr><th>customer ID</th><th>customer Code</th><th>customer Name</th><th>customer Price</th><th>customer Description</th><th>Update</th><th>Remove</th></tr>";
			// changed
			String query = "select * from customers";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next()) {
				String customer_id = Integer.toString(rs.getInt("id"));
				String customer_code = rs.getString("customerCode");
				String customer_name = rs.getString("customerName");
				// changed
				String customer_price = rs.getString("customerPrice");
				String customer_description = rs.getString("customerDesc");

				output += "<tr><td>" + customer_id + "</td>";
				output += "<td>" + customer_code + "</td>";
				output += "<td>" + customer_name + "</td>";
				// changed
				output += "<td>" + "Rs." + customer_price + "</td>";
				output += "<td>" + customer_description + "</td>";

				/*
				 * output += "<td><input name='btnUpdate' type='button' value='Update'</td>" +
				 * "<td><form method='post' action='items.jsp'>" +
				 * "<input name='btnRemove' id='btnRemove' type='submit' value='Remove'>" +
				 * "<input name='itemID' type='hidden' value='" + customer_id + "'>" +
				 * "</form></td></tr>";
				 */

				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						+ "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"
						+ customer_id + "' >Remove</button></td></tr>";

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
	public String updatePost(String ID, String customerCode, String customerName, String customerPrice,
			String customerDesc) {
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
			String query = "UPDATE customers SET customerCode=?,customerName=?,customerPrice=?,customerDesc=? WHERE id=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, customerCode);
			preparedStmt.setString(2, customerName);
			// change
			preparedStmt.setString(3, customerPrice);
			preparedStmt.setString(4, customerDesc);
			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			// output = "Updated successfully";

			String newProudct = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteCustomer(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			// change
			String query = "delete from customers where id = ?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			// output = "Deleted successfully";
			String newProudct = readCustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";

		} catch (Exception e) {
			// output = "Error while deleting the item.";
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
