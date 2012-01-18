package org.bpmnwithactiviti;


import org.activiti.engine.RuntimeService;
import org.bpmnwithactiviti.jee6.ejb.HelloBean;
import org.bpmnwithactiviti.jee6.task.Hello;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class LoanArquillianTest {


    public static Archive<JavaArchive> createContractTestArchive() {
        return ShrinkWrap
                .create(JavaArchive.class, "contract.jar")
                .addClass(org.bpmnwithactiviti.jee6.task.Hello.class);
    }

    public static Archive<JavaArchive> createEjbTestArchive() {
        return ShrinkWrap
                .create(JavaArchive.class, "ejb.jar")
                .addClass(HelloBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
    }


    public static Archive<JavaArchive> createProcessTestArchive() {
        return ShrinkWrap
                .create(JavaArchive.class, "activiti-process.jar")
                .addClass(org.bpmnwithactiviti.jee6.task.Hello.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("processes.xml"))
                .addAsResource("jee6.bpmn20.xml");
    }

    @Deployment
    public static Archive<?> createWrapperWar() {
        return ShrinkWrap.create(WebArchive.class, "wrapper.war").addAsLibraries(createContractTestArchive(),
                createEjbTestArchive(), createProcessTestArchive());
    }

    @Inject
    private RuntimeService runtimeService;


    @EJB(lookup = "java:global/wrapper/HelloBean")
    private Hello helloBean;


    @Test
    public void testThatEjbIsDeployed() {
        assertThat(runtimeService, notNullValue());
        assertThat(helloBean, notNullValue());
        helloBean.sayHello("world");

    }
}
