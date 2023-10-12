package org.team9432.pixie.subsystems

import com.revrobotics.CANSparkMax
import com.revrobotics.CANSparkMax.IdleMode
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.pixie.Constants.Drivetrain.LEFT_DRIVE_ID
import org.team9432.pixie.Constants.Drivetrain.MAX_OUTPUT
import org.team9432.pixie.Constants.Drivetrain.RIGHT_DRIVE_ID

object Drivetrain: SubsystemBase() {
    private val leftMotor = CANSparkMax(LEFT_DRIVE_ID, MotorType.kBrushless)
    private val rightMotor = CANSparkMax(RIGHT_DRIVE_ID, MotorType.kBrushless)

    private val drive = DifferentialDrive(leftMotor, rightMotor)

    init {
        leftMotor.config(true, IdleMode.kBrake)
        rightMotor.config(false, IdleMode.kBrake)
        drive.setDeadband(0.05)
        drive.setMaxOutput(MAX_OUTPUT)
    }

    fun setSpeed(speed: Double, rotation: Double) = drive.arcadeDrive(speed, rotation, true)
    fun stop() = drive.stopMotor()

    private fun CANSparkMax.config(inverted: Boolean, idleMode: IdleMode) {
        setInverted(inverted)
        setIdleMode(idleMode)
    }
}