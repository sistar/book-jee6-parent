package org.bpmnwithactiviti.jee6.task;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import javax.ejb.EJB;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
public class HelloDelegate implements JavaDelegate {

    Logger log = Logger.getLogger(this.getClass().getName());

    @EJB
    private Hello helloBean;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        helloBean.sayHello((String) execution.getVariable("name"));
        execution.setVariable("name", "Kermit");

        log.info(" process variable - name:" + execution.getVariable("name"));
    }

}
