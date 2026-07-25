/*
 * Copyright 2026 Intave
 *
 * This software is licensed under the PolyForm Perimeter License 1.0.0.
 * You may use this software for any purpose, except for providing to
 * others any product that competes with the software.
 *
 * A copy of the license is available at:
 *   https://polyformproject.org/licenses/perimeter/1.0.0/
 */

package de.jpx3.intave.share;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BoundingBoxTest {

	@Test
	void blockPositionsBetween() {
		BoundingBox boundingBox = BoundingBox.originFrom(0, 0, 0, 4, 4, 4);
		BlockPositions positions = boundingBox.blockPositionsBetween();
		Set<BlockPosition> seen = new HashSet<>();
		for (MutableBlockPosition position : positions) {
			assertTrue(position.x() >= 0 && position.x() <= 4);
			assertTrue(position.y() >= 0 && position.y() <= 4);
			assertTrue(position.z() >= 0 && position.z() <= 4);
			seen.add(position.toBlockPosition());
		}
		assertEquals(125, seen.size());
	}

	@Test
	void blockPositionsBetweenDirectional() {
		BoundingBox boundingBox = BoundingBox.originFrom(0, 0, 0, 4, 4, 4);
		BlockPositions positions = boundingBox.blockPositionsBetweenDirectional(
			Motion.of(0, -1, 0)
		);
		Set<BlockPosition> seen = new HashSet<>();
		for (MutableBlockPosition position : positions) {
			assertTrue(position.x() >= 0 && position.x() <= 4);
			assertTrue(position.y() >= 0 && position.y() <= 4);
			assertTrue(position.z() >= 0 && position.z() <= 4);
			seen.add(position.toBlockPosition());
		}
		assertEquals(125, seen.size());
	}
}