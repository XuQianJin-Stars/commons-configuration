/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.configuration.tree.xpath;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.apache.commons.configuration.tree.ImmutableNode;
import org.apache.commons.configuration.tree.InMemoryNodeModel;
import org.apache.commons.configuration.tree.NodeHandler;
import org.apache.commons.jxpath.JXPathContext;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@code XPathContextFactory}.
 *
 * @version $Id$
 */
public class TestXPathContextFactory
{
    /** The factory to be tested. */
    private XPathContextFactory factory;

    @Before
    public void setUp() throws Exception
    {
        factory = new XPathContextFactory();
    }

    /**
     * Tests whether a correct context is created.
     */
    @Test
    public void testCreateContext()
    {
        ImmutableNode node =
                new ImmutableNode.Builder().name("testRoot").create();
        NodeHandler<ImmutableNode> handler =
                new InMemoryNodeModel(node).getNodeHandler();
        JXPathContext context = factory.createContext(node, handler);

        assertTrue("No lenient mode", context.isLenient());
        ConfigurationNodePointerFactory.NodeWrapper<?> wrapper =
                (ConfigurationNodePointerFactory.NodeWrapper<?>) context
                        .getContextBean();
        assertSame("Wrong node", node, wrapper.getNode());
        assertSame("Wrong handler", handler, wrapper.getNodeHandler());
    }
}
