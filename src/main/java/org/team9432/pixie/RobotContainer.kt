package org.team9432.pixie

import edu.wpi.first.wpilibj2.command.button.CommandXboxController
import org.team9432.pixie.commands.drivetrain.ArcadeDrive
import org.team9432.pixie.commands.resetHood
import org.team9432.pixie.commands.shoot
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
        controller.x().onTrue(Robot.setState(State.HIGH_SHOT).andThen(shoot()))
        controller.y().onTrue(Robot.setState(State.MID_SHOT).andThen(shoot()))
        controller.a().onTrue(Robot.setState(State.LOW_SHOT).andThen(shoot()))

        controller.b().onTrue(resetHood())
    }
}