package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.stream.*;

@TeleOp
public class opmode extends LinearOpMode {
    private DcMotor frontLeft, frontRight, backLeft, backRight, clawRaise, leftIntake, rightIntake;
    private Servo clawGrab, clawPancake;

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
        clawGrab = hardwareMap.servo.get("clawGrab");
        clawPancake = hardwareMap.servo.get("clawPancake");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive())
        {
            if(Math.abs(gamepad1.left_stick_y) > 0.5)
            {
                double d = Math.abs(gamepad1.left_stick_y) / gamepad1.left_stick_y;
                setPowerWheels(d, d, d, d);
            }
            else if(Math.abs(gamepad1.left_stick_x) > 0.5)
            {
                double d = Math.abs(gamepad1.left_stick_x) / gamepad1.left_stick_x;
                setPowerWheels(d, d, d * -1, d * -1);
            }
            else if(Math.abs(gamepad1.right_stick_x) > 0.5)
            {
                double d = Math.abs(gamepad1.right_stick_x) / gamepad1.right_stick_x;
                setPowerWheels(d, d * -1, d, d * -1);
            }
            else
            {
                setPowerWheels(0, 0, 0, 0);
            }

            if(gamepad1.left_trigger != 0)
            {
                clawGrab.setDirection(Servo.Direction.FORWARD);
                clawGrab.setPosition(clawGrab.getPosition() + 0.1);
            }
            else if(gamepad1.right_trigger != 0)
            {
                clawGrab.setDirection(Servo.Direction.REVERSE);
                clawGrab.setPosition(clawGrab.getPosition() + 0.1);
            }

            if(gamepad1.dpad_up)
            {
                clawRaise.setPower(1);
            }
            else if(gamepad1.dpad_down)
            {
                clawRaise.setPower(-1);
            }
            else
            {
                clawRaise.setPower(0);
            }

            if(gamepad1.dpad_left)
            {
                clawPancake.setDirection(Servo.Direction.REVERSE);
                clawPancake.setPosition(clawPancake.getPosition() + 0.1);
            }
            else if(gamepad1.dpad_right)
            {
                clawPancake.setDirection(Servo.Direction.FORWARD);
                clawPancake.setPosition(clawPancake.getPosition() + 0.1);
            }

            if(gamepad1.a)
            {
                if(leftIntake.getPower() == 0)
                    setPowerIntake(1);
                else
                    setPowerIntake(0);
            }
            if(gamepad1.y)
            {
                if(frontRight.getDirection().equals(DcMotorSimple.Direction.FORWARD))
                {
                    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
                    backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
                }
                else if(frontRight.getDirection().equals(DcMotorSimple.Direction.REVERSE))
                {
                    frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                    backRight.setDirection(DcMotorSimple.Direction.FORWARD);
                    backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
                }
            }
        }
    }
    //left stick y: move forward / backward
    //left stick x: strafe left / right
    //right stick x: turn left / right
    //left trigger: apply claw
    //right trigger: release claw
    //digital pad up / down: raise / lower claw
    //digital pad left / right: pancake left / right
    //a button: toggle intake
    //y button: reverse wheel direction
}