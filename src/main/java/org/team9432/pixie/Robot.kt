package org.team9432.pixie

import edu.wpi.first.wpilibj.PowerDistribution
import edu.wpi.first.wpilibj2.command.CommandScheduler
import edu.wpi.first.wpilibj2.command.InstantCommand
import org.littletonrobotics.junction.LogFileUtil
import org.littletonrobotics.junction.LoggedRobot
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.networktables.NT4Publisher
import org.littletonrobotics.junction.wpilog.WPILOGReader
import org.littletonrobotics.junction.wpilog.WPILOGWriter


object Robot: LoggedRobot() {

    var state = State.IDLE
    fun setState(state: State) = InstantCommand({ this.state = state })

    override fun robotInit() {
        Logger.getInstance().recordMetadata("ProjectName", "Pixie_Kotlin") // Set a metadata value

        if (isReal()) {
            Logger.getInstance().addDataReceiver(WPILOGWriter("/U")) // Log to a USB stick
            Logger.getInstance().addDataReceiver(NT4Publisher()) // Publish data to NetworkTables
            PowerDistribution(0, PowerDistribution.ModuleType.kCTRE) // Enables power distribution logging
        } else {
            setUseTiming(false) // Run as fast as possible
            val logPath = LogFileUtil.findReplayLog() // Pull the replay log from AdvantageScope (or prompt the user)
            Logger.getInstance().setReplaySource(WPILOGReader(logPath)) // Read replay log
            Logger.getInstance().addDataReceiver(WPILOGWriter(LogFileUtil.addPathSuffix(logPath, "_sim"))) // Save outputs to a new log
        }

        Logger.getInstance().start() // Start logging! No more data receivers, replay sources, or metadata values may be added.

        // Access the RobotContainer object so that it is initialized. This will perform all our
        // button bindings, and put our autonomous chooser on the dashboard.
        RobotContainer
    }

    override fun robotPeriodic() {
        CommandScheduler.getInstance().run()
    }

    override fun disabledInit() {}
    override fun disabledPeriodic() {}
    override fun autonomousInit() {}
    override fun autonomousPeriodic() {}
    override fun teleopInit() {}
    override fun teleopPeriodic() {}

    override fun testInit() {
        CommandScheduler.getInstance().cancelAll()
    }

    override fun testPeriodic() {}
    override fun simulationInit() {}
    override fun simulationPeriodic() {}
}