package org.bpmnwithactiviti.jee6.task;

import javax.ejb.EJB;
import javax.inject.Named;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Named
public class HelloDelegate implements JavaDelegate {

    @EJB(lookup = "java:global/book-jee6-ejb/HelloBean")
    private Hello helloBean;
    
    Log log = LogFactory.getLog(this.getClass());
    
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        //helloBean.sayHello((String) execution.getVariable("name"));
       log.info((String) execution.getVariable("name"));
    }

}
