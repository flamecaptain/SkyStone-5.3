package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcontroller.external.samples.ConceptVuforiaNavigationWebcam;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous

public class AutoOpModeMarkIdWebcam extends LinearOpMode {

    public int wheelRPS = 1;
    public int strafeDPS = 100;

    private DcMotor frontLeft, frontRight, backLeft, backRight;

    private static final String VUFORIA_KEY =
            "ARAcHmb/////AAABmdKWGmLaVkfunxi3zHut0ixX66ybfomfYaggBxYfNEH1ykXbDcszSgoU4qPwWWcO8e2rArv5bsK2ilefGCb+42Ijr/GZXbIsuxQSzzp7qHiFR71MjmozfNiaRCzUIPL5Tbit26z90iKMHkgxudhCpRqCmJiHghvLs9wGjY83MNLNrFAMiLfoKQX3Ao4iOFXm4qHr1h1ZlD2osVt0hMlF9w0jH9u+mLH8p60550r2zQIdmDK18ufuYbbCnSOwGBrdfpr+IE0Qj2zjENGftNEwhI2sI2L8eYr0994k86wt17oHs8mn4yRbJmIYhjCO+G2jv1o3PMrRhI0fzKJq1qQb6BIrikP9LKXPDlpFa5tMA4vQ";

    OpenGLMatrix LastLocation = null;

    VuforiaLocalizer vuforia;//stores instance of Vuforia localization engine

    WebcamName webcamName;//used to identify webcam

    private void setPowerWheels(double bl, double br, double fl, double fr)
    {
        backLeft.setPower(bl);
        backRight.setPower(br);
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
    }
    @Override public void runOpMode() {
        backRight = hardwareMap.dcMotor.get("backRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        ElapsedTime spinFor = new ElapsedTime();

        webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");//retrieves camera
        //displays webcam feed to phone
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        //sets parameters for VL
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = webcamName;
        this.vuforia = ClassFactory.getInstance().createVuforia(parameters);
        //loads data for relic recovery
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("Skystone");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        telemetry.update();
        waitForStart();
        relicTrackables.activate();

        while(opModeIsActive()) {
            //determines whether a vuMark is visible
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                telemetry.addData("VuMark", "%s visible", vuMark);
                //displays position of vuMark
                OpenGLMatrix pose = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getFtcCameraFromTarget();
                telemetry.addData("Pose", format(pose));
                if (pose != null) {
                    VectorF trans = pose.getTranslation();
                    Orientation rot = Orientation.getOrientation(pose, AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);

                    // Extracts X, Y, and Z offset of the target relative to the robot
                    double tX = trans.get(0);
                    double tY = trans.get(1);
                    double tZ = trans.get(2);

                    // Extracts rotational components of the target relative to the robot
                    double rX = rot.firstAngle;
                    double rY = rot.secondAngle;
                    double rZ = rot.thirdAngle;

                    telemetry.addData("position X: ", tX);
                    telemetry.addData("position Y: ", tY);
                    telemetry.addData("position Z: ", tZ);

                    if(Math.abs(tX) > 100){ //checks if skyblock is centered with the robot
                        if(tX > 100){ //if robot too far to the left
                            spinFor.reset();
                            while(spinFor.milliseconds() < strafeDPS*(tX/100)){ //spins wheels for certain time to get to block center
                                setPowerWheels(1,-1,1,-1);
                            }
                            setPowerWheels(0,0,0,0);
                        }else if(tX < -100){ //if robot too far to the right
                            spinFor.reset();
                            while(spinFor.milliseconds() < strafeDPS*(Math.abs(tX)/100)){ //spins wheels for certain time to get to block center
                                setPowerWheels(-1,1,-1,1);
                            }
                            setPowerWheels(0,0,0,0);
                        }
                    }else if(Math.abs(tX) <= 100){ //if robot is near the center of the the block
                        spinFor.reset();
                        while(spinFor.seconds() < wheelRPS*(tZ/100)) { //spins wheels for certain time to get to block
                            setPowerWheels(1, 1, 1, 1);
                        }
                        setPowerWheels(0,0,0,0);
                    }
                }
            }
            else {
                telemetry.addData("VuMark", "not visible");
            }

            telemetry.update();
        }
    }
    String format(OpenGLMatrix transformationMatrix) {
        return (transformationMatrix != null) ? transformationMatrix.formatAsTransform() : "null";
    }
}