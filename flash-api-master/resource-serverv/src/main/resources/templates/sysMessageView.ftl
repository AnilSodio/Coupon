<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="format-detection"content="telephone=no" />
    <title></title>
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
    <h2 class="cont-title">
    	<#if seller?? >
    		${seller.sellerName!!}
    	</#if>
    </h2>
    <div class="cont-time">${sm.createTime?string('yyyy年MM月dd日 HH:mm')}</div>
    <p>${sm.title!!}</p>
   	${sm.content!!}
</div>
</body>
</html>