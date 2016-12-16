package teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class PushbotMain {
    //define all variables used in the class
    public DcMotor rightFrontMotor;
    public DcMotor rightBackMotor;
    public DcMotor leftFrontMotor;
    public DcMotor leftBackMotor;
    public DcMotor screwMotor;
    public DcMotor rightShooterMotor;
    public DcMotor leftShooterMotor;
    public boolean a;
    public boolean b;
    public boolean dpad_down;
    public boolean dpad_up;
    public boolean dpad_right;
    public boolean dpad_left;
    public float left_stick_x;
    public float left_stick_y;
    public float right_stick_x;
    public float right_stick_y;
    public boolean prev_a;
    public boolean prev_b;
    public boolean prev_dpad_down;
    public boolean prev_dpad_up;
    public boolean prev_dpad_left;
    public boolean prev_dpad_right;
    public float prev_left_stick_x;
    public float prev_left_stick_y;
    public float prev_right_stick_x;
    public float prev_right_stick_y;
    public float shooterPower = (float) 0.20;
    public float enginePower = (float) 1.0;
    public float shooterIncr = (float) 0.01;
    public float engineIncr = (float) 0.02;

    //runs on press of the "init" button. Maps engines from the robot to variables,
    // and creates a tick before the first with default values
    public void init(HardwareMap HwMap) {
        HardwareMap HM = HwMap;
        rightFrontMotor = HM.dcMotor.get("R1");
        rightBackMotor = HM.dcMotor.get("R2");
        leftFrontMotor = HM.dcMotor.get("L1");
        leftBackMotor = HM.dcMotor.get("L2");
        screwMotor = HM.dcMotor.get("Arc");
        rightShooterMotor = HM.dcMotor.get("SR");
        leftShooterMotor = HM.dcMotor.get("SL");
        prev_a = false;
        prev_b = false;
        prev_dpad_down = false;
        prev_dpad_up = false;
        prev_dpad_right = false;
        prev_dpad_left = false;
        prev_left_stick_x = (float) 0.0;
        prev_left_stick_y = (float) 0.0;
        prev_right_stick_x = (float) 0.0;
        prev_right_stick_y = (float) 0.0;
    }

    //functions that detect a change in gamepad state by comparing the value of the specified value in the two most recent ticks
    public boolean valueChanged(float prev, float curr) {
        if (prev == curr) {
            return false;
        }
        return true;
    }
    public boolean valueChanged(boolean prev, boolean curr) {
        if (prev == curr) {
            return false;
        }
        return true;
    }
    //a function that returns a modified value, checking if it falls within logical boundaries first
    public float incr(float value, float incr, String sign) {
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
    public void startShooters() {

    }

    public void move(MovementCommand command) {

    }
}