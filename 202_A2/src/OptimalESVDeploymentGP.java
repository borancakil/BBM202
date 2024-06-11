




import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * This class accomplishes Mission Eco-Maintenance
 */
public class OptimalESVDeploymentGP
{
    private ArrayList<Integer> maintenanceTaskEnergyDemands;

    /*
     * Should include tasks assigned to ESVs.
     * For the sample input:
     * 8 100
     * 20 50 40 70 10 30 80 100 10
     * 
     * The list should look like this:
     * [[100], [80, 20], [70, 30], [50, 40, 10], [10]]
     * 
     * It is expected to be filled after getMinNumESVsToDeploy() is called.
     */
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<Integer> getMaintenanceTaskEnergyDemands() {
        return maintenanceTaskEnergyDemands;
    }

    /**
     *
     * @param maxNumberOfAvailableESVs the maximum number of available ESVs to be deployed
     * @param maxESVCapacity the maximum capacity of ESVs
     * @return the minimum number of ESVs required using first fit approach over reversely sorted items.
     * Must return -1 if all tasks can't be satisfied by the available ESVs
     */

    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) {
        // Sort maintenance tasks by energy demands in decreasing order
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());

        ArrayList<Integer> sortedTasks = new ArrayList<>(maintenanceTaskEnergyDemands);
        sortedTasks.sort(Comparator.reverseOrder());
        

        // Initialize list to store remaining energy capacity of available ESVs
        ArrayList<Integer> remainingCapacity = new ArrayList<>();
        for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
            remainingCapacity.add(maxESVCapacity);
        }


        for (Integer demand : maintenanceTaskEnergyDemands) {
            boolean taskAssigned = false;

            for (int i = 0; i < maxNumberOfAvailableESVs; i++) {
                if (remainingCapacity.get(i) >= demand) {
                    if (maintenanceTasksAssignedToESVs.size() <= i) {
                        maintenanceTasksAssignedToESVs.add(new ArrayList<>());
                    }
                    maintenanceTasksAssignedToESVs.get(i).add(demand);
                    remainingCapacity.set(i, remainingCapacity.get(i) - demand);
                    taskAssigned = true;
                    break;
                }
            }

            if (!taskAssigned) {
                return -1;
            }
            
        }
        for (Integer capacity : remainingCapacity) {
            if (capacity < 0) {
                return -1;
            }
        }
        return maintenanceTasksAssignedToESVs.size();
        }

}
