package teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class PushbotMain {
    //define all variables used


    public static DcMotor rightFrontMotor;
    public static DcMotor rightBackMotor;
    public static DcMotor leftFrontMotor;
    public static DcMotor leftBackMotor;
    public static DcMotor rightShooterMotor;
    public static DcMotor leftShooterMotor;
    public static Servo flickServo;


    //runs on press of the "init" button. Maps engines from the robot to variables,
    // and creates a tick before the first with default values
    public void init(HardwareMap HwMap) {
        HardwareMap HM = HwMap;
        rightFrontMotor = HM.dcMotor.get("R1");
        rightBackMotor = HM.dcMotor.get("R2");
        leftFrontMotor = HM.dcMotor.get("L1");
        leftBackMotor = HM.dcMotor.get("L2");
        flickServo =  HM.servo.get("flick");

        leftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightShooterMotor = HM.dcMotor.get("SR");
        leftShooterMotor = HM.dcMotor.get("SL");
        rightShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftShooterMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //DualMotorGroup rightMotors = new DualMotorGroup(rightFrontMotor, rightBackMotor);
        //DualMotorGroup leftMotors = new DualMotorGroup(leftFrontMotor, leftBackMotor);
        //DualMotorGroup shooterMotors = new DualMotorGroup(leftShooterMotor, rightShooterMotor);
        rightShooterMotor.setDirection(DcMotor.Direction.REVERSE);
        //leftMotors.setDirection(DcMotor.Direction.REVERSE);
        leftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        leftBackMotor.setDirection(DcMotor.Direction.REVERSE);

    }


}