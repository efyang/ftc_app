package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by efyang on 12/16/16.
 */

@TeleOp(name="Main Teleop", group="Pushbot")
public class MainOpMode extends LinearOpMode {
    PushbotMain robot = new PushbotMain();
    public static boolean prev_x = false;
    public static boolean prev_a = false;
    public static boolean prev_b = false;
    public static boolean prev_dpad_down = false;
    public static boolean prev_dpad_up = false;
    public static boolean prev_dpad_left = false;
    public static boolean prev_dpad_right = false;
    public static double shooterPower = 0.13;
    public static double enginePower = 0.9;
    public static final double shooterIncr = 0.02;
    public static final double engineIncr = 0.08;
    public static boolean isSpinning = false;
    public static double turnCoefficient = 1.0;
    public static double leftx = 0.0;
    public static double righty = 0.0;
    public static double rightx = 0.0;
    public static double lefty = 0.0;
    public static boolean servoUp = false;
    public static boolean precision = false;
    @Override
    public void runOpMode() throws InterruptedException  {

        robot.init(hardwareMap);
        DcMotor rightFrontMotor = PushbotMain.rightFrontMotor;
        DcMotor leftFrontMotor = PushbotMain.leftFrontMotor;
        DcMotor rightBackMotor = PushbotMain.rightBackMotor;
        DcMotor leftBackMotor = PushbotMain.leftBackMotor;
        DcMotor rightShooterMotor = PushbotMain.rightShooterMotor;
        DcMotor leftShooterMotor = PushbotMain.leftShooterMotor;
        Servo flickServo = PushbotMain.flickServo;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {
            telemetry.clear();

            leftx = (double) gamepad1.left_stick_x;
            lefty = (double) gamepad1.left_stick_y;
            righty = (double) gamepad1.right_stick_y;
            rightx = (double) gamepad1.right_stick_x;
            if (  gamepad1.dpad_up && prev_dpad_up != gamepad1.dpad_up
            ) {
                shooterPower = incr(shooterPower, shooterIncr, "+");
            }
            if (gamepad1.dpad_down && prev_dpad_down != gamepad1.dpad_down
             ) {
                shooterPower = incr(shooterPower, shooterIncr, "-");
            }
            if ( gamepad1.dpad_left && prev_dpad_left != gamepad1.dpad_left
                    ) {
                enginePower = incr(enginePower, engineIncr, "-");
            }
            if ( gamepad1.dpad_right && prev_dpad_right != gamepad1.dpad_right
                    ) {
                enginePower = incr(enginePower, engineIncr, "+");
            }


            if (gamepad1.b) {
                if (isSpinning) {
                    isSpinning = false;
                } else {
                    isSpinning = true;
                }
            }

            if (gamepad1.a) {
                flickServo.setPosition(.3);
            }
            if (gamepad1.x) {
                flickServo.setPosition(.7);
            }
            if (gamepad1.y) {
                if (precision) {
                    precision = false;
                } else {
                    precision = true;
                }
            }






            if (isSpinning) {
                rightShooterMotor.setPower(shooterPower);
                leftShooterMotor.setPower(shooterPower);
            } else {
                rightShooterMotor.setPower(0.0);
                leftShooterMotor.setPower(0.0);
            }

           /* angle = Math.atan2(lefty, leftx);
            angleDegrees = (Math.toDegrees(angle) + 540) % 360;
            magnitude = Math.sqrt(Math.pow(lefty, 2) + Math.pow(leftx, 2));
            telemetry.addData("Angle", angle);
            telemetry.addData("Angle degrees", angleDegrees);
            telemetry.addData("Magnitude", magnitude);
            //telemetry.addData("Servo", flickServo.getPosition());
            telemetry.update();
            double sin = Math.sin(angle);
            if (angleDegrees >= 0 && angleDegrees <= 180) {
                leftMotors.setPower(magnitude * enginePower * sin);
                rightMotors.setPower(magnitude * enginePower * (2 - (1/sin)));
            } else {
                leftMotors.setPower(-magnitude * enginePower * sin);
                rightMotors.setPower(-magnitude * enginePower * (2 - (1/sin)));
            }*/



            //if the user is clearly trying to turn, not go forward precisely...

            if (precision) {
                if (Math.abs(leftx) > Math.abs(lefty)) {
                    //it does not matter which direction they are turning, as the leftx value will be opposite in opposite directions
                    leftFrontMotor.setPower(-leftx * enginePower * turnCoefficient /2);
                    rightFrontMotor.setPower(leftx * enginePower * turnCoefficient /2);
                    leftBackMotor.setPower(-leftx * enginePower * turnCoefficient /2);
                    rightBackMotor.setPower(leftx * enginePower * turnCoefficient /2);
                } else {
                    rightFrontMotor.setPower(lefty * enginePower /2);
                    leftFrontMotor.setPower(lefty * enginePower /2);
                    rightBackMotor.setPower(lefty * enginePower /2);
                    leftBackMotor.setPower(lefty * enginePower /2);
                }
            } else {
                if (Math.abs(leftx) > Math.abs(lefty)) {
                    //it does not matter which direction they are turning, as the leftx value will be opposite in opposite directions
                    leftFrontMotor.setPower(-leftx * enginePower * turnCoefficient);
                    rightFrontMotor.setPower(leftx * enginePower * turnCoefficient);
                    leftBackMotor.setPower(-leftx * enginePower * turnCoefficient);
                    rightBackMotor.setPower(leftx * enginePower * turnCoefficient);
                } else {
                    rightFrontMotor.setPower(lefty * enginePower);
                    leftFrontMotor.setPower(lefty * enginePower);
                    rightBackMotor.setPower(lefty * enginePower);
                    leftBackMotor.setPower(lefty * enginePower);
                }
            }

            telemetry.addData("R vertical", righty);
            telemetry.addData("L vertical",lefty);
            telemetry.addData("R horizontal", rightx);
            telemetry.addData("L horizontal", leftx);
            telemetry.addData("Engine power", enginePower);
            telemetry.addData("Shooter power", shooterPower);
            telemetry.addData("Servo position", flickServo.getPosition());
            telemetry.update();
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
        prev_x = gamepad1.x;
    }
    //a function that returns a modified value, checking if it falls within logical boundaries first
    public double incr(double value, double incr, String sign) {
        if (sign.equals("+")) {
            if (value + incr <= 1.0) {
                return value + incr;
            }
        } else if (sign.equals("-")) {
            if (value - incr >= 0.0) {
                return value - incr;
            }
        }
        return value;
    }

}
