package org.firstinspires.ftc.teamcode.opsmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.objects.Robot;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Claw;

@TeleOp(name="Servo Calib")
public class ServoCalib extends LinearOpMode {
    Robot robot;

    // Float
    float armPos = 0.45f, bucketPos = 0.0f, doorPos = 0.0f;
    int servoNumber = 0; //0 -> ARM, 1-> BUCKET, 2-> CLAW
    Servo armServo;
    Servo bucketServo;
    Servo clawServo;

    boolean changeDown = false;

    float diff = 0.0005f;

    @Override
    public void runOpMode() throws InterruptedException {
        clawServo = this.hardwareMap.get(Servo.class, Claw.HARDWARE_NAME);
        bucketServo = this.hardwareMap.get(Servo.class, Bucket.HARDWARE_NAME);

        waitForStart();


        //robot = new Robot(Auto_RED_FAR_SIDE.StartPos.BACKSTAGE, hardwareMap);
        while (opModeIsActive()) {

            // Switch type
            if (gamepad1.dpad_right) {
                if (changeDown == false) {
                    servoNumber++;
                    if (servoNumber > 2)
                        servoNumber = 0;
                }
                changeDown = true;
            } else {
                changeDown = false;
            }

            if(armPos < 0.0f)armPos = 0.0f;
            if(armPos > 1.0f)armPos = 1.0f;
            if(bucketPos < 0.0f)bucketPos = 0.0f;
            if(bucketPos > 1.0f)bucketPos = 1.0f;
            if(doorPos < 0.0f)doorPos = 0.0f;
            if(doorPos > 1.0f)doorPos = 1.0f;


            telemetry.addData("ARM", "%f", armPos);
            telemetry.addData("BUCKET", "%f", bucketPos);
            telemetry.addData("DOOR", "%f", doorPos);
            telemetry.addLine("=======================");
            switch (servoNumber) {
                case 0: //ARM
                    if (gamepad1.dpad_up) armPos += diff;
                    if (gamepad1.dpad_down) armPos -= diff;
                    //hardwareController.getArm() //TODO fix once the arm is refactored
                    telemetry.addLine("CURRENT IS ARM");
                    break;
                case 1:
                    if (gamepad1.dpad_up) bucketPos += diff;
                    if (gamepad1.dpad_down) bucketPos -= diff;
                    bucketServo.setPosition(bucketPos);
                    telemetry.addLine("CURRENT IS BUCKET");
                    break;
                case 2:
                    if (gamepad1.dpad_up) doorPos += diff;
                    if (gamepad1.dpad_down) doorPos -= diff;
                    clawServo.setPosition(doorPos);
                    telemetry.addLine("CURRENT IS DOOR");
                    break;

            }
            telemetry.update();
        }
    }
}
