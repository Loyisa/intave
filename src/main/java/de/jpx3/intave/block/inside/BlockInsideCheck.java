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

import de.jpx3.intave.block.access.VolatileBlockAccess;
import de.jpx3.intave.block.physics.BlockPhysics;
import de.jpx3.intave.check.movement.physics.environment.SimulationEnvironment;
import de.jpx3.intave.share.BlockPosition;
import de.jpx3.intave.share.Motion;
import de.jpx3.intave.share.Position;
import de.jpx3.intave.user.User;
import org.bukkit.Material;

import java.util.List;

public abstract class BlockInsideCheck {
	public abstract void checkInsideBlocks(
		User user,
		SimulationEnvironment environment,
		Motion motion,
		List<EntityMovement> movements
	);

	protected final void applyBlockEffect(
		User user,
		SimulationEnvironment environment,
		Motion motion,
		BlockPosition blockPosition,
		Position movementFrom,
		boolean insideBlockOrTooFast
	) {
		Material material = VolatileBlockAccess.typeAccess(user, blockPosition);
		if (material == Material.AIR) {
			return;
		}
		Motion newMotion = BlockPhysics.entityInside(
			user, material, environment, blockPosition,
			movementFrom, motion, insideBlockOrTooFast
		);
		if (newMotion != null) {
			motion.setTo(newMotion);
		}
	}
}
