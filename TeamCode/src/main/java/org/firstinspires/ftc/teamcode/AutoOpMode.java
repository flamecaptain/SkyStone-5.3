package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Autonomous

public class AutoOpMode extends LinearOpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight, leftIntake, rightIntake, clawRaise;
    private Servo foundation, clawGrab;
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
    public void runOpMode() {
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");
        clawRaise = hardwareMap.dcMotor.get("clawRaise");
        foundation = hardwareMap.servo.get("foundation");
        clawGrab = hardwareMap.servo.get("clawGrab");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        runTime.reset();
        start();
        while(opModeIsActive())
        {
            if(runTime.seconds() < 1)
            {
                clawGrab.setPosition(0);
            }
            else if(runTime.seconds() < 2)
            {
                clawGrab.setPosition(0.5);
            }
            else if(runTime.seconds() < 3)
            {
                clawGrab.setPosition(1);
            }
            else if(runTime.seconds() < 4)
            {
                clawGrab.setDirection(Servo.Direction.REVERSE);
                clawGrab.setPosition(0.5);
            }
            else if(runTime.seconds() < 5)
            {

            }
        }
    }
}
