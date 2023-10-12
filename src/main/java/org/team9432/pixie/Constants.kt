package org.team9432.pixie

object Constants {
    object Drivetrain {
        const val MAX_OUTPUT = 0.5
        const val DEADBAND = 0.05
    }

    object Shooter {
        const val P = 0.0
        const val I = 0.0
        const val D = 0.0

        const val RPM_TOLERANCE = 100
    }

    object Hood {
        const val P = 0.0
        const val I = 0.0
        const val D = 0.0

        const val ANGLE_TOLERANCE = 0.1
    }
}