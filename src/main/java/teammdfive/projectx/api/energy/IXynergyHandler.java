package teammdfive.projectx.api.energy;

public interface IXynergyHandler extends IXynergyConnection, IXynergyProvider, IXynergyAcceptor {

    XynergyStorage getInternalStorage();

}
