<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Prog.kiev.ua</title>
    </head>
    <body>
        <div align="center">
            <h1>Your photo id is: ${photoId}</h1>

            <input type="submit" value="Delete Photo"  onclick="window.location.href='delete/${photoId}';" />
            <input type="submit" value="Go Back" onclick="window.location.href='';" />

            <br/><br/><img src="show/${photoId}" />
        </div>
    </body>
</html>
