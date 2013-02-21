/*
 * Copyright (c) 2008-2013, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.executor;

import com.hazelcast.nio.serialization.DataSerializableFactory;
import com.hazelcast.nio.serialization.DataSerializerHook;
import com.hazelcast.nio.serialization.IdentifiedDataSerializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @mdogan 2/19/13
 */
public class DataSerializerExecutorHook implements DataSerializerHook {

    static final int CALLABLE_TASK = 500;
    static final int MEMBER_CALLABLE_TASK = 501;
    static final int RUNNABLE_ADAPTER = 502;

    public Map<Integer, DataSerializableFactory> getFactories() {
        Map<Integer, DataSerializableFactory> map = new HashMap<Integer, DataSerializableFactory>();

        map.put(CALLABLE_TASK, new DataSerializableFactory() {
            public IdentifiedDataSerializable create() {
                return new CallableTaskOperation();
            }
        });

        map.put(MEMBER_CALLABLE_TASK, new DataSerializableFactory() {
            public IdentifiedDataSerializable create() {
                return new MemberCallableTaskOperation();
            }
        });

        map.put(RUNNABLE_ADAPTER, new DataSerializableFactory() {
            public IdentifiedDataSerializable create() {
                return new RunnableAdapter();
            }
        });
        return map;
    }
}