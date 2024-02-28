package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    // Motor
    private static TalonSRX Motor;

    public Shooter() {
        Motor = new TalonSRX(Constants.MotorConstants.SHOOTER);
    }

    public void setShooter(boolean input) {
        if (input) {
            Motor.set(ControlMode.PercentOutput, 1);
        } else {
            Motor.set(ControlMode.PercentOutput, 1);
        }
    }

    public void initDefaultCommand() {

    }
}
