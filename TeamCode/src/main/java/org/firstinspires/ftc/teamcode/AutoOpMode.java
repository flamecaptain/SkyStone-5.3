package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class AutoOpMode extends LinearOpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    private ElapsedTime runTime = new ElapsedTime();


    private void setPower(double bl, double br, double fl, double fr)
    {
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }

    public void runOpMode(){
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        double f = .5;
        double b = -.5;
        double s = 0;
        runTime.reset();
        start();
        while(opModeIsActive())
        {
            if(runTime.seconds() < 5)
                setPower(f, f, f, f); //go forward
            else if(runTime.seconds() < 10)
                setPower(b, b, b, b); //go backward
            else if(runTime.seconds() < 15)
                setPower(b, b, f, f); //strafe ____
            else if(runTime.seconds() < 20)
                setPower(f, f, b, b); //strafe ____
            else if(runTime.seconds() < 25)
                setPower(f, b, f, b); //turning right
            else if(runTime.seconds() < 30)
                setPower(b, f, b, f); //turning left
            else
                setPower(s, s, s, s);
        }
    }
}
