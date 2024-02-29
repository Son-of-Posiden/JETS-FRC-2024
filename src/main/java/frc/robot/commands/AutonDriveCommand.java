// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.lib.betaLib.PidConfig;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsytem;

public class AutonDriveCommand extends Command {

  private final DriveSubsytem driveSubsytem;

  private final PidConfig pidConfig = new PidConfig("AutonDrive", 0, Constants.TuningConstants.AUTO_DRIVE_TUNABLE);

  double leftSetpoint, rightSetpoint, currentLeftPos, currentRightPos, leftPower, rightPower, relativeLeftAngle, relativeRightAngle;

  boolean relative;

  public AutonDriveCommand(DriveSubsytem drive, double leftSetpoint, double rightSetpoint, boolean relative) {
    this.leftSetpoint = leftSetpoint;
    this.rightSetpoint = rightSetpoint;
    this.driveSubsytem = new DriveSubsytem();
    this.relative = relative;
  }

  @Override
  public void initialize() {
    currentLeftPos = driveSubsytem.getLeftPos();
    currentRightPos = driveSubsytem.getRightPos();

    if (relative) {
      relativeLeftAngle = driveSubsytem.getLeftPos();
      relativeRightAngle = driveSubsytem.getRightPos();
    }
  }

  @Override
  public void execute() {
    if (relative) {
      currentLeftPos = currentLeftPos - relativeLeftAngle;
      currentRightPos = currentRightPos - relativeRightAngle;
    }
    leftPower = currentLeftPos - leftSetpoint * pidConfig.getKd();
    rightPower = currentRightPos - rightSetpoint * pidConfig.getKd();

    driveSubsytem.drive(leftPower, rightPower);
  }

  @Override
  public void end(boolean interrupted) {
    driveSubsytem.drive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
