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
    private void switchControls()
    {
        if(gp == gamepad1) {
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            gp = gamepad2;
        }
        else if(gp == gamepad2)
        {
            backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            backRight.setDirection(DcMotorSimple.Direction.FORWARD);
            frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
            gp = gamepad1;
        }
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
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);
        gp = gamepad1;
        waitForStart();
        while(opModeIsActive())
        {

        }
        while(opModeIsActive()) {
            if(Math.abs(gp.left_stick_y) > 0.5 && Math.abs(gp.left_stick_y) > Math.abs(gp.left_stick_x)) {
                double d = gp.left_stick_y / Math.abs(gp.left_stick_y);
                setPowerWheels(d, d, d, d);
                if(gp.left_stick_y > 0)
                {
                    //going forward
                }
                else if(gp.left_stick_y < 0)
                {
                    //going backwards
                }
            }
            if(Math.abs(gp.left_stick_x) > 0.5 && Math.abs(gp.left_stick_x) > Math.abs(gp.left_stick_y)) {
                double d = gp.left_stick_x / Math.abs(gp.left_stick_x);
                setPowerWheels(d, d, d * -1.0, d * -1.0);
                if(gp.left_stick_x > 0)
                {
                    //sliding right
                }
                else if(gp.left_stick_x < 0)
                {
                    //sliding left
                }
            }
            if(Math.abs(gp.right_stick_x) > 0.5 && Math.abs(gp.right_stick_x) > Math.abs(gp.left_stick_y) && Math.abs(gp.right_stick_x) > Math.abs(gp.left_stick_x)) {
                double d = gp.right_stick_x / Math.abs(gp.right_stick_x);
                setPowerWheels(d, d * -1.0, d, d * -1.0);
                if(gp.right_stick_x > 0)
                {
                    //turning right
                }
                else if(gp.right_stick_x < 0)
                {
                    //turning left
                }
            }
            if(gp.left_trigger > 0)
            {
                clawGrab.setDirection(Servo.Direction.FORWARD);
                clawGrab.setPosition(gp.left_trigger);
            }
            else if(gp.right_trigger > 0)
            {
                clawGrab.setDirection(Servo.Direction.REVERSE);
                clawGrab.setPosition(gp.right_trigger);
            }
            else if(gp.dpad_up)
            {
                clawRaise.setDirection(DcMotorSimple.Direction.FORWARD);
                clawRaise.setPower(1);
            }
            else if(gp.dpad_down)
            {
                clawRaise.setDirection(DcMotorSimple.Direction.REVERSE);
                clawRaise.setPower(1);
            }
            else if(gp.dpad_left)
            {
                clawPancake.setDirection(Servo.Direction.FORWARD);
                clawPancake.setPosition(clawPancake.getPosition() + 0.1);
            }
            else if(gp.dpad_right)
            {
                clawPancake.setDirection(Servo.Direction.REVERSE);
                clawPancake.setPosition(clawPancake.getPosition() + 0.1);
            }
            if(gp.y)
                switchControls();
            if(gp.a)
            {
                if(leftIntake.getPower() != 0)
                {
                    setPowerIntake(0);
                }
                else
                {
                    setPowerIntake(1);
                }
            }
            if(gp.b)
            {
                setPowerIntake(-1);
            }
            setPowerWheels(0, 0, 0, 0);
            clawRaise.setPower(0);
        }
    }
}