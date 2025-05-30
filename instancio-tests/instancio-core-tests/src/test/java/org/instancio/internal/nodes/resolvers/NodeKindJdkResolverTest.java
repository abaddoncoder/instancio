/*
 * Copyright 2022-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.instancio.internal.nodes.resolvers;

import org.instancio.internal.nodes.NodeKind;
import org.instancio.internal.nodes.NodeKindResolver;
import org.instancio.test.support.pojo.person.Gender;
import org.instancio.test.support.pojo.person.Person;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class NodeKindJdkResolverTest {

    private final NodeKindResolver resolver = new NodeKindJdkResolver();

    @ValueSource(classes = {
            int.class,
            Integer.class,
            String.class,
            LocalDate.class,
            Gender.class, // enum
            Class.class,
            XMLGregorianCalendar.class
    })
    @ParameterizedTest
    void verifyMatches(Class<?> klass) {
        assertThat(resolver.resolve(klass)).contains(NodeKind.JDK);
    }

    @ValueSource(classes = {int[].class, String[].class, Person.class})
    @ParameterizedTest
    void verifyNonMatches(final Class<?> klass) {
        assertThat(resolver.resolve(klass)).isEmpty();
    }
}
