package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double targetPosition;
    double lastPose = 0;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }

    public void setTargetPosition(double position) {
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
        // Sets target position same value as position.
        targetPosition = position;

    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position

        double distance = (targetPosition - pose.getPosition());
        // Finds out the distance between the target position form the position of the robot
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position
        double position = pose.getPosition();
        double speed = lastPose - position;
        double brake = -speed * .5;
        double power = (targetPosition - pose.getPosition()) * .5;
        double input = brake + power;
        drive.tankDrive(input, input);
        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints
        lastPose = position;
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        if (pose.getPosition() == targetPosition)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

}
