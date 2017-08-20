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
  <a class="active" href="audio">AUDIO</a>
  <a href="textaudio">TEXT-SPEECH</a>
</div>

	<div style="padding-left:16px" align="center">
	<br><br>
  <h3>Enter the audio message you would like to send and press the send button .</h2>
  
 <form method='POST' enctype='multipart/form-data' >
 	<input type='file' name='userFile'><br><br>
  	<input type="submit" name="sendAudioButton" value="SEND">
 </form>
   <label name="messagelabel">Audio has been successfully sent to the raspberry pi</label>
</div>
    </body>
</html>
