package org.team9432.pixie.subsystems

import com.revrobotics.CANSparkMax.IdleMode
import com.revrobotics.CANSparkMaxLowLevel.MotorType
import edu.wpi.first.wpilibj.drive.DifferentialDrive
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.team9432.Ports.LEFT_DRIVE_ID
import org.team9432.Ports.RIGHT_DRIVE_ID
import org.team9432.lib.drivers.KSparkMAX
import org.team9432.pixie.Constants.Drivetrain.DEADBAND
import org.team9432.pixie.Constants.Drivetrain.MAX_OUTPUT

object Drivetrain: SubsystemBase() {
    private val leftMotor = KSparkMAX(LEFT_DRIVE_ID, MotorType.kBrushless, inverted = true, IdleMode.kBrake)
    private val rightMotor = KSparkMAX(RIGHT_DRIVE_ID, MotorType.kBrushless, inverted = false, IdleMode.kBrake)

    private val drive = DifferentialDrive(leftMotor, rightMotor)

    init {
        drive.setDeadband(DEADBAND)
        drive.setMaxOutput(MAX_OUTPUT)
    }

    fun setSpeed(speed: Double, rotation: Double) = drive.arcadeDrive(speed, rotation, true)
    fun stop() = drive.stopMotor()
}