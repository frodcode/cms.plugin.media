<%@ page import="org.imgscalr.Scalr; frod.media.image.thumbnail.adjustment.resize.ResizeAdjustment; frod.media.ImageServiceController" contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Media</title>
</head>
<body>
<h1>All media</h1>
<table>
    <tr>
        <th>ID</th>
        <th>Main image title</th>
        <th>Thumbnail</th>
        <th>Link</th>
    </tr>
    <g:each in="${allMedia}" var="media">
        <tr>
            <g:set var="imgLink" value="${createLink([controller: 'imageService', id: keyParser.getUrlPart(media.mainImage, [new ResizeAdjustment(200, 200, Scalr.Mode.AUTOMATIC)])])}"/>
            <td>${media.id}</td>
            <td>${media.mainImage.title}</td>
            <td><g:img uri="${imgLink}"></g:img></td>
            <td><g:link uri="${imgLink}">${imgLink}</g:link></td>
        </tr>
    </g:each>

    <g:form controller="Media" action="upload" method="post" enctype="multipart/form-data">
        <span>Add new: </span><input type="file" name="file"/>
        <input type="submit" value="Upload" />
    </g:form>
</table>
</body>
</html>