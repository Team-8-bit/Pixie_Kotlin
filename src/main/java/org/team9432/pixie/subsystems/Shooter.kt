package org.team9432.pixie.subsystems

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.InstantCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.Ports.SHOOTER_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Shooter
import org.team9432.pixie.Robot

object Shooter: SubsystemBase() {
    private val motor = KSparkMAX(SHOOTER_ID)
    private val pid = PIDController(Shooter.P, Shooter.I, Shooter.D)

    private var setpoint = 0.0

    override fun periodic() {
        motor.set(pid.calculate(motor.encoder.velocity))
    }

    fun nextState(): Command {
        return InstantCommand({ setpoint = Robot.state.shooter.rpm }, this)
    }
}