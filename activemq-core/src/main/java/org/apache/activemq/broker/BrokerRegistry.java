/**
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.activemq.broker;

import java.util.HashMap;
import java.util.Iterator;

/**
 * 
 * @version $Revision: 1.3 $
 */
public class BrokerRegistry {

    static final private BrokerRegistry instance = new BrokerRegistry();
    
    public static BrokerRegistry getInstance() {
        return instance;
    }

    private final Object mutex = new Object();
    private final HashMap brokers = new HashMap();
    
    public BrokerService lookup(String brokerName) {
        synchronized(mutex) {
            return (BrokerService)brokers.get(brokerName);
        }
    }

    /**
     * Returns the first registered broker found
     */
    public BrokerService findFirst() {
        synchronized(mutex) {
            Iterator iter = brokers.values().iterator();
            while (iter.hasNext()) {
            return (BrokerService) iter.next();
            }
            return null;
        }
    }

    public void bind(String brokerName, BrokerService broker) {
        synchronized(mutex) {
            brokers.put(brokerName, broker);
        }
    }
    
    public void unbind(String brokerName) {
        synchronized(mutex) {
            brokers.remove(brokerName);
        }
    }

    public Object getRegistryMutext() {
        return mutex;
    }

}
