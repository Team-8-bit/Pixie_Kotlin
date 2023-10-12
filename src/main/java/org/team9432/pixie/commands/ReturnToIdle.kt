package org.team9432.pixie.commands

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup
import org.team9432.pixie.State
import org.team9432.pixie.Robot
import org.team9432.pixie.subsystems.Hood
import org.team9432.pixie.subsystems.Loader
import org.team9432.pixie.subsystems.Shooter

class ReturnToIdle: SequentialCommandGroup(
    Robot.setState(State.IDLE),
    Shooter.nextState(),
    Loader.nextState(),
    Hood.nextState(),
)