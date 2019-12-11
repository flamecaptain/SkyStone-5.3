package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class TestIntake extends LinearOpMode {
    private DcMotor leftIntake, rightIntake;
    @Override
    public void runOpMode() throws InterruptedException {
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        start();
        leftIntake.setPower(1);
        rightIntake.setPower(1);
        while(opModeIsActive())
        {

        }
    }
}