package vc.thinker.cabbage.web.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.util.WebUtils;

public class SysWebUtils extends WebUtils {
	
	 public static void redirectToSavedRequest(ServletRequest request, ServletResponse response, String fallbackUrl)
	            throws IOException {
	        String successUrl = null;
	        boolean contextRelative = true;


	        if (successUrl == null) {
	            successUrl = fallbackUrl;
	        }

	        if (successUrl == null) {
	            throw new IllegalStateException("Success URL not available via saved request or via the " +
	                    "successUrlFallback method parameter. One of these must be non-null for " +
	                    "issueSuccessRedirect() to work.");
	        }

	        WebUtils.issueRedirect(request, response, successUrl, null, contextRelative);
	    }
}
