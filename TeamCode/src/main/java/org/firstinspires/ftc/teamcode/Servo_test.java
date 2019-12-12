package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@TeleOp
public class Servo_test extends LinearOpMode {
    private Servo test;
    static final double turnAmount = .01;
    static final int timeWait = 50;
    static final double maxPosition = 1;
    static final double minPosition = 0;
    double position = (maxPosition - minPosition)/2;
    public void runOpMode(){
        while(opModeIsActive()){
            if(gamepad1.dpad_down && position > minPosition){
                position = position - turnAmount;
            }else if(gamepad1.dpad_up && position < maxPosition){
                position = position + turnAmount;
            }
            sleep(timeWait);
        }
    }
}

