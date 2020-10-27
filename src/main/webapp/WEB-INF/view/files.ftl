<html lang="zh-CN">
<head>
    <title>Title</title>
</head>
<body>
<ol>
    <#list files as file>
        <li>
            <a href="${springMacroRequestContext.contextPath}/download?file=${file?url("utf-8")}">${file}</a>
        </li>
    </#list>
</ol>
</body>
</html>
