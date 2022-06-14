package model;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class ProductManage {
	
	private Connection connect()
	{
		Connection con=null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide Correct Database Details
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}
	
	public String createProduct(String productCode,String productName, String productPrice, String productDesc) {
		
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			//LocalDate date= LocalDate.now();
			//LocalTime time= LocalTime.now();
			String query = " insert into products(productCode,productName,productPrice,productDesc)"
							+
							" values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, productCode);
			ps.setString(2, productName);
			ps.setString(3, productPrice);
			ps.setString(4, productDesc);
			
			ps.execute();
			con.close();
			
			String newProudct = readProducts();
			
			output="Insert Success";
			output="{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
		}
		catch (Exception e) {
			// TODO: handle exception
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readProducts() {
		String output="";
		
		try {
			Connection con=connect();
			
			if(con==null)
			{
				return "Error";
			}
			output="<table border='1'><tr><th>Product ID</th><th>Product Code</th><th>Product Name</th><th>Product Price</th><th>Product Description</th><th>Update</th><th>Remove</th></tr>";
			//changed
			String query="select * from products";
			Statement st= con.createStatement();
			ResultSet rs= st.executeQuery(query);
			
			while(rs.next())
			{
				String Product_id= Integer.toString(rs.getInt("id"));
				String Product_code= rs.getString("productCode");
				String Product_name = rs.getString("productName");
				//changed
				String Product_price = rs.getString("productPrice");
				String Product_description = rs.getString("productDesc");
				
				output +="<tr><td>"+Product_id+"</td>";
				output +="<td>"+Product_code+"</td>";
				output +="<td>"+Product_name+"</td>";
				//changed
				output +="<td>"+"Rs."+Product_price+"</td>";
				output +="<td>"+Product_description+"</td>";
				
				
				/*
				 * output += "<td><input name='btnUpdate' type='button' value='Update'</td>" +
				 * "<td><form method='post' action='items.jsp'>" +
				 * "<input name='btnRemove' id='btnRemove' type='submit' value='Remove'>" +
				 * "<input name='itemID' type='hidden' value='" + Product_id + "'>" +
				 * "</form></td></tr>";
				 */
				
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
						 + "<td><button class='btnRemove btn btn-danger' name='btnRemove' id ='btnRemove' value='"+ Product_id +"' >Remove</button></td></tr>";
				 
				
			}
			con.close();
			output+="</tabel>";
		} catch (Exception e) {
			// TODO: handle exception
			output="Error while reading the Posts";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	//change
	public String updatePost(String ID, String productCode, String productName, String productPrice, String productDesc) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 //LocalDate date= LocalDate.now();
	 //LocalTime time= LocalTime.now();
	 //change
	 String query = "UPDATE products SET productCode=?,productName=?,productPrice=?,productDesc=? WHERE id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setString(1, productCode); 
	 preparedStmt.setString(2, productName); 
	 //change
	 preparedStmt.setString(3, productPrice); 
	 preparedStmt.setString(4, productDesc); 
	 preparedStmt.setInt(5, Integer.parseInt(ID)); 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 //output = "Updated successfully"; 
	 
	 String newProudct = readProducts();
	 output="{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
	 
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	
	public String deleteProduct(String itemID) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 //change
	 String query = "delete from products where id = ?"; 
	 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(itemID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 
	 //output = "Deleted successfully"; 
	 String newProudct = readProducts();
	 output="{\"status\":\"success\", \"data\": \"" + newProudct + "\"}";
	 
	 } 
	 catch (Exception e) 
	 { 
	 //output = "Error while deleting the item."; 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the order.\"}";
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
}
