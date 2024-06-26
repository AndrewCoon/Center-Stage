package org.firstinspires.ftc.teamcode.opsmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.subsystems.AprilTag;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;
@TeleOp(name="PixelCalib")
public class PixelRandomizationCalibrator extends LinearOpMode {

    AprilTag aprilTag;

    @Override
    public void runOpMode() throws InterruptedException {
        aprilTag = new AprilTag(hardwareMap);
        waitForStart();
        while (opModeIsActive()) {
            // Get april tag pos
            AprilTagPoseFtc pose = aprilTag.getAprilTagWithId(420);
            if(pose.range < 0){
                telemetry.addLine("APRIL TAG WITH ID 420 NOT FOUND");
            }else{
                telemetry.addData("X", "%f", pose.x);
            }
            telemetry.update();
        }
    }
}
