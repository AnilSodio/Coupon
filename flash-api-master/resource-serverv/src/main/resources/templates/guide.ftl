<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>
    	<#if sm??>
	    	${sm.title!}
    	</#if>
    </title>
     <style>
        html, body { margin: 0; padding: 0; font-size: 14px;}
        img { max-width: 100%;}
        .wraper { padding: 15px 15px 30px; line-height: 1.6;}
        .cont-title { margin: 0; padding-top: 10px; font-size: 1.5rem; font-weight: bold; text-align: center; color: #12b7f5;}
        .cont-time { color: #a9a9a9; text-align: center; padding-bottom: 10px; }
    </style>
    
</head>
<body>

<div class="wraper">
	<#if sm??>
	   	${sm.content!!}
	</#if>
</div>

</body>
</html>