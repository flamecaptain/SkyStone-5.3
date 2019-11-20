package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import org.firstinspires.ftc.robotcore.external.hardware.camera.CameraName;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.stream.*;

@TeleOp
public class opmode extends LinearOpMode {
    private DcMotor   frontLeft, frontRight, backLeft, backRight, clawRaise;
    private Servo clawGrab;
    public void runOpMode()
    {
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        clawGrab = hardwareMap.servo.get("clawGrab");
        clawRaise = hardwareMap.dcMotor.get("clawRaise");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        final String[] driver_action = new String[]{
                "forward drive","backwards drive","slide right","slide left",
                "turn left", "turn right"
        };
        waitForStart();
        while(opModeIsActive())
        {
            telemetry.addLine("Driver_Actions : ");
            if(gamepad1.left_stick_y > .5 && gamepad1.left_stick_y > gamepad1.left_stick_x){
                backLeft.setPower(1);
                backRight.setPower(1);
                frontRight.setPower(1);
                frontLeft.setPower(1);
            }
            if(gamepad1.left_stick_y < -.5 && gamepad1.left_stick_y < gamepad1.left_stick_x){
                backLeft.setPower(-1);
                backRight.setPower(-1);
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
            }
            if(gamepad1.left_stick_x > .5 && gamepad1.left_stick_x > gamepad1.left_stick_y){
                backLeft.setPower(1);
                backRight.setPower(1);
                frontLeft.setPower(-1);
                frontRight.setPower(-1);
            }
            if(gamepad1.left_stick_x < -.5 && gamepad1.left_stick_x < gamepad1.left_stick_y)
                backLeft.setPower(-1);
                backRight.setPower(-1);
                frontLeft.setPower(1);
                frontRight.setPower(1);
            }
            if(gamepad1.right_stick_x > .5 && gamepad1.right_stick_x > gamepad1.left_stick_y && gamepad1.right_stick_x > gamepad1.left_stick_x){
                backLeft.setPower(-1);
                backRight.setPower(1);
                frontLeft.setPower(-1);
                frontRight.setPower(1);
            }
            if(gamepad1.right_stick_x < -.5 && gamepad1.right_stick_x < gamepad1.left_stick_y && gamepad1.right_stick_x < gamepad1.left_stick_x){
                backLeft.setPower(1);
                backRight.setPower(-1);
                frontLeft.setPower(1);
                frontRight.setPower(-1);
            }
            backRight.setPower(0);
            backLeft.setPower(0);
            frontRight.setPower(0);
            frontLeft.setPower(0);
            telemetry.update();
    }
}