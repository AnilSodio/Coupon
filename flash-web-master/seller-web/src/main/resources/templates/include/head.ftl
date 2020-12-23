<#include "/include/taglib.ftl" >
<meta http-equiv="Cache-Control" content="no-store" /><meta http-equiv="Pragma" content="no-cache" /><meta http-equiv="Expires" content="0" />
<meta name="author" content="http://www.thinker.vc/"/><meta http-equiv="X-UA-Compatible" content="IE=7,IE=9,IE=10" />


<script src="${ctxStatic}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>

<link href="${pubStatic}/wgt/jquery-validation/1.11.1/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${pubStatic}/wgt/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${pubStatic}/wgt/jquery-validation/1.11.1/jquery.validate.method.min.js" type="text/javascript"></script>


<!-- BEGIN GLOBAL MANDATORY STYLES -->
<!--<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css"/>-->
<link href="${ctxStatic}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css"/>
<link href="${pubStatic}/wgt/jquery-select2/3.4/select2.css" type="text/css" rel="stylesheet" />
<!-- END GLOBAL MANDATORY STYLES -->
<#if lang.getLocale() == 'en'>
<script src="${ctxStatic}/assets/global/plugins/jquery-validation/js/localization/messages_en.js" type="text/javascript"></script>
</#if>


<!-- BEGIN PAGE STYLES -->
<link href="${ctxStatic}/assets/admin/pages/css/tasks.css" rel="stylesheet" type="text/css"/>
<!-- END PAGE STYLES -->
<!-- BEGIN THEME STYLES -->
<link href="${ctxStatic}/assets/global/css/components.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/global/css/plugins.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/admin/layout2/css/layout.css" rel="stylesheet" type="text/css"/>
<link href="${ctxStatic}/assets/admin/layout2/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
<link href="${ctxStatic}/assets/admin/layout2/css/custom.css" rel="stylesheet" type="text/css"/>
<!-- END THEME STYLES -->


<script src="${pubStatic}/bootstrap/bsie/js/bootstrap-ie.min.js" type="text/javascript"></script><![endif]-->
<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<#if lang.getLocale() == 'en'>
    <script src="${pubStatic}/wgt/My97DatePicker/en_WdatePicker.js" type="text/javascript"></script>
<#else>
    <script src="${pubStatic}/wgt/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</#if>
<script src="${pubStatic}/wgt/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>






<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN CORE PLUGINS -->
<!--[if lt IE 9]>
<script src="${ctxStatic}/assets/global/plugins/respond.min.js"></script>
<script src="${ctxStatic}/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->

<!-- IMPORTANT! Load jquery-ui-1.10.3.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${ctxStatic}/assets/global/plugins/jquery-ui/jquery-ui-1.10.3.custom.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->

<!-- IMPORTANT! fullcalendar depends on jquery-ui-1.10.3.custom.min.js for drag & drop support -->
<script src="${ctxStatic}/assets/global/plugins/jquery-easypiechart/jquery.easypiechart.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="${ctxStatic}/assets/global/scripts/metronic.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/admin/layout/scripts/layout.js" type="text/javascript"></script>
<script src="${ctxStatic}/assets/admin/layout2/scripts/demo.js" type="text/javascript"></script>

<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {
   Metronic.init(); // init metronic core componets
   Layout.init(); // init layout
   Demo.init(); // init demo features
});
</script>
<!-- END JAVASCRIPTS -->