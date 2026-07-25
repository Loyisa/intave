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

package de.jpx3.intave.block.inside;

import de.jpx3.intave.share.BoundingBox;
import de.jpx3.intave.share.ClientMath;
import de.jpx3.intave.share.Position;
import de.jpx3.intave.share.RawVector3d;
import it.unimi.dsi.fastutil.longs.LongSet;

public class BlockGetter {



	public static int addCollisionsAlongTravel(
		LongSet longSet,
		RawVector3d move,
		BoundingBox box
	) {
		double boxXSize = box.sizeX();
		double boxYSize = box.sizeY();
		double boxZSize = box.sizeZ();
		RawVector3d vec3i = move.furthestCorner();
		Position boxCenter = box.center();

		Position position = Position.of(
			boxCenter.getX() + boxXSize * 0.5 * vec3i.x(),
			boxCenter.getY() + boxYSize * 0.5 * vec3i.y(),
			boxCenter.getZ() + boxZSize * 0.5 * vec3i.z()
		);

		Position positionFrom = position.add(move.reversed());
		int fromX = ClientMath.floor(positionFrom.getX());
		int fromY = ClientMath.floor(positionFrom.getY());
		int fromZ = ClientMath.floor(positionFrom.getZ());
		int moveXSgn = ClientMath.sign(move.x());
		int moveYSgn = ClientMath.sign(move.y());
		int moveZSgn = ClientMath.sign(move.z());
		double d3 = moveXSgn == 0 ? Double.MAX_VALUE : moveXSgn / move.x();
		double d4 = moveYSgn == 0 ? Double.MAX_VALUE : moveYSgn / move.y();
		double d5 = moveZSgn == 0 ? Double.MAX_VALUE : moveZSgn / move.z();
		double d6 = d3 * (moveXSgn > 0 ? 1.0 - ClientMath.fraction(d3) : ClientMath.fraction(d3));
		double d7 = d4 * (moveYSgn > 0 ? 1.0 - ClientMath.fraction(d4) : ClientMath.fraction(d4));
		double d8 = d5 * (moveZSgn > 0 ? 1.0 - ClientMath.fraction(d5) : ClientMath.fraction(d5));

		int k1 = 0;

		while (d6 <= 1.0 || d7 <= 1.0 || d8 <= 1.0) {
			if (d6 < d7) {
				if (d6 < d8) {
					fromX += moveXSgn;
					d6 += d3;
				} else {
					fromZ += moveZSgn;
					d8 += d5;
				}
			} else if (d7 < d8) {
				fromY += moveYSgn;
				d7 += d4;
			} else {
				fromZ += moveZSgn;
				d8 += d5;
			}

//			BoundingBox.clip
		}
		return 0;
	}

}
