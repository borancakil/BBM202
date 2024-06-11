import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) throws IOException {

        // Mission Power Grid Optimization
        String powerGridDemandFile = args[0];
        int totalDemanded = 0;
        ArrayList<Integer> demands = new ArrayList<>();

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        try (BufferedReader br = new BufferedReader(new FileReader(powerGridDemandFile))) {
            String line = br.readLine();
            String[] tokens = line.split("\\s+");
            for (String token : tokens) {
                totalDemanded += Integer.parseInt(token);
                demands.add(Integer.parseInt(token));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        PowerGridOptimization optimization = new PowerGridOptimization(demands);
        OptimalPowerGridSolution solution = optimization.getOptimalPowerGridSolutionDP();

        System.out.println("The total number of demanded gigawatts: " + totalDemanded);
        System.out.println("Maximum number of satisfied gigawatts: " + solution.getmaxNumberOfSatisfiedDemands());
        System.out.println("Hours at which the battery bank should be discharged: "
                + arrayListToString(solution.getHoursToDischargeBatteriesForMaxEfficiency()));
        System.out.println("The number of unsatisfied gigawatts: "
                + Math.abs(solution.getmaxNumberOfSatisfiedDemands() - totalDemanded));
        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        // Mission Eco-Maintenance
        String esvMaintenanceFile = args[1];
        System.out.println("##MISSION ECO-MAINTENANCE##");

        BufferedReader br = new BufferedReader(new FileReader(esvMaintenanceFile));
            String line = br.readLine();
            String[] esvInfo = line.split("\\s+");
            int maxNumberOfAvailableESVs = Integer.parseInt(esvInfo[0]);
            int maxESVCapacity = Integer.parseInt(esvInfo[1]);

            line = br.readLine();
            String[] taskDemands = line.split("\\s+");
            ArrayList<Integer> maintenanceTaskEnergyDemands = new ArrayList<>();
            for (String task : taskDemands) {
                maintenanceTaskEnergyDemands.add(Integer.parseInt(task));
            }

            OptimalESVDeploymentGP esvDeployment = new OptimalESVDeploymentGP(maintenanceTaskEnergyDemands);
            int minNumESVs = esvDeployment.getMinNumESVsToDeploy(maxNumberOfAvailableESVs, maxESVCapacity);

            if (minNumESVs == -1) {
                System.out.println("Warning: Mission Eco-Maintenance Failed.");
            }
            else {

                System.out.println("The minimum number of ESVs to deploy: " + minNumESVs);
                ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = esvDeployment
                        .getMaintenanceTasksAssignedToESVs();
                for (int i = 0; i < maintenanceTasksAssignedToESVs.size(); i++) {
                    System.out.println("ESV " + (i + 1) + " tasks: " + maintenanceTasksAssignedToESVs.get(i));
                }

            }
                System.out.print("##MISSION ECO-MAINTENANCE COMPLETED##");
        
            br.close();

    }

    public static String arrayListToString(ArrayList<Integer> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
            if (i < list.size() - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
