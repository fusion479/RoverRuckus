package org.firstinspires.ftc.teamcode.RoverRuckus.auton;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.hardware.HardwareMain;

@Autonomous(name = "Gravity Land Marker", group = "test")
public class Land extends LinearOpMode{
    public HardwareMain robot = new HardwareMain(this);
    public String position = "UNKNOWN";
    public void runOpMode(){
        robot.init(hardwareMap);
        while (!opModeIsActive()&&!isStopRequested()) {
            if (position.equals("UNKNOWN")){
                position = robot.vision.getOrder();
            }
            telemetry.addData("Gold Position", position);
            telemetry.update();
        }
        telemetry.addData("before", "lock");
        telemetry.update();
        sleep(10000);
        robot.lift.unlock();
        sleep(1000);

        robot.lift.liftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        robot.lift.liftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        sleep(500);
        robot.lift.setHook(0);
        sleep(1000);
        if (position.equals("LEFT")) {
            telemetry.addData("Path", "left");
            robot.drivetrain.driveToPos(0.8,4);
            robot.drivetrain.turn(20, 1); //right
            robot.drivetrain.driveToPos(0.8, 40);
            robot.drivetrain.turn(90, 1);
            robot.drivetrain.turn(25, 1);
            robot.drivetrain.driveToPos(-0.8, -20);
            robot.marker.armUp();
            sleep(500);
            robot.drivetrain.turn(-90, 0.5);
            robot.drivetrain.driveToPos(0.8, 80);
        }
        else{
            if (position.equals("RIGHT")) {
                telemetry.addData("Path", "right");
                robot.drivetrain.driveToPos(0.8,4);
                robot.drivetrain.turn(-25, 1);
                robot.drivetrain.driveToPos(0.8, 40);
                robot.drivetrain.turn(70, 1);
                robot.drivetrain.driveToPos(0.8, 20);
                robot.drivetrain.turn(90,1);
                robot.drivetrain.turn(90,1);
                robot.marker.armUp();
                sleep(500);
//                robot.drivetrain.turn(-90, 0.5);
                robot.drivetrain.driveToPos(-0.8, -80);
            }
            else{
                telemetry.addData("Path", "middle");
                robot.drivetrain.driveToPos(0.8, 50);
                robot.drivetrain.turn(-90, 1);
                sleep(500);
                robot.marker.armUp();
                sleep(500);
                robot.drivetrain.turn(-25, 1);

                robot.drivetrain.driveToPos(-0.8, -80);
            }
        }

    }
}
