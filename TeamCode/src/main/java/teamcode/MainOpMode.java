package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


/**
 * Created by efyang on 12/16/16.
 */

@TeleOp(name="Main Teleop", group="Robot")
public class MainOpMode extends LinearOpMode {

    //creates an instance variable fo the robot
    MainRobot robot = new MainRobot();

    //initiate all "last tick" variable for controller
    public static boolean prev_x = false;
    public static boolean prev_y = false;
    public static boolean prev_a = false;
    public static boolean prev_b = false;
    public static boolean prev_dpad_down = false;
    public static boolean prev_dpad_up = false;
    public static boolean prev_dpad_left = false;
    public static boolean prev_dpad_right = false;
    public static boolean prev_lbumper = false;
    public static boolean prev_rbumper = false;
    public static float prev_ltrigger = (float) 0;
    public static float prev_rtrigger = (float) 0;

    public static double shooterPower = 0.13;
    public static double enginePower = 0.9;
    public static final double shooterIncr = 0.02;
    public static final double engineIncr = 0.08;
    public static boolean isSpinning = false;
    public static double turnCoefficient = 2.0;
    public static double leftx = 0.0;
    public static double righty = 0.0;
    public static double rightx = 0.0;
    public static double lefty = 0.0;

    public static double precision = 1.0;

    public static double servoUpPosition = .23;
    public static double servoDownPosition = .5;
    public static double servoUpOffset = .03;
    public static double servoDownOffset = .03;
    @Override
    public void runOpMode() throws InterruptedException  {

        robot.init(hardwareMap);
        DcMotor rightFrontMotor = MainRobot.rightFrontMotor;
        DcMotor leftFrontMotor = MainRobot.leftFrontMotor;
        DcMotor rightBackMotor = MainRobot.rightBackMotor;
        DcMotor leftBackMotor = MainRobot.leftBackMotor;
        DcMotor rightShooterMotor = MainRobot.rightShooterMotor;
        DcMotor leftShooterMotor = MainRobot.leftShooterMotor;
        Servo flickServo = MainRobot.flickServo;

        telemetry.addData("say", "before opmode");
        telemetry.update();
        waitForStart();
        while (opModeIsActive()) {

            telemetry.clear();

            //increment and decrement the shooting motors' powers
            if (  gamepad1.dpad_up && prev_dpad_up != gamepad1.dpad_up
            ) {
                shooterPower = incr(shooterPower, shooterIncr, "+");
            }
            if (gamepad1.dpad_down && prev_dpad_down != gamepad1.dpad_down
             ) {
                shooterPower = incr(shooterPower, shooterIncr, "-");
            }

            //increment and decrement the driving motors' powers
            if ( gamepad1.dpad_left && prev_dpad_left != gamepad1.dpad_left
                    ) {
                enginePower = incr(enginePower, engineIncr, "-");
            }
            if ( gamepad1.dpad_right && prev_dpad_right != gamepad1.dpad_right
                    ) {
                enginePower = incr(enginePower, engineIncr, "+");
            }

            //adjust the up offset from default of the servo
            if (gamepad1.left_bumper && prev_lbumper != gamepad1.left_bumper) {
                servoUpOffset = servoUpOffset - .01;
            }
            if (gamepad1.right_bumper && prev_rbumper != gamepad1.right_bumper) {
                servoUpOffset = servoUpOffset + .01;
            }


            //adjust the down offset from default of the servo
            if (gamepad1.left_trigger != (float) 0 && prev_ltrigger != gamepad1.left_trigger) {
                servoDownOffset = servoDownOffset - .01;
            }
            if (gamepad1.right_trigger != (float) 0 && prev_rtrigger != gamepad1.right_trigger) {
                servoDownOffset = servoDownOffset + .01;
            }

            //toggle the shooters
            if (gamepad1.b && prev_b != gamepad1.b) {
                if (isSpinning) {
                    isSpinning = false;
                } else {
                    isSpinning = true;
                }
            }

            //lift servo
            if (gamepad1.a) {
                flickServo.setPosition(servoUpPosition - servoUpOffset);
            }

            //lower servo
            if (gamepad1.x) {
                flickServo.setPosition(servoDownPosition + servoDownOffset);
            }

            //toggle precise driving mode
            if (gamepad1.y && gamepad1.y != prev_y) {
                if (precision == 1.0) {
                    precision = 0.5;
                } else {
                    precision = 1.0;
                }
            }





            //set shooters to correct speed
            if (isSpinning) {
                rightShooterMotor.setPower(shooterPower);
                leftShooterMotor.setPower(shooterPower);
            } else {
                rightShooterMotor.setPower(0.0);
                leftShooterMotor.setPower(0.0);
            }



            //if the x axis is greater than y axis values on joystick (indicates turning)
            if (Math.abs(leftx) > Math.abs(lefty)) {
                //it does not matter which direction they are turning, as the leftx value will be opposite in opposite directions
                leftFrontMotor.setPower(-leftx * enginePower * turnCoefficient * precision);
                rightFrontMotor.setPower(leftx * enginePower * turnCoefficient * precision);
                leftBackMotor.setPower(-leftx * enginePower * turnCoefficient * precision);
                rightBackMotor.setPower(leftx * enginePower * turnCoefficient * precision);
            } else {
                //otherwise go forward
                rightFrontMotor.setPower(lefty * enginePower * precision);
                leftFrontMotor.setPower(lefty * enginePower * precision);
                rightBackMotor.setPower(lefty * enginePower * precision);
                leftBackMotor.setPower(lefty * enginePower * precision);
            }

            //update telemetry
            telemetry.addData("R vertical", righty);
            telemetry.addData("L vertical",lefty);
            telemetry.addData("R horizontal", rightx);
            telemetry.addData("L horizontal", leftx);
            telemetry.addData("Engine power", enginePower);
            telemetry.addData("Shooter power", shooterPower);
            telemetry.addData("Servo position", flickServo.getPosition());
            telemetry.addData("Servo up offset", servoUpOffset);
            telemetry.addData("Servo down offset", servoDownOffset);
            telemetry.update();

            //sets prev values to distinguish button presses tick-to-tick
            setValues();
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
        prev_lbumper = gamepad1.left_bumper;
        prev_rbumper = gamepad1.right_bumper;
        prev_ltrigger = gamepad1.left_trigger;
        prev_rtrigger = gamepad1.right_trigger;
        prev_y = gamepad1.y;
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
