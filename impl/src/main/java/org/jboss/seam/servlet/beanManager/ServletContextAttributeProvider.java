/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.seam.servlet.beanManager;

import javax.enterprise.inject.spi.BeanManager;
import javax.servlet.ServletContext;

import org.jboss.seam.solder.beanManager.BeanManagerProvider;

/**
 * A BeanManager provider for the Servlet Context attribute "javax.enterprise.inject.spi.BeanManager"
 * 
 * @author Nicklas Karlsson
 * 
 * @see org.jboss.seam.servlet.ImplicitServletObjectsProducer
 */
public class ServletContextAttributeProvider implements BeanManagerProvider {
    private static ThreadLocal<ServletContext> servletContext = new ThreadLocal<ServletContext>() {
        @Override
        protected ServletContext initialValue() {
            return null;
        }
    };

    public static void setServletContext(final ServletContext sc) {
        servletContext.set(sc);
    }

    public BeanManager getBeanManager() {
        if (servletContext.get() != null) {
            return (BeanManager) servletContext.get().getAttribute(BeanManager.class.getName());
        }
        return null;
    }

    public int getPrecedence() {
        return 20;
    }
}
