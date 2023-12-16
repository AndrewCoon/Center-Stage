package org.firstinspires.ftc.teamcode.resources;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.objects.Robot;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Camera;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensors;
import org.firstinspires.ftc.teamcode.subsystems.Drone;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagPoseFtc;

import java.util.List;

/*
    Class for making the robot like
    do stuff
 */
public class HardwareController{

    // Subsystems
    private Bucket bucket;
    private Arm arm;
    private Intake intake;
    private DistanceSensors dsensors;
    private Camera camera;
    private Lift lift;
    private Drone drone;
    private Claw claw;

    // Robot we're controlling
    public Robot robot;

    // Roadrunner drive
    SampleMecanumDrive drive;
    HardwareMap hardwareMap;

    // Pass in a hardware map please
    @SuppressLint("SuspiciousIndentation")
    public HardwareController(HardwareMap _hardwareMap) {
        this.hardwareMap = _hardwareMap;

        // Create subsystems
        intake = new Intake();
        dsensors = new DistanceSensors();
        //camera = new Camera(this.hardwareMap);
        lift = new Lift();
        drone = new Drone(this.hardwareMap.get(Servo.class, drone.drone));
        claw = new Claw(this.hardwareMap.get(Servo.class, Claw.HARDWARE_NAME));
        bucket = new Bucket(this.hardwareMap.get(Servo.class, Bucket.HARDWARE_NAME));
        arm = new Arm(this.hardwareMap.get(Servo.class, Arm.HARDWARE_NAME));

        // Init servos
        intake.intakeMotor = this.hardwareMap.get(DcMotor.class, intake.MOTOR);

        // Init lift
        lift.liftMotor = this.hardwareMap.get(DcMotorEx.class, lift.liftName);
        lift.liftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.liftMotor.setDirection(DcMotorSimple.Direction.REVERSE);

        //lift.liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Init distance sensors
        dsensors.left = this.hardwareMap.get(DistanceSensor.class, dsensors.leftSense);
        dsensors.right = this.hardwareMap.get(DistanceSensor.class, dsensors.rightSense);

        // Init drive
        //drive = new SampleMecanumDrive(this.hardwareMap);

    }

    /*
        DRIVE STUFF
     */
    public void driveTo(float xPos, float yPos){
        /*drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .lineTo(new Vector2d(xPos, yPos))
                .build());*/
    }

    public void driveForward(float distance){
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .forward(distance).build());
    }

    public void driveBackwards(float distance){
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .back(distance).build());
    }

    public void driveLeft(float distance){
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .strafeLeft(distance).build());
    }

    public void driveRight(float distance){
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .strafeRight(distance).build());
    }

    /*
        Lift stuff lamo
     */
    public void liftGoToPosition(int pos){
        lift.liftMotor.setTargetPosition(pos);
    }

    public int getLiftPos(){
        return lift.liftMotor.getCurrentPosition();
    }

    public boolean isLiftBusy(){
        return lift.liftMotor.isBusy();
    }
    public void setLiftPower(float s){
        lift.liftMotor.setPower(s);
    }

    public boolean align() {
        // TODO: figure out how to calculate the start pose

        // get the distances
        double leftD = dsensors.left.getDistance(DistanceUnit.INCH);
        double rightD = dsensors.right.getDistance(DistanceUnit.INCH);

        // set the robot's current position relative to backdrop
        Pose2d startPose = new Pose2d(0,0,0);
        drive.setPoseEstimate(startPose);

        // tell it to go up against the backdrop
        drive.followTrajectory(drive.trajectoryBuilder(new Pose2d())
                .splineTo(new Vector2d(0.0),0)
                .build());

        if (leftD == 0 && rightD == 0) {
            return true;
        } else {
            return false;
        }
    }

    public Drone getDrone() {
        return drone;
    }

    public Claw getClaw() {
        return claw;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public Arm getArm() {
        return arm;
    }

    public SampleMecanumDrive getDrive(){
        return drive;
    }

    public void ejectIntake() throws InterruptedException {
        intake.intakeMotor.setPower(.2);
    }

    public double getRightDistance() {
        return dsensors.right.getDistance(DistanceUnit.INCH);
    }

    public double getLeftDistance() {
        return dsensors.left.getDistance(DistanceUnit.INCH);
    }

    // Camera stuff
    public List<AprilTagDetection> getAllAprilTags(){
        return camera.getAllVisibleAprilTags();
    }

    public AprilTagPoseFtc getAprilTagWithId(int id){
        AprilTagPoseFtc tmp = new AprilTagPoseFtc(0,0,0,0,0,0,-100,0,0); // default
        for(AprilTagDetection april : this.getAllAprilTags()){
            if(april.id == id){
                tmp = april.ftcPose;
                break;
            }
        }
        return tmp;
    }
}
