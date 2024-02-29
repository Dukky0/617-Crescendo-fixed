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

    public void setShooter(double input) {
        Motor.set(ControlMode.PercentOutput, input);

    }

    public void initDefaultCommand() {

    }
}
