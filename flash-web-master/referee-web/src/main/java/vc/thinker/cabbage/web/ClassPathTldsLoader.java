package vc.thinker.cabbage.web;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

public class ClassPathTldsLoader {
	  
     
    final private List<String> classPathTlds;
 
    public ClassPathTldsLoader(String... classPathTlds) {
        super();
        this.classPathTlds = Arrays.asList(classPathTlds);
    }
 
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;
     
    @PostConstruct
    public void loadClassPathTlds() {

        freeMarkerConfigurer.getTaglibFactory().setClasspathTlds(classPathTlds);
    }
}
