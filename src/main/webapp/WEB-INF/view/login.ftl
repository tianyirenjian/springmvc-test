<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
${msg!""}
<form method="post" action="${springMacroRequestContext.contextPath}/login">
    <div>
        <label>
            用户名:
            <input type="text" name="name"/>
        </label>
    </div>
    <div>
        <label>
            密码:
            <input type="password" name="pass">
        </label>
    </div>
    <div>
        <input type="submit">
    </div>
</form>
</body>
</html>
