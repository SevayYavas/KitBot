// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.PS5Controller;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Timer;




/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  CANSparkMax frontleftmotor = new CANSparkMax(7,MotorType.kBrushed);
  CANSparkMax backleftmotor = new CANSparkMax(6,MotorType.kBrushed);
  CANSparkMax frontrightmotor = new CANSparkMax(9,MotorType.kBrushed);
  CANSparkMax backrightmotor = new CANSparkMax(8,MotorType.kBrushed);

  CANSparkMax roller = new CANSparkMax(10, MotorType.kBrushed);

  Timer timer = new Timer();

  PS5Controller ps5joystick = new PS5Controller(0);
  DifferentialDrive m_robotdrive = new DifferentialDrive(frontleftmotor, frontrightmotor);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    backrightmotor.setInverted(true);
    frontrightmotor.setInverted(true);
    backleftmotor.follow(frontleftmotor);
    backrightmotor.follow(frontleftmotor);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {
    timer.reset();
    timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if(timer.get() < 10.0){
      m_robotdrive.arcadeDrive(0.5, 0.5);
    }
    else if(timer.get() > 10.0){
      m_robotdrive.arcadeDrive(0.0, 0.0);
    }
    
  }

  @Override
  public void teleopInit() {
    m_robotdrive.arcadeDrive(ps5joystick.getRightX(), ps5joystick.getRightY());
  }

  @Override
  public void teleopPeriodic() {
    if(ps5joystick.getRawButton(1)){
      roller.set(0.5);
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
