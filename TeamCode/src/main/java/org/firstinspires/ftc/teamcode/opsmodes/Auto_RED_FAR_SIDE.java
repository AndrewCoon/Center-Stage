package org.firstinspires.ftc.teamcode.opsmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.objects.Marker;

@Autonomous(group="drive")
public class Auto_RED_FAR_SIDE extends AutonomousBaseFarSide {
    
    @Override
    public Marker getMarker() {
        return Marker.RED;
    }
}
