/*
 * Copyright 2013 the original author or authors.
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
package org.vertx.spring.examples;

import org.springframework.context.SmartLifecycle;
import org.vertx.java.core.AsyncResult;
import org.vertx.java.core.AsyncResultHandler;
import org.vertx.java.core.Handler;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;

/**
 * @author swilliams
 *
 */
public abstract class AbstractHandlerBean<T> implements SmartLifecycle, Handler<Message<T>> {

  private final EventBus eventBus;

  private volatile boolean running = false;

  private boolean autoStartup = true;

  private int phase;

  private String address;

  private boolean addressFixed = false;

  public AbstractHandlerBean(EventBus eventBus) {
    this.eventBus = eventBus;
    this.address = this.getClass().getName().toLowerCase();
  }

  /* (non-Javadoc)
   * @see org.springframework.context.Lifecycle#start()
   */
  @Override
  public void start() {
    this.addressFixed = true;
    eventBus.registerHandler(getAddress(), this, new AsyncResultHandler<Void>() {
      @Override
      public void handle(AsyncResult<Void> event) {
        AbstractHandlerBean.this.running = true;
      }
    });
  }

  /* (non-Javadoc)
   * @see org.springframework.context.Lifecycle#stop()
   */
  @Override
  public void stop() {
    // TODO Auto-generated method stub
  }

  /* (non-Javadoc)
   * @see org.springframework.context.SmartLifecycle#stop(java.lang.Runnable)
   */
  @Override
  public void stop(final Runnable callback) {
    eventBus.unregisterHandler(getAddress(), this, new AsyncResultHandler<Void>() {
      @Override
      public void handle(AsyncResult<Void> event) {
        callback.run();
      }
    });
  }

  /* (non-Javadoc)
   * @see org.springframework.context.SmartLifecycle#isAutoStartup()
   */
  @Override
  public boolean isAutoStartup() {
    return autoStartup;
  }

  /* (non-Javadoc)
   * @see org.springframework.context.Lifecycle#isRunning()
   */
  @Override
  public boolean isRunning() {
    return running;
  }

  /* (non-Javadoc)
   * @see org.springframework.context.Phased#getPhase()
   */
  @Override
  public int getPhase() {
    return phase;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    if (this.addressFixed) {
      throw new IllegalStateException("Can't set the address after startup");
    }
    this.address = address;
  }

  public void setAutoStartup(boolean autoStartup) {
    this.autoStartup = autoStartup;
  }

  public void setPhase(int phase) {
    this.phase = phase;
  }

}
