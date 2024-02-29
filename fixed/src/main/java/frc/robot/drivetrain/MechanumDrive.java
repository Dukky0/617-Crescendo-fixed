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
        BackRight = new TalonSRX(Constants.MotorConstants.BACK_RIGHT);

        Gyro = new AnalogGyro(Constants.DataConstants.GYRO);
    }

    public void setDrive(double f_left, double f_right, double b_left, double b_right) {
        FrontLeft.set(ControlMode.PercentOutput, f_left);
        FrontRight.set(ControlMode.PercentOutput, f_right);
        BackLeft.set(ControlMode.PercentOutput, b_left);
        BackRight.set(ControlMode.PercentOutput, b_right);
    }

    public void initDefaultCommand() {

    } 
}
