package org.bpmnwithactiviti;

import de.freenet.wasabi.util.ShrinkwrapHelper;
import org.bpmnwithactiviti.jee6.ejb.HelloBean;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;

import java.io.File;
import java.net.MalformedURLException;

import static org.junit.Assert.assertTrue;

public class ShrinkWarpTest {

    @Test
    public void testWhatShrinkwrapDeploys() throws MalformedURLException {

        assertTrue(ShrinkwrapHelper.manifestFromModule("book-jee6-process").isFile());


        JavaArchive l = ShrinkWrap
                .create(JavaArchive.class, "ejb.jar")
                .addAsManifestResource(ShrinkwrapHelper.manifestFromModule("book-jee6-process"),"MANIFEST.MF")
                .addClass(HelloBean.class)
                .addAsManifestResource(EmptyAsset.INSTANCE,"beans.xml");
        File target = new File("target");
        if(!target.isDirectory()) {
            target.mkdir();
        }
        l.as(ZipExporter.class).exportTo(new File("target/ejb.jar"),true);

    }

}
