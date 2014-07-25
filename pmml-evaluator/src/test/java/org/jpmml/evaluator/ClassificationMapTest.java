/*
 * Copyright (c) 2013 KNIME.com AG, Zurich, Switzerland
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its contributors
 *    may be used to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.jpmml.evaluator;

import java.util.Arrays;
import java.util.Map;

import com.google.common.collect.Maps;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClassificationMapTest {

	@Test
	public void increasingOrder(){
		ClassificationMap.Type type = ClassificationMap.Type.SIMILARITY;

		assertTrue(type.compare(0.5, 0.0) > 0);
		assertTrue(type.compare(0.0, 0.5) < 0);

		assertTrue(type.compare(0.5, 0.5) == 0);

		assertTrue(type.compare(1.0, 0.5) > 0);
		assertTrue(type.compare(0.5, 1.0) < 0);
	}

	@Test
	public void decreasingOrder(){
		ClassificationMap.Type type = ClassificationMap.Type.DISTANCE;

		assertTrue(type.compare(0.5, 0.0) < 0);
		assertTrue(type.compare(0.0, 0.5) > 0);

		assertTrue(type.compare(0.5, 0.5) == 0);

		assertTrue(type.compare(1.0, 0.5) < 0);
		assertTrue(type.compare(0.5, 1.0) > 0);
	}

	@Test
	public void subtract(){
		Map<String, Double> values = Maps.newLinkedHashMap();
		values.put("loud", 0.2d);
		values.put("louder", 0.7d);
		values.put("insane", 1d);

		ClassificationMap.subtract(values, Arrays.asList("loud", "louder", "insane"));

		assertEquals(Double.valueOf(0.2d - 0d), values.get("loud"));
		assertEquals(Double.valueOf(0.7d - 0.2d), values.get("louder"));
		assertEquals(Double.valueOf(1d - 0.7d), values.get("insane"));
	}
}