package org.firstinspires.ftc.teamcode.objects;

import org.firstinspires.ftc.teamcode.subsystems.Arm;
import org.firstinspires.ftc.teamcode.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class RobotSettings {
    public static class Preferences {
        // Vision
        public static boolean useWebCam = true;
        public static boolean hasWebCam2 = false;
        public static boolean useAprilTagVision = true;
        public static boolean useColorBlobVision = true;
        public static boolean showVisionView = true;
        // Drive Base
        public static boolean useExternalOdometry = true;
    }

    public final String ROBOT_NAME = "gOoFbALL";

    // Drive
    public static final String BANA_LFDRIVE_MOTOR = "leftFront";
    public static final String BANA_RFDRIVE_MOTOR = "rightFront";
    public static final String BANA_LBDRIVE_MOTOR = "leftRear";
    public static final String BANA_RBDRIVE_MOTOR = "rightRear";

    // Field dimensions.
    // TODO: Verify that these measurements are correct
    public static final double FULL_FIELD_INCHES                = 141.0;
    public static final double HALF_FIELD_INCHES                = FULL_FIELD_INCHES / 2.0;
    public static final double FULL_TILE_INCHES                 = 23.75;

    public static final double ROBOT_LENGTH                     = 17.0;
    public static final double ROBOT_WIDTH                      = 17.0;
    public static final double DRIVE_BASE_LENGTH                = 0;
    public static final double DRIVE_BASE_WIDTH                 = 0;

    /*
     Camera stuff
    */
    public static final int CAM_IMAGE_WIDTH                     = 640;
    public static final int CAM_IMAGE_HEIGHT                    = 480;
    public static final OpenCvCameraRotation CAM_ORIENTATION    = OpenCvCameraRotation.UPRIGHT;

    // Camera location on robot.
    public static final double CAM_FRONT_OFFSET                 = 0;    //Camera offset from front of robot in inches
    public static final double CAM_LEFT_OFFSET                  = 0;    //Camera offset from left of robot in inches
    public static final double CAM_HEIGHT_OFFSET                = 0;    //Camera offset from floor in inches
    public static final double CAM_TILT_DOWN                    = 0;    //Camera tilt down angle from horizontal in deg

    // Locations
    public static final float BACKSTAGE_BLUE                    = 22.0f;
    public static final float BACKSTAGE_RED                     = (float) (FULL_FIELD_INCHES - 22.0f);


    // Positions of the pixel for autnomous
    public static final int PIXEL_LEFT = 133;                            // Fill value in with real value later
    public static final int PIXEL_CENTER = 332;                        // Fill value in with real value later
    public static final int PIXEL_RIGHT = 332;                         // Fill value in with real value later
}
