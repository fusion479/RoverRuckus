package org.firstinspires.ftc.teamcode.RoverRuckus.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.hardware.HardwareMain;

@Autonomous(name = "sampleTest", group = "Test")
public class sampletest extends LinearOpMode {
    public HardwareMain robot = new HardwareMain(this);
    public void runOpMode(){
        robot.init(hardwareMap);
        waitForStart();
        robot.sampleTest();
    }
}
