package org.team9432.pixie.commands

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import edu.wpi.first.wpilibj2.command.WaitCommand
import org.team9432.State
import org.team9432.pixie.Robot
import org.team9432.pixie.subsystems.Hood
import org.team9432.pixie.subsystems.Loader
import org.team9432.pixie.subsystems.Shooter

class Shoot: SequentialCommandGroup(
    ParallelCommandGroup( // Raise the hood, then start the shooter after a short delay
        Hood.nextState(),
        SequentialCommandGroup(
            WaitCommand(0.1),
            Shooter.nextState()
        )
    ), // Once the shooter is sped up and the hood is at the correct angle, run the loader for 0.5 seconds
    Loader.nextState(),
    WaitCommand(0.5),
    Robot.setState(State.IDLE), // Set the state to idle and stop the loader
    Loader.nextState(),
    ReturnToIdle() // Stop and reset everything else
)