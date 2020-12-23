<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <title>${appName!}</title>

    <style type="text/css">
        body, html {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑"; background-color: #f5f5f5; font-size: 15px;}
        .wraper { width: 100%; height: 100%; box-sizing: border-box; flex-direction: column; color: #000;
             line-height: 2;
            text-align: center;}

        .journey-map { padding-bottom: 1rem;}
        .journey-map img { width: 100%;}




        .journey-footer { flex: 1; margin: 1.2rem 0 1rem;}
        .sysCode { color: #888;}

        @media screen and (max-width: 370px) {
            body, html { font-size: 12px;}
        }
        @media screen and (max-width: 380px) {
            body, html { font-size: 16px;}
        }

        .ft-down { position: fixed; z-index: 10; box-shadow: 0 -2px 2px 2px #eee; bottom: 0; width: 100%; left: 0; box-sizing: border-box; padding: 0.6rem; display: flex;
            background-color: #fff;align-items: center;}
        .ft-logo { flex: 3; display: flex; align-items: center;}
        .ft-link { flex: 2; text-align: right; display: flex; align-items: center;justify-content: flex-end;}
        .ft-logo img { width: 35vw}
        .ft-link img { width: 25vw}
        .inv-body { padding-bottom: 10vh; font-size: 1.2rem;}
        .fz-28 { font-size: 1.6rem;}

    </style>

</head>
<body>

<div class="wraper">

    <div class="journey-map">
        <img src="/img/invitation.png" />
    </div>

    <div class="journey-wrap">

        <div>Referral a good friend to you</div>
        <div class="inv-body">${amount!}point integral</div>

        <div class="journey-footer bor-top">
            <div class="sysCode">Your Referral Code</div>
            <div class="journey-ft-main">
                <text class="fz-28">${inviteCode!}</text>
            </div>

        </div>

    </div>


    <div class="ft-down">
        <div class="ft-logo"><img src="/img/logo_icon.png" /></div>
	        <a class="ft-link" href="${settingBO.appDownloadUrl!}"><img src="/img/download_n.png" /></a>
    </div>


</div>



</body>
</html>