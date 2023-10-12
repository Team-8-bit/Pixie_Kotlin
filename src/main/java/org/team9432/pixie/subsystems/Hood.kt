package org.team9432.pixie.subsystems

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.Ports.SHOOTER_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Hood
import org.team9432.pixie.Robot

object Hood: SubsystemBase() {
    private val motor = KSparkMAX(SHOOTER_ID)
    private val pid = PIDController(Hood.P, Hood.I, Hood.D)

    private var setpoint = 0.0

    private fun getAngle(): Double {
        return motor.encoder.position //TODO:  Math to convert motor rotation to hood angle
    }

    override fun periodic() {
        motor.set(pid.calculate(getAngle()))
    }

    fun nextState(): Command {
        return InstantCommand({ setpoint = Robot.state.hood.angle }, this)
    }
}