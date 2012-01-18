package org.bpmnwithactiviti;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Since we found the JBoss AS 7.0 implementation of the automatic enrichment of deployment units not working under
 * JBoss AS 7.1 we are providing a test that asserts the existence of the implicitly added Activiti libraries in the
 * classpath of our deployment unit when a processes.xml is provided.
 */
@RunWith(Arquillian.class)
public class Jboss7DeploymentUnitEnrichmentTest {

    @Deployment
    public static Archive<?> createTestArchive() throws IOException {
        return ShrinkWrap
                .create(JavaArchive.class, "activiti-process.jar")
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"))
                .addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("processes.xml"));
    }

    @Test
    public void processEngineImplicitlyExistsInClassPathWhenProcessesXmlIsPresent() throws Exception {
        Class.forName("org.activiti.engine.ProcessEngine");
    }

}
