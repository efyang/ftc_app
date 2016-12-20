package teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class PushbotMain {
    //define all variables used

    //EDWARD: in order for a variable to be referenced in an opmode it must be static!
    public static DcMotor rightFrontMotor;
    public static DcMotor rightBackMotor;
    public static DcMotor leftFrontMotor;
    public static DcMotor leftBackMotor;
    public static DcMotor screwMotor;
    public static DcMotor rightShooterMotor;
    public static DcMotor leftShooterMotor;
    public boolean a;
    public boolean b;
    public boolean dpad_down;
    public boolean dpad_up;
    public boolean dpad_right;
    public boolean dpad_left;
    public float left_stick_x;
    public static float left_stick_y = (float) 0.0;
    public float right_stick_x;
    public static float right_stick_y = (float) 0.0;
    /*public boolean prev_a;
    public boolean prev_b;
    public boolean prev_dpad_down;
    public boolean prev_dpad_up;
    public boolean prev_dpad_left;
    public boolean prev_dpad_right;  May be removed in future versions
    public float prev_left_stick_x;
    public float prev_left_stick_y;
    public float prev_right_stick_x;
    public float prev_right_stick_y;*/
    public double shooterPower = 0.20;
    public double enginePower = 1.0;
    public double shooterIncr = 0.01;
    public double engineIncr = 0.02;

    //runs on press of the "init" button. Maps engines from the robot to variables,
    // and creates a tick before the first with default values
    public void init(HardwareMap HwMap) {
        HardwareMap HM = HwMap;
        rightFrontMotor = HM.dcMotor.get("R1");
        rightBackMotor = HM.dcMotor.get("R2");
        leftFrontMotor = HM.dcMotor.get("L1");
        leftBackMotor = HM.dcMotor.get("L2");
        screwMotor = HM.dcMotor.get("Arc");
        //rightShooterMotor = HM.dcMotor.get("SR");
        //leftShooterMotor = HM.dcMotor.get("SL");
        /*prev_a = false;
        prev_b = false;
        prev_dpad_down = false;
        prev_dpad_up = false;
        prev_dpad_right = false;
        prev_dpad_left = false;
        prev_left_stick_x = (float) 0.0;
        prev_left_stick_y = (float) 0.0;
        prev_right_stick_x = (float) 0.0;
        prev_right_stick_y = (float) 0.0;*/
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
    public void startShooters() {

    }

    // pivot by `degrees` degrees
    private void pivot(float degrees) {

    }

    // move forward/backword by `meters` meters
    private void move(double meters) {

    }
}