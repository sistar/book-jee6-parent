package ws.impl;

import org.bpmnwithactiviti.jee6.task.Hello;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.WebServiceContext;

/**
 * Created by IntelliJ IDEA.
 * User: RSI
 * Date: 22.12.11
 * Time: 14:18
 * To change this template use File | Settings | File Templates.
 */
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
