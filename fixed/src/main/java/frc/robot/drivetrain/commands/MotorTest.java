package frc.robot.drivetrain.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.drivetrain.MechanumDrive;

public class MotorTest extends Command {
    public final MechanumDrive m_mechanumDrive;

    public MotorTest(MechanumDrive mechanumDrive) {
        m_mechanumDrive = mechanumDrive;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(mechanumDrive);
    }


    // Called just before this Command runs the first time
    public void initialize() {

    }

    public void execute() {
        m_mechanumDrive.setDrive(0.1, 0.1, 0.1, 0.1);
    }
}
