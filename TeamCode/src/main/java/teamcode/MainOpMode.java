package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
/**
 * Created by efyang on 12/16/16.
 */

@TeleOp(name="Pushbot: Main Teleop", group="Pushbot")
public class MainOpMode extends LinearOpMode {
    PushbotMain robot = new PushbotMain();
    DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
    DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
    DcMotor rightBackMotor = PushbotMain.rightBackMotor;
    DcMotor leftBackMotor = PushbotMain.leftBackMotor;
    DcMotor screwMotor = PushbotMain.screwMotor;
    //DcMotor rightShooterMotor = PushbotMain.rightShooterMotor;
    //DcMotor leftShooterMotor = PushbotMain.leftShooterMotor;
    public static boolean prev_a = false;
    public static boolean prev_b = false;
    public static boolean prev_dpad_down = false;
    public static boolean prev_dpad_up = false;
    public static boolean prev_dpad_left = false;
    public static boolean prev_dpad_right = false;
    public static double shooterPower = 0.20;
    public static double enginePower = 0.8;
    public static final double shooterIncr = 0.01;
    public static final double engineIncr = 0.02;




    DualMotorGroup rightMotors = new DualMotorGroup(rightFrontMotor, rightBackMotor);
    DualMotorGroup leftMotors = new DualMotorGroup(leftFrontMotor, leftBackMotor);
    leftMotors.setDirection(Direction.REVERSE);


    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        // say hello
        telemetry.addData("say", "Hey kid.");
        telemetry.update();
        double lx;
        double ly;
        double rx;
        double ry;
        waitForStart();

        while (opModeIsActive()) {
            lx = (double) gamepad1.left_stick_x;
            ly = (double) gamepad1.left_stick_y;
            ry = (double) gamepad1.right_stick_y;
            rx = (double) gamepad1.right_stick_x;
            if (prev_dpad_up != gamepad1.dpad_up && gamepad1.dpad_up) {
                shooterPower = incr(shooterPower, shooterIncr, "+");
            }
            if (prev_dpad_down != gamepad1.dpad_down && gamepad1.dpad_down) {
                shooterPower = incr(shooterPower, shooterIncr, "-");
            }
            if (prev_dpad_left != gamepad1.dpad_left && gamepad1.dpad_left) {
                enginePower = incr(enginePower, engineIncr, "-");
            }
            if (prev_dpad_right != gamepad1.dpad_right && gamepad1.dpad_right) {
                enginePower = incr(enginePower, engineIncr, "+");
            }

            //if the user is clearly trying to turn, not go forward precisely...
            if (Math.abs(lx) > Math.abs(ly)) {
                //it does not matter which direction they are turning, as the lx value will be opposite in opposite directions
                leftMotors.setPower(lx);
                rightMotors.setPower(-lx);
            } else {
                rightMotors.setPower(ly);
                leftMotors.setPower(ly);
            }
            telemetry.addData("say", "R vertical: " + gamepad1.right_stick_y);
            telemetry.addData("say", "L vertical: " + gamepad1.left_stick_y);
            telemetry.addData("say", "R horizontal: " + gamepad1.right_stick_x);
            telemetry.addData("say", "L horizontal: " + gamepad1.left_stick_x);

            setValues(); //sets prev values to distinguish button presses tick-to-tick, preventing one press being
                         //detected as 100 presses
        }
    }
    public void setValues() {
        prev_a = gamepad1.a;
        prev_b = gamepad1.b;
        prev_dpad_down = gamepad1.dpad_down;
        prev_dpad_left = gamepad1.dpad_left;
        prev_dpad_right = gamepad1.dpad_right;
        prev_dpad_up = gamepad1.dpad_up;
    }
    //a function that returns a modified value, checking if it falls within logical boundaries first
    public double incr(double value, double incr, String sign) {
        if (sign.equals("+")) {
            if (value + incr <= 1.0) {
                return value + incr;
            }
        } else if (sign.equals('-')) {
            if (value - incr >= 0.0) {
                return value - incr;
            }
        }
        return value;
    }

    // startup shooter motors - they should remain spinning while the robot is running
    public void updateShooters() {

    }

    // pivot by `degrees` degrees
    private void pivot(float degrees) {

    }

    // move forward/backword by `meters` meters
    private void move(double meters) {

    }
}
