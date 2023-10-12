package org.team9432.pixie.subsystems

import com.revrobotics.CANSparkMax.IdleMode
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj.DigitalInput
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.FunctionalCommand
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.pixie.Ports.HOOD_ID
import org.team9432.pixie.Ports.HOOD_LIMIT_PORT
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Hood
import org.team9432.pixie.Constants.Hood.ANGLE_TOLERANCE
import org.team9432.pixie.Robot
import kotlin.math.abs

object Hood: SubsystemBase() {
    private val motor = KSparkMAX(HOOD_ID, idleMode = IdleMode.kBrake)
    private val pid = PIDController(Hood.P, Hood.I, Hood.D)
    private var mode = Mode.PID

    private val limit = DigitalInput(HOOD_LIMIT_PORT)

    init {
        pid.setTolerance(0.0)
    }

    private var setpoint = 0.0
    private var speed = 0.0

    fun resetPosition(position: Double) {
        motor.encoder.position = position
    }

    fun setSpeed(speed: Double) {
        mode = Mode.SPEED
        this.speed = speed
    }

    private fun atSetpoint() = abs(getAngle() - setpoint) < ANGLE_TOLERANCE
    fun atLimit() = !limit.get()

    private fun getAngle(): Double {
        return motor.encoder.position //TODO:  Math to convert motor rotation to hood angle
    }

    override fun periodic() {
        when (mode) {
            Mode.PID -> motor.set(pid.calculate(getAngle()))
            Mode.SPEED -> motor.set(speed)
        }
    }

    fun next(): Command {
        return FunctionalCommand(
            { setpoint = Robot.state.hood.angle; mode = Mode.PID }, // Init
            {}, // Execute
            {}, // End
            { atSetpoint() }, // isFinished
            this
        )
    }
}

private enum class Mode {
    PID, SPEED
}