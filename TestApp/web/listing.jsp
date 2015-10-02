

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="MyPackage.Contact"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Contact list</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
    <script type="text/javascript" src="js/bootstrap.js"></script>
    </head>
    <body>
        <div class="container">
        <div id = "tableDiv" class="col-md-12">
            <table id="table228" class="table-content table-striped table">
    <tr>
                <th id="th1">ID</th>
                <th id="th2">Name</th>
                <th id="th3">SureName</th>
                <th id="th4">Login</th>
                <th id="th5">Email</th>
                <th id="th6">PhoneNumber</th>
            </tr>
    <%
                List <Contact> allItems =  null; 
                allItems = (ArrayList<Contact>)request.getAttribute("items");
                for(Contact item : allItems)
                {%>
                <tr>
                    <td><%=item.getID()%></td>
                    <td><%=item.getName()%></td>
                    <td><%=item.getSureName()%></td>
                    <td><%=item.getLogin()%></td>
                    <td><%=item.getEmail()%></td>
                    <td><%=item.getPhoneNumber()%></td>
                </tr>
                <%    
                }
                %>
                
    </table>
        </div>
        </div>
    </body>
