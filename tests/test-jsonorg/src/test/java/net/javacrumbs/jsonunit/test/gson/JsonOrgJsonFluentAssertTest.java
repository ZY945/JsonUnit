/**
 * Copyright 2009-2016 the original author or authors.
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
package net.javacrumbs.jsonunit.test.gson;

import net.javacrumbs.jsonunit.test.base.AbstractJsonFluentAssertTest;
import net.javacrumbs.jsonunit.test.base.JsonTestUtils;
import org.json.JSONArray;
import org.junit.Test;

import static java.util.Arrays.asList;
import static net.javacrumbs.jsonunit.fluent.JsonFluentAssert.assertThatJson;

public class JsonOrgJsonFluentAssertTest extends AbstractJsonFluentAssertTest {
    @Override
    protected Object readValue(String value) {
        return JsonTestUtils.readByJsonOrg(value);
    }

    @Test
    @Override
    public void shouldAllowUnquotedKeysAndCommentInExpectedValue() {
        assertThatJson("{\"test\":1}").isEqualTo("{test:1}");
    }

    @Test(expected = AssertionError.class)
    public void testNotEqualsToToArray() {
        // array serialization not supported
        assertThatJson("{\"test\":[1,2,3]}").node("test").isNotEqualTo(new JSONArray(asList(1, 2, 3)));
    }

    @Test
    public void testEqualsToArray() {
        // array serialization not supported
        assertThatJson("{\"test\":[1,2,3]}").node("test").isEqualTo(new JSONArray(asList(1, 2, 3)));
    }
}