package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class Drone {
    public static final String HARDWARE_NAME = "servoD";

    private static final float DRONE_READY = 0.38f;
    private static final float DRONE_LAUNCHED = 0.65f;

    private final Servo servo;

    public Drone(Servo servo) {
        this.servo = servo;
        this.servo.setPosition(DRONE_READY);
    }

    public void reset() {
        servo.setPosition(DRONE_READY);
    }

    public void launch() {
        servo.setPosition(DRONE_LAUNCHED);
    }

    public double getPosition() {
        return servo.getPosition();
    }


}
