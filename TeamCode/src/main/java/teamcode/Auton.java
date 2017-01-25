package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction;

@Autonomous(name="Auton", group="MainPushbot")
public class Auton extends LinearOpMode {
    PushbotMain robot = new PushbotMain();
    public DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
    public DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
    public DcMotor rightBackMotor = PushbotMain.rightBackMotor;
    public DcMotor leftBackMotor = PushbotMain.leftBackMotor;
    public DcMotor rightShooterMotor = PushbotMain.rightShooterMotor;
    public DcMotor leftShooterMotor = PushbotMain.leftShooterMotor;
    public Servo flickServo = PushbotMain.flickServo;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()) {

            setShooters(.11);

            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {

            }

            flickServo.setPosition(0.5);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            setLeftMotors(0.5);
            setRightMotors(0.5);

            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {

            }

            setLeftMotors(0.0);
            setRightMotors(0.0);
        }
    }

    public void setShooters(double d) {
        rightShooterMotor.setPower(d);
        leftShooterMotor.setPower(d);
    }
    public void setRightMotors(double d) {
        rightFrontMotor.setPower(d);
        rightBackMotor.setPower(d);
    }
    public void setLeftMotors(double d) {
        leftFrontMotor.setPower(d);
        leftBackMotor.setPower(d);
    }
}