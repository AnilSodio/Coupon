package vc.thinker.cabbage.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class Sitemesh3Filter extends ConfigurableSiteMeshFilter {

	   @Override
	   protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
	      builder.addDecoratorPath("/*","/layouts/default.ftl");
	      builder.addExcludedPath("/login");
	      builder.addExcludedPath("/static/*");
	   }
}
