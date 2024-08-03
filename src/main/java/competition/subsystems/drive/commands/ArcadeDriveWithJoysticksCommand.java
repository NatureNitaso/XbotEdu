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
        addRequirements(driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // Tracks the x and y position of the joystick and determines the course of action to take
        double leftRight = operatorInterface.gamepad.getLeftStickX();
        double upDown = operatorInterface.gamepad.getLeftStickY();

        MoveForward(leftRight, upDown);
        MoveBackward(leftRight, upDown);

    }

    public void MoveForward(double leftRight, double upDown){
        // Checks for the vals before determining action
        if (upDown > 0 && leftRight <= 0)
        {
            drive.tankDrive(upDown, upDown);
        }
    }

    public void MoveBackward(double leftRight, double upDown){
        // Checks condition before initalizing the action
        if (upDown < 0 && leftRight >= 0)
        {
            drive.tankDrive(upDown, upDown);
        }
    }

}
