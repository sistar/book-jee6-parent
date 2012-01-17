package ws.impl;

import org.bpmnwithactiviti.jee6.task.Hello;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;


@WebService(name = "wsName", targetNamespace = "http://ws/",serviceName = "helloService")
@Stateless
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class CxfEjb {
    @Resource
    private WebServiceContext ctx;


    @EJB(lookup = "java:global/book-jee6-ejb/HelloBean")
    Hello helloBean;

    @WebMethod
    public String sayHello(String name) {
        System.out.println("\n\t [CXF_EJB] sayHello() invoked : Hello, Mr. " + name);
        return "Hello, Mr. " + helloBean.sayHello(name);
    }
}
