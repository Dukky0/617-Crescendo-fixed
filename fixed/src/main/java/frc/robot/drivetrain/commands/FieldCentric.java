package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.drivetrain.MechanumDrive;

public class FieldCentric extends Command {
    public final MechanumDrive m_mechanumDrive;

    public static Joystick driver;

    public FieldCentric(MechanumDrive mechanumDrive) {
        m_mechanumDrive = mechanumDrive;
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
        angle -= MechanumDrive.Gyro.getAngle();

        m_mechanumDrive.setDrive(angle, magnitude, twist);
    }

     // Make this return true when this Command no longer needs to run execute()
     public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    public void end() {
        m_mechanumDrive.setDrive(0.0, 0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

