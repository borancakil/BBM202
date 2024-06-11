import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */

 
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;

    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour) {
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;

    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }


    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP() {
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        int[] SOL = new int[N + 1];
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>(N + 1);

        SOL[0] = 0;
        HOURS.add(new ArrayList<>());

        for (int j = 1; j <= N; j++) {
            int maxEnergy = 0;
            int optimalHour = -1;
            for (int i = 0; i < j; i++) {
                int chargingEfficiency = (j - i) * (j - i);
                int energy = Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), chargingEfficiency) + SOL[i];
                if (energy > maxEnergy) {
                    maxEnergy = energy;
                    optimalHour = i;
                }
            }
            SOL[j] = maxEnergy;
            ArrayList<Integer> hours = new ArrayList<>(HOURS.get(optimalHour));
            hours.add(j);
            HOURS.add(hours);
        }

        int maxNumberOfSatisfiedDemands = SOL[N];
        ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency = HOURS.get(N);

        return new OptimalPowerGridSolution(maxNumberOfSatisfiedDemands, hoursToDischargeBatteriesForMaxEfficiency);
    }
    
    
    
}
