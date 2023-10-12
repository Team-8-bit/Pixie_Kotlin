package org.team9432.pixie

import edu.wpi.first.wpilibj.TimedRobot
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.CommandScheduler

object Robot : TimedRobot() {
    private var autonomousCommand: Command? = null


    override fun robotInit() {
        // Access the RobotContainer object so that it is initialized. This will perform all our
        // button bindings, and put our autonomous chooser on the dashboard.
        RobotContainer
    }


    override fun robotPeriodic() {
        CommandScheduler.getInstance().run()
    }

    override fun disabledInit() {

    }

    override fun disabledPeriodic() {

    }

    override fun autonomousInit() {
        autonomousCommand = RobotContainer.getAutonomousCommand()
        autonomousCommand?.schedule()
    }

    override fun autonomousPeriodic() {

    }

    override fun teleopInit() {
        autonomousCommand?.cancel()
    }

    override fun teleopPeriodic() {

    }

    override fun testInit() {
        CommandScheduler.getInstance().cancelAll()
    }

    override fun testPeriodic() {

    }

    override fun simulationInit() {

    }

    override fun simulationPeriodic() {

    }
}