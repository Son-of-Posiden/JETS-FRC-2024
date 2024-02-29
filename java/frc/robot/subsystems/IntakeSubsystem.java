// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */

  private final CANSparkMax intakeMotor;

  public IntakeSubsystem() {
    intakeMotor = new CANSparkMax(Constants.MotorConstants.INTAKE_MOTOR_PORT, MotorType.kBrushed);
    intakeMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void intake(double speed) {
    intakeMotor.set(speed);
  }
}
