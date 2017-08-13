<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>Spring Photos</title>
</head>
<body>
	<div align="center">
		<h2>Select an image to upload to local storage:</h2>
		<form action="addPhoto" enctype="multipart/form-data" method="POST">
			Select Image: <input type="file" name="photo"> <input
				type="submit" value="Upload" />
		</form>

		<form action="view" method="POST">
			<h2>Storage operations:</h2>
			Image ID: <input type="text" name="photoId"> <input
				type="submit" value="Search by ID" /> <input type="button"
				value="Show All"
				onclick="window.location.href='showAll'; return false;" />

		</form>
	</div>
</body>
</html>
