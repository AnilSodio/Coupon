package vc.thinker.cabbage.web.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.springframework.beans.factory.annotation.Value;

public class Sitemesh3Filter extends ConfigurableSiteMeshFilter {
    @Value("${adminPath}")
    private String adminPath;

    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*", "/layouts/default.ftl");
        builder.addExcludedPath("/login");
        builder.addExcludedPath("/static/*");
        builder.addExcludedPath(adminPath + "/sys/resource/edit");
    }
}
