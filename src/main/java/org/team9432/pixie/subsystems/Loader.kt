package org.team9432.pixie.subsystems

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.Ports.LOADER_ID
import org.team9432.Ports.SHOOTER_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Shooter
import org.team9432.pixie.Robot

object Loader: SubsystemBase() {
    private val motor = KSparkMAX(LOADER_ID)

    private var setpoint = 0.0

    override fun periodic() {
        motor.set(setpoint)
    }

    fun nextState(): Command {
        return InstantCommand({ setpoint = Robot.state.loader.speed }, this)
    }
}