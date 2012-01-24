import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springfriends.GreeterService;
import org.springfriends.impl.GreeterServiceImpl;
import org.springfriends.impl.GreetingManagerImpl;

import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(Arquillian.class)
public class SpringTest {

    @Deployment
    public static Archive<?> createTestArchive() {

        WebArchive webArchive = ShrinkWrap
                .create(WebArchive.class, "spring-helloworld.war")
                .addAsManifestResource(EmptyAsset.INSTANCE,
                        ArchivePaths.create("beans.xml"))
                .addAsResource("META-INF/MANIFEST.MF")
                .addClass(GreeterService.class)
                .addPackage(GreeterServiceImpl.class.getPackage())
                .addAsResource("application-context.xml");

        Collection<JavaArchive> coreSpringArchives = DependencyResolvers
                .use(MavenDependencyResolver.class)
                .artifact("org.springframework:spring-context:3.1.0.RELEASE")
                .resolveAs(JavaArchive.class);

        System.out.println("ARCH:" + coreSpringArchives);  //see transitive dependencies
        webArchive.addAsLibraries(coreSpringArchives);
        return webArchive;
    }

    @Test
    public void testThatSpringLikesArquillian() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application-context.xml");
        GreetingManagerImpl greetingManagerImpl = (GreetingManagerImpl) applicationContext.getBean("greetingManager");
        assertThat(greetingManagerImpl.doGreet(), equalTo("hello kermit!"));
    }
}
