package org.bpmnwithactiviti;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.history.HistoricDetail;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class LoanArquillianTest {
    final static String LOAN_PROCESS_DEFINITION_KEY = "jee6";

    @Deployment
    public static Archive<?> createTestArchive() throws IOException {
        JavaArchive webArchive = ShrinkWrap
                .create(JavaArchive.class, "wichtig.jar")
                .setManifest("META-INF/MANIFEST.MF")
                .addAsResource(EmptyAsset.INSTANCE, "beans.xml");
        webArchive.writeTo(new FileOutputStream(new File("D:/war.war")), Formatters.VERBOSE);
        ;
        return webArchive;
    }

    @EJB(lookup = "java:global/processEngine/default")
    private ProcessEngine processEngine;
  /*  @Inject
    private Logger log;
*/
    @Test
    public void test() {
        Map<String, Object> processVariables = new HashMap<String, Object>();
       /* processVariables.put("name", "Miss Piggy");

        processVariables.put("orderType", "BestellungMail");
        processEngine.getRuntimeService().startProcessInstanceByKey(
                LOAN_PROCESS_DEFINITION_KEY, processVariables);

        List<HistoricDetail> historyVariables = processEngine
                .getHistoryService().createHistoricDetailQuery()
                .variableUpdates().orderByVariableRevision().desc().list();
        assertNotNull(historyVariables);
        for (HistoricDetail historicDetail : historyVariables) {
            ReflectionToStringBuilder.reflectionToString(historicDetail);
            log.fine(ReflectionToStringBuilder
                    .reflectionToString(historicDetail));

        }*/
    }

}
