package org.vertx.java.deploy.impl.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vertx.java.test.junit.VertxConfigurableJUnit4Runner;
import org.vertx.java.test.junit.annotations.TestVerticle;
import org.vertx.java.test.junit.support.VertxTestBase;


@RunWith(VertxConfigurableJUnit4Runner.class)
@TestVerticle(main="spring:org.vertx.java.deploy.impl.spring.beans.TestConfiguration", urls={
  "src/test/java", "src/test/resources"
})
public class SpringVerticleFactoryTest extends VertxTestBase {

  @Before
  public void setup() {
    sleep(1000L); // Still needs a delay for some reason
  }

  @Test
  public void testVertxAutowiredBean() throws Exception {
    testMessageEcho("vertx.test.echo0", "What's the time Mr Wolf?");
  }

  @Test
  public void testVertxAwareBean() throws Exception {
    testMessageEcho("vertx.test.echo1", "I'm Winston Wolfe. I solve problems.");
  }
}
