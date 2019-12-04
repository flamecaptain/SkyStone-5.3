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
        double f = .5;
        double b = -.5;
        double s = 0;
        runTime.reset();
        start();
        while(opModeIsActive()){
            while(runTime.seconds() < 1){
                setPower(f,f,f,f);
            }
            while(runTime.seconds() < 2 && runTime.seconds() >= 1){
                setPower(b,b,b,b);
            }
            while(runTime.seconds() < 3 && runTime.seconds() >= 2){
                setPower(b,b,f,f);
            }
            while(runTime.seconds() < 4 && runTime.seconds() >= 3){
                setPower(f,f,b,b);
            }
            while(runTime.seconds() < 5 && runTime.seconds() >= 4){
                setPower(f,b,f,b);
            }
            while(runTime.seconds() < 6 && runTime.seconds() >= 5){
                setPower(b,f,b,f);
            }
            if (runTime.seconds() >= 6) {
                setPower(s,s,s,s);
            }
        }
/*        while(opModeIsActive())
        {
            if(runTime.seconds() < 1)
                setPower(f, f, f, f);
            else if(runTime.seconds() < 2)
                setPower(b, b, b, b);
            else if(runTime.seconds() < 3)
                setPower(b, b, f, f);
            else if(runTime.seconds() < 4)
                setPower(f, f, b, b);
            else if(runTime.seconds() < 5)
                setPower(f, b, f, b);
            else if(runTime.seconds() < 6)
                setPower(b, f, b, f);
            else
                setPower(s, s, s, s);
        }*/
    }
}
