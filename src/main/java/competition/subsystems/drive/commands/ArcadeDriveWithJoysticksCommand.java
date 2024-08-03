package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.operator_interface.OperatorInterface;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    OperatorInterface operatorInterface;
    DriveSubsystem drive;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        this.operatorInterface = oi;
        this.drive = driveSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // Tracks the x and y position of the joystick and determines the course of action to take
        double xVal = operatorInterface.gamepad.getLeftStickX();
        double yVal = operatorInterface.gamepad.getLeftStickY();

        // Checks for the vals before determining action
        if (xVal > 0 && yVal == 0)
        {
            drive.tankDrive(xVal, xVal);
        }
    }

}
