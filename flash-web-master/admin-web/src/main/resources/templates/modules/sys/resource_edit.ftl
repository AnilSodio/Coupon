<#include "/include/taglib.ftl" >
<br/>
<form class="form-horizontal" action="${ctx}/sys/resource/save" method="post" id="inputForm" name="inputForm">
    <input type="hidden" name="id" value="${info.id!''}"/>
    <div class="form-body">
        <div class="form-group">
            <label class="col-md-4 control-label"><@spring.message code="sys.msg.messageKey"></@spring.message>:</label>
            <div class="col-md-6">
                <label class="control-label">${info.name!''}</label> <input type="hidden" name="id" value="${info.name!''}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"><@spring.message code="sys.msg.language"></@spring.message>:</label>
            <div class="col-md-6">
                <label class="control-label">${info.language!''}</label> <input type="hidden" name="id" value="${info.language!''}"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"><@spring.message code="sys.msg.text"></@spring.message>:</label>
            <div class="col-md-6">
                <input id="text" name="text" value="${info.text!''}" class="form-control required"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-md-4 control-label"><@spring.message code="sys.msg.functionModule"></@spring.message>:</label>
            <div class="col-md-6">
                <input id="functionModule" name="functionModule" value="${info.functionModule!''}" class="form-control required"/>
            </div>
        </div>
        <div class="form-actions">
            <div class="row">
                <div class="col-md-offset-4 col-md-6">
                    <input id="btnSubmit" class="btn btn-primary" type="submit"
                           value="<@spring.message code="form.save"></@spring.message>"/>&nbsp;
                </div>
            </div>
        </div>
    </div>
</form>
<script>
    $(document).ready(function () {
        jQuery("#inputForm").validate({
            rules: {}
        });

    });
</script>