<%@ page contentType="text/html;charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Asynchronous file Upload in Java Web Application</title>

</head>
<body>
<form id="upload-form" action="TestServlet" method="post" enctype="multipart/form-data" >
    <input type="file" id="file" name="file" />
    <span id="upload-error" class="error">${uploadError}</span>
    <input type="submit" id="upload-button" value="upload"/>
</form>
</body>
</html>