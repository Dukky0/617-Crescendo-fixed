package frc.robot.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    // Motor
    private static TalonSRX Motor;

    public Intake() {
        Motor = new TalonSRX(Constants.MotorConstants.INTAKE);
    }

    public void setIntake(boolean input) {
        if (input) {
            Motor.set(ControlMode.PercentOutput, 1);
        } else {
            Motor.set(ControlMode.PercentOutput, 1);
        }
        
    }

    public void initDefaultCommand() {

    }
}
