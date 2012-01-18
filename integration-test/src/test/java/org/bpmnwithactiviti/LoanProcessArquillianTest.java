package org.bpmnwithactiviti;


import org.activiti.engine.RuntimeService;
import org.bpmnwithactiviti.jee6.ejb.HelloBean;
import org.bpmnwithactiviti.jee6.task.Hello;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class LoanProcessArquillianTest {

    @Deployment(name = "ejb",order = 1)
    public static Archive<?> createTestArchive() throws IOException {
        return ShrinkWrap
                .create(JavaArchive.class, "ejb.jar").
                        addClass(HelloBean.class)
                .addClass(Hello.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }

    @Deployment(name = "process", testable = true, order = 2)
    public static Archive<?> createProcessTestArchive() throws IOException {
        return ShrinkWrap
                .create(JavaArchive.class, "activiti-process.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("processes.xml"))
                .addClass(Hello.class)
                .addAsResource("jee6.bpmn20.xml");
    }


    @Inject
    private RuntimeService runtimeService;



    @EJB(lookup = "java:global/ejb/HelloBean")
    private Hello helloBean;

    @Test
    @OperateOnDeployment("process")
    public void testThatEjbIsDeployed() {
        assertThat(helloBean, notNullValue());
        helloBean.sayHello("world");
        // assertThat(runtimeService,notNullValue());
    }
}
