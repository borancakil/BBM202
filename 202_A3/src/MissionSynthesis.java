import java.util.*;

// Class representing the Mission Synthesis
public class MissionSynthesis {

    // Private fields
    private final List<MolecularStructure> humanStructures; // Molecular structures for humans
    private final ArrayList<MolecularStructure> diffStructures; // Anomalies in Vitales structures compared to humans

    // Constructor
    public MissionSynthesis(List<MolecularStructure> humanStructures, ArrayList<MolecularStructure> diffStructures) {
        this.humanStructures = humanStructures;
        this.diffStructures = diffStructures;
    }

    // Method to synthesize bonds for the serum
    public List<Bond> synthesizeSerum() {
        List<Bond> serum = new ArrayList<>();

        // Generate potential bonds between human and Vitales structures
        List<Bond> potentialBonds = generatePotentialBonds();

        // Check if potentialBonds list is empty
        if (potentialBonds.isEmpty()) {
            System.out.println("No potential bonds found.");
            return serum;
        }

        // Sort potential bonds by strength
        potentialBonds.sort((b1, b2) -> Double.compare(b1.getWeight(), b2.getWeight()));

        // Track connected components
        int[] component = new int[humanStructures.size() + diffStructures.size()];
        for (int i = 0; i < component.length; i++) {
            component[i] = i;
        }

        // Iterate over potential bonds and add minimum strength bonds to serum
        for (Bond bond : potentialBonds) {
            int fromIndex = findIndex(humanStructures, bond.getFrom());
            int toIndex = findIndex(diffStructures, bond.getTo()) + humanStructures.size();

            // Check if fromIndex or toIndex is -1
            if (fromIndex == -1 || toIndex == -1) {
                System.out.println("Invalid index found for bond: " + bond);
                continue;
            }

            if (!connected(component, fromIndex, toIndex)) {
                union(component, fromIndex, toIndex);
                serum.add(bond);
            }
        }

        return serum;
    }


    


    // Method to check if two molecules are connected
    private boolean connected(int[] component, int x, int y) {
        return find(component, x) == find(component, y);
    }

    // Method to find the root of an element in the connected component
    private int find(int[] component, int x) {
        while (component[x] != x) {
            x = component[x];
        }
        return x;
    }

    // Method to unite two elements in the connected component
    private void union(int[] component, int x, int y) {
        int rootX = find(component, x);
        int rootY = find(component, y);
        component[rootY] = rootX;
    }



    private List<Bond> generatePotentialBonds() {
    List<Bond> potentialBonds = new ArrayList<>();
    for (MolecularStructure humanStructure : humanStructures) {
        for (MolecularStructure diffStructure : diffStructures) {
            for (Molecule humanMolecule : humanStructure.getMolecules()) {
                for (Molecule diffMolecule : diffStructure.getMolecules()) {
                    double bondStrength = calculateBondStrength(humanMolecule, diffMolecule);
                    // "from" and "to" parameters should be assigned accordingly
                    Bond bond = new Bond(diffMolecule, humanMolecule, bondStrength);
                    potentialBonds.add(bond);
                }
            }
        }
    }
    return potentialBonds;
}


    

    private double calculateBondStrength(Molecule molecule1, Molecule molecule2) {
        return (molecule1.getBondStrength() + molecule2.getBondStrength()) / 2.0;
    }
    
    


    // Method to print the synthesized bonds
    public void printSynthesis(List<Bond> serum) {

        /* YOUR CODE HERE */

        int total = 0;


        System.out.println("Typical human molecules selected for synthesis: " + humanStructures.get(0).getMolecules());
        System.out.println("Vitales molecules selected for synthesis: " + diffStructures.get(0).getMolecules());

        System.out.println("Synthesizing the serum...");
        for (Bond bond : serum) {
            System.out.println(
                    "Bond between " + bond.getFrom().getId() + " - " + bond.getTo().getId() + " with strength "
                            + bond.getWeight());
            total += bond.getWeight();

        }

        System.out.println("The total serum bond strength is " + total);
    }
    


    private int findIndex(List<MolecularStructure> structures, Molecule molecule) {
        for (int i = 0; i < structures.size(); i++) {
            List<Molecule> molecules = structures.get(i).getMolecules();
            for (int j = 0; j < molecules.size(); j++) {
                if (molecules.get(j).equals(molecule)) {
                    return i;
                }
            }
        }
        return -1;
    }





    
}
