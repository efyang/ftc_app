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


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        waitForStart();
        DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
        DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
        DcMotor rightBackMotor = PushbotMain.rightBackMotor;
        DcMotor leftBackMotor = PushbotMain.leftBackMotor;
        DcMotor rightShooterMotor = PushbotMain.rightShooterMotor;
        DcMotor leftShooterMotor = PushbotMain.leftShooterMotor;
        Servo flickServo = PushbotMain.flickServo;
        rightFrontMotor.setDirection(Direction.REVERSE);
        rightBackMotor.setDirection(Direction.REVERSE);
        leftFrontMotor.setDirection(Direction.FORWARD);
        leftBackMotor.setDirection(Direction.FORWARD);
        while(opModeIsActive()) {

            rightShooterMotor.setPower(.137);
            leftShooterMotor.setPower(.137);

            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {

            }

            flickServo.setPosition(0.5);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }

            leftFrontMotor.setPower(0.7);
            leftBackMotor.setPower(0.7);
            rightFrontMotor.setPower(0.7);
            rightBackMotor.setPower(0.7);
            rightShooterMotor.setPower(0.0);
            leftShooterMotor.setPower(0.0);

            try {
                Thread.sleep(1300);
            } catch (InterruptedException e) {

            }

            leftFrontMotor.setPower(0.0);
            leftBackMotor.setPower(0.0);
            rightFrontMotor.setPower(0.0);
            rightBackMotor.setPower(0.0);
        }
    }


}