package org.bpmnwithactiviti;

import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class LoanTest {
	Log log = LogFactory.getLog(this.getClass());
    final static String LOAN_PROCESS_DEFINITION_KEY ="jee6";
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();
    @Deployment(resources="jee6.bpmn20.xml")
    @org.junit.Test
    public void testLoan() throws Exception {
        Map<String, Object> processVariables =
                new HashMap<String, Object>();
        processVariables.put("name", "Miss Piggy");

        processVariables.put("orderType", "BestellungMail");
        
        activitiRule.getRuntimeService().startProcessInstanceByKey(LOAN_PROCESS_DEFINITION_KEY,processVariables);
        log.debug(activitiRule);
        List<HistoricDetail> historyVariables =
                activitiRule.getHistoryService()
                        .createHistoricDetailQuery()
                .variableUpdates()
                .orderByVariableRevision()
                .desc()
                .list();
        assertNotNull(historyVariables);
        for(HistoricDetail historicDetail : historyVariables){
            ReflectionToStringBuilder.reflectionToString(historicDetail);
            log.debug(ReflectionToStringBuilder.reflectionToString(historicDetail));

        }
    }
}
