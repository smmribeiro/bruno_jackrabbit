/*
 * Copyright 2004-2005 The Apache Software Foundation or its licensors,
 *                     as applicable.
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
package org.apache.jackrabbit.core.query.lucene;

import org.apache.jackrabbit.core.NodeImpl;

import javax.jcr.NodeIterator;

/**
 * Extends the {@link javax.jcr.NodeIterator} interface by adding a {@link
 * #getScore()} method that returns the score for the node that is returned by
 * {@link javax.jcr.NodeIterator#nextNode()}.
 */
public interface ScoreNodeIterator extends NodeIterator {

    /**
     * Returns the score of the node returned by {@link #nextNode()}. In other
     * words, this method returns the score value of the next
     * <code>Node</code>.
     *
     * @return the score of the node returned by {@link #nextNode()}.
     * @throws java.util.NoSuchElementException
     *          if there is no next node.
     */
    public float getScore();

    /**
     * Returns the next <code>Node</code> in the result set.
     *
     * @return the next <code>Node</code> in the result set.
     * @throws java.util.NoSuchElementException
     *          if iteration has no more <code>Node</code>s.
     */
    public NodeImpl nextNodeImpl();
}
