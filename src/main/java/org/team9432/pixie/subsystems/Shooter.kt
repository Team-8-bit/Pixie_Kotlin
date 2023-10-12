package org.team9432.pixie.subsystems

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.pixie.Ports.SHOOTER_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Shooter
import org.team9432.pixie.Constants.Shooter.RPM_TOLERANCE
import org.team9432.pixie.Robot
import kotlin.math.abs

object Shooter: SubsystemBase() {
    private val motor = KSparkMAX(SHOOTER_ID)
    private val pid = PIDController(Shooter.P, Shooter.I, Shooter.D)

    init {
        pid.setTolerance(0.0)
    }

    private var setpoint = 0.0

    private fun atSetpoint() = abs(motor.encoder.velocity - setpoint) < RPM_TOLERANCE

    override fun periodic() {
        motor.set(pid.calculate(motor.encoder.velocity))
    }

    fun next(): Command {
        return FunctionalCommand(
            { setpoint = Robot.state.shooter.rpm }, // Init
            {}, // Execute
            {}, // End
            { atSetpoint() }, // isFinished
            this
        )
    }
}