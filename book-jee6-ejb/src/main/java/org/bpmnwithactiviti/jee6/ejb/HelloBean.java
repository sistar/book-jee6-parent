package org.bpmnwithactiviti.jee6.ejb;

import javax.ejb.Stateless;

import org.bpmnwithactiviti.jee6.task.Hello;

@Stateless
public class HelloBean implements Hello {



    public String sayHello(String name) {
        String res = "hi " + name;
		System.out.println("hi " + name);
        return res;
	}

}
