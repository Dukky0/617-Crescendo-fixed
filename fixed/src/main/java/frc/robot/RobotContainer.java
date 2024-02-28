// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.drivetrain.MechanumDrive;
import frc.robot.drivetrain.commands.FieldCentric;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.Suck;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.Shoot;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems are defined here...
  private final MechanumDrive m_mechanumDrive = new MechanumDrive();
  private final Intake m_intake= new Intake();
  private final Shooter m_shooter = new Shooter();

  public static Joystick driver;
  public static JoystickButton intakeButton;
  public static Trigger trigger;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {
    m_mechanumDrive.setDefaultCommand(
      new FieldCentric(m_mechanumDrive)
    );

    m_intake.setDefaultCommand(
      new Suck(m_intake)
    );

    m_shooter.setDefaultCommand(
      new Shoot(m_shooter)
    );

    driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

    intakeButton = new JoystickButton(driver, Constants.OperatorConstants.THUMB_BUTTON);
    trigger = new JoystickButton(driver, Constants.OperatorConstants.TRIGGER);

    // intakeButton.whileTrue(new Suck(m_intake));
    // trigger.whileTrue(new Shoot(m_shooter));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    // return Autos.exampleAuto(m_exampleSubsystem);
    return null;
  }
}
