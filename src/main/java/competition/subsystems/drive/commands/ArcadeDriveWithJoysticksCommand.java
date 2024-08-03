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

        Move(leftRight, upDown);

    }

    public void Move(double left, double right){
        // Checks for the vals before determining action

        double leftPower = right - left;
        double rightPower = right + left;

        drive.tankDrive(leftPower, rightPower);

    }



}
