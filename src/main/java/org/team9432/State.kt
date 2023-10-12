package org.team9432

enum class State(val shooter: Shooter) {
    IDLE(Shooter.IDLE),
    LOW_SHOT(Shooter.LOW),
    MID_SHOT(Shooter.MID),
    HIGH_SHOT(Shooter.HIGH),

    ;


    enum class Shooter(val rpm: Double) {
        IDLE(0.0),
        LOW(1000.0),
        MID(2000.0),
        HIGH(3000.0),
    }
}
