package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


public class Lift extends org.firstinspires.ftc.teamcode.hardware.Mechanism {
    private static final double     COUNTS_PER_MOTOR_REV    = 1120;
    private static final double     SPROCKET_DIAMETER_INCHES   = 2.0;
    private static final double     COUNTS_PER_INCH         =
            (COUNTS_PER_MOTOR_REV) / (SPROCKET_DIAMETER_INCHES * 3.1415);
    private static final double liftSpeed = 0.5;
    public DcMotor liftRight;
    public DcMotor liftLeft;
    private DistanceSensor sensorRange;
    private Servo hook;
    public Servo lockLeft;
    public  Servo lockRight;
    public boolean hasSensor;
    public LinearOpMode opMode;

    public Lift(LinearOpMode opMode){
        this.opMode = opMode;
    }

    public void init(HardwareMap hwMap) {
        liftLeft = hwMap.dcMotor.get("liftLeft");
        liftRight = hwMap.dcMotor.get("liftRight");
        hook = hwMap.servo.get("hook");
        lockLeft = hwMap.servo.get("lockLeft");
        lockRight = hwMap.servo.get("lockRight");
        liftRight.setDirection(DcMotorSimple.Direction.FORWARD);
        liftLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        liftLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        liftLeft.setPower(0);
        liftRight.setPower(0);

        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        liftLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setHook(double position){
        hook.setPosition(position);
    }

    public void hook(){
        setHook(0);
    }

    public void unhook(){
        setHook(1);
    }

    public void lock(){
        lockRight.setPosition(0);
        lockLeft.setPosition(1);
    }

    public  void unlock(){
        lockRight.setPosition(1);
        lockLeft.setPosition(0);
        opMode.telemetry.addData("lock",lockRight.getPosition());
    }

    public void setLiftPower(double power){
        liftLeft.setPower(power);
        liftRight.setPower(power);
    }

    public void liftToPos(double distance){
        liftRight.setTargetPosition((int)(liftRight.getCurrentPosition()+distance*COUNTS_PER_INCH));
        liftLeft.setTargetPosition((int)(liftLeft.getCurrentPosition()+distance*COUNTS_PER_INCH));
        setLiftPower(1);
        liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void land() {
        liftLeft.setTargetPosition(300);
        liftRight.setTargetPosition(300);
        liftRight.setPower(1);
        liftLeft.setPower(1);
        liftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        liftRight.setPower(0);
        liftLeft.setPower(0);
    }


    public void sendTelemetry(){
        opMode.telemetry.addData("Left Lift Motor", liftLeft.getCurrentPosition());
        opMode.telemetry.addData("Right Lift Motor", liftRight.getCurrentPosition());
        opMode.telemetry.addData("Left Lift Servo", lockLeft.getPosition());
        opMode.telemetry.addData("Right Lift Servo", lockRight.getPosition());
        opMode.telemetry.addData("Hook", hook.getPosition());
    }

}