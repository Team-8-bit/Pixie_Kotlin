package org.team9432.pixie

enum class State(val shooter: Shooter, val loader: Loader, val hood: Hood) {
    IDLE(Shooter.IDLE, Loader.IDLE, Hood.IDLE),
    LOW_SHOT(Shooter.LOW, Loader.LOAD, Hood.LOW),
    MID_SHOT(Shooter.MID, Loader.LOAD, Hood.MID),
    HIGH_SHOT(Shooter.HIGH, Loader.LOAD, Hood.HIGH),

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

    enum class Hood(val angle: Double) {
        IDLE(0.0),
        LOW(0.1),
        MID(0.2),
        HIGH(0.3),
    }
}
