package org.team9432.pixie.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj2.command.Commands.*
import org.team9432.pixie.subsystems.Hood

fun resetHood(): CommandBase = sequence(
    runOnce({ Hood.setSpeed(-0.1) }, Hood), // Slowly start moving the hood down
    parallel( // Wait until it reaches the limit, or one second has elapsed (to prevent it from not finding the limit and running forever)
        waitUntil { Hood.atLimit() },
        waitSeconds(1.0)
    ),
    runOnce({ Hood.setSpeed(0.0) }, Hood), // Stop the hood, then set the position to 0
    runOnce({ Hood.resetPosition(0.0) }),
)