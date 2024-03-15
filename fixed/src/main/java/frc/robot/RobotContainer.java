// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.drivetrain.MechanumDrive;
import frc.robot.drivetrain.commands.FieldCentric;
import frc.robot.drivetrain.commands.Kinematics;
import frc.robot.drivetrain.commands.MotorTest;
import frc.robot.drivetrain.commands.Turn;
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
  public static JoystickButton invIntake;
  public static JoystickButton invShooter;
  public static Trigger trigger;
  public static AnalogGyro gyro;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);
    gyro = new AnalogGyro(Constants.DataConstants.GYRO);

    // SmartDashboard.putNumber("Axis 0", driver.getRawAxis(0));
    // SmartDashboard.putNumber("Axis 1", driver.getRawAxis(1));

    // Configure the trigger bindings
    configureBindings();
  }

  private void configureBindings() {

    m_mechanumDrive.setDefaultCommand(
      new Kinematics(m_mechanumDrive, gyro.getAngle())
    );

    driver = new Joystick(Constants.OperatorConstants.kDriverControllerPort);

    intakeButton = new JoystickButton(driver, Constants.OperatorConstants.THUMB_BUTTON);
    trigger = new JoystickButton(driver, Constants.OperatorConstants.TRIGGER);
    invIntake = new JoystickButton(driver, Constants.OperatorConstants.RIGHT_BUTTON);
    invShooter = new JoystickButton(driver, Constants.OperatorConstants.LEFT_BUTTON);

    intakeButton.onTrue(new Suck(m_intake, 1));
    intakeButton.onFalse(new Suck(m_intake, 0.0));
    trigger.onTrue(new Shoot(m_shooter, 1));
    trigger.onFalse(new Shoot(m_shooter, 0.0));
    invIntake.onTrue(new Suck(m_intake, -0.5));
    invIntake.onFalse(new Suck(m_intake, 0.0));
    invShooter.onTrue(new Shoot(m_shooter, -0.5));
    invShooter.onFalse(new Shoot(m_shooter, 0.0));
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
