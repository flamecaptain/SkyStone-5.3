package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class AutoOpMode extends LinearOpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight, leftIntake, rightIntake, clawRaise;
    private Servo foundation;
    private ElapsedTime runTime = new ElapsedTime();

    private void setPower(double bl, double br, double fl, double fr)
    {
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }
    private void setPowerIntake(double power)
    {
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }
    public void runOpMode(){
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");
        clawRaise = hardwareMap.dcMotor.get("clawRaise");
        foundation = hardwareMap.servo.get("foundation");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        double f = .5;
        double b = -.5;
        double s = 0;
        runTime.reset();
        start();
        while(opModeIsActive())
        {

        }
    }
}
