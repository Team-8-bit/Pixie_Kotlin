package org.team9432.pixie.commands

import edu.wpi.first.wpilibj2.command.CommandBase
import edu.wpi.first.wpilibj2.command.Commands.sequence
import org.team9432.pixie.Robot
import org.team9432.pixie.State
import org.team9432.pixie.subsystems.Hood
import org.team9432.pixie.subsystems.Loader
import org.team9432.pixie.subsystems.Shooter

fun returnToIdle(): CommandBase = sequence(
    Robot.setState(State.IDLE),
    Shooter.next(),
    Loader.next(),
    Hood.next(),
)