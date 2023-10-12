package org.team9432.pixie.commands

import edu.wpi.first.wpilibj2.command.*
import org.team9432.pixie.subsystems.Hood

class ResetHood: SequentialCommandGroup(
    InstantCommand({ Hood.setSpeed(-0.1) }, Hood), // Slowly start moving the hood down
    ParallelRaceGroup( // Wait until it reaches the limit, or one second has elapsed (to prevent it from not finding the limit and running forever)
        WaitUntilCommand { Hood.atLimit() },
        WaitCommand(1.0)
    ),
    InstantCommand({ Hood.setSpeed(0.0) }, Hood), // Stop the hood, then set the position to 0
    InstantCommand({ Hood.resetPosition(0.0) })
)