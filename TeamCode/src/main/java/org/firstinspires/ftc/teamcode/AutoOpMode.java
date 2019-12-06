package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class AutoOpMode extends LinearOpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight, leftIntake, rightIntake, clawRaise;
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
/*            switch((int)runTime.seconds())
            {
                case 0: setPower(f, f, f, f); break; //go forward
                case 2: setPower(s, s, s, s); break; //stop
                case 3: setPower(b, b, b, b); break; //go backward
                case 5: setPower(s, s, s, s); break; //stop
                case 6: setPower(b, b, f, f); break; //strafe ____
                case 8: setPower(s, s, s, s); break; //stop
                case 9: setPower(f, f, b, b); break; //strafe ____
                case 11: setPower(s, s, s, s); break; //stop
                case 12: setPower(f, b, f, b); break; //turn right
                case 14: setPower(s, s, s, s); break; //stop
                case 15: setPower(b, f, b, f); break; //turn left
                case 17: setPower(s, s, s, s); break; //stop
            }*/
            switch((int)runTime.seconds())
            {
                case 0: setPowerIntake(0.75); break;
                case 10: setPowerIntake(0.75); break;
            }
        }
    }
}
