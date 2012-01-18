package org.bpmnwithactiviti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class Jboss7ProcessEngineInjectionTest {

    @Deployment
    public static Archive<?> createTestArchive() throws IOException {
        return ShrinkWrap
                .create(JavaArchive.class, "activiti-process.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("processes.xml"))
                .addAsResource("helloworld.bpmn20.xml");
    }

    @Inject
    private RuntimeService runtimeService;

    @Test
    public void processEngineCanAccessDeployedBpmnProcess() throws Exception {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloworld");
        assertNotNull(processInstance.getId());
    }

}
