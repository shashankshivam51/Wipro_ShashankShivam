<%@ page import="java.util.*,java.io.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Contact Manager</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>
    body {
      background-color: #000;
      color: #f5c518;
      font-family: "Segoe UI", sans-serif;
      margin: 0;
      padding: 0;
    }

    header {
      text-align: center;
      background-color: #111;
      padding: 20px;
      font-size: 28px;
      font-weight: bold;
      border-bottom: 2px solid #f5c518;
    }

    .container {
      max-width: 800px;
      margin: 30px auto;
      background: #1a1a1a;
      padding: 25px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(255, 215, 0, 0.3);
    }

    h2 {
      text-align: center;
      margin-bottom: 15px;
      color: #ffd700;
    }

    button, input[type=submit] {
      background-color: #f5c518;
      color: #000;
      padding: 8px 15px;
      border: none;
      border-radius: 5px;
      font-weight: 600;
      cursor: pointer;
    }

    button:hover, input[type=submit]:hover {
      background-color: #ffdb4d;
    }

    form {
      margin-top: 15px;
      background-color: #000;
      padding: 15px;
      border-radius: 10px;
    }

    label {
      display: block;
      margin-top: 10px;
    }

    input[type=text], input[type=email] {
      width: 96%;
      padding: 8px;
      margin-top: 5px;
      border: 1px solid #f5c518;
      border-radius: 5px;
      background-color: #111;
      color: #fff;
    }

    .contact-card {
      background: #222;
      border: 1px solid #f5c518;
      padding: 12px;
      border-radius: 8px;
      margin: 12px 0;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .msg {
      text-align: center;
      margin-top: 10px;
      font-weight: bold;
    }

    .actions form {
      display: inline;
    }

    @media(max-width:600px) {
      .container { width: 90%; }
      .contact-card { flex-direction: column; align-items: flex-start; }
    }
  </style>
</head>
<body>

<%
  
  class Contact {
    String name, email, phone;
    Contact(String name, String email, String phone) {
      this.name = name; this.email = email; this.phone = phone;
    }
  }

  List<Contact> contactList = (List<Contact>) session.getAttribute("contacts");
  if (contactList == null) {
    contactList = new ArrayList<>();
    session.setAttribute("contacts", contactList);
  }

  String action = request.getParameter("action");
  String message = "";

  if ("add".equals(action)) {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");

    if (name == null || name.trim().isEmpty() || email == null || email.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
      message = " Please fill in all fields correctly!";
    } else {
      contactList.add(new Contact(name, email, phone));
      session.setAttribute("contacts", contactList);
      message = " Contact added successfully!";
    }
  } else if ("delete".equals(action)) {
    int index = Integer.parseInt(request.getParameter("index"));
    if (index >= 0 && index < contactList.size()) {
      contactList.remove(index);
      message = " Contact deleted successfully.";
    }
  } else if ("edit".equals(action)) {
    int index = Integer.parseInt(request.getParameter("index"));
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phone = request.getParameter("phone");
    if (index >= 0 && index < contactList.size()) {
      contactList.set(index, new Contact(name, email, phone));
      message = " Contact updated successfully!";
    }
  }
%>

  <header>Assignment 16 </header>

  <div class="container">
    <h2>Manage Your Contacts</h2>

    <form method="post">
      <input type="hidden" name="action" value="add">
      <label>Name:</label>
      <input type="text" name="name" required>
      <label>Email:</label>
      <input type="email" name="email" required>
      <label>Phone:</label>
      <input type="text" name="phone" required>
      <input type="submit" value="Add Contact">
    </form>

    <% if (!message.isEmpty()) { %>
      <div class="msg"><%= message %></div>
    <% } %>

    <div id="contactList">
      <% if (contactList.isEmpty()) { %>
        <p style="text-align:center; color:gray;">No contacts added yet.</p>
      <% } else { 
        int i = 0;
        for (Contact c : contactList) { %>
          <div class="contact-card">
            <div>
              <strong><%= c.name %></strong><br>
              <%= c.email %><br>
              <%= c.phone %>
            </div>
            <div class="actions">
              <!-- Edit Form -->
              <form method="post" style="display:inline;">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="index" value="<%= i %>">
                <input type="text" name="name" value="<%= c.name %>" required>
                <input type="email" name="email" value="<%= c.email %>" required>
                <input type="text" name="phone" value="<%= c.phone %>" required>
                <input type="submit" value="Update">
              </form>

              
              <form method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="index" value="<%= i %>">
                <input type="submit" value="Delete">
              </form>
            </div>
          </div>
      <% i++; } } %>
    </div>
  </div>
</body>
</html>
