package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class opmode extends LinearOpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight, clawRaise, leftIntake, rightIntake;
    private CRServo clawGrab, clawPancake, foundation;
    private Gamepad gp;
    private void setPowerWheels(double bl, double br, double fl, double fr)
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

    public void runOpMode()
    {
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        clawRaise = hardwareMap.dcMotor.get("clawRaise");
        leftIntake = hardwareMap.dcMotor.get("leftIntake");
        rightIntake = hardwareMap.dcMotor.get("rightIntake");
        clawGrab = hardwareMap.crservo.get("clawGrab");
        clawPancake = hardwareMap.crservo.get("clawPancake");
        foundation = hardwareMap.crservo.get("foundation");
        gp = gamepad1;
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive())
        {
            if(Math.abs(gp.left_stick_y) > 0.5)
            {
                double d = 0.6;
                if(gp.left_stick_y < 0)
                    d *= -1;
                setPowerWheels(d, d, d, d);
            }
            else if(Math.abs(gp.left_stick_x) > 0.5)
            {
                double d = 0.6;
                if(gp.left_stick_x < 0)
                    d *= -1;
                setPowerWheels(d, -d, -d, d);
            }
            else if(Math.abs(gp.right_stick_x) > 0.5)
            {
                double d = 0.6;
                if(gp.right_stick_x < 0)
                    d *= -1;
                setPowerWheels(-d, d, -d, d);
            }
            else
            {
                setPowerWheels(0, 0, 0, 0);
            }

            if(gp.left_trigger != 0)
            {
                clawGrab.setDirection(CRServo.Direction.REVERSE);
                clawGrab.setPower(1);
            }
            else if(gp.right_trigger != 0)
            {
                clawGrab.setDirection(CRServo.Direction.FORWARD);
                clawGrab.setPower(1);
            }
            else
            {
                clawGrab.setPower(0);
            }

            if(gp.left_bumper)
            {
                foundation.setDirection(CRServo.Direction.REVERSE);
                foundation.setPower(0.75);
            }
            else if(gp.right_bumper)
            {
                foundation.setDirection(CRServo.Direction.FORWARD);
                foundation.setPower(0.75);
            }
            else
            {
                foundation.setPower(0);
            }

            if(gp.dpad_up)
            {
                clawRaise.setPower(0.3);
            }
            else if(gp.dpad_down)
            {
                clawRaise.setPower(-0.3);
            }
            else
            {
                clawRaise.setPower(0);
            }

            if (gp.dpad_left)
                clawPancake.setPower(-0.3);
            else if (gp.dpad_right)
                clawPancake.setPower(0.3);
            else
                clawPancake.setPower(0);

            if(gp.a)
            {
                setPowerIntake(0.75);
            }
            else if(gp.b)
            {
                setPowerIntake(-0.75);
            }
            else
                setPowerIntake(0);

            if(gp.y)
            {
                if(gp == gamepad1)
                    gp = gamepad2;
                else if(gp == gamepad2)
                    gp = gamepad1;
            }
        }
    }
    //left stick y: move forward / backward
    //left stick x: strafe left / right
    //right stick x: turn left / right
    //right trigger: apply claw
    //left trigger: release claw
    //right bumper: apply foundation grabber
    //left bumper: release foundation grabber
    //digital pad up / down: raise / lower claw
    //digital pad left / right: pancake claw left / right
    //a button: apply intake wheels inward
    //b button: apply intake wheels outward
    //y button: pass controller
}