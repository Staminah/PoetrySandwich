<%-- 
    Document   : login
    Created on : 22 févr. 2018, 13:26:20
    Author     : anthony.fleury
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="POST" action="j_security_check">                
            Username: <input type="text" name="j_username" />   
            Password: <input type="password" name="j_password" />  
            <input type="submit" value="Login" />              
            <input type="reset" value="Reset" />          
        </form>        
    </body>
</html>
