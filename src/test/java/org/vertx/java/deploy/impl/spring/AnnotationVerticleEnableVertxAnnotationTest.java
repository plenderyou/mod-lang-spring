/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vertx.java.deploy.impl.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.vertx.java.test.TestVerticle;
import org.vertx.java.test.VertxTestBase;
import org.vertx.java.test.junit.VertxJUnit4ClassRunner;

/**
 * @author swilliams
 *
 */
@RunWith(VertxJUnit4ClassRunner.class)
@TestVerticle(main="spring:org.vertx.spring.examples.TestEnableVertxConfiguration")
public class AnnotationVerticleEnableVertxAnnotationTest extends VertxTestBase {

  @Test
  public void testAnnotatedReplyingVertxMessageListener() throws Exception {
    testMessageEcho("test.annotation.reply", "This is a reply?");
  }

  @Test
  public void testAnnotatedVertxMessageListener() throws Exception {
    getVertx().eventBus().send("test.annotation.receive", "Nothing to see here.");
  }
}
