package org.vertx.java.deploy.impl.spring;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.vertx.java.test.junit.VertxJUnit4ClassRunner;
import org.vertx.java.test.TestVerticle;
import org.vertx.java.test.VertxTestBase;


@RunWith(VertxJUnit4ClassRunner.class)
@TestVerticle(main="spring:org.vertx.java.deploy.impl.spring.beans.TestConfiguration")
public class SpringVerticleFactoryAnnotationConfigTest extends VertxTestBase {

  @Before
  public void setup() {
    lightSleep(1000L); // Still needs a delay for some reason
  }

  @Test
  public void testVertxAutowiredBean() throws Exception {
    testMessageEcho("vertx.test.echo0", "What do you call a lost wolf?  A where-wolf!");
  }

  @Test
  public void testVertxAwareBean() throws Exception {
    testMessageEcho("vertx.test.echo1", "Bad Wolf.");
  }
}
