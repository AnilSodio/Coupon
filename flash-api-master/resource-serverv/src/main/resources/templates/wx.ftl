<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${info.appName!}</title>
    <style>
        body,html,p { margin: 0; padding: 0; font-size: 10pt; line-height: 1.6; color: #222; font-family: "PingFangSC-Light";}
        .about-style { padding: 20px;}
        .about-style p { padding-bottom: 6pt;}
        h2 { font-size: 14pt; font-weight: bold; margin: 0; padding: 10pt 0 6pt;}
    </style>
    
    
    <script type="text/javascript">
	    var u = navigator.userAgent;
	    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
	    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	    if (isAndroid) {
	        location.href = "${info.androidDownloadUrl!}";
	    } else if (isiOS) {
	        location.href = "${info.iosDownloadUrl!}";
	    }
	</script>
	
	<#-- 
		<script type="text/javascript">
		    var u = navigator.userAgent;
		    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
		    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
		    if (isAndroid) {
		        location.href = "https://www.pgyer.com/zeNp";
		    } else if (isiOS) {
		        location.href = "https://itunes.apple.com/us/app/%E6%89%BF%E8%BF%9C%E5%8D%95%E8%BD%A6/id1294533160?l=zh&ls=1&mt=8";
		    }
		    
		</script>
	-->
	
</head>
<body>
<img src="/img/io_no.jpg" style="width: 100%;"/>
</body>
</html>