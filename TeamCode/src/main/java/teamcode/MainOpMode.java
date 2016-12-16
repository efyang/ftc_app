package teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by efyang on 12/16/16.
 */

@TeleOp(name="Pushbot: Main Teleop", group="Pushbot")
public class MainOpMode extends LinearOpMode {
    PushbotMain robot;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
    }
}
