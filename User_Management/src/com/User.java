package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {

	public Connection connect()
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.cj.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paflab", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
		}



	public String insertUser(String name, String email, String password, String nic) {
		
		 String output = "";
		 
		 try {
		
		Connection con = connect();
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		// create a prepared statement
		String query = "insert into user (userID, userName, userEmail, userPassword, userNIC)"
				 + " values (?, ?, ?, ?, ?)"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		// binding values
		preparedStmt.setInt(1, 0); 
		preparedStmt.setString(2, name); 
		preparedStmt.setString(3, email); 
		preparedStmt.setString(4, password); 
		preparedStmt.setString(5, nic);
		
		System.out.println(name);
		 
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newUsers = readUsers();
		 output =  "{\"status\":\"success\", \"data\": \"" + 
				 newUsers + "\"}"; 
		 } 

		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";  
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}


	public String readUsers()
	{ 
			 String output = ""; 
			try
			 { 
			 Connection con = connect(); 
			 if (con == null) 
			 { 
			 return "Error while connecting to the database for reading."; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User Name</th>" 
			 +"<th>User Email</th><th>User Password</th>"
			 + "<th>User NIC</th>" 
			 + "<th>Update</th><th>Remove</th></tr>"; 
			 String query = "select * from user"; 
			 java.sql.Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
			 String userID = Integer.toString(rs.getInt("userID")); 
			 String userName = rs.getString("userName"); 
			 String userEmail = rs.getString("userEmail"); 
			 String userPassword = rs.getString("userPassword"); 
			 String userNIC = rs.getString("userNIC"); 
			 // Add a row into the html table
			 output += "<tr><td><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + userID + "'>"
					 + userName + "</td>";
			 output += "<td>" + userEmail + "</td>"; 
			 output += "<td>" + userPassword + "</td>"; 
			 
			 output += "<td>" + userNIC + "</td>";
			 // buttons
			 output += "<td><input name='btnUpdate' " 
			 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-userid='" + userID + "'></td>"
			 + "<td><form method='post' action='User.jsp'>"
			 + "<input name='btnRemove' " 
			 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-userid='" + userID + "'>"
			 + "<input name='hidUserDDelete' type='hidden' " 
			 + " value='" + userID + "'>" + "</form></td></tr>"; 
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
			 } 
			catch (Exception e) 
			 { 
			 output = "Error while reading the users."; 
			 System.err.println(e.getMessage()); 
			 } 
			return output; 
		}

	public String deleteUser(String userID)
	{ 
	 String output = ""; 
	try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for deleting."; 
	 } 
	 // create a prepared statement
	 String query = "delete from user where userID=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(userID)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newUsers = readUsers();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newUsers + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while deleting the User.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
		}

	public String updateUser(String ID, String name, String email, String password, String nic)
	//4
	{
	String output = "";
	try {
	Connection conn = connect();
	if (conn == null) {
	return "Error while connecting to the database for updating.";
	}

	// create a prepared statement
	String query = "UPDATE user SET userName=?,userEmail=?,userPassword=?,userNIC=? WHERE userID=?";
	PreparedStatement preparedStmt = conn.prepareStatement(query);
	//binding values
	preparedStmt.setString(1, name);
	preparedStmt.setString(2, email);
	preparedStmt.setString(3, password);
	preparedStmt.setString(4, nic);
	preparedStmt.setInt(5, Integer.parseInt(ID));
	//execute the statement
	preparedStmt.execute();
	conn.close();
	String newUsers = readUsers();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newUsers + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the users.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}

}
