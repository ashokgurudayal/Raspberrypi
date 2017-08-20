<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/resources/style.css" rel="stylesheet" >
        <title>Raspberry Pi</title>
    </head>
    <body>
    
    <h1 align="center">
     <image src="${pageContext.request.contextPath}/resources/raspberry-pi-logo.png" alt="raspberrypilogo"  style=" max-width:50px; max-height:50px;">
    </image>
    RASPBERRY PI WEB</h1>
     <div class="topnav">
     
  <a  href="home">HOME</a>
  <a href="text">TEXT</a>
  <a  href="audio">AUDIO</a>
  <a class="active" href="textaudio">TEXT-SPEECH</a>
</div>
	<br><br>
	<div style="padding-left:16px" align="center">
	 <h3>Enter the text message you would like to send and speak out from Raspberry Pi</h2>
  
  <form method="post" action="textaudio">
  <label for="textMsg"> TEXT </label>
  <input name="textentered" type= "text" id="textMsg"><br><br>
  <input type="submit" value="SEND" >
  </form>
  <br>
  <% String s = (String) request.getAttribute("sendMessage");%>
  <% if (s.contains("problem")){ %>
  <font color="red">
  ${sendMessage} 
  </font>
  <%}else {%>
  <font color="green">
  ${sendMessage} 
  <%} %>
</div>
    </body>
</html>
