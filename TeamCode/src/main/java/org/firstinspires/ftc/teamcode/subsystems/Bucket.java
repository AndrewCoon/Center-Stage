package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;

public class Bucket {
    public static final String ROTATION_SERVO = "servoR";
    public static final String TRAPDOOR_SERVO = "servoT";

    public Servo bucketRotation = null;
    public Servo bucketTrapdoor = null;
}