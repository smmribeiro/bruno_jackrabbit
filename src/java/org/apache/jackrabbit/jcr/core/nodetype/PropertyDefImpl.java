/*
 * Copyright 2004 The Apache Software Foundation.
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
package org.apache.jackrabbit.jcr.core.nodetype;

import org.apache.log4j.Logger;
import org.apache.jackrabbit.jcr.core.InternalValue;
import org.apache.jackrabbit.jcr.core.NamespaceResolver;

import javax.jcr.RepositoryException;
import javax.jcr.Value;
import javax.jcr.nodetype.PropertyDef;

/**
 * A <code>PropertyDefImpl</code> ...
 *
 * @author Stefan Guggisberg
 * @version $Revision: 1.11 $, $Date: 2004/09/09 15:23:43 $
 */
public class PropertyDefImpl extends ItemDefImpl implements PropertyDef {

    private static Logger log = Logger.getLogger(PropertyDefImpl.class);

    private final PropDef propDef;


    /**
     * Package private constructor
     *
     * @param propDef    property definition
     * @param ntMgr      node type manager
     * @param nsResolver namespace resolver
     */
    PropertyDefImpl(PropDef propDef, NodeTypeManagerImpl ntMgr, NamespaceResolver nsResolver) {
	super(propDef, ntMgr, nsResolver);
	this.propDef = propDef;
    }

    public PropDef unwrap() {
	return propDef;
    }

    //----------------------------------------------------------< PropertyDef >
    /**
     * @see PropertyDef#getDefaultValues
     */
    public Value[] getDefaultValues() {
	InternalValue[] defVals = propDef.getDefaultValues();
	if (defVals == null) {
	    return null;
	}
	Value[] values = new Value[defVals.length];
	for (int i = 0; i < defVals.length; i++) {
	    try {
		values[i] = defVals[i].toJCRValue(nsResolver);
	    } catch (RepositoryException re) {
		// should never get here
		String propName = (getName() == null) ? "[null]" : getName();
		log.error("illegal default value specified for property " + propName + " in node type " + getDeclaringNodeType(), re);
		return null;
	    }
	}
	return values;
    }

    /**
     * @see PropertyDef#getRequiredType
     */
    public int getRequiredType() {
	return propDef.getRequiredType();
    }

    /**
     * @see PropertyDef#getValueConstraint
     */
    public String getValueConstraint() {
	ValueConstraint[] constraints = propDef.getValueConstraints();
	if (constraints == null || constraints.length == 0) {
	    return null;
	} else {
	    return constraints[0].getDefinition();
	}
    }

    /**
     * @see PropertyDef#isMultiple
     */
    public boolean isMultiple() {
	return propDef.isMultiple();
    }
}

