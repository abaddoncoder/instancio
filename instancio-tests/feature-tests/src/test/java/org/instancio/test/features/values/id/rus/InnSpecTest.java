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
package org.instancio.test.features.values.id.rus;

import org.instancio.Instancio;
import org.instancio.generator.specs.rus.InnSpec;
import org.instancio.junit.InstancioExtension;
import org.instancio.test.features.values.AbstractValueSpecTestTemplate;
import org.instancio.test.support.tags.Feature;
import org.instancio.test.support.tags.FeatureTag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

@FeatureTag(Feature.VALUE_SPEC)
@ExtendWith(InstancioExtension.class)
class InnSpecTest extends AbstractValueSpecTestTemplate<String> {

    @Override
    protected InnSpec spec() {
        return Instancio.gen().id().rus().inn();
    }

    @Override
    protected void assertDefaultSpecValue(final String actual) {
        assertThat(actual)
                .hasSizeBetween(10, 12)
                .containsOnlyDigits();
    }

    @Test
    void individual() {
        assertThat(spec().individual().get())
                .hasSize(12)
                .containsOnlyDigits();
    }

    @Test
    void juridical() {
        assertThat(spec().juridical().get())
                .hasSize(10)
                .containsOnlyDigits();
    }
}
