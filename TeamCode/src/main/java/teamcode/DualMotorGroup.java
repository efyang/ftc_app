package teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by efyang on 12/16/16.
 */

// two motors that are controlled together - same commands
public class DualMotorGroup {
    private DcMotor mot1;
    private DcMotor mot2;

    public DualMotorGroup(DcMotor mot1, DcMotor mot2) {
        this.mot1 = mot1;
        this.mot2 = mot2;
        // make the second motor definitely identical to the first one
        mot2.setTargetPosition(mot1.getTargetPosition());
        mot2.setMode(mot1.getMode());
    }

    public DcMotor.RunMode getMode() {
        return mot1.getMode();
    }

    public int getTargetPosition() {
        return mot1.getTargetPosition();
    }

    public void setMode(DcMotor.RunMode mode) {
        mot2.setMode(mode);
        mot1.setMode(mode);
    }

    public void setPower(double power) {
        mot2.setPower(power);
        mot1.setPower(power);
    }
}
