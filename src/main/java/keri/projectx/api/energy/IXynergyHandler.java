package keri.projectx.api.energy;

public interface IXynergyHandler {

    boolean canConnectXynergy();

    boolean canProvideXynergy();

    boolean canAcceptXynergy();

    XynergyStorage getInternalStorage();

}
