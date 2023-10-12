package org.team9432

enum class State(val shooter: Shooter, val loader: Loader) {
    IDLE(Shooter.IDLE, Loader.IDLE),
    LOW_SHOT(Shooter.LOW, Loader.LOAD),
    MID_SHOT(Shooter.MID, Loader.LOAD),
    HIGH_SHOT(Shooter.HIGH, Loader.LOAD),

    ;

    enum class Shooter(val rpm: Double) {
        IDLE(0.0),
        LOW(1000.0),
        MID(2000.0),
        HIGH(3000.0),
    }

    enum class Loader(val speed: Double) {
        IDLE(0.0),
        LOAD(0.25),
        UNLOAD(-0.25),
    }
}
