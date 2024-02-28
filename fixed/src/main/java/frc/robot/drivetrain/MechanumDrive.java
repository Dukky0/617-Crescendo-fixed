package frc.robot.drivetrain;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class MechanumDrive extends SubsystemBase {
    private static TalonSRX FrontLeft;
    private static TalonSRX FrontRight;
    private static TalonSRX BackLeft;
    private static TalonSRX BackRight;

    public static AnalogGyro Gyro;

    public MechanumDrive() {
        FrontLeft = new TalonSRX(Constants.MotorConstants.FRONT_LEFT);
        FrontRight = new TalonSRX(Constants.MotorConstants.FRONT_RIGHT);
        BackLeft = new TalonSRX(Constants.MotorConstants.BACK_LEFT);
        BackRight = new TalonSRX(Constants.MotorConstants.BACK_LEFT);

        Gyro = new AnalogGyro(Constants.DataConstants.GYRO);
    }

    public void setDrive(double translationAngle, double translationPower, double turnPower) {
        // calculate motor power
        double ADPower = translationPower * Math.sqrt(2) * 0.5 * (Math.sin(translationAngle) + Math.cos(translationAngle));
        double BCPower = translationPower * Math.sqrt(2) * 0.5 * (Math.sin(translationAngle) - Math.cos(translationAngle));

        // check if turning power will interfere with normal translation
        // check ADPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        double turningScale = Math.max(Math.abs(ADPower + turnPower), Math.abs(ADPower - turnPower));
        // check BCPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        turningScale = Math.max(turningScale, Math.max(Math.abs(BCPower + turnPower), Math.abs(BCPower - turnPower)));

        // adjust turn power scale correctly
        if (Math.abs(turningScale) < 1.0) {
            turningScale = 1.0;
        }

        FrontLeft.set(ControlMode.PercentOutput, (ADPower - turningScale) / turningScale);
        FrontRight.set(ControlMode.PercentOutput, (BCPower + turningScale) / turningScale);
        BackLeft.set(ControlMode.PercentOutput, (BCPower - turningScale) / turningScale);
        BackRight.set(ControlMode.PercentOutput, (ADPower + turningScale) / turningScale);
    }

    public void initDefaultCommand() {

    } 
}
