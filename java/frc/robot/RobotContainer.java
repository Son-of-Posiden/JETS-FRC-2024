// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.DriveSubsytem;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.SenserSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class RobotContainer {
  
  private final DriveSubsytem m_driveSubsytem = new DriveSubsytem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  private final SenserSubsystem m_senserSubsystem = new SenserSubsystem();

  

  private final XboxController m_driverController =
      new XboxController(OperatorConstants.kDriverControllerPort);

  //IF REALLY SLOW, EDIT THIS
  private double driveSpeed = 0.1;
  private double intakeSpeed = 0.75;


  public RobotContainer() {
      m_driveSubsytem.drive(m_driverController.getLeftY()*driveSpeed, m_driverController.getRightY()*driveSpeed);

      if (m_driverController.getAButton()) {
        m_intakeSubsystem.intake(intakeSpeed);
        m_indexerSubsystem.index(intakeSpeed);
      } else if (m_senserSubsystem.limitSwitch.get()) {
        m_intakeSubsystem.intake(0.0);
        m_indexerSubsystem.index(0.0);
      }

      if (m_driverController.getYButtonPressed()) {

        if (m_senserSubsystem.isSpeaker()) {
          m_shooterSubsystem.speakerAutoShoot(m_senserSubsystem.distanceFromTag());
        } else {
          m_shooterSubsystem.ampLinearInterpolation(m_senserSubsystem.distanceFromTag());
        }
        
        new WaitCommand(0.2);
        m_indexerSubsystem.index(1.0);
      }
      
      m_shooterSubsystem.shooter(m_driverController.getLeftTriggerAxis(),m_driverController.getLeftTriggerAxis());
  }

  // public Command getAutonomousCommand() {
  //   return Autos.exampleAuto(m_exampleSubsystem);
  // }
}
