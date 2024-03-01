package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.drivetrain.MechanumDrive;

public class FieldCentric extends Command {
    public final MechanumDrive m_mechanumDrive;

    public static Joystick driver;

    private static double FrontLeft;
    private static double FrontRight;
    private static double BackLeft;
    private static double BackRight;

    public final AnalogGyro i_angle;

    public FieldCentric(MechanumDrive mechanumDrive, AnalogGyro angle) {
        m_mechanumDrive = mechanumDrive;
        i_angle = angle;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(mechanumDrive);
    }


    // Called just before this Command runs the first time
    public void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

        // get joystick input
        double angle = Math.atan2(driver.getRawAxis(1), driver.getRawAxis(0));
        double magnitude = Math.hypot(driver.getRawAxis(0), driver.getRawAxis(1));
        double twist = driver.getTwist();
        
        // use field centric controls by subtracting off the robot angle
        angle -= i_angle.getAngle();

        mechanumCalc(angle, magnitude, twist);

        m_mechanumDrive.setDrive(FrontLeft, FrontRight, BackLeft, BackRight);
    }

    public void mechanumCalc(double translationAngle, double translationPower, double turnPower) {
        // calculate motor power
        double ADPower = translationPower * (Math.sqrt(2) * 0.5) * (Math.sin(translationAngle) + Math.cos(translationAngle));
        double BCPower = translationPower * (Math.sqrt(2) * 0.5) * (Math.sin(translationAngle) - Math.cos(translationAngle));

        // check if turning power will interfere with normal translation
        // check ADPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        double turningScale = Math.max(Math.abs(ADPower + turnPower), Math.abs(ADPower - turnPower));
        // check BCPower to see if trying to apply turnPower would put motor power over 1.0 or under -1.0
        turningScale = Math.max(turningScale, Math.max(Math.abs(BCPower + turnPower), Math.abs(BCPower - turnPower)));

        // adjust turn power scale correctly
        if (Math.abs(turningScale) < 1.0) {
            turningScale = 1.0;
        }

        FrontLeft = ((-1 * ADPower) - turningScale) / turningScale;
        BackLeft = ((-1 * BCPower) + turningScale) / turningScale;
        FrontRight = (BCPower - turningScale) / turningScale;
        BackRight = (ADPower + turningScale) / turningScale;

        // FrontLeft = -1 * ADPower;
        // BackLeft = -1 * BCPower;
        // FrontRight = BCPower;
        // BackRight = ADPower;
    }


     // Make this return true when this Command no longer needs to run execute()
     public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        m_mechanumDrive.setDrive(0.0, 0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

