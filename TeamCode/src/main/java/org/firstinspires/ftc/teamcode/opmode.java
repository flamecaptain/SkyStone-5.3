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
     * Sets all motors to the input power n
     * @param n
     */
    private void setPower(double n)
    {
        backLeft.setPower(n);
        backRight.setPower(n);
        frontLeft.setPower(n);
        frontRight.setPower(n);
    }
    /**
     * Sets the left two motors to the input power n and the right two motors to the negative input power n
     * @param n
     */
    private void setPowerReverse(double n)
    {
        backLeft.setPower(n);
        backRight.setPower(n * -1);
        frontLeft.setPower(n);
        frontRight.setPower(n * -1);
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
        final String[] driver_action = new String[]{
                "forward drive","backwards drive","slide right","slide left",
                "turn left", "turn right"
        };
        waitForStart();
        while(opModeIsActive()) {
            telemetry.addLine("Driver_Actions : ");
            if(Math.abs(gamepad1.left_stick_y) > 0.5 && Math.abs(gamepad1.left_stick_y) > Math.abs(gamepad1.left_stick_x))
                setPower(gamepad1.left_stick_y / Math.abs(gamepad1.left_stick_y));
            if(Math.abs(gamepad1.left_stick_x) > 0.5 && Math.abs(gamepad1.left_stick_x) > Math.abs(gamepad1.left_stick_y))
                setPower(gamepad1.left_stick_x / Math.abs(gamepad1.left_stick_x));
            if(Math.abs(gamepad1.right_stick_x) > 0.5 && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_y) && Math.abs(gamepad1.right_stick_x) > Math.abs(gamepad1.left_stick_x))
                setPower(gamepad1.right_stick_x / Math.abs(gamepad1.right_stick_x) * -1.0);
            setPower(0);
            telemetry.update();
            
        }
    }
}