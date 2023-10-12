package org.team9432.pixie.commands.drivetrain

import edu.wpi.first.wpilibj2.command.CommandBase
import org.team9432.pixie.subsystems.Drivetrain

class ArcadeDrive(private val speed: () -> Double, private val rotation: () -> Double): CommandBase() {
    init {
        addRequirements(Drivetrain)
    }

    override fun execute() {
        Drivetrain.setSpeed(speed.invoke(), rotation.invoke())
    }

    override fun end(interrupted: Boolean) = Drivetrain.stop()
}