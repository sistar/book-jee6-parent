package org.bpmnwithactiviti;

import org.activiti.engine.RuntimeService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class RuntimeServiceAvailableForTwoDeploymentsTest {

    @Deployment
    public static Archive<?> createWebArchive() {
        return ShrinkWrap.create(WebArchive.class, "wrapper.war")
                .addAsLibrary(createSomeJar())
                .addAsLibrary(createProcessJar());
    }

    public static Archive<?> createSomeJar() {
        return ShrinkWrap.create(JavaArchive.class, "asome.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    public static Archive<?> createProcessJar() {
        return ShrinkWrap.create(JavaArchive.class, "process.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "processes.xml")
                .addAsResource("jee6.bpmn20.xml");
    }

    @Inject
    private RuntimeService runtimeService;

    @Test
    public void shouldHaveAccessToRuntimeService() {
        assertNotNull(runtimeService);
    }

}
