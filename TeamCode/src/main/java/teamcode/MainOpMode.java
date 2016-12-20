package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by efyang on 12/16/16.
 */

@TeleOp(name="Pushbot: Main Teleop", group="Pushbot")
public class MainOpMode extends LinearOpMode {
    PushbotMain robot;
    DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
    DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
    DcMotor rightBackMotor = PushbotMain.rightBackMotor;
    DcMotor leftBackMotor = PushbotMain.leftBackMotor;
    double right_stick_y = PushbotMain.right_stick_y;
    double left_stick_y = PushbotMain.left_stick_y;

    DualMotorGroup rightMotors = new DualMotorGroup(rightFrontMotor, rightBackMotor);
    DualMotorGroup leftMotors = new DualMotorGroup(leftFrontMotor, leftBackMotor);

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // say hello
        telemetry.addData("say", "Hello Driver");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            rightMotors.setPower((double) right_stick_y);
            leftMotors.setPower((double) left_stick_y);
        }
    }
    public void setValues() {

    }
}
