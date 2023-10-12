package org.team9432.pixie

import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import org.team9432.State
import org.team9432.pixie.commands.ResetHood
import org.team9432.pixie.commands.Shoot
import org.team9432.pixie.commands.drivetrain.ArcadeDrive
import org.team9432.pixie.subsystems.Drivetrain

object RobotContainer {
    private val controller = CommandXboxController(0)

    init {
        configureBindings()
        configureDefaultCommands()
    }

    private fun configureDefaultCommands() {
        Drivetrain.defaultCommand = ArcadeDrive({ controller.leftY }, { controller.rightX })
    }

    private fun configureBindings() {
        controller.x().onTrue(Robot.setState(State.HIGH_SHOT).andThen(Shoot()))
        controller.y().onTrue(Robot.setState(State.MID_SHOT).andThen(Shoot()))
        controller.a().onTrue(Robot.setState(State.LOW_SHOT).andThen(Shoot()))

        controller.b().onTrue(ResetHood())
    }
}