package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class TestIntake extends LinearOpMode {
    private DcMotor leftIntake, rightIntake, clawRaise;
    private Servo clawGrab, clawPancake;
    private ElapsedTime runTime;
    @Override
    public void runOpMode() throws InterruptedException {
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");
        clawRaise = hardwareMap.dcMotor.get("clawRaise");
        clawGrab = hardwareMap.servo.get("clawGrab");
        clawPancake = hardwareMap.servo.get("clawPancake");
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        start();
        runTime.reset();
        leftIntake.setPower(1);
        rightIntake.setPower(1);
        while(opModeIsActive())
        {
            switch((int)runTime.seconds())
            {
                case 2: leftIntake.setPower(0); rightIntake.setPower(0); break;
                case 3: clawGrab.setPosition(0.1); break;
                case 5: clawRaise.setPower(0.3); break;
                case 7: clawRaise.setPower(0); break;
                case 8: clawPancake.setPosition(0.3);
                case 10: clawGrab.setDirection(Servo.Direction.REVERSE); clawGrab.setPosition(0); break;
            }
        }
    }
}