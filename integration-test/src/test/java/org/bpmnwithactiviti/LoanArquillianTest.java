package org.bpmnwithactiviti;


import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricDetail;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.bpmnwithactiviti.jee6.ejb.HelloBean;
import org.bpmnwithactiviti.jee6.task.BeerOrderTask;
import org.bpmnwithactiviti.jee6.task.Hello;
import org.bpmnwithactiviti.jee6.task.HelloDelegate;
import org.bpmnwithactiviti.jee6.task.Party;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class LoanArquillianTest {
    Logger log = Logger.getLogger("Process Logger");


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
                .addClass(HelloDelegate.class)
                .addClass(BeerOrderTask.class)
                .addClass(Party.class)
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

    @Inject
    private HistoryService historyService;

    @EJB(lookup = "java:global/wrapper/HelloBean")
    private Hello helloBean;

    @Test
    public void testThatEjbIsDeployed() {
        assertThat(runtimeService, notNullValue());
        assertThat(helloBean, notNullValue());
        helloBean.sayHello("world");

    }

    @Test
    public void testThatProcessIsStarted() throws Exception {
        Map<String, Object> processVariables =
                new HashMap<String, Object>();
        processVariables.put("name", "Miss Piggy");

        processVariables.put("orderType", "BestellungMail");

        runtimeService.startProcessInstanceByKey("jee6", processVariables);

        List<HistoricDetail> historyVariables =
                historyService
                        .createHistoricDetailQuery()
                        .variableUpdates()
                        .orderByVariableRevision()
                        .desc()
                        .list();
        log.info("HISTORY SIZE: " + historyVariables.size());
        assertNotNull(historyVariables);
        for (HistoricDetail historicDetail : historyVariables) {
            ReflectionToStringBuilder.reflectionToString(historicDetail);
            log.info("HISTORY: "+ReflectionToStringBuilder.reflectionToString(historicDetail));
        }
    }
}