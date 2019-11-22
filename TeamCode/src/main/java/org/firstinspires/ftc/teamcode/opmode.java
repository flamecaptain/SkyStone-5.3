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

    /**
     *
     * @param bl back left power
     * @param br back right power
     * @param fl front left power
     * @param fr front right power
     */
    private void setPower(double bl, double br, double fl, double fr)
    {
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }
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
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addLine("Driver_Actions: ");
            if(Math.abs(gamepad1.left_stick_y) > 0.5 && Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x)) {
                double d = gamepad1.left_stick_y / Math.abs(gamepad1.left_stick_y);
                setPower(d, d, d, d);
                if(gamepad1.left_stick_y > 0)
                {
                    //going forward
                }
                else if(gamepad1.left_stick_y < 0)
                {
                    //going backwards
                }
            }
            if(Math.abs(gamepad1.left_stick_x) > 0.5 && Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y)) {
                double d = gamepad1.left_stick_x / Math.abs(gamepad1.left_stick_x);
                setPower(d, d, d * -1.0, d * -1.0);
                if(gamepad1.left_stick_x > 0)
                {
                    //sliding right
                }
                else if(gamepad1.left_stick_x < 0)
                {
                    //sliding left
                }
            }
            if(Math.abs(gamepad1.right_stick_x) > 0.5 && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_y) && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_x)) {
                double d = gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x);
                setPower(d, d * -1.0, d, d * -1.0);
                if(gamepad1.right_stick_x > 0)
                {
                    //turning right
                }
                else if(gamepad1.right_stick_x < 0)
                {
                    //turning left
                }
            }
            setPower(0, 0, 0, 0);
            telemetry.update();
        }
    }
}