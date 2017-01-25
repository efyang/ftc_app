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
    DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
    DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
    DcMotor rightBackMotor = PushbotMain.rightBackMotor;
    DcMotor leftBackMotor = PushbotMain.leftBackMotor;
    DcMotor rightShooterMotor = PushbotMain.rightShooterMotor;
    DcMotor leftShooterMotor = PushbotMain.leftShooterMotor;
    Servo flickServo = PushbotMain.flickServo;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        DualMotorGroup rightMotors = new DualMotorGroup(rightFrontMotor, rightBackMotor);
        DualMotorGroup leftMotors = new DualMotorGroup(leftFrontMotor, leftBackMotor);
        DualMotorGroup shooterMotors = new DualMotorGroup(leftShooterMotor, rightShooterMotor);
        rightShooterMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotors.setDirection(DcMotor.Direction.REVERSE);
        waitForStart();
        while(opModeIsActive()) {
            shooterMotors.setPower(.107);

            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {

            }

            flickServo.setPosition(0.5);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            leftMotors.setPower(0.5);
            rightMotors.setPower(0.5);

            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {

            }

            leftMotors.setPower(0.0);
            rightMotors.setPower(0.0);
        }
    }
}