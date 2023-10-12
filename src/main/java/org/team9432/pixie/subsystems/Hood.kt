package org.team9432.pixie.subsystems

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.Ports.HOOD_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Hood
import org.team9432.pixie.Constants.Hood.ANGLE_TOLERANCE
import org.team9432.pixie.Robot
import kotlin.math.abs

object Hood: SubsystemBase() {
    private val motor = KSparkMAX(HOOD_ID)
    private val pid = PIDController(Hood.P, Hood.I, Hood.D)

    init {
        pid.setTolerance(0.0)
    }

    private var setpoint = 0.0

    private fun atSetpoint() = abs(getAngle() - setpoint) < ANGLE_TOLERANCE

    private fun getAngle(): Double {
        return motor.encoder.position //TODO:  Math to convert motor rotation to hood angle
    }

    override fun periodic() {
        motor.set(pid.calculate(getAngle()))
    }

    fun nextState(): Command {
        return FunctionalCommand(
            { setpoint = Robot.state.hood.angle }, // Init
            {}, // Execute
            {}, // End
            { atSetpoint() }, // isFinished
            this
        )
    }
}