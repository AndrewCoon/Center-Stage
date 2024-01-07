package org.firstinspires.ftc.teamcode.resources;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;

/**
    Makes sure trajectories are linked together
 */
public class RoadRunnerHelper{
    private SampleMecanumDrive drive;
    private Trajectory prev;
    private Pose2d pose;

    public RoadRunnerHelper(SampleMecanumDrive drive){
        this.drive = drive;
        pose = new Pose2d(0, 0, 0);
        drive.setPoseEstimate(pose);
    }

    // [NOTE] All functions return RoadRunnerHelper so its possible
    // to chain functions together
    // (im assuming thats how roadrunner does it internally idk i havent checked)

    // Move functions

    /**
     * I dare you to guess what this does
     * @param dist
     */
    public RoadRunnerHelper forward(double dist){
        Trajectory traj;
        if(prev == null){
            if(pose == null)
                traj = drive.trajectoryBuilder(new Pose2d()).forward(dist).build();
            else{
                traj = drive.trajectoryBuilder(pose).forward(dist).build();
                pose = null;
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).forward(dist).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    public RoadRunnerHelper reverse(double dist){
        Trajectory traj;
        if(prev == null){
            if(pose == null)
                traj = drive.trajectoryBuilder(new Pose2d()).back(dist).build();
            else{
                traj = drive.trajectoryBuilder(pose).back(dist).build();
                pose = null;
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).back(dist).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    /**
     * Turn to angle (positive is turning left)
     * @param angle Turn angle
     */
    public RoadRunnerHelper turn(double angle)  {
        Trajectory traj = null;
        if(prev == null){
            if(pose == null)
                throw new RotateWithoutPreviousPathException();
            else{
                traj = drive.trajectoryBuilder(pose).splineTo(new Vector2d(prev.end().getX() + 0.01, prev.end().getY() - 0.01) , Math.toRadians(angle)).build();
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).splineTo(new Vector2d(prev.end().getX() + 0.01, prev.end().getY() - 0.01) , Math.toRadians(angle)).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    /**
     * Turn to angle with speed (positive is turning left)
     * @param angle Turn angle (in degrees)
     * @param speed Rotation speed
     * @param angAcc Max angular acceleration
     */
    public RoadRunnerHelper turn(double angle, double speed, double angAcc)  {
        TrajectorySequence traj = null;
        if(prev == null){
            if(pose == null)
                throw new RotateWithoutPreviousPathException();
            else{
                traj = drive.trajectorySequenceBuilder(pose).turn(Math.toRadians(angle), speed, angAcc).build();
                pose = null;
            }
        }else{
            traj = drive.trajectorySequenceBuilder(prev.end()).turn(Math.toRadians(angle), speed, angAcc).build();

        }
        drive.followTrajectorySequence(traj);
        prev = null;
        pose = traj.end();
        return this;
    }

    // Strafe
    public RoadRunnerHelper strafeRight(double dist){
        Trajectory traj;
        if(prev == null){
            if(pose == null)
                traj = drive.trajectoryBuilder(new Pose2d()).strafeRight(dist + (dist * 0.13)).build();
            else{
                traj = drive.trajectoryBuilder(pose).strafeRight(dist + (dist * 0.13)).build();
                pose = null;
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).strafeRight(dist + (dist * 0.13)).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    public RoadRunnerHelper strafeLeft(double dist){
        Trajectory traj;
        if(prev == null){
            if(pose == null)
                traj = drive.trajectoryBuilder(new Pose2d()).strafeLeft(dist + (dist * 0.13)).build();
            else{
                traj = drive.trajectoryBuilder(pose).strafeLeft(dist + (dist * 0.13)).build();
                pose = null;
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).strafeLeft(dist + (dist * 0.13)).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    /**
     * Spline to location
     * @param x X Location (wow)
     * @param y Y Location (crazy)
     * @param ang Angle (degrees) (wack)
     * @return
     */
    public RoadRunnerHelper splineToLinearHeading(double x, double y, double ang){
        Trajectory traj;
        Pose2d endPose = new Pose2d(x, y, Math.toRadians(ang));
        if(prev == null){
            if(pose == null) {
                traj = drive.trajectoryBuilder(new Pose2d()).splineToLinearHeading(endPose, 0).build();
            }else{
                traj = drive.trajectoryBuilder(pose).splineToLinearHeading(endPose, 0).build();
                pose = null;
            }
        }else{
            traj = drive.trajectoryBuilder(prev.end()).splineToLinearHeading(endPose, 0).build();
        }
        drive.followTrajectory(traj);
        prev = traj;
        return this;
    }

    /**
     * Waits for seconds
     * @param seconds wait for seconds
     * @return Not important, dont worry about it
     * @throws InterruptedException oops
     */
    public RoadRunnerHelper waitSeconds(double seconds) throws InterruptedException {
        drive.wait((long) seconds);
        return this;
    }


    /**
     * Clears the current path to start a new one
     * I don't know when you would use this but
     * why not maybe some edge case
     */
    public void resetPath(){
        prev = null;
    }

}

