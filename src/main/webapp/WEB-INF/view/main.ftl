<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
欢迎 ${user}, 查看 <a href="${springMacroRequestContext.contextPath}/files">文件列表</a> <br>
<a href="${springMacroRequestContext.contextPath}/logout">退出</a>
<br>
${msg!""}
<br>
<form action="${springMacroRequestContext.contextPath}/upload" method="post" enctype="multipart/form-data">
    <div>
        <label>
            文件名:
            <input type="text" name="filename">
        </label>
    </div>
    <div>
        <label>
            文件:
            <input type="file" name="file">
        </label>
    </div>
    <input type="submit">
</form>
</body>
</html>